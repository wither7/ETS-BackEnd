package com.example.helloworld.enums;

import io.swagger.annotations.ApiModel;

/**
 * @Classname UserRole
 * @Description 用户身份枚举类
 * @Date 2021/12/6 17:52
 * @Created by 86150
 */
@ApiModel("UserRole")
public enum UserRole {

    DEFAULT(0)//初始
    ,STUDENT(1)//学生
    ,ASSISTANT(2)//助教
    ,TEACHER(3)    //教师
    ,ADMINISTRATOR(4);
    //基础身份只有四种  责任教师在课程选择时判断

    private final Integer value;


    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
