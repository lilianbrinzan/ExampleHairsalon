package de.ait.ec.controllers;

import de.ait.ec.controllers.api.HairsalonsApi;
import de.ait.ec.dto.*;
import de.ait.ec.services.HairsalonsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class HairsalonsController implements HairsalonsApi {

    HairsalonsService hairsalonsService;

    @Override
    public ResponseEntity<HairsalonDto> addHairsalon(NewHairsalonDto newHairsalon) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hairsalonsService.addHairsalon(newHairsalon));
    }


    @Override
    public ResponseEntity<HairsalonDto> updateHairsalon(Long hairsalonId, UpdateHairsalonDto updateHairsalon) {

        return ResponseEntity
                .ok()
                .body(hairsalonsService.updateHairsalon(hairsalonId, updateHairsalon));
    }

    @Override
    public ResponseEntity<ClientsDto> addClientToHairsalon(Long hairsalonId, ClientForHairsalonDto client){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hairsalonsService.addClientToHairsalon(hairsalonId, client));
    }

    @Override
    public ResponseEntity<ClientsDto> getClientsOfHairsalon(Long hairsalonId) {

        return ResponseEntity
                .ok()
                .body(hairsalonsService.getClientsOfHairsalon(hairsalonId));
    }

    @Override
    public ResponseEntity<SectionsDto> addSectionToHairsalon(Long hairsalonId, SectionForHairsalonDto section){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hairsalonsService.addSectionToHairsalon(hairsalonId, section));
    }

    @Override
    public ResponseEntity<SectionsDto> getSectionsOfHairsalon(Long hairsalonId){
        return ResponseEntity.ok()
                .body(hairsalonsService.getSectionsOfHairsalon(hairsalonId));
    }

    @Override
    public ResponseEntity<HairsalonDto> getHairsalon(Long hairsalonId){
        return ResponseEntity
                .ok()
                .body(hairsalonsService.getHairsalon(hairsalonId));
    }

    @Override
    public ResponseEntity<HairsalonsDto> getHairsalons() {
        return ResponseEntity
                .ok()
                .body(hairsalonsService.getHairsalons());
    }
}
