package org.example.myjavaproappgame.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Field question must be not blank")
    @Size(min =3, max =15)
    private String question;

    @NotBlank (message = "Field answer must be not blank")
    @Size (min =3, max =15)
    private String answer;

    @NotBlank (message = "Field level must be not blank")
    @Size (min=2, max=2, message = "Level length must be 2 character" )
    @Pattern(regexp = "^[A-C]+[1-2]", message = "Level could be A1, A2, B1, B2, C1 or C2")
    private String level;

    @NotBlank (message = "Field topic must be not blank")
    @Size (min =3, max =15)
    private String topic;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}