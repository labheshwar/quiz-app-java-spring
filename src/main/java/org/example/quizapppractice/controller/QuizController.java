package org.example.quizapppractice.controller;

import org.example.quizapppractice.model.Question;
import org.example.quizapppractice.model.QuestionWrapper;
import org.example.quizapppractice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
     QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam int numQ, @RequestParam String title) {
        try {
            return quizService.createQuiz(numQ, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getQuestions/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable(value = "id") int id) {
        try {
            return quizService.getQuestionsById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
