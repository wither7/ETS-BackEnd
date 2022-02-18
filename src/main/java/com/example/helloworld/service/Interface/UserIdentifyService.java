package com.example.helloworld.service.Interface;

import com.example.helloworld.entitiy.Notice;
import com.example.helloworld.entitiy.UserIdentify;

import java.util.List;

/**
 * @Classname UserIdentifyService
 * @Description TODO
 * @Date 2021/12/8 21:13
 * @Created by 86150
 */
public interface UserIdentifyService {
    //添加用户验证信息
    public void addUserIdentify(UserIdentify userIdentify);
    //删除用户验证信息
    public void deleteUserIdentify(String email);
    //判断用户验证信息是否存在
    public boolean existUserIdentify(String email);
    //根据用户邮箱查询用户验证信息
    public UserIdentify findUserIdentify(String email);
    //查询所有用户验证信息
    public List<UserIdentify> findAllUserIdentify();
}
