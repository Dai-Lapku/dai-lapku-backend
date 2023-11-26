package ua.edu.ukma.dailapku.dailapkubackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_likes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal likedAnimal;
}

