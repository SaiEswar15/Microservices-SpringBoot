package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Questions;

public interface QuestionsRepositoryCustom 
{
    List<Questions> getQuizByCategoryAndNoOfQues(String category, int noOfQuestions);
}
