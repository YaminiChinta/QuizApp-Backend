package com.example.quiz.service;

import com.example.quiz.dao.QuestionDao;
import com.example.quiz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Added the Question Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getByCategory(String cat) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(cat), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addAllQuestions(List<Question> questionList) {
        for(Question question:questionList){
            questionDao.save(question);
        }
        return new ResponseEntity<>("Added All Questions", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("Deleted the Question", HttpStatus.OK);
    }

    public ResponseEntity<Question> updateQuestion(Integer id, Question question) {
        try{
            Question question1=questionDao.findById(id).orElseThrow();
            question1.setQuestionTitle(question.getQuestionTitle());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            question1.setRightAnswer(question.getRightAnswer());
            question1.setCategory(question.getCategory());
            question1.setDifficultyLevel(question.getDifficultyLevel());
            questionDao.save(question1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
