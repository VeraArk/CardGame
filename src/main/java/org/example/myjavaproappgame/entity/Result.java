package org.example.myjavaproappgame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer numbersOfCards;
    private Integer numbersOfRightAnswer;
    private ResultStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
