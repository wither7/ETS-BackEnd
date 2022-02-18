package com.example.backend.controller;/**
 * @Classname DocumentStorageController
 * @Description TODO
 * @Date 2021/12/18 16:03
 * @Created by 86150
 */

import com.example.backend.entitiy.Document;
import com.example.backend.service.Implement.ClassServiceImpl;
import com.example.backend.service.Implement.DocumentServiceImpl;
import com.example.backend.service.Implement.QiniuServiceImpl;
import com.example.backend.service.Implement.UserServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:  资料库后端接口
 * @ author: YXJ
 * @ date: 2021-12-18 16:03:55
 */
@Api("资料库管理后端接口")
@RestController
@RequestMapping("/document")
public class DocumentStorageController {
    @Autowired
    private DocumentServiceImpl documentService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private QiniuServiceImpl qiniuService;


    @ApiOperation("根据id获取文件信息")
    @RequestMapping(value = "/getDocumentById",method = RequestMethod.GET)
    public Document getDocumentById(@RequestParam("fileId") Integer fileId)
    {
        return documentService.findDocument(fileId);
    }

    @ApiOperation("获取课程下某种类的所有文件信息")
    @RequestMapping(value = "/getAllDocumentByClassIdAndCategory",method = RequestMethod.GET)
    public List<Document> getAllDocumentByClassIdAndCategory(@RequestParam("classId") Integer classId,
                                                             @RequestParam("category") String category)
    {
        if(!classService.existMyClass(classId)){
            System.out.println("不存在课程id");
            return new ArrayList<>();
        }
        return documentService.findAllDocumentByClassIdAndCategory(classId,category);
    }
/*
 * Mark:
 * 同时使用@RequestBody 和 MultipartFile 会出错
 * 解决方法是去掉  @RequestBody
 */
    @ResponseBody
    @RequestMapping(value = "/uploadDocument" , method = RequestMethod.POST)
    @ApiOperation("上传文件到资料库")
    public String uploadFile(@RequestParam("classId") Integer classId,
                             @RequestParam("uploaderId") Integer uploaderId,
                             @RequestParam("category") String category,
                             @RequestParam  MultipartFile file )
    {

        //判断文件是否为空
        if (file.isEmpty()) {
            return "文件为空";
        }

        if(!classService.existMyClass(classId)){
            return "班级id不存在";
        }
        if(!userService.existUser(uploaderId)){
            return "上传者id不存在";
        }
        Document document = new Document();
        document.setClassId(classId);
        document.setUploaderId(uploaderId);
        document.setCategory(category);
        document.setBelongClass(classService.findMyClassById(classId));
        document.setUploader(userService.findUser(uploaderId));

        //设置文件名路径名字：classId/category/uploaderId/文件名字
        String fileURL = document.getClassId().toString() + "/"
                        + document.getCategory() + "/"
                        + document.getUploaderId().toString() + "/"
                        + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();


        System.out.println("Filename : " + fileURL);

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return "转换为InputStream失败";
        }
        try {
            Response response = qiniuService.uploadFile(inputStream,fileURL);
            if (response.isOK())
            {
                document.setFilename(fileName);
                document.setFileURL(fileURL);

                documentService.addDocument(document);
                return "上传文件资料成功";
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

    @ApiOperation("下载资料库文件")
    @RequestMapping(value = "/downloadDocument" , method = RequestMethod.GET)
    public String downloadReportTemplate(@RequestParam @ApiParam("文件id")Integer fileId)
    {
        if(!documentService.existDocument(fileId))
            return "文件id不存在";
        Document document = documentService.findDocument(fileId);
        if(document.getFileURL() == null){
            return "文件url不存在";
        }
        try {
            return qiniuService.getPublicFile(document.getFileURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @ApiOperation("根据id删除资料库文件")
    @RequestMapping(value = "/deleteDocumentById" , method = RequestMethod.DELETE)
    public String deleteExperimentProject(@RequestParam("fileId") Integer fileId)
    {
        if(documentService.existDocument(fileId))
        {
            Document document = documentService.findDocument(fileId);
            //在文件服务器中删除
            try {
                qiniuService.delete(document.getFileURL());
            } catch (QiniuException e) {
                e.printStackTrace();
            }
            documentService.deleteDocument(fileId);
            return "删除实验项目成功";
        }
        else
        {
            return "实验项目id不存在";
        }
    }
}
