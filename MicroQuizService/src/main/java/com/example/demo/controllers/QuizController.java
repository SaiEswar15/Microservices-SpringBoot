package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.QuestionsWrapper;
import com.example.demo.models.QuizParams;
import com.example.demo.models.Response;
import com.example.demo.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController 
{
	@Autowired
	private QuizService quizService;

	@PostMapping("/question/create")
	public ResponseEntity<List<Integer>> createQuiz(@RequestBody QuizParams quizParams )
	{
		return quizService.createQuizService(quizParams.getCategory(), quizParams.getNoOfQuestions(), quizParams.getTitle());
		//we should RETURN id of quiz created so that we can display the quiz created with id
		//try
	}
	
	@GetMapping("/question/getQuiz/{id}")
	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@PathVariable int id)
	{
		return quizService.getQuizQuestionsService(id);
	}
	
	@PostMapping("/question/submit/{id}")
	public int submitAndGetResult(@PathVariable int id, @RequestBody List<Response> response)
	{
		return quizService.calculateResult(id,response);
		
	}
}
