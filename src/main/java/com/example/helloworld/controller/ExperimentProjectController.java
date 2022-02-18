package com.example.helloworld.controller;/**
 * @Classname ExperimentProjectController
 * @Description TODO
 * @Date 2021/12/3 18:33
 * @Created by 86150
 */

import com.example.helloworld.dao.ExperimentProjectRepository;
import com.example.helloworld.entitiy.*;
import com.example.helloworld.service.Implement.*;
import com.example.helloworld.service.Interface.ClassService;
import com.example.helloworld.service.Interface.ExperimentProjectService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.Query;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 实验项目管理
 * @ author: YXJ
 * @ date: 2021-12-03 18:33:14
 */
@Api("实验项目管理后端接口")
@RestController
@RequestMapping("exp")
public class ExperimentProjectController {
    @Autowired
    private ExperimentProjectServiceImpl experimentProjectService;
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private QiniuServiceImpl qiniuService;
    @Autowired
    private TakeExperimentServiceImpl takeExperimentService;
    @Autowired
    private TakeClassServiceImpl takeClassService;

    @ApiOperation("根据classId查找所有实验项目")
    @RequestMapping(value = "/getExperimentByClassId/{classId}",method = RequestMethod.GET)
    public List<ExperimentProject> getAllExperimentProjectByClassId(@PathVariable @ApiParam("课程id") Integer classId)
    {
        return experimentProjectService.findAllExperimentProjectByClassId(classId);
    }

    @ApiOperation("添加实验项目")
    @RequestMapping(value = "addExperimentProject",method = RequestMethod.POST)
    @ResponseBody
    public String addExperimentProject(@RequestBody @ApiParam("要添加的实验项目") ExperimentProject experimentProject)
    {
//        System.out.println(user);

        if(! classService.existMyClass(experimentProject.getClassId()))
        {
            return "class不存在";
        }
        MyClass myClass = classService.findMyClassById(experimentProject.getClassId());
        experimentProject.setMyClass(myClass);

        if(experimentProject.getExperimentId() == null)
        {
            experimentProject.setExperimentId(0);
        }
        final ExperimentProject experimentProject1 = experimentProjectService.addAndFlushExperimentProject(experimentProject);

        //为班级下的所有学生添加映射关系
        final List<TakeClass> allTakeClass = takeClassService.findAllTakeClassByClassId(experimentProject1.getClassId());
        for(TakeClass takeclass : allTakeClass)
        {
            User student = takeclass.getStudent();
            TakeExperiment takeExperiment = new TakeExperiment();
            takeExperiment.setStudentId(student.getUid());
            takeExperiment.setStudent(student);
            takeExperiment.setExperimentId(experimentProject1.getExperimentId());
            takeExperiment.setExperimentProject(experimentProject1);

            System.out.println(takeExperiment);
            takeExperimentService.addTakeExperiment(takeExperiment);
        }
        return "添加实验项目成功";
    }

    @ApiOperation("修改实验项目(除报告模板外)")
    @RequestMapping(value = "updateExperimentProject",method = RequestMethod.PUT)
    @ResponseBody
    public String updateExperimentProject(@RequestBody @ApiParam("要更新的实验项目") ExperimentProject experimentProject)
    {
//        System.out.println(user);

        if(! classService.existMyClass(experimentProject.getClassId()))
        {
            return "class不存在";
        }
        Integer expId = experimentProject.getExperimentId();
        if( expId == null)
        {
            return "实验id不存在";
        }
        ExperimentProject originExp = experimentProjectService.findExperimentProjectById(expId);
        //将原实验项目的模板更新到新实验项目
        experimentProject.setReport_template(originExp.getReport_template());
        experimentProjectService.updateExperimentProject(experimentProject);
        return "更新实验项目成功";

    }

    @ResponseBody
    @RequestMapping(value = "/uploadReportTemplate" , method = RequestMethod.POST)
    @ApiOperation("上传实验报告模板")
    public String uploadFile(@RequestParam @ApiParam("实验报告模板文件") MultipartFile file ,
                             @RequestParam @ApiParam("实验id")Integer experimentId ,
                             @RequestParam @ApiParam("教师id")Integer teacherId)
    {

        //判断文件是否为空
        if (file.isEmpty()) {
            return "文件为空";
        }
        ExperimentProject exp = experimentProjectService.findExperimentProjectById(experimentId);
        if(exp == null)
        {
            return "实验id不存在";
        }

        //设置文件名路径名字：classId/experimentId/ReportTemplate/userid/文件名字
        String fileName = exp.getClassId() + "/"+ exp.getExperimentId() + "/ReportTemplate/"
                + teacherId.toString()
                + "/" + file.getOriginalFilename();
        System.out.println("Filename : " + fileName);

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

//                【如果上传成功返回gson数据，内含重要的图片信息key】
//                方法一：使用默认putRet
//                DefaultPutRet defaultPutRet= gson.fromJson(response.bodyString(),DefaultPutRet.class);
//                log.info("default putRet"+defaultPutRet.toString());
//                //方法二：使用自定义putRet
//                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
//                log.info("QiNiuPutRet"+ret);
//
//                return ApiResponse.ofSuccess(ret);
                exp.setReport_template(fileName);
                experimentProjectService.updateExperimentProject(exp);
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
        return null;
    }

    @ApiOperation("根据id删除实验项目")
    @RequestMapping(value = "/deleteExperimentProject/{expId}" , method = RequestMethod.DELETE)
    public String deleteExperimentProject(@PathVariable @ApiParam("要删除的实验项目id") Integer expId)
    {
        if(experimentProjectService.existExperimentProject(expId))
        {
            ExperimentProject experimentProject = experimentProjectService.findExperimentProjectById(expId);
            //在文件服务器上删除报告模板
            try {
                qiniuService.delete(experimentProject.getReport_template());
            } catch (QiniuException e) {
                e.printStackTrace();
            }

            experimentProjectService.deleteExperimentProject(expId);
            return "删除实验项目成功";
        }
        else
        {
            return "实验项目id不存在";
        }
    }

    @ApiOperation("下载实验报告模板")
    @RequestMapping(value = "/downloadReportTemplate" , method = RequestMethod.GET)
    public String downloadReportTemplate(@RequestParam @ApiParam("实验id")Integer experimentId)
    {
        if(!experimentProjectService.existExperimentProject(experimentId))
            return "实验id不存在";
        ExperimentProject exp = experimentProjectService.findExperimentProjectById(experimentId);
        if(exp.getReport_template() == null)
        {
            return "该实验项目暂无报告模板";
        }
        try {
            return qiniuService.getPublicFile(exp.getReport_template());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
