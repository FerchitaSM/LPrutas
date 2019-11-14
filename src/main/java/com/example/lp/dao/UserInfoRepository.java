package com.example.lp.dao;
import com.example.lp.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Integer> {

    List<UserInfoEntity> findAllByUserInfoStatus(int user_info_status);
}
