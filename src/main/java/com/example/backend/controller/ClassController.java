package com.example.backend.controller;/**
 * @Classname ClassController
 * @Description TODO
 * @Date 2021/11/28 10:29
 * @Created by 86150
 */

import com.example.backend.entitiy.MyClass;
import com.example.backend.service.Implement.ClassServiceImpl;
import com.example.backend.service.Implement.CourseServiceImpl;
import com.example.backend.service.Implement.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-28 10:29:09
 */
@Api("班级管理后端接口")
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    public CourseServiceImpl courseService;
    @Autowired
    public ClassServiceImpl classService;
    @Autowired
    public UserServiceImpl userService;

    @ApiOperation("获取所有班级")
    @RequestMapping(value = "/getAllClass",method = RequestMethod.GET)
    public List<MyClass> getClassList()
    {
        return classService.findAllMyClass();
    }


    @ApiOperation("获取课程下的所有班级")
    @RequestMapping(value = "/getAllClassInCourse",method = RequestMethod.GET)
    public List<MyClass> getClassListByCourseId(@RequestParam Integer courseId)
    {
        return classService.findAllMyClassByCourseId(courseId);
    }

    @ApiOperation("获取教师授课的所有班级")
    @RequestMapping(value = "/getAllClassByTeacherId",method = RequestMethod.GET)
    public List<MyClass> getAllClassByTeacherId(@RequestParam Integer teacherId)
    {
        return classService.findAllMyClassByTeacherId(teacherId);
    }

    @ApiOperation("添加班级")
//    @ApiImplicitParam(name = "class" , value = "班级类 ",required = true,dataType = "MyClass")
    @RequestMapping(value = "addClass"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addClass(@RequestBody @ApiParam("myClass") MyClass myClass)
    {
        System.out.println(myClass);
        if( myClass.getClassId() == null || myClass.getClassId() != 0)
            myClass.setClassId(0);
        if(classService.existMyClass(myClass.getClassId()))
        {
            return "添加班级失败：该班级id已存在";
        }
        if(!userService.existUser(myClass.getTeacherId()))
        {
            return "添加班级失败：教师id不存在";
        }
        myClass.setCourse(courseService.findCourseById(myClass.getCourseId()));
        myClass.setTeacher(userService.findUser(myClass.getTeacherId()));
        System.out.println(myClass);
        classService.addMyClass(myClass);

        return "添加班级成功";
    }

    @ApiOperation("更新班级")
    @RequestMapping(value = "updateClass"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updateClass(@RequestBody MyClass myClass)
    {
        if(!classService.existMyClass(myClass.getClassId()))
        {
            return "更新班级失败：该班级id不存在";
        }
        if(!userService.existUser(myClass.getTeacherId()))
        {
            return "更新班级失败：教师id不存在";
        }
//        myClass.set(userService.findUser(course.getResTeacherId()));
        classService.updateMyClass(myClass);
        return "更新班级成功";
    }

    @ApiOperation("删除班级")
    @RequestMapping(value = "deleteClass"  ,method = RequestMethod.PUT)
    public String deleteClass(@RequestParam Integer classId)
    {
        if(!classService.existMyClass(classId))
        {

            return "删除班级失败：该班级id不存在";
        }
//        if(!userService.existUser(myClass.getTeacher_id()))
//        {
//            return "添加班级失败：教师id不存在";
//        }
//        myClass.set(userService.findUser(course.getResTeacherId()));
        classService.deleteMyClass(classId);
        return "删除班级成功";
    }


}
