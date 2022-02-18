package com.example.helloworld.controller;/**
 * @Classname TakePracticeController
 * @Description TODO
 * @Date 2021/12/30 15:54
 * @Created by 86150
 */

import com.example.helloworld.entitiy.JoinPK.TakePracticePK;
import com.example.helloworld.entitiy.Practice;
import com.example.helloworld.entitiy.TakePractice;
import com.example.helloworld.entitiy.User;
import com.example.helloworld.service.Implement.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 学生参与对抗练习控制类
 * @ author: YXJ
 * @ date: 2021-12-30 15:54:07
 */
@Api("参与对抗练习管理后端接口")
@RestController
@RequestMapping("/takePractice")
public class TakePracticeController {

    @Autowired
    private PracticeServiceImpl practiceService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TakePracticeServiceImpl takePracticeService;
    @Autowired
    private GradeServiceImpl gradeService;

    @ApiOperation("添加学生 - 对抗练习 关系")
    @RequestMapping(value = "addTakePractice"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addTakePractice(@RequestBody TakePractice takePractice)
    {
        Integer studentId = takePractice.getStudentId();
        Integer practiceId = takePractice.getPracticeId();
        if(studentId == null || practiceId == null || !userService.existUser(studentId) || !practiceService.existPractice(practiceId))
        {
            return "添加失败，id非法";
        }
        takePractice.setStudent(userService.findUser(studentId));
        takePractice.setPractice(practiceService.findPractice(practiceId));

        takePracticeService.addTakePractice(takePractice);
        return "添加学生 - 对抗练习 关系成功";
    }

    @ApiOperation("更新学生 - 对抗练习 关系")
    @RequestMapping(value = "updateTakePractice"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updateTakePractice(@RequestBody TakePractice takePractice)
    {
        Integer studentId = takePractice.getStudentId();
        Integer practiceId = takePractice.getPracticeId();
        if(studentId == null || practiceId == null || !userService.existUser(studentId) ||
                !practiceService.existPractice(practiceId) ||
                !takePracticeService.existTakePractice(new TakePracticePK(studentId,practiceId)))
        {
            return "更新失败，id非法";
        }

        takePracticeService.updateTakePractice(takePractice);
        return "更新学生 - 对抗练习 关系成功";
    }
    /*
     * TODO
     */
    @ApiOperation("更新学生 - 对抗练习 关系中的学生成绩 100分制")
    @RequestMapping(value = "updateTakePracticeScore"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updateTakePractice(@RequestBody TakePracticePK takePracticePK,
                                     @RequestParam Integer paperScore,
                                     @RequestParam Integer takeTime)
    {
        Integer studentId = takePracticePK.getStudentId();
        Integer practiceId = takePracticePK.getPracticeId();
        if(studentId == null || practiceId == null || !userService.existUser(studentId) ||
                !practiceService.existPractice(practiceId) ||
                !takePracticeService.existTakePractice(new TakePracticePK(studentId,practiceId)))
        {
            return "更新失败，id非法";
        }
        final Practice practice = practiceService.findPractice(practiceId);
        TakePractice takePractice = takePracticeService.findTakePractice(takePracticePK);
        takePractice.setPaperScore(paperScore);

        takePractice.setUseTime(takeTime);
        takePracticeService.updateTakePractice(takePractice);
        //更新组内成绩
        takePracticeService.updateGroupScore(takePracticePK);
        //更新学生总成绩
        gradeService.updateSumGrade(practice.getClassId(),studentId);
        return "更新学生 - 对抗练习 关系中的成绩成功";
    }

    @ApiOperation("删除学生 - 对抗练习 关系")
    @RequestMapping(value = "deleteTakePractice"  ,method = RequestMethod.PUT)
    public String deleteTakePractice(@RequestParam TakePracticePK takePracticePK)
    {
        takePracticeService.deleteTakePractice(takePracticePK);
        return "删除成功";
    }

    @ApiOperation("根据PK查询学生-对抗练习关系")
    @RequestMapping(value = "getTakePracticeByPK"  ,method = RequestMethod.GET)
    public TakePractice getTakePracticeByPK(@RequestParam Integer practiceId,
                                            @RequestParam Integer studentId)
    {

        return takePracticeService.findTakePractice(new TakePracticePK(studentId,practiceId));
    }

    @ApiOperation("查询参与对抗练习的所有学生")
    @RequestMapping(value = "getTakePracticeByPracticeId"  ,method = RequestMethod.GET)
    public List<TakePractice> getTakePracticeByPracticeId(@RequestParam Integer practiceId)
    {
        return takePracticeService.findTakePracticeByPracticeId(practiceId);
    }

    @ApiOperation("查询学生参与的所有对抗练习")
    @RequestMapping(value = "getTakePracticeByStudentId"  ,method = RequestMethod.GET)
    public List<TakePractice> getTakePracticeByStudentId(@RequestParam Integer studentId)
    {
        return takePracticeService.findTakePracticeByStudentId(studentId);
    }




}
