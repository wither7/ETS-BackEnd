package com.example.backend.dao;

import com.example.backend.entitiy.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname PracticeRepository
 * @Description TODO
 * @Date 2021/12/20 22:12
 * @Created by 86150
 */
public interface PracticeRepository extends JpaRepository<Practice ,Integer> , JpaSpecificationExecutor<Practice> {

}
