package de.ait.ec.services.impl;

import de.ait.ec.dto.*;
import de.ait.ec.handler.RestException;
import de.ait.ec.models.Hairsalon;
import de.ait.ec.models.Section;
import de.ait.ec.models.User;
import de.ait.ec.repositories.HairsalonsRepository;
import de.ait.ec.repositories.SectionsRepository;
import de.ait.ec.repositories.UsersRepository;
import de.ait.ec.services.HairsalonsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

import static de.ait.ec.dto.HairsalonDto.from;
import static de.ait.ec.dto.SectionDto.from;
import static de.ait.ec.dto.UserDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Service
public class HairsalonsServiceImpl implements HairsalonsService {

    HairsalonsRepository hairsalonsRepository;

    UsersRepository usersRepository;

    UsersServiceImpl usersService;

    SectionsRepository sectionsRepository; //sectionsRepository;

    @Transactional
    @Override
    public HairsalonDto addHairsalon(NewHairsalonDto newHairsalon) {
        Hairsalon hairsalon = Hairsalon.builder() //hairsalon
                .startDate(LocalDate.parse(newHairsalon.getStartDate())) //newHairsalon
                .finishDate(LocalDate.parse(newHairsalon.getFinishDate())) //newHairsalon
                .title(newHairsalon.getTitle()) //newHairsalon
                .description(newHairsalon.getDescription()) //newHairsalon
                .build();

        hairsalonsRepository.save(hairsalon); //hairsalonsRepository

        return from(hairsalon); //hairsalon
    }

    @Transactional
    @Override
    public HairsalonDto updateHairsalon(Long hairsalonId, UpdateHairsalonDto updateHairsalon) {

        Hairsalon hairsalon = getHairsalonOrThrow(hairsalonId);

        hairsalon.setDescription(updateHairsalon.getDescription());

        if (updateHairsalon.getStartDate() != null) { //Hairsalon
            hairsalon.setStartDate(LocalDate.parse(updateHairsalon.getStartDate()));
        }

        if (updateHairsalon.getFinishDate() != null) { //Hairsalon

            hairsalon.setFinishDate(LocalDate.parse(updateHairsalon.getFinishDate()));
        }

        hairsalonsRepository.save(hairsalon);

        return from(hairsalon); //hairsalon
    }

    @Override
    public ClientsDto addClientToHairsalon(Long hairsalonId, ClientForHairsalonDto clientId) {
        Hairsalon hairsalon = getHairsalonOrThrow(hairsalonId);

        User client = usersService.getUserOrThrow(clientId.getClientId());

        client.getHairsalons().add(hairsalon);

        usersRepository.save(client); //client

       return getClients(hairsalon);
    }


    private static ClientsDto getClients(Hairsalon hairsalon) {
        return ClientsDto.builder() //ClientsDto
                        .clients(from(hairsalon.getClients().stream()
                        .sorted(Comparator.comparing(User::getId))
                        .collect(Collectors.toList())))
                        .build();
    }

    @Override
    public ClientsDto getClientsOfHairsalon(Long hairsalonId) {
        Hairsalon hairsalon = getHairsalonOrThrow(hairsalonId);

        return getClients(hairsalon);

    }

    @Override
    public HairsalonDto getHairsalon(Long hairsalonId) {

        return from(getHairsalonOrThrow(hairsalonId));
    }

    @Override
    public HairsalonsDto getHairsalons() {
        return HairsalonsDto.builder() //HairsalonsDto
                .hairsalons(from(hairsalonsRepository.findAll()))
                .build();
    }

    @Transactional
    @Override
    public SectionsDto addSectionToHairsalon(Long hairsalonId, SectionForHairsalonDto section) {

        Section sectionForHairsalon = sectionsRepository.findById(
                section.getSectionId()).orElseThrow(() ->
                new RestException(HttpStatus.NOT_FOUND, "Section with id <" + section.getSectionId() + "> not found"));

        Hairsalon hairsalon = getHairsalonOrThrow(hairsalonId);

        sectionForHairsalon.setHairsalon(hairsalon);

        sectionsRepository.save(sectionForHairsalon);

        hairsalon.getSections().add(sectionForHairsalon);

        return SectionsDto.builder() //SectionsDto
                .sections(from(hairsalon.getSections()))
                .build();
    }

    @Override
    public SectionsDto getSectionsOfHairsalon(Long hairsalonId) {
        Hairsalon hairsalon = getHairsalonOrThrow(hairsalonId);

        return SectionsDto.builder() //return SectionsDto.builder()
                .sections(from(hairsalon.getSections()))
                .build();
    }

    Hairsalon getHairsalonOrThrow(Long hairsalonId) {
            return hairsalonsRepository.findById(hairsalonId).orElseThrow(
                    () -> new RestException(HttpStatus.NOT_FOUND, "Hairsalon with id <" + hairsalonId + "> not found"));
    }


}
