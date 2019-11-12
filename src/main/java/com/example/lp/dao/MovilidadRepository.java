package com.example.lp.dao;

import com.example.lp.domain.MovilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovilidadRepository extends JpaRepository<MovilidadEntity,Integer> {

    List<MovilidadEntity> findAllByStatus(int status);

}
