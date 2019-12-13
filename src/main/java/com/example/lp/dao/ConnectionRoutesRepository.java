package com.example.lp.dao;

import com.example.lp.domain.ConnectionRoutesEntity;
import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionRoutesRepository extends JpaRepository<ConnectionRoutesEntity,Integer> {

    List<ConnectionRoutesEntity> findAllByTypeTransport(@Param("typeConnection") int typeConnection);
}
