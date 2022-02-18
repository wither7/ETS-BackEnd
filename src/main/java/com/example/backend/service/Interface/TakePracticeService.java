package com.example.backend.service.Interface;

import com.example.backend.entitiy.JoinPK.TakePracticePK;
import com.example.backend.entitiy.TakePractice;

import java.util.List;

/**
 * @Classname TakePracticeService
 * @Description TODO
 * @Date 2021/12/31 14:14
 * @Created by 86150
 */
public interface TakePracticeService {
    //添加学生 - 对抗练习关系
    public void addTakePractice(TakePractice takePractice);
    //更新学生 - 对抗练习关系
    public void updateTakePractice(TakePractice takePractice);
    //根据id删除学生 - 对抗练习关系
    public void deleteTakePractice(TakePracticePK takePracticePK);
    //根据id判断学生 - 对抗练习关系是否存在
    public boolean existTakePractice(TakePracticePK takePracticePK);
    //根据id查询学生 - 对抗练习关系
    public TakePractice findTakePractice(TakePracticePK takePracticePK);
    //根据学生id查询学生 - 对抗练习关系
    public List<TakePractice> findTakePracticeByStudentId(Integer studentId);
    //根据练习id查询学生 - 对抗练习关系
    public List<TakePractice> findTakePracticeByPracticeId(Integer practiceId);

}
