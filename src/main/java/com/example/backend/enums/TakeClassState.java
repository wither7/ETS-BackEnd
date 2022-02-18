package com.example.backend.enums;

/**
 * @Classname TakeClassState
 * @Description TODO
 * @Date 2021/11/28 18:21
 * @Created by 86150
 */
/*
 * 学生上课表的状态
 */
public enum TakeClassState {
    DEFAULT(0),
    QUIT(1),
    TAKEN(2),
    DELETED(3);

    private Integer value;


    TakeClassState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
