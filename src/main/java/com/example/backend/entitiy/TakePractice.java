package com.example.backend.entitiy;/**
 * @Classname TakePractice
 * @Description TODO
 * @Date 2021/12/20 22:24
 * @Created by 86150
 */

import com.example.backend.entitiy.JoinPK.TakePracticePK;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-20 22:24:54
 */
@ApiModel("学生-实验项目映射实体类")
@Getter
@Setter
@ToString
@Entity
@Table(name = "take_practice")//表名
@IdClass(TakePracticePK.class)
public class TakePractice {
    @Id
    @Column(name = "practice_id" , nullable = false)
    private Integer practiceId;//该属性名必须和PK类中属性名完全一致

    @Id
    @Column(name = "student_id" )
    private Integer studentId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "student_answer" )
    private Integer answer;

    @Column(name = "use_time" )
    private Integer useTime;//答题用时  以秒为单位


    @Column(name = "paper_score" )
    private Integer paperScore;//设置为整数 方便比较

    @Column(name = "group_score" )
    private Double groupScore;

    @Column(name = "take_practice_state")
    private Integer takePracticeState;

    @ManyToOne(targetEntity = User.class ,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", referencedColumnName = "uid" , insertable = false , updatable = false)
    private User student;

    @ManyToOne(targetEntity =Practice.class,  cascade = CascadeType.PERSIST)
    @JoinColumn(name = "practice_id", referencedColumnName = "practice_id", insertable = false, updatable = false)
    private Practice practice;

    public TakePractice() {
        paperScore = 0;
        groupScore = 0.0;
        takePracticeState = 0;
    }
}
