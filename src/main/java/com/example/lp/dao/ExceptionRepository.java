package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<ExceptionEntity,Integer> {

}
