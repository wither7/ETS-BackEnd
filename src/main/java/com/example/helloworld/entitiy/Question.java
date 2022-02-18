package com.example.helloworld.entitiy;/**
 * @Classname Question
 * @Description TODO
 * @Date 2021/12/20 21:50
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-20 21:50:45
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "question")//表名
public class Question {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "practice_id" , updatable = false ,insertable = false)
    private Integer practiceId;

    @Column(name = "question_description")
    private String Description;

    @Column(name = "question_answer")
    private Integer Answer;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

//    @ManyToOne(targetEntity = Practice.class)
//    @JoinColumn(name = "practice_id" , referencedColumnName = "practice_id")
//    private Practice belongPractice;

}
