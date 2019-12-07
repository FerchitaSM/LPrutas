package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxiRepository extends JpaRepository<ExceptionEntity,Integer> {
    @Query( value  =  " SELECT zone FROM parada_taxi" , nativeQuery  =  true )
    List<String> findAllZones();


    @Query( value  =  " SELECT answer_message FROM exception where question_message =?1" , nativeQuery  =  true )
    String findAnswerMessageByQuestionMessage(String question);




}
