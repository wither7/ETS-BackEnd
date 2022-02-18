package com.example.backend.entitiy;/**
 * @Classname Practice
 * @Description TODO
 * @Date 2021/12/20 22:03
 * @Created by 86150
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 对抗练习
 * @ author: YXJ
 * @ date: 2021-12-20 22:03:13
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "practice")//表名
public class Practice {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "practice_id")
    private Integer practiceId;

    @Column(name = "class_id" , updatable = false , insertable = false)
    private Integer classId;

    @Column(name = "title")
    private String title;

    @Column(name = "release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date releaseTime;

    @Column(name = "due_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date dueTime;

    @ManyToOne(targetEntity = MyClass.class)
    @JoinColumn(name = "class_id" , referencedColumnName = "class_id")
    private MyClass belongClass;

    @OneToMany(targetEntity = Question.class)
    @JoinColumn(name = "practice_id" , referencedColumnName = "practice_id")
    private List<Question> questions;




}
