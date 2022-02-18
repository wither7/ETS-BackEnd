package com.example.helloworld.controller;/**
 * @Classname AssistantController
 * @Description TODO
 * @Date 2021/12/7 19:33
 * @Created by 86150
 */

import com.example.helloworld.entitiy.AssistantInClass;
import com.example.helloworld.entitiy.JoinPK.AssistantInClassPK;
import com.example.helloworld.entitiy.MyClass;
import com.example.helloworld.entitiy.User;
import com.example.helloworld.enums.UserRole;
import com.example.helloworld.service.Implement.AssistantInClassServiceImpl;
import com.example.helloworld.service.Implement.ClassServiceImpl;
import com.example.helloworld.service.Implement.UserServiceImpl;
import com.example.helloworld.service.Interface.AssistantInClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ program: 后端test
 * @ description: 助教管理
 * @ author: YXJ
 * @ date: 2021-12-07 19:33:32
 */
@Api("助教管理后端接口")
@RestController
@RequestMapping("/assistant")
public class AssistantController {
    @Autowired
    private AssistantInClassServiceImpl assistantService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ClassServiceImpl classService;


    @ApiOperation("根据班级id获取所有助教关系")
    @RequestMapping(value = "/getAllAssistantInClassByClassId",method = RequestMethod.GET)
    public List<AssistantInClass> getAllAssistantInClass(@RequestParam("classId") Integer classId)
    {
        return assistantService.findAllAssistantInClassByClassId(classId);
    }

    @ApiOperation("根据用户id获取所有助教关系")
    @RequestMapping(value = "/getAllAssistantInClassByUserId",method = RequestMethod.GET)
    public List<AssistantInClass> getAllAssistantInClassByUserId(@RequestParam("userId") Integer userId)
    {
        return assistantService.findAllAssistantInClassByAssistantId(userId);
    }

    @ApiOperation("添加助教")
    @RequestMapping(value = "addAssistant"  ,method = RequestMethod.POST)
    @ResponseBody
    public String addAssistant(@RequestBody AssistantInClass assistantInClass)
    {
        Integer assistantId = assistantInClass.getAssistantId();
        Integer classId = assistantInClass.getClassId();

        if(!classService.existMyClass(assistantInClass.getClassId()))
        {
            return "添加助教关系失败：班级id不存在";
        }
        if(!userService.existUser(assistantInClass.getAssistantId()))
        {
            return "添加助教关系失败：助教id不存在";
        }
        User user = userService.findUser(assistantId);
        user.setRole(UserRole.ASSISTANT.getValue());
        userService.updateUser(user);
        assistantInClass.setAssistant(user);
        assistantInClass.setMyClass(classService.findMyClassById(classId));
        assistantService.addAssistant(assistantInClass);
        return "添加助教成功";
    }

    @ApiOperation("删除助教关系")
    @RequestMapping(value = "deleteAssistant"  ,method = RequestMethod.PUT)
    public String deleteClass(@RequestBody AssistantInClassPK assistantInClassPK)
    {
        if(!assistantService.existAssistant(assistantInClassPK))
        {
            return "删除助教失败：该助教关系id不存在";
        }
        assistantService.deleteAssistant(assistantInClassPK);
        return "删除助教成功";
    }


}
