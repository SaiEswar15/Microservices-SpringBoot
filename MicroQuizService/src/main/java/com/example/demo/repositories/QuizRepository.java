package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> 
{

}
