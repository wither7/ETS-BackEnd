package com.example.helloworld.controller;/**
 * @Classname QiniuTestController
 * @Description TODO
 * @Date 2021/11/30 16:57
 * @Created by 86150
 */

import com.example.helloworld.service.Implement.QiniuServiceImpl;
import com.example.helloworld.service.Interface.QiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-30 16:57:30
 */
//@Api("文件管理测试")
@RestController
//@RequestMapping("/qiniu")
public class QiniuTestController {

    @Autowired
    private QiniuServiceImpl qiNiuService;

    @ApiOperation("上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam ( name ="userId", value = "用户id" ,type = "Integer") ,
            @ApiImplicitParam ( name ="classId", value = "班级id" , type = "Integer")
    })
    @ResponseBody
    @RequestMapping(value = "/uploadFile" , method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file ,@RequestParam Integer classId ,@RequestParam Integer userId )
    {
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getQueryString());
//        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
//        System.out.println(request);
//        System.out.println(params.getParameter("classId"));
//        String classId = request.getParameter("classId");
//        String userId = request.getParameter("userId");

        //判断文件是否为空
        if (file.isEmpty()) {
            return "文件为空";
        }
        //设置文件名路径名字：courseid/userid/文件名字
        String fileName = classId + "/"+ userId + "/"+file.getOriginalFilename();
        System.out.println(fileName);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Response response = qiNiuService.uploadFile(inputStream,fileName);
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
                    return "success";
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

}
