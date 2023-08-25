package de.ait.ec.services.impl;

import de.ait.ec.dto.UserDto;
import de.ait.ec.dto.UsersDto;
import de.ait.ec.handler.RestException;
import de.ait.ec.models.Hairsalon;
import de.ait.ec.models.User;
import de.ait.ec.repositories.HairsalonsRepository;
import de.ait.ec.repositories.UsersRepository;
import de.ait.ec.services.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static de.ait.ec.dto.UserDto.from;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    HairsalonsRepository hairsalonsRepository;

    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

    @Override
    public UsersDto getAllClientsNotInHairsalon(Long hairsalonId) {

        Hairsalon hairsalon = hairsalonsRepository.findById(hairsalonId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Hairsalon with id <" + hairsalonId + "> not found"));

        return UsersDto.builder()
                .clients(from(usersRepository.findClientsNotInHairsalon(hairsalon)))
                .build();
    }

    @Transactional
    @Override
    public UserDto deleteUser(Long userId) {
        User user = getUserOrThrow(userId);

        user.setState(User.State.DELETED);

        if (user.getRole().equals(User.Role.CLIENT)) { //CLIENT
            user.getHairsalons().clear(); //Hairsalons
        }

        usersRepository.save(user);

        logoutIfNecessary(userId);

        return from(user);
    }

    private void logoutIfNecessary(Long userIdForLogout) {
        // объект аутентификации текущего пользователя
        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication());
        // нашли текущего пользователя по email
        User currentUser = usersRepository.findByEmail(token.getName()).orElseThrow();
        // если мы удаляем себя
        if (currentUser.getId().equals(userIdForLogout)) {
            // завершаем свою сессию
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + userId + "> not found"));
    }
}
