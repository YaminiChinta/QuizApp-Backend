package com.example.quiz.controller;

import com.example.quiz.model.QuestionWrapper;
import com.example.quiz.model.QuizResponse;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String cat, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(cat, numQ, title);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submitQuiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> quizResponsesList){
        return quizService.calculateQuiz(id, quizResponsesList);
    }
}
