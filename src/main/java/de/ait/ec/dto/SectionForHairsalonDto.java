package de.ait.ec.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SectionForHairsalonDto {

    @NotNull
    private Long sectionId; //sectionId
}
