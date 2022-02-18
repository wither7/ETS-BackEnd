package com.example.helloworld.enums;

import io.swagger.annotations.ApiModel;

/**
 * @Classname UserState
 * @Description TODO
 * @Date 2021/11/27 22:26
 * @Created by 86150
 */


public enum UserState {

    DEFAULT(0)//初始
    ,ACTIVE(1)//激活
    ,DELETED(2);//删除

    private Integer value;

    UserState(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
