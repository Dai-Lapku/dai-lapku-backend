package ua.edu.ukma.dailapku.dailapkubackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shelters")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shelters_users",
            joinColumns = @JoinColumn(name = "shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    @ToString.Exclude
    private Set<User> managers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shelter")
    @ToString.Exclude
    private Set<Animal> animals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shelter shelter = (Shelter) o;

        if (!Objects.equals(id, shelter.id)) return false;
        if (!Objects.equals(name, shelter.name)) return false;
        return Objects.equals(address, shelter.address);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
