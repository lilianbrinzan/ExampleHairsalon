package de.ait.ec.services.impl;

import de.ait.ec.dto.SectionDto;
import de.ait.ec.dto.SectionsDto;
import de.ait.ec.dto.NewSectionDto;
import de.ait.ec.models.Section;
import de.ait.ec.repositories.SectionsRepository;
import de.ait.ec.services.SectionsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static de.ait.ec.dto.SectionDto.from;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class SectionsServiceImpl implements SectionsService { //Sections

    SectionsRepository sectionsRepository; //sectionsRepository;

    @Transactional
    @Override
    public SectionDto addSection(NewSectionDto section) {
        Section newSection = Section.builder() //newSection
                .name(section.getName()) //section
                .startTime(LocalTime.parse(section.getStartTime())) //section
                .finishTime(LocalTime.parse(section.getFinishTime())) //section
                .dayOfWeek(DayOfWeek.valueOf(section.getDayOfWeek())) //section
                .build();

       sectionsRepository.save(newSection);

        return from(newSection); //newSection
    }

    @Override
    public SectionsDto getAllSectionsNotInHairsalon() {
        List<Section> sections = sectionsRepository.findAllByHairsalonIsNull();

        return SectionsDto.builder() //Sections
                .sections(from(sections))
                .build();
    }
}
