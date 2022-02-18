package com.example.helloworld.service.Interface;

import com.example.helloworld.entitiy.MyClass;

import java.util.List;

/**
 * @Classname ClassService
 * @Description TODO
 * @Date 2021/11/28 10:03
 * @Created by 86150
 */
public interface ClassService {
    //添加班级
    public void addMyClass(MyClass myClass);
    //更新班级
    public void updateMyClass(MyClass myClass);
    //根据班级id删除班级
    public void deleteMyClass(Integer class_id);
    //查找所有班级
    public List<MyClass> findAllMyClass();
    //查找课程下的所有班级
    public List<MyClass> findAllMyClassByCourseId(Integer courseId);
    //查找教师的所有班级
    public List<MyClass> findAllMyClassByTeacherId(Integer teacherId);
    //根据班级id查询班级
    public MyClass findMyClassById(Integer class_id);
    //根据班级id判读班级是否存在
    public boolean existMyClass(Integer class_id);
}
