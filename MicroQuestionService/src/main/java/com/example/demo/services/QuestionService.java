package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Questions;
import com.example.demo.models.QuestionsWrapper;
import com.example.demo.models.Response;
import com.example.demo.repositories.QuestionsRepository;
import com.example.demo.repositories.QuestionsRepositoryCustomImpl;

@Service
public class QuestionService 
{
	@Autowired
	private QuestionsRepository quesRepo;
	
	@Autowired
	private QuestionsRepositoryCustomImpl questionsRepositoryCustomImpl;
	
	public ResponseEntity<List<Questions>> getAllQuestionsService() 
	{
		try 
		{
			return new ResponseEntity<>(quesRepo.findAll(), HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<Questions>(), HttpStatus.BAD_REQUEST);
			
	}

	public ResponseEntity<List<Questions>> getQuestionsByCategoryService(String category) 
	{
		try 
		{
			return new ResponseEntity<>(quesRepo.findByCategory(category), HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<Questions>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestionService(Questions question) 
	{
		quesRepo.save(question);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> createQuizService(String category, int noOfQuestions) 
	{
		try 
		{
			List<Questions> quizQuestions = questionsRepositoryCustomImpl.getQuizByCategoryAndNoOfQues(category, noOfQuestions);
			
			
			
			List<Integer> idList = new ArrayList<>();
			
			for (Questions ele : quizQuestions)
			{
				idList.add(ele.getId());
			}
			return new ResponseEntity<>(idList, HttpStatus.CREATED);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
	}
	
	
	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestionsService(List<Integer> id) 
	{
		
		List<QuestionsWrapper> wrapperList = new ArrayList<>();
		try 
		{
			for (Integer e : id)
			{
				Optional<Questions> ele = quesRepo.findById(e);
				QuestionsWrapper wrapper = new QuestionsWrapper(ele.get().getId(), ele.get().getQuestion(),
						ele.get().getOption1(), ele.get().getOption2(), ele.get().getOption3(), ele.get().getOption4() );
				wrapperList.add(wrapper);
				
			}
			
			return new ResponseEntity<>(wrapperList, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
	}
	
	
	public int calculateResult( List<Response> response) {
		
		
		int score = 0;
		
		for(Response ele : response)
		{
			if(ele.getResponse().equals(quesRepo.findById(ele.getId()).get().getRightanswer()))
			{
				score++;
			}			
		}
		
		return score;
	}
	
}
