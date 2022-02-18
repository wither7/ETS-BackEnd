package com.example.backend.dao;

import com.example.backend.entitiy.ExperimentProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname ExpProjectRepository
 * @Description TODO
 * @Date 2021/11/28 10:02
 * @Created by 86150
 */
public interface ExperimentProjectRepository extends JpaRepository<ExperimentProject , Integer>
        , JpaSpecificationExecutor<ExperimentProject> {

}
