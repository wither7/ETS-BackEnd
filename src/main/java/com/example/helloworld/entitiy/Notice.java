package com.example.helloworld.entitiy;/**
 * @Classname Notice
 * @Description TODO
 * @Date 2021/11/13 20:39
 * @Created by 86150
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @ program: Helloworld
 * @ description: 公告板中的信息
 * @ author: YXJ
 * @ date: 2021-11-13 20:39:22
 */
//@Data//可能会导致严重的内存消耗(?
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "notice")//表名
public class Notice {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "notice_id")
    private Integer notice_id;

    //@Column中字段名和数据库中属性字段必须严格一致
    //由于publisher_id字段在注解中使用了两次 因此必须设置为只读
    @Column(name = "publisher_id" , insertable = false,updatable = false)
    private Integer publisher_id;

//    @Column(name = "class_id" , insertable = false , updatable = false)
//    private Integer classId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private  String content;

    @Column(name = "is_topping")
    private boolean is_topping;

    @Column(name = "release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseTime;
/*
 * 需要使用Sort排序的字段  不能有下划线！！！（如release_time）
 */
    //状态  1-正常 0-删除
    @Column(name = "state")
    private Integer state ;

    //配置多对一关系  实体类间包含关系   对应数据库中外键联系
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "publisher_id" , referencedColumnName = "uid")
    private User publisher;

//    @ManyToOne(targetEntity = MyClass.class)
//    @JoinColumn(name = "class_id" , referencedColumnName = "class_id")
//    private MyClass myClass;

    public Notice() {
        this.notice_id = 0;//为0时自增
//        this.publisher_id = 0;
        this.title = null;
        this.content = null;
        this.is_topping = false;
        this.releaseTime = new Date();
        this.state = 1;
    }
//    public Notice(Integer uid, Integer publisher_id, String title,  boolean is_topping, Date release_time) {
//        this.id = uid;
//        this.publisher_id = publisher_id;
//        this.title = title;
////        this.content = content;
//        this.is_topping = is_topping;
//        this.release_time = release_time;
//    }
//
//    public Notice(Integer uid, Integer publisher_id, String title, String content, boolean is_topping) {
//        this.id = uid;
//        this.publisher_id = publisher_id;
//        this.title = title;
//        this.content = content;
//        this.is_topping = is_topping;
//        this.release_time = new Date();
//    }
//
//    public Notice(Integer uid, Integer publisher_id, String title, String content, boolean is_topping, Date release_time) {
//        this.id = uid;
//        this.publisher_id = publisher_id;
//        this.title = title;
//        this.content = content;
//        this.is_topping = is_topping;
//        this.release_time = release_time;
//    }

}
