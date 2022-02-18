package com.example.backend.service.Implement;/**
 * @Classname TakeClassServiceImpl
 * @Description TODO
 * @Date 2021/11/28 17:07
 * @Created by 86150
 */

import com.example.backend.dao.TakeClassRepository;
import com.example.backend.entitiy.JoinPK.TakeClassPK;
import com.example.backend.entitiy.TakeClass;
import com.example.backend.service.Interface.TakeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-28 17:07:46
 */
@Service
public class TakeClassServiceImpl implements TakeClassService {
    @Autowired
    private TakeClassRepository takeClassDao;

    public TakeClassServiceImpl() {
        super();
    }

    @Override
    public void addTakeClass(TakeClass takeClass) {
        takeClassDao.save(takeClass);
    }

    @Override
    public void updateTakeClass(TakeClass takeClass) {
        takeClassDao.save(takeClass);
    }

    @Override
    public void deleteTakeClass(TakeClassPK takeClassPK) {
//        TakeClass takeClass =  takeClassDao.findById(takeClassPK).orElse(null);
//        if(takeClass != null)
//        {
//            takeClass.setState(TakeClassState.DELETED.getValue());
//            takeClassDao.save(takeClass);
//        }
        takeClassDao.deleteById(takeClassPK);
    }

    @Override
    public List<TakeClass> findAllTakeClass() {
        return takeClassDao.findAll();
    }

    @Override
    public List<TakeClass> findAllTakeClassByClassId(Integer classId) {
        Specification<TakeClass> spec = new Specification<TakeClass>() {
            //实现Specification接口
            @Override//重写toPredicate方法
            public Predicate toPredicate(Root<TakeClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较属性
                Path<Object> classIdPath = root.get("classId");
                /*
                 * 2.构造查询条件
                 *    Predicate equal(Expression<?> var1, Expression<?> var2);
                 * var1 : 需要比较的属性(path)
                 * var2 ： 需要比较的值
                 */
                //借助CriteriaBuilder cb实现查询条件的构造
                return cb.equal(classIdPath, classId);
            }
        };
        return takeClassDao.findAll(spec);
    }

    @Override
    public List<TakeClass> findAllTakeClassByStudentId(Integer studentId) {
        /*
         * TODO：增加classname 多表联合查询
         */
        Specification<TakeClass> spec = new Specification<TakeClass>() {
            //实现Specification接口
            @Override//重写toPredicate方法
            public Predicate toPredicate(Root<TakeClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较属性
                Path<Object> studentIdPath = root.get("studentId");
                /*
                 * 2.构造查询条件
                 *    Predicate equal(Expression<?> var1, Expression<?> var2);
                 * var1 : 需要比较的属性(path)
                 * var2 ： 需要比较的值
                 */
                //借助CriteriaBuilder cb实现查询条件的构造
                return cb.equal(studentIdPath, studentId);
            }
        };
        return takeClassDao.findAll(spec);
    }

    @Override
    public TakeClass findTakeClassById(TakeClassPK takeClassPK) {
        return takeClassDao.findById(takeClassPK).orElse(null);
    }

    @Override
    public boolean existTakeClass(TakeClassPK takeClassPK) {
        return takeClassDao.existsById(takeClassPK);
    }


}
