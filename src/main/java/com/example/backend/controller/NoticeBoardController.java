package com.example.backend.controller;/**
 * @Classname NoticeBoardController
 * @Description TODO
 * @Date 2021/11/13 19:19
 * @Created by 86150
 */

import com.example.backend.entitiy.Notice;
import com.example.backend.entitiy.StudyFeedback;
import com.example.backend.service.Implement.ClassServiceImpl;
import com.example.backend.service.Implement.NoticeServiceImpl;
import com.example.backend.service.Implement.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @ program: Helloworld
 * @ description: 公告板接口
 * @ author: YXJ
 * @ date: 2021-11-13 19:19:40
 */
@Api("公告板管理后端接口")
@RestController
@RequestMapping("/notice")
public class NoticeBoardController {

    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("获取所有公告")
    @RequestMapping(value = "/getAllNotice" , method = RequestMethod.GET)
    public List<Notice> getNoticeList()
    {
        return noticeService.findAllNotice();
    }

    @ApiOperation("根据id查询公告")
    @RequestMapping(value = "/getNotice/{id}" , method = RequestMethod.GET)
    public Notice getNotice(@PathVariable Integer id)
    {
        return noticeService.findNotice(id);
    }

    @ApiOperation("根据id删除公告")
    @ApiImplicitParam(name = "id" , value = "公告板id")
    @RequestMapping(value = "/deleteNotice/{id}" , method = RequestMethod.DELETE)
    public String delNotice(@PathVariable Integer id)
    {
        if(noticeService.existNotice(id))
        {
            noticeService.deleteNotice(id);
            return "删除成功";
        }
        else
        {
            return "公告id不存在";
        }
    }

    @ApiOperation("置顶公告")
    @RequestMapping(value = "topNotice/{id}" , method = RequestMethod.PUT)
    public String topNotice(@PathVariable Integer id)
    {
        if(noticeService.existNotice(id))
        {
            noticeService.topNotice(id);
            return "置顶成功";
        }
        else
        {
            return "公告id不存在";
        }
    }

    @ApiOperation("取消置顶公告")
    @RequestMapping(value = "unTopNotice/{id}" , method = RequestMethod.PUT)
    public String unTopNotice(@PathVariable Integer id)
    {
        if(noticeService.existNotice(id))
        {
            noticeService.unTopNotice(id);
            return "解除置顶成功";
        }
        else
        {
            return "公告id不存在";
        }
    }

    @RequestMapping(value = "addNotice"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addNotice(@RequestBody Notice notice)
    {

        if(notice.getPublisher_id() == null )
        {
            return "添加公告失败：公告发布者id为空";
        }
        Integer id = notice.getPublisher_id();
        if( ! userService.existUser(id))
        {
            return "添加公告失败：公告发布者id不存在";
        }
//        if( notice.getClassId() == null || !classService.existMyClass(notice.getClassId()))
//        {
//            return "添加公告失败：班级id不存在";
//        }
        notice.setPublisher(userService.findUser(notice.getPublisher_id()));
//        notice.setMyClass(classService.findMyClassById(notice.getClassId()));

        System.out.println(notice);
        if(noticeService.existNotice(notice))
        {
            return "添加公告失败：公告id已存在";
        }
        noticeService.addNotice(notice);
        return "添加公告成功";

    }

    @RequestMapping(value = "updateNotice",method = RequestMethod.PUT)
    @ResponseBody
    public String updateNotice(@RequestBody Notice notice)
    {
//        System.out.println(notice);
        if(!noticeService.existNotice(notice))
        {
            return "公告不存在";
        }
        Notice oldNotice = noticeService.findNotice(notice.getNotice_id());
        if(!Objects.equals(notice.getPublisher_id(), oldNotice.getPublisher_id()))
            return "上传者id不同";
//        if(!Objects.equals(notice.getClassId(), oldNotice.getClassId()))
//            return "班级id不同";
        notice.setPublisher(userService.findUser(notice.getPublisher_id()));
        noticeService.updateNotice(notice);
            return "添加成功";

    }

    @ApiOperation("学生反馈学习情况")
    @RequestMapping(value = "addStudyFeedback",method = RequestMethod.PUT)
    public String addStudyFeedback(@RequestBody StudyFeedback studyFeedback ,
                                   @RequestParam Integer classId)
    {

//        if(!noticeService.existNotice(notice))
//        {
//            return "公告不存在";
//        }
//        Notice oldNotice = noticeService.findNotice(notice.getNotice_id());
//        if(!Objects.equals(notice.getPublisher_id(), oldNotice.getPublisher_id()))
//            return "上传者id不同";
//        if(!Objects.equals(notice.getClassId(), oldNotice.getClassId()))
//            return "班级id不同";
//
//        noticeService.updateNotice(notice);
        return "添加成功";

    }

    @ApiOperation("责任教师查看课程下所有学习情况反馈")
    @RequestMapping(value = "getAllStudyFeedbackInCourse",method = RequestMethod.GET)
    public List<StudyFeedback> addStudyFeedback(@RequestParam Integer courseId)
    {

//        if(!noticeService.existNotice(notice))
//        {
//            return "公告不存在";
//        }
//        Notice oldNotice = noticeService.findNotice(notice.getNotice_id());
//        if(!Objects.equals(notice.getPublisher_id(), oldNotice.getPublisher_id()))
//            return "上传者id不同";
//        if(!Objects.equals(notice.getClassId(), oldNotice.getClassId()))
//            return "班级id不同";
//
//        noticeService.updateNotice(notice);
        return null;

    }
}
