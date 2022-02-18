package com.example.backend.controller;/**
 * @Classname SendMessageController
 * @Description TODO
 * @Date 2021/12/8 19:35
 * @Created by 86150
 */

import com.example.backend.entitiy.UserIdentify;
import com.example.backend.service.Implement.SendMessageService;
import com.example.backend.service.Implement.UserIdentifyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ program: 后端test
 * @ description: 发送验证码邮件
 * @ author: YXJ
 * @ date: 2021-12-08 19:35:45
 */
@Api("发送验证码")
@RestController
@RequestMapping("identifying")
public class SendMessageController {
    @Autowired
    private UserIdentifyServiceImpl userIdentifyService;

    @Autowired
    private SendMessageService sendMessageService;

    @ApiOperation("发送验证码到用户邮箱 , 需要给出验证邮件的标题")
    @RequestMapping(value = "sendIdentifyMessage" , method = RequestMethod.GET)
    public String sendIdentifyMessage(@RequestParam("email") String tarEmail,
                                      @RequestParam("emailTitle") String title)
    {
        if(tarEmail == null || !tarEmail.endsWith(".com"))
            return "邮箱格式非法";

        sendMessageService.sendIdentifyCodeMailTo(tarEmail , title);

        return "发送验证邮件成功";
    }

    @ApiOperation("检测验证码是否正确")
    @RequestMapping(value = "check",method = RequestMethod.PUT)
    public boolean checkIdentifyMessage(@RequestBody UserIdentify userIdentify)
    {
        if(userIdentify.getEmail() == null)
            return false;
        UserIdentify checkUI = userIdentifyService.findUserIdentify(userIdentify.getEmail());
        return checkUI.equals(userIdentify);
    }
}
