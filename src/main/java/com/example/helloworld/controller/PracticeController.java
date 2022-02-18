package com.example.helloworld.controller;/**
 * @Classname PracticeController
 * @Description TODO
 * @Date 2021/12/30 15:09
 * @Created by 86150
 */

import com.example.helloworld.entitiy.*;
import com.example.helloworld.service.Implement.ClassServiceImpl;
import com.example.helloworld.service.Implement.PracticeServiceImpl;
import com.example.helloworld.service.Implement.TakeClassServiceImpl;
import com.example.helloworld.service.Implement.TakePracticeServiceImpl;
import com.example.helloworld.service.Interface.PracticeService;
import com.example.helloworld.service.Interface.TakePracticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @ program: 后端test
 * @ description: 对抗练习控制层
 * @ author: YXJ
 * @ date: 2021-12-30 15:09:09
 */
@Api("对抗练习管理后端接口")
@RestController
@RequestMapping("/practice")
public class PracticeController {
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private PracticeServiceImpl practiceService;
    @Autowired
    private TakePracticeServiceImpl takePracticeService;
    @Autowired
    private TakeClassServiceImpl takeClassService;


    @ApiOperation("添加对抗练习")
    @RequestMapping(value = "addPractice"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addPractice(@RequestBody Practice practice)
    {
        if(practice.getClassId() == null || !classService.existMyClass(practice.getClassId()))
        {
            return "classId不存在";
        }
        System.out.println(practice);

        practice.setBelongClass(classService.findMyClassById(practice.getClassId()));
        Practice practice1 = practiceService.addAndFlushPractice(practice);
        //为班级下的所有学生添加映射关系
        final List<TakeClass> allTakeClass = takeClassService.findAllTakeClassByClassId(practice1.getClassId());

        for (TakeClass takeClass : allTakeClass) {
            User student = takeClass.getStudent();
            TakePractice takePractice = new TakePractice();
            takePractice.setStudentId(student.getUid());
            takePractice.setStudent(student);
            takePractice.setPracticeId(practice1.getPracticeId());
            takePractice.setPractice(practice1);

            takePracticeService.addTakePractice(takePractice);
        }
        //分组
        takePracticeService.divGroups(practice1.getPracticeId());

        return "添加对抗练习成功";
    }

    @ApiOperation("更新对抗练习")
    @RequestMapping(value = "updatePractice"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updatePractice(@RequestBody Practice practice)
    {
        if(practice.getClassId() == null || !classService.existMyClass(practice.getClassId()))
        {
            return "classId不存在";
        }
        if(practice.getPracticeId() == null || !practiceService.existPractice(practice.getPracticeId()))
        {
            return "practiceId不存在";
        }
        practiceService.updatePractice(practice);
        return "更新对抗练习成功";
    }

    @ApiOperation("删除对抗练习")
    @RequestMapping(value = "deletePractice"  ,method = RequestMethod.PUT)
    public String deletePractice(@RequestParam Integer practiceId)
    {
        practiceService.deletePractice(practiceId);
        return "删除对抗练习成功";
    }

    @ApiOperation("查询班级下所有对抗练习")
    @RequestMapping(value = "getPracticeInClass"  ,method = RequestMethod.GET)
    public List<Practice> getPracticeInClass(@RequestParam Integer classId)
    {
        return practiceService.findAllPracticeByClassId(classId);
    }

    @ApiOperation("根据id查询对抗练习")
    @RequestMapping(value = "getPracticeById"  ,method = RequestMethod.GET)
    public Practice getPracticeById(@RequestParam Integer practiceId)
    {
        return practiceService.findPractice(practiceId);
    }

}
