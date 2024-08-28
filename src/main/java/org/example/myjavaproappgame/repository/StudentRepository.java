package org.example.myjavaproappgame.repository;

import org.example.myjavaproappgame.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String name);
    Optional<Student> findByLevel(String level);

}
