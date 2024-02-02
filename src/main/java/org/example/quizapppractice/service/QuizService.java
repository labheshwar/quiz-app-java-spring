package org.example.quizapppractice.service;

import org.example.quizapppractice.dao.QuestionDao;
import org.example.quizapppractice.dao.QuizDao;
import org.example.quizapppractice.model.Question;
import org.example.quizapppractice.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(int numQ, String title) {
        Quiz quiz = new Quiz();

        List<Question> questions = questionDao.generateNumOfRandQues(numQ);

        quiz.setQuizTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
