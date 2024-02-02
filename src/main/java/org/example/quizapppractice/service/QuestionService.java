package org.example.quizapppractice.service;

import org.example.quizapppractice.dao.QuestionDao;
import org.example.quizapppractice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getByDifficulty(String difficulty) {
        return questionDao.findByDifficultyLevel(difficulty);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Success";
    }
}
