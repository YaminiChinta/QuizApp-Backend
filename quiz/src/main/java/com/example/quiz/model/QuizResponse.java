package com.example.quiz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuizResponse {
    private Integer id;
    private String userAnswer;

    public QuizResponse(Integer id, String userAnswer) {
        this.id = id;
        this.userAnswer = userAnswer;
    }
}
