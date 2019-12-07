package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExceptionRepository extends JpaRepository<ExceptionEntity,Integer> {
    @Query( value  =  " SELECT question_message FROM exception" , nativeQuery  =  true )
    List<String> findAllQuestionMessage();


    @Query( value  =  " SELECT answer_message FROM exception where questxion_message =?1" , nativeQuery  =  true )
    String findAnswerMessageByQuestionMessage(String question);




}
