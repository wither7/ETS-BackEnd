package com.example.backend.service.Interface;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * @Classname QiniuService
 * @Description TODO
 * @Date 2021/11/30 16:05
 * @Created by 86150
 */
public interface QiniuService {
    //上传文件到七牛云
    Response uploadFile(File file) throws QiniuException;
    //输入流方式上传文件到七牛云
    Response uploadFile(InputStream inputStream, String fileName) throws QiniuException;
    //在七牛云服务器中删除文件
    String delete(String key) throws QiniuException;
    //获取文件的下载url
    public String getPublicFile(String fileKey) throws Exception;
}
