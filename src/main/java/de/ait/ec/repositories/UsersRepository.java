package de.ait.ec.repositories;

import de.ait.ec.models.Hairsalon;
import de.ait.ec.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    @Query("select user from User user where user.role = 'CLIENT' and :hairsalon not member of user.hairsalons")
        List<User> findClientsNotInHairsalon(@Param("hairsalon") Hairsalon hairsalon);

    boolean existsByRole(User.Role role);
}
