package com.example.helloworld.service.Implement;/**
 * @Classname UserNotice
 * @Description TODO
 * @Date 2021/11/15 20:57
 * @Created by 86150
 */

import com.example.helloworld.dao.UserRepository;
import com.example.helloworld.entitiy.User;
import com.example.helloworld.enums.UserState;
import com.example.helloworld.service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-15 20:57:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;

//    private Specification<User> deleteSpecification = new Specification<User>() {
//        @Override
//        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            Path<Object> userStatePath = root.get("userState");
//            return criteriaBuilder.notEqual(userStatePath , UserState.DELETED.getValue());
//        }
//    };

    public void addUser(User user)
    {
        userDao.save(user);
    }

    public void deleteUser(User user)
    {
        userDao.delete(user);
//        user.setUserState(UserState.DELETED.getValue());
//        userDao.save(user);
    }
    public void deleteUser(Integer id)
    {
//        User user = userDao.findById(id).get();
//        user.setUserState(UserState.DELETED.getValue());
//        userDao.save(user);
          userDao.deleteById(id);
    }

    public void updateUser(User user)
    {
        userDao.save(user);
    }

    public boolean existUser(Integer id)
    {
        return userDao.existsById(id);
    }

    public boolean existUser(String email)
    {
        /*
         * 使用example 按例查询
         */

//        User user = new User();
//        user.setEmail(email);
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("email" , ExampleMatcher.GenericPropertyMatchers.exact());
//        Example<User> example = Example.of(user , matcher);
//        return userDao.count(example) > 0 ;

        return userDao.userExist(email) > 0;
    }

    public User findUser(Integer id)
    {
        return userDao.findById(id).orElse(new User());

    }

    public User findUser(String email)
    {
        List<User> users = userDao.findByEmail(email);
        //错误判断
        return users.isEmpty() ? new User() : users.get(0);
    }

    public List<User> findAllUser()
    {
        return userDao.findAll();
    }

    public long countUser()
    {
        return userDao.count();

    }

    public boolean userLogin(String email, String password)
    {
        return userDao.userLoginCheck(email,password) == 1;

    }

    @Override
    public void userActivate(Integer id) {
        User user = userDao.findById(id).get();
        user.setUserState(UserState.ACTIVE.getValue());
    }
}
