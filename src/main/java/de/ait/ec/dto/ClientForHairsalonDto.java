package de.ait.ec.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientForHairsalonDto {

    @NotNull
    private Long clientId; //clientId
}
