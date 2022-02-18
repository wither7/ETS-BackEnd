package com.example.helloworld.dao;

import com.example.helloworld.entitiy.MyClass;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname ClassRepository
 * @Description TODO
 * @Date 2021/11/28 9:59
 * @Created by 86150
 */
public interface ClassRepository extends JpaRepository<MyClass, Integer> , JpaSpecificationExecutor<MyClass> {

}
