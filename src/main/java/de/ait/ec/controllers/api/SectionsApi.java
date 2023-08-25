package de.ait.ec.controllers.api;

import de.ait.ec.dto.SectionDto;
import de.ait.ec.dto.SectionsDto;
import de.ait.ec.dto.NewSectionDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/sections") //sections
@Tags(value =
@Tag(name = "Sections") //Sections
)
public interface SectionsApi {

    //
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping
    ResponseEntity<SectionDto> addSection(@RequestBody @Valid NewSectionDto section);

    //
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/withoutHairsalon") //withoutHairsalon
    ResponseEntity<SectionsDto> getAllSectionsNotInHairsalon();
}
