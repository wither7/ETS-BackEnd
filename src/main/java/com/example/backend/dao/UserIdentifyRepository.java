package com.example.backend.dao;

import com.example.backend.entitiy.UserIdentify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname UserIdentifyRepository
 * @Description TODO
 * @Date 2021/12/8 21:12
 * @Created by 86150
 */
public interface UserIdentifyRepository extends JpaRepository<UserIdentify , String> ,
                                                JpaSpecificationExecutor<UserIdentify> {
}
