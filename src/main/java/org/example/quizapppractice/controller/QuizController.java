package org.example.quizapppractice.controller;

import org.example.quizapppractice.Question;
import org.example.quizapppractice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuizController {

    @Autowired
    QuestionService questionService;

    @GetMapping("getAll")
    public List<Question> getAllQuestions () {
        return questionService.getAllQuestions();
    }

    @GetMapping("getAll/{difficulty}")
    public List<Question> getByDifficulty(@PathVariable("difficulty") String difficulty) {
        return questionService.getByDifficulty(difficulty);
    }

    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
