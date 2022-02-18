package com.example.helloworld.service.Implement;
/**
 * @Classname CourseServiceImpl
 * @Description TODO
 * @Date 2021/11/27 18:32
 * @Created by 86150
 */

import com.example.helloworld.dao.CourseRepository;
import com.example.helloworld.entitiy.Course;
import com.example.helloworld.entitiy.Notice;
import com.example.helloworld.service.Interface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: 后端test
 * @ description: 课程
 * @ author: YXJ
 * @ date: 2021-11-27 18:32:12
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseDao;

    @Override
    public void addCourse(Course course) {
        courseDao.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseDao.save(course);
    }

    @Override
    public void deleteCourse(Integer course_id) {
        courseDao.deleteById(course_id);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseDao.findAll();
    }

    @Override
    public Course findCourseById(Integer course_id) {
        return courseDao.findById(course_id).orElse(new Course());
    }

    @Override
    public boolean existCourse(Integer course_id) {
        return courseDao.existsById(course_id);
    }

    @Override
    public List<Course> findUserCourses(Integer uid)
    {
        Specification<Course> spec = new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //1.获取比较属性
                Path<Course> teacher_id = root.get("resTeacherId");
                /*
                 * 2.构造查询条件
                 *    Predicate equal(Expression<?> var1, Expression<?> var2);
                 * var1 : 需要比较的属性(path)
                 * var2 ： 需要比较的值
                 */
                //借助CriteriaBuilder cb实现查询条件的构造
                return criteriaBuilder.equal(teacher_id, uid);
            }


        };
        return courseDao.findAll(spec);

    }
}
