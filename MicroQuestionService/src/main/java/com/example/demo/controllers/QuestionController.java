package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Questions;
import com.example.demo.models.QuestionsWrapper;
import com.example.demo.models.Response;
import com.example.demo.services.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController 
{
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Questions>> getAllQuestions()
	{
		return questionService.getAllQuestionsService();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category)
	{
		return questionService.getQuestionsByCategoryService(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion( @RequestBody Questions question)
	{
		return questionService.addQuestionService(question);
	}
	
	
	//implement the method to update the question or any field in questions table database
	//try putMapping
	
	
	//implement the method to delete the question or any field in questions table database
	//try DeleteMapping
	
	
	//find a way to generate quiz
	//earlier we used quiz table which created a quiz and it took questions from question database
	//now question need the quiz (since it is in another MicroService database)
	
	@PostMapping("/create")
	public ResponseEntity<List<Integer>> createQuiz(@RequestParam String category, @RequestParam int noOfQuestions )
	{
		return questionService.createQuizService(category, noOfQuestions);
		//we should RETURN id of quiz created so that we can display the quiz created with id
		//try
	}
	
	@PostMapping("/getQuiz")
	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@RequestBody List<Integer> id)
	{
		return questionService.getQuizQuestionsService(id);
	} 
	
	@PostMapping("/submit")
	public int submitAndGetResult( @RequestBody List<Response> response)
	{
		return questionService.calculateResult(response);
		
	}
	
	
}
