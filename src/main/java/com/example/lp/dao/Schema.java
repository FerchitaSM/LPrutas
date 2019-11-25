package com.example.lp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Schema {}/*extends JpaRepository {
    @Query( value  =  " SELECT table_name FROM information_schema.columns WHERE (table_schema='lpbus_bot' and ordinal_position=1 ) " , nativeQuery  =  true )
    List<String> findTableName();

    @Query( value  =  " show fields from ?1 ) " , nativeQuery  =  true )
    List<String> findColumnsTableName(String tanleName);

}
*/


