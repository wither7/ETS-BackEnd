package com.example.helloworld.entitiy;/**
 * @Classname ExperimentProject
 * @Description TODO
 * @Date 2021/11/27 21:15
 * @Created by 86150
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @ program: 后端test
 * @ description: 实验项目
 * @ author: YXJ
 * @ date: 2021-11-27 21:15:15
 */

@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "experiment_project")
public class ExperimentProject {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "experiment_id")
    private Integer experimentId;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "title")
    private String  title;

    @Column(name = "description")
    private String description;

    //存储报告模板的url
    @Column(name = "report_template")
    private String report_template;

    @Column(name = "release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date releaseTime;

    @Column(name = "due_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date dueTime;

    @Column(name = "state")
    private Integer state;

    @ManyToOne(targetEntity = MyClass.class)
    @JoinColumn(name = "class_id" , referencedColumnName = "class_id" , insertable = false , updatable = false)
    private MyClass myClass;

    public ExperimentProject() {
        this.experimentId = 0 ;
        this.state  =1;
    }


    public ExperimentProject(Integer experiment_id, Integer class_id, String title,
                             String description, String report_template, Date release_time,
                             Date due_time, Integer state) {
        this.experimentId = experiment_id;
        this.classId = class_id;
        this.title = title;
        this.description = description;
        this.report_template = report_template;
        this.releaseTime = release_time;
        this.dueTime = due_time;
        this.state = state;
    }
}
