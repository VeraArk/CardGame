package org.example.myjavaproappgame.repository;

import org.example.myjavaproappgame.entity.Game;
import org.example.myjavaproappgame.entity.Result;
import org.example.myjavaproappgame.entity.ResultStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

}
