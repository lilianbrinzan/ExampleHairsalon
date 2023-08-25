package de.ait.ec.services;

import de.ait.ec.dto.SectionDto;
import de.ait.ec.dto.SectionsDto;
import de.ait.ec.dto.NewSectionDto;

public interface SectionsService {

    SectionDto addSection(NewSectionDto section);

    SectionsDto getAllSectionsNotInHairsalon();
}
