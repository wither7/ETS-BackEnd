package com.example.backend.service.Interface;

import com.example.backend.entitiy.Question;

/**
 * @Classname QuestionService
 * @Description TODO
 * @Date 2021/12/31 12:59
 * @Created by 86150
 */
public interface QuestionService {
    //添加问题
    public void addQuestion(Question question);
    //更新问题
    public void updateQuestion(Question question);
    //根据问题id删除问题
    public void deleteQuestion(Integer id);
    //根据问题id判断是否存在
    public boolean existQuestion(Integer id);
    //根据问题id查询
    public Question findQuestion(Integer id);

}
