package com.example.backend.service.Interface;

import com.example.backend.entitiy.AssistantInClass;
import com.example.backend.entitiy.JoinPK.AssistantInClassPK;

import java.util.List;

/**
 * @Classname AssistantInClassService
 * @Description TODO
 * @Date 2021/12/7 19:00
 * @Created by 86150
 */

public interface AssistantInClassService {
    //添加助教
    public void addAssistant(AssistantInClass assistantInClass);
    //删除助教
    public void deleteAssistant(AssistantInClassPK assistantInClassPK);
    //查询所有助教
    public List<AssistantInClass> findAllAssistantInClass();
    //查询班级下所有助教
    public List<AssistantInClass> findAllAssistantInClassByClassId(Integer classId);
    //根据用户id查询助教
    public List<AssistantInClass> findAllAssistantInClassByAssistantId(Integer assistantId);
    //判断助教关系是否存在
    public boolean existAssistant(AssistantInClassPK assistantInClassPK);
}
