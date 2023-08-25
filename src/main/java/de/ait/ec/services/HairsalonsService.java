package de.ait.ec.services;

import de.ait.ec.dto.*;

public interface HairsalonsService {
    HairsalonDto addHairsalon(NewHairsalonDto newHairsalon);

    HairsalonDto updateHairsalon(Long hairsalonId, UpdateHairsalonDto newHairsalon);

    ClientsDto addClientToHairsalon(Long hairsalonId, ClientForHairsalonDto client);

    ClientsDto getClientsOfHairsalon(Long hairsalonId);

    HairsalonDto getHairsalon(Long hairsalonId);

    HairsalonsDto getHairsalons();

    SectionsDto addSectionToHairsalon(Long hairsalonId, SectionForHairsalonDto section);

    SectionsDto getSectionsOfHairsalon(Long hairsalonId);
}
