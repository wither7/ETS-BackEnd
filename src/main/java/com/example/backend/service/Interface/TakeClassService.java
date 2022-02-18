package com.example.backend.service.Interface;

import com.example.backend.entitiy.JoinPK.TakeClassPK;
import com.example.backend.entitiy.TakeClass;

import java.util.List;

/**
 * @Classname TakeClassService
 * @Description TODO
 * @Date 2021/11/28 17:05
 * @Created by 86150
 */

public interface TakeClassService {
    //添加学生 - 班级关系
    public void addTakeClass(TakeClass takeClass);
    //更新学生 - 班级关系
    public void updateTakeClass(TakeClass takeClass);
    //删除学生 - 班级关系
    public void deleteTakeClass(TakeClassPK takeClassPK);
    //查询所有学生 - 班级关系
    public List<TakeClass> findAllTakeClass();
    //根据班级id查询所有学生 - 班级关系（查询班级下的所有学生）
    public List<TakeClass> findAllTakeClassByClassId(Integer classId);
    //根据学生id查询所有学生 - 班级关系（查询学生的所有班级）
    public List<TakeClass> findAllTakeClassByStudentId(Integer studentId);
    //根据学生 - 班级关系主键查询关系
    public TakeClass findTakeClassById(TakeClassPK takeClassPK);
    //根据学生 - 班级关系主键判断关系是否存在
    public boolean existTakeClass(TakeClassPK takeClassPK);
}
