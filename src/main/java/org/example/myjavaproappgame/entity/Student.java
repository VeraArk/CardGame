package org.example.myjavaproappgame.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Entity
@Table(name = "student")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Student name must be not blank")
    @Pattern(regexp = "\\d+", message = "Id can contain only digital")
    private Long id;

    @NotBlank(message = "Student name must be not blank")
    @Size (min =3, max =15, message = "Student name length must be between from 3 to 15 character")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Manager name can contain only latin characters or digital")
    private String name;

    @NotBlank(message = "Email must be not blank")
    @Email
    private String email;

    @NotBlank (message = "Password must be not blank")
    @Pattern (regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*_]){8,}$", message = "Password length must be from 8 character")
    private String password;

    @NotBlank (message = "Field level name must be not blank")
    @Size (min=2, max=2,  message = "Level length must be 2 character")
    @Pattern(regexp = "^[A-C]+[1-2]", message = "Level could be A1, A2, B1, B2, C1 or C2")
    private String level;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List <Card> cards;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Game> game;
}