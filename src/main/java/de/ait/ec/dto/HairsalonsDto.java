package de.ait.ec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HairsalonsDto {

    private List<HairsalonDto> hairsalons; //hairsalons
}
