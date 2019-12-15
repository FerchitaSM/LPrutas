package com.example.lp.dao;

import com.example.lp.domain.ConnectionRoutesEntity;
import com.example.lp.domain.TypeConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeConnectionRepository extends JpaRepository<TypeConnectionEntity,Integer> {
    List<ConnectionRoutesEntity> findByCompare_and(@Param("tTransportA") int tTransportA,@Param("tTransportB") int tTransportB);
    List<ConnectionRoutesEntity> findByCompare_or(@Param("tTransportA") int tTransportA,@Param("tTransportB") int tTransportB);
}
