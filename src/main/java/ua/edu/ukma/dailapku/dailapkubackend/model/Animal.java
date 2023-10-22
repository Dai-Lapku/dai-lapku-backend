package ua.edu.ukma.dailapku.dailapkubackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "animals")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date birthDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelter;

    // TODO: add animal photos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (!Objects.equals(id, animal.id)) return false;
        if (!Objects.equals(name, animal.name)) return false;
        if (!Objects.equals(birthDate, animal.birthDate)) return false;
        return Objects.equals(species, animal.species);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        return result;
    }
}
