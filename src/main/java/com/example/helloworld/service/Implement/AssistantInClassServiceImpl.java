package com.example.helloworld.service.Implement;/**
 * @Classname AssistantInClassServiceImpl
 * @Description TODO
 * @Date 2021/12/7 19:21
 * @Created by 86150
 */

import com.example.helloworld.dao.AssistantInClassRepository;
import com.example.helloworld.entitiy.AssistantInClass;
import com.example.helloworld.entitiy.JoinPK.AssistantInClassPK;
import com.example.helloworld.service.Interface.AssistantInClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.nio.channels.Pipe;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-07 19:21:07
 */
@Service
public class AssistantInClassServiceImpl implements AssistantInClassService {
    @Autowired
    private AssistantInClassRepository assistantDao;
    public AssistantInClassServiceImpl() {
        super();
    }

    @Override
    public void addAssistant(AssistantInClass assistantInClass) {
        assistantDao.save(assistantInClass);
    }

    @Override
    public void deleteAssistant(AssistantInClassPK assistantInClassPK) {
        assistantDao.deleteById(assistantInClassPK);
    }

    @Override
    public List<AssistantInClass> findAllAssistantInClass() {
        return assistantDao.findAll();
    }

    @Override
    public List<AssistantInClass> findAllAssistantInClassByClassId(Integer classId) {
        Specification<AssistantInClass> spec = new Specification<AssistantInClass>() {
            @Override
            public Predicate toPredicate(Root<AssistantInClass> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("classId");
                return criteriaBuilder.equal(path , classId);
            }
        };
        return assistantDao.findAll(spec);
    }

    @Override
    public List<AssistantInClass> findAllAssistantInClassByAssistantId(Integer assistantId) {
        Specification<AssistantInClass> spec = new Specification<AssistantInClass>() {
            @Override
            public Predicate toPredicate(Root<AssistantInClass> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("assistantId");
                return criteriaBuilder.equal(path , assistantId);
            }
        };
        return assistantDao.findAll(spec);
    }


    @Override
    public boolean existAssistant(AssistantInClassPK assistantInClassPK) {
        return assistantDao.existsById(assistantInClassPK);
    }
}
