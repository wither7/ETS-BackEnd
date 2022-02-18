package com.example.backend.controller;/**
 * @Classname BatchAddUser
 * @Description TODO
 * @Date 2021/12/7 21:14
 * @Created by 86150
 */

import com.example.backend.entitiy.User;
import com.example.backend.service.Implement.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @ program: 后端test
 * @ description: 批量添加用户
 * @ author: YXJ
 * @ date: 2021-12-07 21:14:00
 */
@Api("批量创建用户")
@RestController
@RequestMapping("batchAdd")
public class BatchAddController {
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("批量创建用户")
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String batchAddUser(@RequestParam("userFile") MultipartFile userFile) {
        if (userFile == null)
            return "文件为空";
        String fileName = userFile.getOriginalFilename();
        assert fileName != null;
        if (!fileName.endsWith(".txt"))
            return "上传文件应为.txt格式";

        File file = null;
        try {
            file = File.createTempFile("tmp", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.deleteOnExit();

        BufferedReader reader = null;
        String temp = null;
        try {
            userFile.transferTo(file); //MultipartFile转File
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//解决服务器上乱码
//            reader.readLine();
            while ((temp = reader.readLine()) != null) {
                /*
                 * 期待接受的字符串形式：
                 * email,name,mobile,gender
                 */

                User tmpUser = new User();

                String[] s = temp.split(",");
                if(s.length < 4 )
                {
                    return "文件格式有误";
                }
                tmpUser.setUid(0);
                tmpUser.setPassword("123456");
                tmpUser.setEmail(s[0]);
                tmpUser.setUserName(s[1]);
                tmpUser.setMobile(s[2]);
                tmpUser.setGender(s[3]);

                userService.addUser(tmpUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "批量添加用户成功";
    }

}
