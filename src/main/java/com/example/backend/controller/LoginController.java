package com.example.backend.controller;/**
 * @Classname LoginController
 * @Description TODO
 * @Date 2021/11/17 19:40
 * @Created by 86150
 */

import com.example.backend.service.Implement.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ program: 后端test
 * @ description: 登录控制器
 * @ author: YXJ
 * @ date: 2021-11-17 19:40:38
 */
@Api("登录相关后端接口")
//@RequestMapping("login")
@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService ;


    @RequestMapping(value = "login" , method = RequestMethod.GET)
    //get方式传参不用写ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email" , value = "登录邮箱" , paramType = "String"),
            @ApiImplicitParam(name = "password" , value = "登录密码" , paramType = "String")
    })
    public boolean loginCheck(String email , String password)
    {
        //System.out.println(email+ " ; " + password);
//        return userService.userLogin(user.getEmail(), user.getPassword());
        return userService.userLogin(email, password);
    }
}
