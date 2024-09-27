package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {

	List<Questions> findByCategory(String Category);
	
	

}
