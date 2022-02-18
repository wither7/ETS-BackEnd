package com.example.helloworld.entitiy;/**
 * @Classname StudyFeedback
 * @Description TODO
 * @Date 2021/12/30 15:24
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @ program: 后端test
 * @ description: 学生学习反馈信息
 * @ author: YXJ
 * @ date: 2021-12-30 15:24:27
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "study_feedback")//表名
public class StudyFeedback {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "student_id",updatable = false,insertable = false)
    private Integer studentId;

    @Column(name = "course_id",updatable = false,insertable = false)
    private Integer courseId;

    @Column(name = "content")
    private String content;

    @Column(name = "release_time")
    private Date releaseTime;

    @Column(name = "state")
    private Integer state;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id" , referencedColumnName = "uid")
    private User Student;

    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id" , referencedColumnName = "course_id")
    private Course Course;
}

