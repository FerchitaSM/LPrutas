package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRoutesRepository extends JpaRepository<ExceptionEntity,Integer> {

}
