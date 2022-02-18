package com.example.helloworld.dto;/**
 * @Classname StudentGrade
 * @Description TODO
 * @Date 2021/12/28 0:13
 * @Created by 86150
 */

import com.example.helloworld.entitiy.MyClass;
import com.example.helloworld.entitiy.User;
import lombok.Data;

/**
 * @ program: 后端test
 * @ description: 学生成绩封装类
 * @ author: YXJ
 * @ date: 2021-12-28 00:13:05
 */
@Data
public class StudentGrade {
    private Integer classId;

    private Integer studentId;

    private User student;

    private MyClass myClass;

    private Double attendanceScore;

    private Double experimentScore;

    private Double practiceScore;

    private Double totalScore;

    public StudentGrade() {
    }

    public StudentGrade(Integer classId, Integer studentId,
                        User student, MyClass myClass, Double attendanceScore, Double experimentScore,
                        Double practiceScore, Double totalScore) {
        this.classId = classId;
        this.studentId = studentId;
        this.student = student;
        this.myClass = myClass;
        this.attendanceScore = attendanceScore;
        this.experimentScore = experimentScore;
        this.practiceScore = practiceScore;
        this.totalScore = totalScore;
    }
}
