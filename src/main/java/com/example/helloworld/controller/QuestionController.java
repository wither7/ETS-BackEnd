package com.example.helloworld.controller;/**
 * @Classname QuestionController
 * @Description TODO
 * @Date 2021/12/31 20:05
 * @Created by 86150
 */

import com.example.helloworld.entitiy.Practice;
import com.example.helloworld.entitiy.Question;
import com.example.helloworld.service.Implement.PracticeServiceImpl;
import com.example.helloworld.service.Implement.QuestionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.PrinterAbortException;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 单个问题管理
 * @ author: YXJ
 * @ date: 2021-12-31 20:05:35
 */
@Api("对抗练习问题管理后端接口")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private PracticeServiceImpl practiceService;


    @ApiOperation("添加问题")
    @RequestMapping(value = "addQuestion"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addQuestion(@RequestBody Question question)
    {
        Integer practiceId = question.getPracticeId();
        if(practiceId == null || !practiceService.existPractice(question.getPracticeId()))
        {
            return "对抗练习id不存在";
        }
        Practice practice = practiceService.findPractice(practiceId);
//        question.setBelongPractice(practice);
        practice.getQuestions().add(question);
        //先更新问题再更新练习
        questionService.addQuestion(question);
        practiceService.updatePractice(practice);

        return "添加问题成功";

    }

    @ApiOperation("更新问题")
    @RequestMapping(value = "updateQuestion"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updateQuestion(@RequestBody Question question)
    {
        Integer practiceId = question.getPracticeId();
        if(practiceId == null || !practiceService.existPractice(question.getPracticeId()))
        {
            return "对抗练习id不存在";
        }
        questionService.updateQuestion(question);
        return "更新问题成功";
    }

    @ApiOperation("删除问题")
    @RequestMapping(value = "deleteQuestion"  ,method = RequestMethod.PUT)
    public String deleteQuestion(@RequestParam Integer questionId)
    {
        questionService.deleteQuestion(questionId);
        return "删除问题成功";
    }

    @ApiOperation("查询问题")
    @RequestMapping(value = "getQuestionById"  ,method = RequestMethod.GET)
    public Question getQuestionById(@RequestParam Integer questionId)
    {
        return questionService.findQuestion(questionId);
    }
}
