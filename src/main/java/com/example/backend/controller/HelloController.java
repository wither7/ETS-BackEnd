package com.example.backend.controller;
/**
 * @Classname Helloworld
 * @Description TODO
 * @Date 2021/11/11 12:35
 * @Created by 86150
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ program: Helloworld
 * @ description: 0.
 * @ author: YXJ
 * @ date: 2021-11-11 12:35:01
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "B站关注嘉然今天吃什么\n关注嘉然 顿顿解馋！\n关注珈乐 不会挨饿！";
    }


}
