package com.example.helloworld.entitiy;/**
 * @Classname TakeClass
 * @Description TODO
 * @Date 2021/11/28 15:37
 * @Created by 86150
 */

import com.example.helloworld.entitiy.JoinPK.TakeClassPK;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ program: 后端test
 * @ description: 学生班级多对多关系中间表
 * @ author: YXJ
 * @ date: 2021-11-28 15:37:36
 */
@ApiModel
@Getter
@Setter
@ToString
@Entity
@Table(name = "take_class")//表名
@IdClass(TakeClassPK.class)
public class TakeClass implements Serializable {
    private static final long serialVersionUID = 3671504291813299190L;
//    @EmbeddedId//联合主键声明
//    @AttributeOverrides({
//            //name = 联合主键类成员名 , column = @Column(name = 数据库字段名))
//            @AttributeOverride(name = "student_id" , column = @Column(name = "student_id")),
//            @AttributeOverride(name = "class_id" , column = @Column(name = "class_id"))
//    })
//    private TakeClassPK takeClassPK = new TakeClassPK();

    //联合主键的另一种写法
    @Id
    @Column(name = "class_id")
    private Integer classId;//该属性名必须和PK类中属性名完全一致

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "attendance")
    private Integer attendance;

    @Column(name = "attendance_grade")
    private Double attendanceGrade;

    @Column(name = "experiment_grade")
    private Double experimentGrade;

    @Column(name = "practice_grade")
    private Double practiceGrade;

    @Column(name = "total_grade")
    private Double totalGrade;

    @Column(name = "last_attendant_date")
    private Date lastAttendantDate;

    @Column(name = "state")
    private Integer state;


    @ManyToOne(targetEntity = User.class ,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", referencedColumnName = "uid" , insertable = false , updatable = false)
    private User student;

    @ManyToOne(targetEntity =MyClass.class,  cascade = CascadeType.PERSIST)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", insertable = false, updatable = false)
    private MyClass myclass;



    public TakeClass() {
    }

    public TakeClass(Integer classId, Integer studentId, Integer attendance, Double attendanceGrade, Double experimentGrade,
                     Double practiceGrade, Double totalGrade, Date lastAttendantDate, Integer state, User student, MyClass myclass) {
        this.classId = classId;
        this.studentId = studentId;
        this.attendance = attendance;
        this.attendanceGrade = attendanceGrade;
        this.experimentGrade = experimentGrade;
        this.practiceGrade = practiceGrade;
        this.totalGrade = totalGrade;
        this.lastAttendantDate = lastAttendantDate;
        this.state = state;
        this.student = student;
        this.myclass = myclass;
    }
}
