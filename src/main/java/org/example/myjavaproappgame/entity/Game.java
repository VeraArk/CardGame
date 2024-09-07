package org.example.myjavaproappgame.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field \"Number of cards\" must be not blank")
    @Pattern(regexp = "\\d+", message = "Field \"Numbers of cards\" can contain only digital")
    @Size(min =1, max =2)
    private Integer numbersOfCards; // Количество карточек для игры

    @Pattern(regexp = "[0-9]+")
    @Size(min =1, max =2)
    private Integer numbersOfRightAnswer = 0; // Количество правильных ответов

    private ResultStatus status; // Статус игры

    @NotBlank (message = "Field \"level\" must be not blank")
    @Pattern(regexp = "^[ABC]+[1-2]", message = "Level could be A1, A2, B1, B2, C1 or C2")
    private String level;

    @NotBlank (message = "Field \"topic\" must be not blank")
    @Pattern(regexp = "[A-Za-z]+")
    @Size (min =3, max =15)
    private String topic;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private Student student;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Card> cards;

}
