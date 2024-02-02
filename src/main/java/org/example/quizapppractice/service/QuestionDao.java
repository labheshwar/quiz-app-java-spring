package org.example.quizapppractice.service;

import org.example.quizapppractice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByDifficultyLevel(String difficulty);
}
