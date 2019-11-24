package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExceptionRepository extends JpaRepository<ExceptionEntity,Integer> {
    @Query( value  =  " SELECT question_message FROM exception" , nativeQuery  =  true )
    List<String> findAllQuestionMessage();


}
