package com.example.helloworld.dao;

import com.example.helloworld.entitiy.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname QuestionRepository
 * @Description TODO
 * @Date 2021/12/20 22:13
 * @Created by 86150
 */
public interface QuestionRepository extends JpaRepository<Question,Integer> , JpaSpecificationExecutor<Question> {
}
