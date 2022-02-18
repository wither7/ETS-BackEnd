package com.example.helloworld.entitiy.JoinPK;/**
 * @Classname TakeClassPK
 * @Description TODO
 * @Date 2021/11/28 15:45
 * @Created by 86150
 */

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @ program: 后端test
 * @ description: ；联合主键类
 * @ author: YXJ
 * @ date: 2021-11-28 15:45:15
 */
//@Embeddable
@Data
@ApiModel
public class TakeClassPK implements Serializable {

    private static final long serialVersionUID = 219360372861451354L;//？
//    @Column(name = "student_id")
    private Integer studentId;

//    @Column(name = "class_id")
    private Integer classId;

    public TakeClassPK() {
    }

    public TakeClassPK(Integer studentId, Integer classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


 /*
     *
       * @idClass
        使复合主键类成为非嵌入类,使用 @IdClass 批注为实体指定一个复合主键类（通常由两个或更多基元类型或 JDK 对象类型组成）。
        * 从原有数据库映射时（此时数据库键由多列组成），通常将出现复合主键。

        复合主键类具有下列特征：

        它是一个普通的旧式 Java 对象 (POJO) 类。

        它必须为 public，并且必须有一个 public 无参数构造函数。

        如果使用基于属性的访问，则主键类的属性必须为 public 或 protected。

        它必须是可序列化的。

        它必须定义 equals 和 hashCode 方法。

        这些方法的值相等性的语义必须与键映射到的数据库类型的数据库相等性一致。

        它的字段或属性的类型和名称必须与使用 @Id 进行批注的实体主键字段或属性的类型和名称相对应
     */