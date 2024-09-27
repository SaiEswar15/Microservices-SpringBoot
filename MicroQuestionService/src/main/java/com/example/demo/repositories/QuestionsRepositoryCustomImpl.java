package com.example.demo.repositories;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Questions;

@Repository
public class QuestionsRepositoryCustomImpl implements QuestionsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<Questions> getQuizByCategoryAndNoOfQues(String category, int noOfQuestions) 
    {
        String sql = "SELECT * FROM questions WHERE category = :category ORDER BY rand() LIMIT " + noOfQuestions;
        Query query = entityManager.createNativeQuery(sql, Questions.class);
        query.setParameter("category", category);
        return query.getResultList();
    }
}
