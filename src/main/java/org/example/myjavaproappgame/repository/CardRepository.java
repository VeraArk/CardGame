package org.example.myjavaproappgame.repository;

import org.example.myjavaproappgame.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByQuestion(String question);

    Optional<Card> findByTopic(String topic);

    Optional<Card> findByLevel(String level);

}
