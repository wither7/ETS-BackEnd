package com.example.backend.entitiy;/**
 * @Classname UserIdentify
 * @Description TODO
 * @Date 2021/12/8 21:07
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ program: 后端test
 * @ description: 用户验证信息实体类
 * @ author: YXJ
 * @ date: 2021-12-08 21:07:08
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_identify")
public class UserIdentify {
    @Id//主码
    @Column(name = "email")
    private String email;//实体类中成员字段和数据库中属性字段必须严格一致

    @Column(name = "code")
    private String code;

    public UserIdentify() {
    }

    public UserIdentify(String email, String code) {
        this.email = email;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentify that = (UserIdentify) o;
        return Objects.equals(email, that.email) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, code);
    }
}
