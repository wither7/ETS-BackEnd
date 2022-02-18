package com.example.helloworld.service.Interface;

import com.example.helloworld.dto.StudentGrade;

import java.util.List;

/**
 * @Classname GradeService
 * @Description TODO
 * @Date 2021/12/27 22:24
 * @Created by 86150
 */
//成绩管理接口
public interface GradeService {
    //查找学生的实验总成绩
    public Double getExpScore(Integer classId , Integer studentId);
    //查找学生的对抗练习总成绩
    public Double getPracticeScore(Integer classId , Integer studentId);
    //查找学生的考勤总成绩
    public Double getAttendanceScore(Integer classId , Integer studentId);
    //查找学生的课程总成绩
    public Double getSumScore(Integer classId , Integer studentId);
    //更新学生的总成绩
    public void updateSumGrade(Integer classId , Integer studentId);
    //获得学生四项成绩
//    public StudentGrade getStudentGrade(Integer classId , Integer studentId);
//    //获得班级下所有学生四项成绩
//    public List<StudentGrade> getAllGradeInClass(Integer classId );
}
