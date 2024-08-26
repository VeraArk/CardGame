package org.example.myjavaproappgame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "student")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String level;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List <Card> cards;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Result> result;
}