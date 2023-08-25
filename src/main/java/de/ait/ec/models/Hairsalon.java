package de.ait.ec.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(of = {"id", "title"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hairsalon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 1000)
    private String description;

    private LocalDate startDate;

    private LocalDate finishDate;

    @OneToMany(mappedBy = "hairsalon") //hairsalon
    private List<Section> sections;

    @ManyToMany(mappedBy = "hairsalons") //hairsalons
    private Set<User> clients;
}
