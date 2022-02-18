package com.example.backend.controller;/**
 * @Classname TakeExperimentController
 * @Description TODO
 * @Date 2021/12/6 20:00
 * @Created by 86150
 */

import com.example.backend.entitiy.ExperimentProject;
import com.example.backend.entitiy.JoinPK.TakeExperimentPK;
import com.example.backend.entitiy.MyClass;
import com.example.backend.entitiy.TakeExperiment;
import com.example.backend.entitiy.User;
import com.example.backend.enums.TakeExpState;
import com.example.backend.service.Implement.*;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 学生 - 实验 映射关系管理
 * @ author: YXJ
 * @ date: 2021-12-06 20:00:10
 */
@Api("学生 - 实验 映射关系管理后端接口")
@RequestMapping("takeExp")
@RestController
public class TakeExperimentController {
    @Autowired
    private TakeExperimentServiceImpl takeExpService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ExperimentProjectServiceImpl expService;
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private QiniuServiceImpl qiniuService;
    @Autowired
    private GradeServiceImpl gradeService;

    @ApiOperation("添加学生 - 实验 关系")
    @RequestMapping(value = "addTakeExp"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addTakeExp(@RequestBody @ApiParam("TakeExperiment") TakeExperiment takeExperiment)
    {
        Integer studentId = takeExperiment.getStudentId();
        Integer expId = takeExperiment.getExperimentId();

        if( !userService.existUser(studentId))
        {
            return "添加学生 - 实验 关系失败：该学生id不存在";
        }
        if( !expService.existExperimentProject(expId))
        {
            return "添加学生 - 实验 关系失败：实验id不存在";
        }

        takeExperiment.setStudent(userService.findUser(studentId));
        takeExperiment.setExperimentProject(expService.findExperimentProjectById(expId));
        takeExpService.addTakeExperiment(takeExperiment);
        return "添加学生 - 实验 关系成功";
    }

    @ApiOperation("更新学生-实验 关系表内的成绩")
    @RequestMapping(value = "updateTakeExpScore"  ,method = RequestMethod.PUT)
    @ResponseBody
    public String updateTakeExp(@RequestBody @ApiParam("TakeExperimentPK") TakeExperimentPK takeExperimentPK,
                                @RequestParam("ExpScore") double expScore)
    {

        if(!takeExpService.existTakeExperiment(takeExperimentPK))
        {//如果不存在就创建一个关系
          return "学生 - 实验 关系不存在";
        }

        TakeExperiment takeExperiment = takeExpService.findTakeExperimentById(takeExperimentPK);

        takeExperiment.setScore(expScore);
        takeExpService.updateTakeExperiment(takeExperiment);
        //更新总成绩
        MyClass myClass = takeExperiment.getExperimentProject().getMyClass();
        gradeService.updateSumGrade(myClass.getClassId(),takeExperiment.getStudentId());

        return "更新学生实验成绩成功";
    }

    @ApiOperation("更新学生-实验 上传实验报告")
    @RequestMapping(value = "uploadExperimentReport"  ,method = RequestMethod.POST)
    @ResponseBody
    public String uploadExperimentReport(@RequestParam("studentId") Integer studentId,
                                         @RequestParam("ExperimentId") Integer expId ,
                                         @RequestParam @ApiParam("实验报告文件") MultipartFile file )
    {
        if (file.isEmpty()) {
            return "文件为空";
        }
        if(!userService.existUser(studentId)){
            return "更新实验成绩失败：学生id不存在";
        }
        if(!expService.existExperimentProject(expId)){
            return "更新实验成绩失败：实验id不存在";
        }

        TakeExperiment takeExperiment ;
        TakeExperimentPK takeExperimentPK = new TakeExperimentPK(studentId ,expId);
//        if(!takeExpService.existTakeExperiment(takeExperimentPK))
//        {//如果不存在就创建一个关系
//            takeExperiment = new TakeExperiment();
//            takeExperiment.setExperimentId(expId);
//            takeExperiment.setStudentId(studentId);
//            takeExperiment.setExperimentProject(expService.findExperimentProjectById(expId));
//            takeExperiment.setStudent(userService.findUser(studentId));
//        }
//        else {
            takeExperiment = takeExpService.findTakeExperimentById(takeExperimentPK);
//        }

        ExperimentProject exp = takeExperiment.getExperimentProject();
        User student = takeExperiment.getStudent();

        //设置文件名路径名字：classId/experimentId/ExpReport/userid/文件名字
        String fileName = exp.getClassId() + "/"+ exp.getExperimentId() + "/ExpReport/"
                + student.getUid().toString()
                + "/" + file.getOriginalFilename();

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return "转换为InputStream失败";
        }
        try {
            Response response = qiniuService.uploadFile(inputStream,fileName);
            if (response.isOK())
            {
                //设置上传报告名和上传时间
                takeExperiment.setReportURL(fileName);
                takeExperiment.setReportName(file.getOriginalFilename());
                takeExperiment.setSubmitTime(new Date());
                //更新状态
                takeExperiment.setState(TakeExpState.SUBMITTED.getValue());
                //更新映射表
                takeExpService.updateTakeExperiment(takeExperiment);
                return "上传报告模板成功";
            }
            else
            {
                //失败返回错误码和错误信息
                return response.getInfo();
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        return "";
    }

    @ApiOperation("下载学生实验报告")
    @RequestMapping(value = "/downloadStudentReport" , method = RequestMethod.GET)
    public String downloadStudentReport(@RequestParam("studentId") Integer studentId,
                                        @RequestParam("ExperimentId") Integer expId )
    {
        TakeExperimentPK takeExperimentPK = new TakeExperimentPK(studentId , expId);
        if(!takeExpService.existTakeExperiment(takeExperimentPK))
            return "学生 - 实验映射关系不存在";
        TakeExperiment tkExp = takeExpService.findTakeExperimentById(takeExperimentPK);
        if(tkExp.getReportURL() == null)
        {
            return "该学生还未提交实验报告";
        }
        try {
            return qiniuService.getPublicFile(tkExp.getReportURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @ApiOperation("预览学生实验报告")
    @RequestMapping(value = "/previewStudentReport" , method = RequestMethod.GET)
    public String previewStudentReport(@RequestParam("studentId") Integer studentId,
                                        @RequestParam("ExperimentId") Integer expId )
    {
        TakeExperimentPK takeExperimentPK = new TakeExperimentPK(studentId , expId);
        if(!takeExpService.existTakeExperiment(takeExperimentPK))
            return "学生 - 实验映射关系不存在";
        TakeExperiment tkExp = takeExpService.findTakeExperimentById(takeExperimentPK);
        if(tkExp.getReportURL() == null)
        {
            return "该学生还未提交实验报告";
        }
        try {
            return qiniuService.getBasePublicFile(tkExp.getReportURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    @ApiOperation("删除学生-实验 映射关系 " )
    @RequestMapping(value = "deleteTakeExp"  ,method = RequestMethod.PUT)
    public String deleteTakeExp(@RequestBody TakeExperimentPK takeExperimentPK)
    {
        if(!takeExpService.existTakeExperiment(takeExperimentPK))
        {
            return "删除学生-实验 映射关系失败：学生-实验映射表id不存在";
        }
        takeExpService.deleteTakeExperiment(takeExperimentPK);
        return "删除学生-实验 映射关系成功";
    }

    @ApiOperation("查询某班级下某学生的所有实验" )
    @RequestMapping(value = "getStudentTakeExp"  ,method = RequestMethod.GET)
    public List<TakeExperiment> getStudentTakeExp(@RequestParam("studentId") Integer studentId ,
                                                  @RequestParam("classId") Integer classId )
    {

        List<TakeExperiment> studentExpInClass = takeExpService.findStudentExpInClass(studentId, classId);
        System.out.println(studentExpInClass);
        return studentExpInClass;

    }

    @ApiOperation("查询某班级下某实验的所有报告" )
    @RequestMapping(value = "getAllTakeExpInExp"  ,method = RequestMethod.GET)
    public List<TakeExperiment> getAllTakeExpInExp(@RequestParam("expId") Integer expId )
    {

        return takeExpService.findAllTakeExperimentByExpId(expId);
    }
}
