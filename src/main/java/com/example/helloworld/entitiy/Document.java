package com.example.helloworld.entitiy;/**
 * @Classname Document
 * @Description TODO
 * @Date 2021/12/18 12:49
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description: 文件资料库实体类
 * @ author: YXJ
 * @ date: 2021-12-18 12:49:35
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "document_storage")
public class Document {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "file_url")
    private String fileURL;

    @Column(name = "category")
    private String category;

    @Column(name = "class_id", updatable = false , insertable = false)
    private Integer classId;

    @Column(name = "uploader_id",updatable = false , insertable = false)
    private Integer uploaderId;


    @ManyToOne(targetEntity = MyClass.class)
    @JoinColumn(name = "class_id" , referencedColumnName = "class_id")
    private MyClass belongClass;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uploader_id" , referencedColumnName = "uid")
    private User Uploader;

    public Document() {
    }
}
