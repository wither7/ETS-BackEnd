package com.example.helloworld.config;/**
 * @Classname QiniuConfig
 * @Description TODO
 * @Date 2021/11/30 16:22
 * @Created by 86150
 */

import lombok.Getter;
import lombok.Setter;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-30 16:22:17
 */

public class QiniuConfig {
    //旧配置
//    private String AK = "1b-drkEL9Vrzl-z8vMv8loT1b7xP_igdwzM3aMXK";//ACCESS_KEY
//    private String SK = "2jn0jrQR3t7S_UxDXZ6rxXnBKpea0-GMXhIdwPEv";//SECRET_KEY
//    private String Bucket = "ets-file-database";//七牛云空间名称
//    private String Domain = "r3dk6q8tg.hn-bkt.clouddn.com";//七牛云域名

    private String AK = "WbSYuzTj7bD7eZ1oY3Um9T1B2vF4UKePc7WkHVC8";//ACCESS_KEY
    private String SK = "m8f75nldJ6qNMGAMYUUVG5uy3E6m8c-ZHjxvwZBT";//SECRET_KEY
    private String Bucket = "ets-file-data";//七牛云空间名称
    private String Domain = "r4au73ulm.hn-bkt.clouddn.com";//七牛云域名

    public String getAK() {
        return AK;
    }

    public void setAK(String AK) {
        this.AK = AK;
    }

    public String getSK() {
        return SK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    public String getBucket() {
        return Bucket;
    }

    public void setBucket(String bucket) {
        Bucket = bucket;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }
}
