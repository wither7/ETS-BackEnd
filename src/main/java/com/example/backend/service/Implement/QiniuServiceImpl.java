package com.example.backend.service.Implement;/**
 * @Classname QiniuServiceImpl
 * @Description TODO
 * @Date 2021/11/30 16:16
 * @Created by 86150
 */

import com.example.backend.config.QiniuConfig;
import com.example.backend.service.Interface.QiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-30 16:16:38
 */
//@Slf4j
@Service
public class QiniuServiceImpl implements QiniuService {
    static QiniuConfig qiniuConfig = new QiniuConfig();

    // 要上传的空间名称
    private static final String BUCKETNAME = qiniuConfig.getBucket();

    // 密钥
    private static final Auth auth = Auth.create(qiniuConfig.getAK(), qiniuConfig.getSK());

    // 外链默认域名
    private static final String DOMAIN = qiniuConfig.getDomain();

    private StringMap putPolicy;

    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;

    public QiniuServiceImpl() {
        super();
    }

    public String getUploadToken() {
        return auth.uploadToken(BUCKETNAME, null, 3600, putPolicy);
    }

    @Override
    public Response uploadFile(File file) throws QiniuException {

        try {
            // 调用put方法上传
            String token = auth.uploadToken(BUCKETNAME);
            if(token == null) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            Response res = uploadManager.put(file, null, token);//File key token
            // 打印返回的信息
            System.out.println(res.bodyString());
//            if (res.isOK()) {
//                Ret ret = res.jsonToObject(Ret.class);
//                //如果不需要对图片进行样式处理，则使用以下方式即可
                return res;
//                return DOMAIN + ret.key + "?" + style;
//            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }

    @Override
    public Response uploadFile(InputStream inputStream, String fileName) throws QiniuException {
        try {
            // 调用put方法上传
            String token = auth.uploadToken(BUCKETNAME);
            if(token == null) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            if(inputStream == null) {
                System.out.println("未获取到inputStream，请重试！");
                return null;
            }
//            System.out.println(token);
//            System.out.println(inputStream.toString());
//            Response res = uploadManager.put(file, null, token);//File key token
            Response response = this.uploadManager.put(inputStream, fileName, token, null, null);
            // 打印返回的信息
            System.out.println(response.bodyString());
//            if (res.isOK()) {
//                Ret ret = res.jsonToObject(Ret.class);
//                //如果不需要对图片进行样式处理，则使用以下方式即可
            return response;
//                return DOMAIN + ret.key + "?" + style;
//            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;


        //重复上传3次试试
//        int retry = 0;
//        while (response.needRetry() && retry < 3) {
//            response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
//            retry++;
//        }
//        return response;

    }


    @Override
    /*
     *
     * @param key  要删除的文件名(全路径)
     * @return java.lang.String
     * @author YXJ
     * @description
     * @date 2021/12/5 17:10
     */
    public String delete(String key) throws QiniuException {
        Response response = bucketManager.delete(BUCKETNAME, key);
        int retry = 0;
        //判断是否需要 重试 删除 且重试次数为3
        while (response.needRetry() && retry++ < 3) {
//            log.info("当前操作需要进行重试，目前重试第{}次", retry + 1);
            response = bucketManager.delete(BUCKETNAME, key);
        }
        return response.statusCode == 200 ? "删除成功!" : "删除失败!";

    }

    /**
     * 获取私有空间文件
     * @param fileKey  要下载的文件名
     * @return String  下载地址
     */
    @Override
    public String getPublicFile(String fileKey) throws Exception{
//        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
//        String baseurl = "https://" + DOMAIN + "/" + fileKey;
//        String url = auth.privateDownloadUrl(baseurl);
//        log.info("下载地址：{}", url);
//        return url;
        System.out.println("FileKey:" + fileKey);
        String filename = fileKey.substring(fileKey.lastIndexOf('/')+1);
        System.out.println("FileName:"+filename);
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", "http://" + DOMAIN, encodedFileName ) ;
        publicUrl += String.format("?attname=%s",URLEncoder.encode(filename, "utf-8"));
        System.out.println("publicURL:" + publicUrl );
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);


    }
    
    public String getBasePublicFile(String fileKey) throws Exception{
//        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
//        String baseurl = "https://" + DOMAIN + "/" + fileKey;
//        String url = auth.privateDownloadUrl(baseurl);
//        log.info("下载地址：{}", url);
//        return url;
        System.out.println("FileKey:" + fileKey);
        String filename = fileKey.substring(fileKey.lastIndexOf('/')+1);
        System.out.println("FileName:"+filename);
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s",  DOMAIN, fileKey ) ;
//        publicUrl += String.format("?attname=%s",URLEncoder.encode(filename, "utf-8"));
        System.out.println("publicURL:" + publicUrl );
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
//        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
        return publicUrl;

    }

}
