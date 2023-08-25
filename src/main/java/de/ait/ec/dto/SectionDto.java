package de.ait.ec.dto;

import de.ait.ec.models.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDto {

    private Long id;

    private String name;

    private String dayOfWeek;

    private String startTime;

    private String finishTime;

    private Long courseId;

    public static SectionDto from(Section section) { //section
        SectionDto result = SectionDto.builder()
                .id(section.getId()) //section
                .name(section.getName()) //section
                .dayOfWeek(section.getDayOfWeek().toString()) //section
                .startTime(section.getStartTime().toString()) //section
                .finishTime(section.getFinishTime().toString()) //section
                .build();

        if (section.getHairsalon() != null) { //section.getHairsalon()
            result.setHairsalonId(section.getHairsalon().getId());
        }

        return result;
    }

    private void setHairsalonId(Long id) {
    }

    public static List<SectionDto> from(Collection<Section> sections) { //sections

        return sections.stream() //sections
                .map(SectionDto::from)
                .collect(Collectors.toList());
    }
}
