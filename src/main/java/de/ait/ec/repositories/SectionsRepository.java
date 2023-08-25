package de.ait.ec.repositories;

import de.ait.ec.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionsRepository extends JpaRepository<Section, Long> {

    List<Section> findAllByHairsalonIsNull();
}
