package com.example.backend.dao;

import com.example.backend.entitiy.AssistantInClass;
import com.example.backend.entitiy.JoinPK.AssistantInClassPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname AssistantInClassRepository
 * @Description TODO
 * @Date 2021/12/6 22:23
 * @Created by 86150
 */
public interface AssistantInClassRepository extends JpaRepository<AssistantInClass , AssistantInClassPK>
                                                , JpaSpecificationExecutor<AssistantInClass>
{
}
