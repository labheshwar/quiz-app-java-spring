package org.example.quizapppractice.service;

import org.example.quizapppractice.dao.QuestionDao;
import org.example.quizapppractice.dao.QuizDao;
import org.example.quizapppractice.model.Question;
import org.example.quizapppractice.model.QuestionWrapper;
import org.example.quizapppractice.model.Quiz;
import org.example.quizapppractice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question question : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer>  calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        int right = 0;
        for (int i = 0; i<responses.size(); i++) {
            if (responses.get(i).getResponse().equals(questionsFromDb.get(i).getRightAnswer())) {
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
