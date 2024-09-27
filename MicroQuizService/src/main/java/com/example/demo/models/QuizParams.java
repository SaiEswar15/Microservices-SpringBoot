package com.example.demo.models;


public class QuizParams 
{
	private String category;
	private int noOfQuestions;
	private String title;
	
	public QuizParams() {
		
	}
	
	public QuizParams(String category, int noOfQuestions, String title) {
		super();
		this.category = category;
		this.noOfQuestions = noOfQuestions;
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNoOfQuestions() {
		return noOfQuestions;
	}
	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
