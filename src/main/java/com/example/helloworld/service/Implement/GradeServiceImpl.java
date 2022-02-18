package com.example.helloworld.service.Implement;/**
 * @Classname GradeServiceImpl
 * @Description TODO
 * @Date 2021/12/27 22:37
 * @Created by 86150
 */

import com.example.helloworld.controller.TakeClassController;
import com.example.helloworld.dto.StudentGrade;
import com.example.helloworld.entitiy.*;
import com.example.helloworld.entitiy.JoinPK.TakeClassPK;
import com.example.helloworld.entitiy.JoinPK.TakePracticePK;
import com.example.helloworld.service.Interface.GradeService;
import com.example.helloworld.service.Interface.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-27 22:37:31
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private TakeExperimentServiceImpl takeExperimentService;
    @Autowired
    private TakeClassServiceImpl takeClassService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PracticeServiceImpl practiceService;
    @Autowired
    private TakePracticeServiceImpl takePracticeService;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Double getExpScore(Integer classId, Integer studentId) {
        List<TakeExperiment> studentExpInClass = takeExperimentService.findStudentExpInClass(studentId, classId);
        if(studentExpInClass.size()<=0)
        {
            return 0.0;
        }
        Double score = 0.0;
        for(TakeExperiment takeExp : studentExpInClass)
        {
            if(takeExp.getScore() != null)
                score += takeExp.getScore();
        }
        return score / studentExpInClass.size();//平均分
    }

    @Override
    public Double getPracticeScore(Integer classId, Integer studentId) {
        /*
         * TODO
         */
        double score = 0.0;
        final List<Practice> allPractice = practiceService.findAllPracticeByClassId(classId);
        int n = allPractice.size();
        if(n <= 0)
            return 0.0;
        for(Practice practice : allPractice)
        {
            TakePracticePK takePracticePK = new TakePracticePK(studentId , practice.getPracticeId());
            if(takePracticeService.existTakePractice(takePracticePK)) {
                TakePractice takePractice = takePracticeService.findTakePractice(takePracticePK);
                double pracScore = takePractice.getGroupScore() == null ? 0.0 : takePractice.getGroupScore();
                score += pracScore;
            }
            else
            {
                System.out.println("error"+practice);
            }
        }
        return score / n;
    }

    @Override
    public Double getAttendanceScore(Integer classId, Integer studentId) {
        TakeClassPK takeClassPK = new TakeClassPK(studentId,classId);
        Integer attendance = 0;
        TakeClass takeClass = takeClassService.findTakeClassById(takeClassPK);
        attendance = takeClass.getAttendance() == null ? 0 : takeClass.getAttendance();
        return (attendance + 0.0 ) / TakeClassController.attendanceMax * 100;
    }

    @Override
    public Double getSumScore(Integer classId, Integer studentId) {
        return null;
    }

    @Override
    public void updateSumGrade(Integer classId, Integer studentId) {
        TakeClassPK takeClassPK = new TakeClassPK(studentId,classId);
        TakeClass takeClass = takeClassService.findTakeClassById(takeClassPK);
        MyClass myClass = classService.findMyClassById(classId);
        Course course = myClass.getCourse();

        double attendanceScore = this.getAttendanceScore(classId , studentId) != null ? this.getAttendanceScore(classId , studentId) : 0.0 ;
        double expScore = this.getExpScore(classId , studentId) != null ? this.getExpScore(classId , studentId) : 0.0;
        double practiceScore = this.getPracticeScore(classId , studentId) != null ? this.getPracticeScore(classId , studentId) : 0.0;

        Double sumScore = attendanceScore * course.getAttendanceWeight() +
                expScore * course.getExperimentWeight() +
                practiceScore * course.getPracticeWeight();
        takeClass.setAttendanceGrade(attendanceScore);
        takeClass.setExperimentGrade(expScore);
        takeClass.setPracticeGrade(practiceScore);
        takeClass.setTotalGrade(sumScore);
        System.out.println("sumScore 更新为 ：" + sumScore);
        takeClassService.updateTakeClass(takeClass);
    }



//    @Override
//    public StudentGrade getStudentGrade(Integer classId, Integer studentId)
//    {
//        StudentGrade studentGrade = new StudentGrade();
//        studentGrade.setClassId(classId);
//
//        studentGrade.setStudentId(studentId);
//        studentGrade.setStudent(userService.findUser(studentId));
//        studentGrade.setAttendanceScore(this.getAttendanceScore(classId,studentId));
//        studentGrade.setExperimentScore(this.getExpScore(classId,studentId));
//        studentGrade.setPracticeScore(this.getPracticeScore(classId,studentId));
//        studentGrade.setTotalScore(this.getSumScore(classId,studentId));
//
//        return null;
//    }
//
//    @Override
//    public List<StudentGrade> getAllGradeInClass(Integer classId) {
//        return null;
//    }

}
