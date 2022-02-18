package com.example.backend.entitiy;/**
 * @Classname AssistantInClass
 * @Description TODO
 * @Date 2021/12/6 22:04
 * @Created by 86150
 */

import com.example.backend.entitiy.JoinPK.AssistantInClassPK;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ program: 后端test
 * @ description: 助教
 * @ author: YXJ
 * @ date: 2021-12-06 22:04:22
 */
@ApiModel("助教 - 班级 映射关系实体类")
@Getter
@Setter
@ToString
@Entity
@Table(name = "assistant_in_class")//表名
@IdClass(AssistantInClassPK.class)
public class AssistantInClass {
    @Id
    @Column(name = "assistant_id" , nullable = false)
    private Integer assistantId;//该属性名必须和PK类中属性名完全一致

    @Id
    @Column(name = "class_id" , nullable = false)
    private Integer classId;


    @ManyToOne(targetEntity = User.class ,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "assistant_id", referencedColumnName = "uid" , insertable = false , updatable = false)
    private User assistant;

    @ManyToOne(targetEntity =MyClass.class,  cascade = CascadeType.PERSIST)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", insertable = false, updatable = false)
    private MyClass myClass;

    public AssistantInClass() {
    }

}
