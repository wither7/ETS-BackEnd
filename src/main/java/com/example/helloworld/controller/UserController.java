package com.example.helloworld.controller;/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/11/15 20:56
 * @Created by 86150
 */

import com.example.helloworld.entitiy.User;
import com.example.helloworld.enums.UserRole;
import com.example.helloworld.service.Implement.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-15 20:56:24
 */

@Api("用户管理后端接口")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public List<User> getUserList()
    {
        List<User> users= userService.findAllUser();
//        System.out.println(users);
        return users;
    }

    @ApiOperation("根据uid查找用户")
    @ApiImplicitParam(name = "id" , value = "要查找的用户uid",required = true,dataType = "Integer")
    @RequestMapping(value = "/getUserById/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer id)
    {
        return userService.findUser(id);
    }


    @ApiOperation(" DEFAULT(0)//初始 \n" +
            "    ,STUDENT(1)//学生\n" +
            "    ,ASSISTANT(2)//助教\n" +
            "    ,TEACHER(3)    //教师\n" +
            "    ,ADMINISTRATOR(4); // 管理员" )
    @RequestMapping(value = "/getUserRoleById/{id}",method = RequestMethod.GET)
    public Integer getUserRoleById(@PathVariable @ApiParam("userId") Integer id)
    {
        return userService.findUser(id).getRole();
    }

    @ApiOperation("根据email查找用户")
    @ApiImplicitParam(name = "email" , value = "要查找的用户email",required = true,dataType = "String")
    @RequestMapping(value = "/getUserByEmail/{email}",method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable String email)
    {
        return userService.findUser(email);
    }


    @ApiOperation("根据email查询用户是否存在 ")
    @ApiImplicitParam(name = "email" , value = "要查找的用户邮箱",required = true,dataType = "String" )
//    @ApiResponses({
//        @ApiResponse(code = 200 , message = "request success " , response = Boolean.class)
//    })
    @RequestMapping(value = "/findUserByEmail/{email}",method = RequestMethod.GET)
    public Boolean findUserByEmail(@PathVariable String  email)
    {
        return userService.existUser(email);
    }


    @ApiOperation("根据uid删除用户")
    @ApiImplicitParam(name = "id" , value = "要删除的用户uid",required = true,dataType = "Integer")
    @RequestMapping(value = "/deleteUser/{id}" , method = RequestMethod.DELETE)
    public String delUser(@PathVariable Integer id)
    {
        if(userService.existUser(id))
        {
            userService.deleteUser(id);
            return "删除成功";
        }
        else
        {
            return "公告id不存在";
        }
    }

    @ApiOperation("添加用户")
    @ApiImplicitParam(name = "user" , value = "要添加的用户信息")
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user)
    {
//        System.out.println(user);

        if(userService.existUser(user.getEmail()))
        {
            return "用户email已存在";
        }
        else
        {
            userService.addUser(user);
            return "添加成功";
        }
//        return "s";
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "user" , value = "要更新的用户信息")
    @ResponseBody
    @RequestMapping(value = "updateUser" ,method = RequestMethod.PUT)
    public String updateUser(@RequestBody User user)
    {
        System.out.println(user);
        if(!userService.existUser(user.getUid()))
        {
            return "公告不存在";
        }
        else
        {
            userService.updateUser(user);
            return "添加成功";
        }
    }

    @ApiOperation("获取用户总数")
    @RequestMapping(value = "countUser" ,method = RequestMethod.GET)
    public long countUser()
    {
        return userService.countUser();
    }


    /*
     * test enum model
     */
    @ApiOperation("test")
    @RequestMapping(value = "/getUserRoleByEnum",method = RequestMethod.GET)
    public void  getUserRoleByEnum(@RequestBody @ApiParam("userRole") UserRole userRole)
    {

    }
}

/*
 *  @RequestParam和@PathVariable的区别
 *  @PathVariable : 路径变量  url中呈现形式为test/123
 *  @RequestParam ：请求参数  url中呈现形式为test?user_id = 123
 *
 */