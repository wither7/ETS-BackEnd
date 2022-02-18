package com.example.helloworld.entitiy.JoinPK;/**
 * @Classname TakeExperimentPK
 * @Description TODO
 * @Date 2021/12/3 20:49
 * @Created by 86150
 */

import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-03 20:49:33
 */
@Data
@ApiModel
public class TakeExperimentPK implements Serializable {
    private static final long serialVersionUID  = 5608695828980016302L;//？

    private Integer studentId;

    private Integer experimentId;

    public TakeExperimentPK() {
    }

    public TakeExperimentPK(Integer studentId, Integer experimentId) {
        this.studentId = studentId;
        this.experimentId = experimentId;
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
