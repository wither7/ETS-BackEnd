package com.example.helloworld.entitiy.JoinPK;/**
 * @Classname practiceGroupPK
 * @Description TODO
 * @Date 2021/12/20 22:14
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-20 22:14:45
 */
@Data
@ApiModel
public class TakePracticePK implements Serializable {
    private static final long serialVersionUID = -853982039773387158L; //？
    //    @Column(name = "student_id")
    private Integer studentId;

    //    @Column(name = "class_id")
    private Integer practiceId;

    public TakePracticePK() {
    }

    public TakePracticePK(Integer studentId, Integer practiceId) {
        this.studentId = studentId;
        this.practiceId = practiceId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
