package com.example.backend.service.Implement;/**
 * @Classname UserIdentifyServiceImpl
 * @Description TODO
 * @Date 2021/12/8 21:16
 * @Created by 86150
 */

import com.example.backend.dao.UserIdentifyRepository;
import com.example.backend.entitiy.UserIdentify;
import com.example.backend.service.Interface.UserIdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-08 21:16:37
 */
@Service
public class UserIdentifyServiceImpl implements UserIdentifyService {
    @Autowired
    private UserIdentifyRepository userIdentifyDao;


    public UserIdentifyServiceImpl() {
        super();
    }

    @Override
    public void addUserIdentify(UserIdentify userIdentify) {
        userIdentifyDao.save(userIdentify);
    }

    @Override
    public void deleteUserIdentify(String email) {
        userIdentifyDao.deleteById(email);
    }

    @Override
    public boolean existUserIdentify(String email) {
        return userIdentifyDao.existsById(email);
    }

    @Override
    public UserIdentify findUserIdentify(String email) {
        return userIdentifyDao.findById(email).orElse(null);
    }

    @Override
    public List<UserIdentify> findAllUserIdentify() {
        return userIdentifyDao.findAll();
    }
}
