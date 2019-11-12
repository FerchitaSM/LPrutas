package com.example.lp.dao;

import com.example.lp.domain.TipoMovilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoMovilidadRepository extends JpaRepository<TipoMovilidadEntity,Integer> {

    List<TipoMovilidadEntity> findAllByStatus(int status);

}