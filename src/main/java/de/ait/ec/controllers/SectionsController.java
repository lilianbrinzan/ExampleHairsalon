package de.ait.ec.controllers;

import de.ait.ec.controllers.api.SectionsApi;
import de.ait.ec.dto.SectionDto;
import de.ait.ec.dto.SectionsDto;
import de.ait.ec.dto.NewSectionDto;
import de.ait.ec.services.SectionsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
public class SectionsController implements SectionsApi {

    SectionsService sectionsService;

    @Override
    public ResponseEntity<SectionDto> addSection(NewSectionDto section) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sectionsService.addSection(section));
    }

    @Override
    public ResponseEntity<SectionsDto> getAllSectionsNotInHairsalon() {
        return ResponseEntity
                .ok(sectionsService.getAllSectionsNotInHairsalon());

    }
}
