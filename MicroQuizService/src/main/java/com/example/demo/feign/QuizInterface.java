package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.QuestionsWrapper;
import com.example.demo.models.Response;

@FeignClient("MICROQUESTIONSERVICE")
public interface QuizInterface 
{
	@PostMapping("/question/create")
	public ResponseEntity<List<Integer>> createQuiz(@RequestParam String category, @RequestParam int noOfQuestions );
	
	@PostMapping("/question/getQuiz")
	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@RequestBody List<Integer> id);
	
	@PostMapping("/question/submit")
	public int submitAndGetResult( @RequestBody List<Response> response);
	
}
