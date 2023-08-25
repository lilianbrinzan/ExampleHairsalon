package de.ait.ec.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(of = {"id", "email"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    public enum Role {
        ADMIN,
        CLIENT, //CLIENT
        MANAGER
    }

    public enum State {
        CONFIRMED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    @ManyToMany
    @JoinTable(name = "client_hairsalon", // "client_hairsalon"
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"), // "client_id"
            inverseJoinColumns = @JoinColumn(name = "hairsalon_id", referencedColumnName = "id"), // "hairsalon_id"
            uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "hairsalon_id"})) // "client_id" "hairsalon_id"
    private Set<Hairsalon> hairsalons;
}
