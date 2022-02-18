package com.example.backend.service.Implement;/**
 * @Classname ClassServiceImpl
 * @Description TODO
 * @Date 2021/11/28 10:09
 * @Created by 86150
 */

import com.example.backend.dao.ClassRepository;
import com.example.backend.entitiy.MyClass;
import com.example.backend.service.Interface.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-28 10:09:38
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classDao;

    public ClassServiceImpl() {
        super();
    }

    @Override
    public void addMyClass(MyClass myClass) {
        classDao.save(myClass);
    }

    @Override
    public void updateMyClass(MyClass myClass) {
        classDao.save(myClass);
    }

    @Override
    public void deleteMyClass(Integer class_id) {
        classDao.deleteById(class_id);

    }

    @Override
    public List<MyClass> findAllMyClass() {
        return classDao.findAll();
    }

    @Override
    public List<MyClass> findAllMyClassByCourseId(Integer courseId) {
        Specification<MyClass> spec = new Specification<MyClass>() {
            @Override
            public Predicate toPredicate(Root<MyClass> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("courseId");
                return criteriaBuilder.equal(path , courseId);

            }
        };
        return classDao.findAll(spec);
    }

    @Override
    public List<MyClass> findAllMyClassByTeacherId(Integer teacherId) {
        Specification<MyClass> spec = new Specification<MyClass>() {
            @Override
            public Predicate toPredicate(Root<MyClass> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("teacherId");
                return criteriaBuilder.equal(path , teacherId);

            }
        };
        return classDao.findAll(spec);
    }

    @Override
    public MyClass findMyClassById(Integer class_id) {
        return classDao.findById(class_id).orElse(new MyClass());
    }

    @Override
    public boolean existMyClass(Integer class_id) {
        return classDao.existsById(class_id);
    }


}
