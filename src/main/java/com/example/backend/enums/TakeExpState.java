package com.example.backend.enums;

/**
 * @Classname TakeExpState
 * @Description TODO
 * @Date 2021/12/18 0:27
 * @Created by 86150
 */
public enum TakeExpState {
    DEFAULT(0)//未提交
    ,SUBMITTED(1)//已提交
    ;


    private final Integer value;

    TakeExpState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
