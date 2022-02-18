package com.example.helloworld.entitiy;/**
 * @Classname Class
 * @Description TODO
 * @Date 2021/11/27 20:03
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description: 班级
 * @ author: YXJ
 * @ date: 2021-11-27 20:03:39
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "myclass")
public class MyClass {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "course_id" )
    private Integer courseId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "state")
    private Integer state;

    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id" , referencedColumnName = "course_id" ,insertable = false,updatable = false)
    private Course course;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id" , referencedColumnName = "uid",insertable = false,updatable = false)
    private User Teacher;

    public MyClass() {
        this.state = 1;
    }

    public MyClass(Integer class_id, Integer course_id, Integer teacherId, String className, Integer state, Course course) {
        this.classId = class_id;
        this.courseId = course_id;
        this.teacherId = teacherId;
        this.className = className;
        this.state = state;
        this.course = course;
    }
}
