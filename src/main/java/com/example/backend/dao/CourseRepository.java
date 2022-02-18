package com.example.backend.dao;

import com.example.backend.entitiy.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname CourseRepository
 * @Description TODO
 * @Date 2021/11/27 18:05
 * @Created by 86150
 */
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {

}
