package com.example.helloworld.service.Implement;/**
 * @Classname QuestionServiceImpl
 * @Description TODO
 * @Date 2021/12/31 13:31
 * @Created by 86150
 */

import com.example.helloworld.dao.QuestionRepository;
import com.example.helloworld.entitiy.Notice;
import com.example.helloworld.entitiy.Question;
import com.example.helloworld.service.Interface.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-31 13:31:04
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionDao;

    @Override
    public void addQuestion(Question question) {
        questionDao.save(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.save(question);
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    @Override
    public boolean existQuestion(Integer id) {
        return questionDao.existsById(id);
    }

    @Override
    public Question findQuestion(Integer id) {
        return questionDao.findById(id).orElse(null);
    }
}
