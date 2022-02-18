package com.example.backend.controller;/**
 * @Classname GradeController
 * @Description TODO
 * @Date 2021/12/28 0:19
 * @Created by 86150
 */

import com.example.backend.dto.StudentGrade;
import com.example.backend.service.Implement.ClassServiceImpl;
import com.example.backend.service.Implement.GradeServiceImpl;
import com.example.backend.service.Implement.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ program: 后端test
 * @ description: 成绩管理控制层
 * @ author: YXJ
 * @ date: 2021-12-28 00:19:57
 */
@Api("成绩管理后端接口")
@RestController
@RequestMapping("grade")
public class GradeController {
    @Autowired
    private GradeServiceImpl gradeService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ClassServiceImpl classService;

    @ApiOperation("获取班级下所有学生成绩")
    @RequestMapping(value = "/getAllGradeInClass",method = RequestMethod.GET)
    public List<StudentGrade> getAllGradeInClass(@RequestParam Integer classId)
    {
//        if( !userService.existUser(studentId))
//        {
////            return "该学生id不存在";
//        }

        return null;
    }

    @ApiOperation("获取某学生所有课程成绩")
    @RequestMapping(value = "/getStudentGrade",method = RequestMethod.GET)
    public List<StudentGrade> getStudentGrade(@RequestParam Integer studentId)
    {
        return null;
    }

}
