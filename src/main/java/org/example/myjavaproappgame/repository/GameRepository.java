package org.example.myjavaproappgame.repository;

import org.example.myjavaproappgame.entity.Game;
import org.example.myjavaproappgame.entity.ResultStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

   // List<Game> findByResult (ResultStatus status);
}
