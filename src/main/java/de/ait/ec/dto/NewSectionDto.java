package de.ait.ec.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewSectionDto {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    private String dayOfWeek;

    @NotNull
    @NotBlank
    @NotEmpty
    private String startTime;

    @NotNull
    @NotBlank
    @NotEmpty
    private String finishTime;
}
