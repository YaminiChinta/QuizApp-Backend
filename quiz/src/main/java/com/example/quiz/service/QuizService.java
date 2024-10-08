package com.example.quiz.service;

import com.example.quiz.dao.QuestionDao;
import com.example.quiz.dao.QuizDao;
import com.example.quiz.model.Question;
import com.example.quiz.model.QuestionWrapper;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String cat, int numQ, String title) {

        List<Question> questionList=questionDao.findRandomQuestionsByCat(cat, numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<>("Saved the quiz", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz=quizDao.findById(id).orElseThrow();
        List<Question> questions=quiz.getQuestionList();
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for(Question q: questions){
            QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(qw);
        }

        return  new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateQuiz(Integer id, List<QuizResponse> quizResponsesList) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questionList=quiz.getQuestionList();
        int count=0;
        int i=0;
        for(QuizResponse quizResponse:quizResponsesList){
            if(quizResponse.getUserAnswer().equals(questionList.get(i).getRightAnswer())){
                count++;
            }
            i++;
        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
