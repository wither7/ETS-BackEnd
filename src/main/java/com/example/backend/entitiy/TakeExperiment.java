package com.example.backend.entitiy;/**
 * @Classname TakeExperiment
 * @Description TODO
 * @Date 2021/12/3 20:48
 * @Created by 86150
 */

import com.example.backend.entitiy.JoinPK.TakeExperimentPK;
import com.example.backend.enums.TakeExpState;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @ program: 后端test
 * @ description: 学生-实验项目映射类
 * @ author: YXJ
 * @ date: 2021-12-03 20:48:13
 */
@ApiModel("学生-实验项目映射实体类")
@Getter
@Setter
@ToString
@Entity
@Table(name = "take_experiment")//表名
@IdClass(TakeExperimentPK.class)
public class TakeExperiment {
    @Id
    @Column(name = "experiment_id" , nullable = false)
    private Integer experimentId;//该属性名必须和PK类中属性名完全一致

    @Id
    @Column(name = "student_id" , nullable = false)
    private Integer studentId;

    @Column(name = "submit_time")
    private Date submitTime;

    //存放提交报告的url
    @Column(name = "report_url")
    private String reportURL;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "score")
    private Double score;

    @Column(name = "state")
    private Integer state;

    @ManyToOne(targetEntity = User.class ,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", referencedColumnName = "uid" , insertable = false , updatable = false)
    private User student;

    @ManyToOne(targetEntity =ExperimentProject.class,  cascade = CascadeType.PERSIST)
    @JoinColumn(name = "experiment_id", referencedColumnName = "experiment_id", insertable = false, updatable = false)
    private ExperimentProject experimentProject;


    public TakeExperiment() {
        score  = 0.0;
        state = TakeExpState.DEFAULT.getValue();
    }

    public TakeExperiment(Integer experimentId, Integer studentId, Date submitTime, String reportURL, String reportName,
                          Double score, Integer state, User student, ExperimentProject experimentProject) {
        this.experimentId = experimentId;
        this.studentId = studentId;
        this.submitTime = submitTime;
        this.reportURL = reportURL;
        this.reportName = reportName;
        this.score = score;
        this.state = state;
        this.student = student;
        this.experimentProject = experimentProject;
    }
}
