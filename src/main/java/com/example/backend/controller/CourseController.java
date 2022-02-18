package com.example.backend.controller;/**
 * @Classname CourseController
 * @Description TODO
 * @Date 2021/11/27 18:45
 * @Created by 86150
 */

import com.example.backend.entitiy.Course;
import com.example.backend.entitiy.User;
import com.example.backend.enums.UserRole;
import com.example.backend.service.Implement.CourseServiceImpl;
import com.example.backend.service.Implement.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ program: 后端test
 * @ description: 课程管理
 * @ author: YXJ
 * @ date: 2021-11-27 18:45:27
 */
@Api("课程管理后端接口")
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public CourseServiceImpl courseService;
    @Autowired
    public UserServiceImpl userService;

    @ApiOperation("获取所有课程")
    @RequestMapping(value = "/getAllCourse",method = RequestMethod.GET)
    public List<Course> getCourseList()
    {
        return courseService.findAllCourse();
    }

    @ApiOperation("获取责任教师的所有课程")
    @RequestMapping(value = "/getResTeacherAllCourse",method = RequestMethod.GET)
    public List<Course> getUserCourseList(@RequestParam("teacher_id") Integer teacher_id)
    {
        return courseService.findUserCourses(teacher_id);
    }

    @ApiOperation("添加课程")
    @ApiImplicitParam(name = "course" , value = "要添加的课程 ",required = true,dataType = "Course")
    @RequestMapping(value = "addCourse"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addCourse(@RequestBody Course course)
    {
//        if(courseService.existCourse(course.getCourse_id()))
//        {
//            return "添加课程失败：该课程id已存在";
//        }
        if(course.getCourseId() == null || course.getCourseId() != 0)
            course.setCourseId(0);


        if(!userService.existUser(course.getResTeacherId()))
        {
            return "添加课程失败：责任教师id不存在";
        }
        User resTeacher = userService.findUser(course.getResTeacherId());
        resTeacher.setRole(UserRole.TEACHER.getValue());
        course.setResponsible_teacher(resTeacher);
        course.setAttendanceWeight(0.33);
        course.setPracticeWeight(0.33);
        course.setExperimentWeight(0.33);
        courseService.addCourse(course);
        return "添加课程成功";

    }

    @ApiOperation("删除课程")
    @RequestMapping(value = "deleteCourse"  ,method = RequestMethod.PUT)
    public String deleteCourse(@RequestParam("courseId") @ApiParam("courseId") Integer courseId)
    {
        if(!courseService.existCourse(courseId))
        {
            return "删除课程失败：该课程id不存在";
        }
       courseService.deleteCourse(courseId);
//     Course course = courseService.findCourseById(courseId);
        return "删除课程成功";
    }

    @ApiOperation("更新课程")
    @ApiImplicitParam(name = "course" , value = "要更新的课程 ",required = true ,dataType = "Course")
    @RequestMapping(value = "updateCourse"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updateCourse(@RequestBody Course course)
    {
        if(!courseService.existCourse(course.getCourseId()))
        {
            return "更新课程失败：该课程id不存在";
        }
        if(!userService.existUser(course.getResTeacherId()))
        {
            return "更新课程失败：责任教师id不存在";
        }

        User resTeacher = userService.findUser(course.getResTeacherId());
        resTeacher.setRole(UserRole.TEACHER.getValue());
        course.setResponsible_teacher(resTeacher);
        courseService.updateCourse(course);

        return "更新课程成功";

    }
}
