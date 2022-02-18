package com.example.helloworld.service.Interface;

import com.example.helloworld.entitiy.ExperimentProject;
import com.example.helloworld.entitiy.JoinPK.TakeClassPK;
import com.example.helloworld.entitiy.TakeClass;

import java.util.List;

/**
 * @Classname ExperimentProjectService
 * @Description TODO
 * @Date 2021/12/3 18:06
 * @Created by 86150
 */
public interface ExperimentProjectService {
    //添加实验项目
    public void addExperimentProject(ExperimentProject experimentProject);
    //更新实验项目信息
    public void updateExperimentProject(ExperimentProject experimentProject);
    //删除实验项目
    public void deleteExperimentProject(Integer expId);
    //查询所有实验项目
    public List<ExperimentProject> findAllExperimentProject();
    //查找班级下所有实验项目
    public List<ExperimentProject> findAllExperimentProjectByClassId(Integer classId);
    //根据实验id查询实验项目
    public ExperimentProject findExperimentProjectById(Integer expId);
    //根据实验id判读实验项目是否存在
    public boolean existExperimentProject(Integer expId);
}
