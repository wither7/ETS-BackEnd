package com.example.backend.entitiy;/**
 * @Classname Course
 * @Description TODO
 * @Date 2021/11/27 16:55
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description: 课程实体类
 * @ author: YXJ
 * @ date: 2021-11-27 16:55:17
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "course")
public class Course {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_name")
    private  String courseName;

    @Column(name = "responsible_teacher_id" , insertable = false,updatable = false)
    private Integer resTeacherId;

    @Column(name = "attendance_weight")
    private double attendanceWeight;

    @Column(name = "experiment_weight")
    private double experimentWeight;

    @Column(name = "practice_weight")
    private double practiceWeight;

    @Column(name = "state")
    private Integer state = 1;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "responsible_teacher_id" , referencedColumnName = "uid")
    private User responsible_teacher;


    public Course() {
//        this.courseId = 0;
        this.courseName = "default course name";
        this.attendanceWeight = 0;
        this.experimentWeight = 0;
        this.practiceWeight = 0;
        this.state = 1;
    }

    public Course(Integer course_id, String name, Integer resTeacherId, double attendance_weight,
                  double experiment_weight, double practice_weight, Integer state) {
        this.courseId = course_id;
        this.courseName = name;
        this.resTeacherId = resTeacherId;
        this.attendanceWeight = attendance_weight;
        this.experimentWeight = experiment_weight;
        this.practiceWeight = practice_weight;
        this.state = state;
    }
}
