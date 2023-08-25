package de.ait.ec.repositories;

import de.ait.ec.models.Hairsalon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HairsalonsRepository extends JpaRepository<Hairsalon, Long> {
}
