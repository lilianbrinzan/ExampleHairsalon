package de.ait.ec.dto;


import de.ait.ec.models.Hairsalon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HairsalonDto {

    private Long id;

    private String title;

    private String description;

    private String startDate;

    private String finishDate;

    public static HairsalonDto from(Hairsalon hairsalon) { //hairsalon
        HairsalonDto result =  builder()
                .id(hairsalon.getId()) //hairsalon
                .title(hairsalon.getTitle()) //hairsalon
                .description(hairsalon.getDescription()) //hairsalon
                .build();

        if (hairsalon.getStartDate() != null) { //hairsalon
            result.setStartDate(hairsalon.getStartDate().toString()); //hairsalon
        }

        if (hairsalon.getFinishDate() != null) { //hairsalon
            result.setFinishDate(hairsalon.getFinishDate().toString()); //hairsalon
        }

        return result;
    }

    public static List<HairsalonDto> from(List<Hairsalon> hairsalons) { //hairsalons
        return hairsalons.stream() //hairsalons
                .map(HairsalonDto::from)
                .collect(Collectors.toList());
    }

}
