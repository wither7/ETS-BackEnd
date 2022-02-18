package com.example.backend.controller;/**
 * @Classname TakeClassController
 * @Description TODO
 * @Date 2021/12/1 21:53
 * @Created by 86150
 */

import com.example.backend.entitiy.JoinPK.TakeClassPK;
import com.example.backend.entitiy.TakeClass;
import com.example.backend.entitiy.User;
import com.example.backend.enums.UserRole;
import com.example.backend.service.Implement.*;
import com.example.backend.utils.Helper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 学生-班级 管理
 * @ author: YXJ
 * @ date: 2021-12-01 21:53:36
 */
@Api("学生 - 班级管理后端接口")
@RestController
@RequestMapping("/takeClass")
public class TakeClassController {
    @Autowired
    public CourseServiceImpl courseService;
    @Autowired
    public ClassServiceImpl classService;
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public TakeClassServiceImpl takeClassService;
    @Autowired
    public GradeServiceImpl gradeService;

    public final static Integer attendanceMax = 20;

    @ApiOperation("获取班级下的所有选课信息")
    @RequestMapping(value = "/getAllTakeClass",method = RequestMethod.GET)
    public List<TakeClass> getTakeClassList(@RequestParam("classId") Integer classId)
    {
        List<TakeClass> allTakeClassByClassId = takeClassService.findAllTakeClassByClassId(classId);
        for(TakeClass takeClass : allTakeClassByClassId)
        {
            gradeService.updateSumGrade(takeClass.getClassId(),takeClass.getStudentId());
        }
        return takeClassService.findAllTakeClassByClassId(classId);
    }

    @ApiOperation("获取学生的所有选课信息")
    @RequestMapping(value = "/getStudentAllTakeClass",method = RequestMethod.GET)
    public List<TakeClass> getTakeClassListByStudentId(@RequestParam("studentId") Integer studentId)
    {
        return takeClassService.findAllTakeClassByStudentId(studentId);
    }


    @ApiOperation("添加学生-班级映射关系")
    @ApiImplicitParam(name = "takeClass" , value = "学生-班级映射类",required = true,dataType = "TakeClass")
    @RequestMapping(value = "/addTakeClass"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addTakeClass(@RequestBody TakeClass takeClass)
    {
        if(takeClass.getClassId() == null || takeClass.getStudentId() == null )
        {
            return "id为空";
        }
        if(takeClassService.existTakeClass(new TakeClassPK(takeClass.getClassId() ,takeClass.getStudentId())))
        {
            return "添加学生-班级关系失败：id已存在";
        }
        User student = userService.findUser(takeClass.getStudentId());
        student.setRole(UserRole.STUDENT.getValue());
        userService.updateUser(student);
        User student1 = userService.findUser(takeClass.getStudentId());
        takeClass.setStudent(student1);
        takeClass.setMyclass(classService.findMyClassById(takeClass.getClassId()));
        takeClass.setAttendance(0);
        takeClass.setPracticeGrade(0.0);
        takeClass.setAttendanceGrade(0.0);
        takeClass.setPracticeGrade(0.0);
        takeClass.setExperimentGrade(0.0);
        takeClass.setTotalGrade(0.0);
        takeClassService.addTakeClass(takeClass);

        return "添加学生 - 班级关系成功";
    }

    @ApiOperation("删除学生 - 班级关系")
    @RequestMapping(value = "/deleteTakeClass"  ,method = RequestMethod.PUT)
    public String deleteTakeClass(@RequestBody TakeClassPK takeClassPK)
    {
        if(!takeClassService.existTakeClass(takeClassPK))
        {
            return "删除学生-班级关系失败：id不存在";
        }
//        if(!userService.existUser(myClass.getTeacher_id()))
//        {
//            return "添加班级失败：教师id不存在";
//        }
//        myClass.set(userService.findUser(course.getResTeacherId()));
        takeClassService.deleteTakeClass(takeClassPK);
        return "删除学生-班级关系成功";
    }

    @ApiOperation("更新 学生-班级 关系中的总成绩  不应被调用")
    @RequestMapping(value = "/updateTakeClassGrade"  ,method = RequestMethod.PUT)
    public String updateTakeClassGrade(@RequestBody TakeClassPK takeClassPK ,
                               @RequestParam("totalGrade") double totalGrade)
    {
        if(!takeClassService.existTakeClass(takeClassPK))
        {
            return "更新学生成绩失败：id不存在";
        }

        TakeClass takeClass = takeClassService.findTakeClassById(takeClassPK);
        takeClass.setTotalGrade(totalGrade);
        takeClassService.updateTakeClass(takeClass);

        return "更新学生总成绩成功";
    }

    @ApiOperation("更新 学生-班级 关系中的签到次数")
    @RequestMapping(value = "/updateTakeClassAttendance"  ,method = RequestMethod.PUT)
    public String updateTakeClassAttendance(@RequestBody TakeClassPK takeClassPK)
    {
        if(!takeClassService.existTakeClass(takeClassPK))
        {
            return "更新学生出勤次数失败：id不存在";
        }
        TakeClass takeClass = takeClassService.findTakeClassById(takeClassPK);
        //Test
        gradeService.updateSumGrade(takeClass.getClassId(),takeClass.getStudentId());

        if(takeClass.getLastAttendantDate() != null)
        {
            Date nowDate = new Date();
            if(Helper.isSameDay(nowDate , takeClass.getLastAttendantDate()))
            {
                return "今日已打过卡";
            }
            takeClass.setLastAttendantDate(nowDate);
        }
        else
        {
            takeClass.setLastAttendantDate(new Date());
        }
        Integer attendance = takeClass.getAttendance() == null ? 0 : takeClass.getAttendance();
        if(attendance<attendanceMax)
            ++attendance;
        else
            return "打卡次数已满";
        takeClass.setAttendance(attendance);
        takeClassService.updateTakeClass(takeClass);
        gradeService.updateSumGrade(takeClass.getClassId(),takeClass.getStudentId());
        return "更新学生考勤次数成功";
    }


}
