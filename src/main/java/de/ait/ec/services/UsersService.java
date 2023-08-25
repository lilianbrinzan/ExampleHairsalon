package de.ait.ec.services;

import de.ait.ec.dto.UserDto;
import de.ait.ec.dto.UsersDto;


public interface UsersService {
    UserDto getUser(Long userId);

    UsersDto getAllClientsNotInHairsalon(Long hairsalonId);

    UserDto deleteUser(Long userId);
}
