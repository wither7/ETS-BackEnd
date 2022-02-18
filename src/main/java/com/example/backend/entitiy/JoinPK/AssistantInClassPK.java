package com.example.backend.entitiy.JoinPK;/**
 * @Classname AssistantInClassPK
 * @Description TODO
 * @Date 2021/12/6 22:05
 * @Created by 86150
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @ program: 后端test
 * @ description: 助教 主键
 * @ author: YXJ
 * @ date: 2021-12-06 22:05:36
 */
@Data
public class AssistantInClassPK implements Serializable {
    private static final long serialVersionUID = 2050374621154398021L;//？

    private Integer assistantId;
    private Integer classId;

    public AssistantInClassPK() {
    }

    public AssistantInClassPK(Integer assistantId, Integer classId) {
        this.assistantId = assistantId;
        this.classId = classId;
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
