package com.example.helloworld.dao;/**
 * @Classname UserRepository
 * @Description TODO
 * @Date 2021/11/15 20:54
 * @Created by 86150
 */

import com.example.helloworld.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-15 20:54:28
 */

/*
 *   继承两个接口
 *   JpaRepository<User, Integer > ： 封装了基本CURD
 *   JpaSpecificationExecutor<User> ： 封装了复杂查询
 */
public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    /*
     * jpql查询方式
     * 使用 @Query 注解  注入查询语句
     * value中写查询语句
     * nativeQuery : 是否使用原生sql语句查询
     *
     * 使用@Modifying 注解 标志该语句是更新操作
     */

    @Query(value = "select count(*) from user where user.email = :email" , nativeQuery = true)
//    @Query(value = "from User where email = ? " )
    public Integer userExist(@Param("email") String email);


    @Query(value = "select * from user where user.email = :email" , nativeQuery = true)
    public List<User> findByEmail(@Param("email") String email);

    @Query(value = "select count(*) from user where user.email = :email and user.password = :password" , nativeQuery = true)
    public Integer userLoginCheck(@Param("email") String email , @Param("password") String password);



}
