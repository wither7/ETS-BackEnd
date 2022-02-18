package com.example.backend.service.Interface;

import com.example.backend.entitiy.User;

import java.util.List;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2021/11/27 18:23
 * @Created by 86150
 */
public interface UserService {
    //添加用户
    public void addUser(User user);
    //删除用户
    public void deleteUser(User user);
    //根据uid删除用户
    public void deleteUser(Integer id);
    //更新用户
    public void updateUser(User user);
    //根据uid判断用户是否存在
    public boolean existUser(Integer id);
    //根据邮箱判断用户是否存在
    public boolean existUser(String email);
    //根据uid查询用户
    public User findUser(Integer id);
    //根据邮箱查询用户
    public User findUser(String email);
    //查询所有用户
    public List<User> findAllUser();
    //获取用户总数
    public long countUser();
    //判断用户能否登录
    public boolean userLogin(String email, String password);
    //根据uid激活用户
    public void userActivate(Integer id);
}
