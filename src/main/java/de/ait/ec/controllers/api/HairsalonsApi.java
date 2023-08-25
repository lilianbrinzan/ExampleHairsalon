package de.ait.ec.controllers.api;

import de.ait.ec.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/hairsalons") //hairsalons
@Tags(value =
@Tag(name = "Hairsalons") //Hairsalons
)
public interface HairsalonsApi {

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping
    ResponseEntity<HairsalonDto> addHairsalon(@RequestBody @Valid NewHairsalonDto newHairsalon);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PutMapping("/{hairsalon-id}") // hairsalon-id
    ResponseEntity<HairsalonDto> updateHairsalon(@PathVariable("hairsalon-id") Long hairsalonId,
                                               @RequestBody @Valid UpdateHairsalonDto updateHairsalon);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/{hairsalon-id}/clients") // {hairsalon-id}/clients
    ResponseEntity<ClientsDto> addClientToHairsalon(@PathVariable("hairsalon-id") Long hairsalonId,
                                                       @RequestBody @Valid ClientForHairsalonDto client);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/{hairsalon-id}/clients") // {hairsalon-id}/clients
    ResponseEntity<ClientsDto> getClientsOfHairsalon(@PathVariable("hairsalon-id") Long hairsalonId);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/{hairsalon-id}/sections") // {hairsalon-id}/sections
    ResponseEntity<SectionsDto> addSectionToHairsalon(@PathVariable("hairsalon-id") Long hairsalonId,
                                                       @RequestBody @Valid SectionForHairsalonDto section);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/{hairsalon-id}/sections") //{hairsalon-id}/sections
    ResponseEntity<SectionsDto> getSectionsOfHairsalon(@PathVariable("hairsalon-id") Long hairsalonId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{hairsalon-id}") //{hairsalon-id}
    ResponseEntity<HairsalonDto> getHairsalon(@PathVariable("hairsalon-id") Long hairsalonId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    ResponseEntity<HairsalonsDto> getHairsalons();
}
