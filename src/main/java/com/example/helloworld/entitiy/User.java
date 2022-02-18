package com.example.helloworld.entitiy;/**
 * @Classname User
 * @Description TODO
 * @Date 2021/11/15 20:39
 * @Created by 86150
 */

import com.example.helloworld.enums.UserRole;
import com.example.helloworld.enums.UserState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description: 用户实体类
 * @ author: YXJ
 * @ date: 2021-11-15 20:39:58
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")//表名
public class User {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "uid")
    private Integer uid;//实体类中成员字段和数据库中属性字段必须严格一致

    @Column(name = "email" ,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private  String userName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "role")
    private Integer role;

    @Column(name = "user_state")
    private Integer userState;

    public User() {
        this.uid=0;
        this.userState = UserState.DEFAULT.getValue();
        this.role = UserRole.DEFAULT.getValue();
    }

    public User(Integer uid, String email, String password,
                String userName, String gender, String mobile, Integer role, Integer state) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.gender = gender;
        this.mobile = mobile;
        this.role = role;
        this.userState = state;
    }
}
