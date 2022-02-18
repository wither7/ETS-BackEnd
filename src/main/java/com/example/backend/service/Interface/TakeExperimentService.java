package com.example.backend.service.Interface;

import com.example.backend.entitiy.JoinPK.TakeExperimentPK;
import com.example.backend.entitiy.TakeExperiment;

import java.util.List;

/**
 * @Classname TakeExperimentService
 * @Description TODO
 * @Date 2021/12/6 9:21
 * @Created by 86150
 */
public interface TakeExperimentService {
    //添加学生 - 实验关系
    public void addTakeExperiment(TakeExperiment takeExperiment);
    //更新学生 - 实验关系
    public void updateTakeExperiment(TakeExperiment takeExperiment);
    //删除学生 - 实验关系
    public void deleteTakeExperiment(TakeExperimentPK takeExperimentPK);
    //查询所有学生 - 实验关系
    public List<TakeExperiment> findAllTakeExperiment();
    //根据实验id查询所有学生 - 实验关系 
    public List<TakeExperiment> findAllTakeExperimentByExpId(Integer expId);
    //根据学生id查询所有学生 - 实验关系
    public List<TakeExperiment> findAllTakeExperimentByStudentId(Integer studentId);
    //根据实验id查询所有学生 - 实验关系
    public TakeExperiment findTakeExperimentById(TakeExperimentPK takeExperimentPK);
    //根据学生 - 实验关系主键判断学生 - 实验关系是否存在
    public boolean existTakeExperiment(TakeExperimentPK takeExperimentPK);
}
