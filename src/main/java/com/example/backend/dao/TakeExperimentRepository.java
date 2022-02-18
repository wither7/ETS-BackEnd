package com.example.backend.dao;

import com.example.backend.entitiy.JoinPK.TakeExperimentPK;
import com.example.backend.entitiy.TakeExperiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname TakeExperimentRepository
 * @Description TODO
 * @Date 2021/12/6 18:55
 * @Created by 86150
 */
public interface TakeExperimentRepository extends JpaRepository<TakeExperiment, TakeExperimentPK>,
                                                    JpaSpecificationExecutor<TakeExperiment>
{

}
