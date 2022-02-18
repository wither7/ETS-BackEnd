package com.example.helloworld.dao;

import com.example.helloworld.entitiy.JoinPK.TakeClassPK;
import com.example.helloworld.entitiy.TakeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname TakeClassRepository
 * @Description TODO
 * @Date 2021/11/28 16:17
 * @Created by 86150
 */
public interface TakeClassRepository extends JpaRepository<TakeClass , TakeClassPK>,
        JpaSpecificationExecutor<TakeClass> {

}
