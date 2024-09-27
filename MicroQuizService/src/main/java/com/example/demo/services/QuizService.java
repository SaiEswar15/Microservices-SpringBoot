package com.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.feign.QuizInterface;
import com.example.demo.models.QuestionsWrapper;
import com.example.demo.models.Quiz;
import com.example.demo.models.Response;
//import com.example.demo.repositories.QuestionsRepositoryCustomImpl;
import com.example.demo.repositories.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepository quizRepo;
	
	
	@Autowired
	private QuizInterface quizInterface;
	

	public ResponseEntity<List<Integer>> createQuizService(String category, int noOfQuestions, String title) {
		
		//to create a quiz earlier we had a question repository from which we got the questions 
		//but now we don't have it we should interact with MicroQuestionSevice project
		//we don't need questions but the list of id's of questions
		try 
		{
			
			List<Integer> listIds = quizInterface.createQuiz(category, noOfQuestions).getBody();
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(listIds);
			quizRepo.save(quiz);
	
			return new ResponseEntity<>(listIds, HttpStatus.CREATED);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestionsService(int id) 
	{
		
		try 
		{	
			Quiz q = quizRepo.findById(id).get();
			List<Integer> quizIdList = q.getQuestions();
			
			List<QuestionsWrapper> wrapperList = quizInterface.getQuizQuestions(quizIdList).getBody();
			 
			return new ResponseEntity<>(wrapperList, HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}


	public int calculateResult(int id, List<Response> response) 
	{		
		return quizInterface.submitAndGetResult(response);
	}

}
