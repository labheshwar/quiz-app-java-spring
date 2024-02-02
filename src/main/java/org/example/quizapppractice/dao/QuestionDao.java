package org.example.quizapppractice.dao;

import org.example.quizapppractice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByDifficultyLevel(String difficulty);

    @Query(value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> generateNumOfRandQues(int numQ);
}
