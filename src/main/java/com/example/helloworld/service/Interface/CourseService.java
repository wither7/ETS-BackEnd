package com.example.helloworld.service.Interface;

import com.example.helloworld.entitiy.Course;
import com.example.helloworld.entitiy.Notice;

import java.util.List;

/**
 * @Classname CourseService
 * @Description TODO
 * @Date 2021/11/27 18:06
 * @Created by 86150
 */
public interface CourseService {
    // 添加课程
    public void addCourse(Course course);
    // 更新课程
    public void updateCourse(Course course);
    // 根据id删除课程
    public void deleteCourse(Integer course_id);
    // 查找所有课程
    public List<Course> findAllCourse();
    // 根据课程id查询课程
    public Course findCourseById(Integer course_id);
    // 根据课程id判断课程是否存在
    public boolean existCourse(Integer course_id);
    //查找责任教师的所有课程
    public List<Course> findUserCourses(Integer uid);
}
