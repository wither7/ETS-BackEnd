package com.example.backend.dao;

import com.example.backend.entitiy.JoinPK.TakePracticePK;
import com.example.backend.entitiy.TakePractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname TakePracticeRepository
 * @Description TODO
 * @Date 2021/12/31 14:10
 * @Created by 86150
 */
public interface TakePracticeRepository extends JpaRepository<TakePractice, TakePracticePK>,
        JpaSpecificationExecutor<TakePractice> {
}
