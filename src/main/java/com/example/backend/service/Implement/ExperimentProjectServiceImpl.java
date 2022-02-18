package com.example.backend.service.Implement;/**
 * @Classname ExperimentProjectServiceImpl
 * @Description TODO
 * @Date 2021/12/3 18:11
 * @Created by 86150
 */

import com.example.backend.dao.ExperimentProjectRepository;
import com.example.backend.entitiy.ExperimentProject;
import com.example.backend.service.Interface.ExperimentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-03 18:11:49
 */
@Service
public class ExperimentProjectServiceImpl implements ExperimentProjectService {
    public ExperimentProjectServiceImpl() {
        super();
    }
    @Autowired
    private ExperimentProjectRepository experimentProjectDao;

    @Override
    public void addExperimentProject(ExperimentProject experimentProject) {
        experimentProjectDao.save(experimentProject);
    }

    public ExperimentProject addAndFlushExperimentProject(ExperimentProject experimentProject) {
        return experimentProjectDao.saveAndFlush(experimentProject);
    }

    @Override
    public void updateExperimentProject(ExperimentProject experimentProject) {
        experimentProjectDao.save(experimentProject);
    }

    @Override
    public void deleteExperimentProject(Integer expId) {
        experimentProjectDao.deleteById(expId);
        //把url对应的文件也删了

    }

    @Override
    public List<ExperimentProject> findAllExperimentProject() {
        return experimentProjectDao.findAll();
    }

    @Override
    public List<ExperimentProject> findAllExperimentProjectByClassId(Integer classId) {
        Specification<ExperimentProject> spec = new Specification<ExperimentProject>() {
            @Override
            public Predicate toPredicate(Root<ExperimentProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("classId");
                return criteriaBuilder.equal(path, classId);
            }
        };
        return experimentProjectDao.findAll(spec);
    }

    @Override
    public ExperimentProject findExperimentProjectById(Integer expId) {
        return experimentProjectDao.findById(expId).orElse(null);
    }

    @Override
    public boolean existExperimentProject(Integer expId) {
        return experimentProjectDao.existsById(expId);
    }
}
