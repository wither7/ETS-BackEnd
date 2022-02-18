package com.example.backend.service.Implement;/**
 * @Classname TakeExperimentServiceImpl
 * @Description TODO
 * @Date 2021/12/6 19:50
 * @Created by 86150
 */

import com.example.backend.dao.TakeExperimentRepository;
import com.example.backend.entitiy.ExperimentProject;
import com.example.backend.entitiy.JoinPK.TakeExperimentPK;
import com.example.backend.entitiy.TakeExperiment;
import com.example.backend.service.Interface.TakeExperimentService;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-06 19:50:13
 */
@Service
public class TakeExperimentServiceImpl implements TakeExperimentService {
    @Autowired
    private TakeExperimentRepository takeExperimentDao;
    @Autowired
    private QiniuServiceImpl qiniuService;
    public TakeExperimentServiceImpl() {
        super();
    }

    @Override
    public void addTakeExperiment(TakeExperiment takeExperiment) {
        takeExperimentDao.save(takeExperiment);
    }

    @Override
    public void updateTakeExperiment(TakeExperiment takeExperiment) {
        takeExperimentDao.save(takeExperiment);
    }

    @Override
    public void deleteTakeExperiment(TakeExperimentPK takeExperimentPK) {
        TakeExperiment takeExperiment = takeExperimentDao.findById(takeExperimentPK).orElse(null);
        if(takeExperiment == null)
            return ;
        //同时删除对应的文件
        try {
            qiniuService.delete(takeExperiment.getReportURL());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        takeExperimentDao.deleteById(takeExperimentPK);
    }

    @Override
    public List<TakeExperiment> findAllTakeExperiment() {
        return takeExperimentDao.findAll();
    }

    @Override
    public List<TakeExperiment> findAllTakeExperimentByExpId(Integer expId) {
        Specification<TakeExperiment> spec = new Specification<TakeExperiment>() {
            @Override
            public Predicate toPredicate(Root<TakeExperiment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("experimentId");
                query.where(criteriaBuilder.equal(path , expId));
                query.orderBy(criteriaBuilder.desc(root.get("state").as(Integer.class)),
                        criteriaBuilder.asc(root.get("score").as(Double.class))   );

                return query.getRestriction();
            }
        };
        return takeExperimentDao.findAll(spec);
    }

    @Override
    public List<TakeExperiment> findAllTakeExperimentByStudentId(Integer studentId) {
        Specification<TakeExperiment> spec = new Specification<TakeExperiment>() {
            @Override
            public Predicate toPredicate(Root<TakeExperiment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("studentId");
                query.where(criteriaBuilder.equal(path , studentId));

                return query.getRestriction();
            }
        };
        return takeExperimentDao.findAll(spec);
    }

    @Override
    public TakeExperiment findTakeExperimentById(TakeExperimentPK takeExperimentPK) {
        return takeExperimentDao.findById(takeExperimentPK).orElse(new TakeExperiment());
    }

    @Override
    public boolean existTakeExperiment(TakeExperimentPK takeExperimentPK) {
        return takeExperimentDao.existsById(takeExperimentPK);
    }

    //多表联查
    public List<TakeExperiment> findStudentExpInClass(Integer studentId , Integer classId){
        Specification<TakeExperiment> spec = new Specification<TakeExperiment>() {
            @Override
            public Predicate toPredicate(Root<TakeExperiment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //创建左外连接  Join<左，右>     root.join("副表实体在主表主体中的属性名"，连接方式)
                Join< TakeExperiment , ExperimentProject> join = root.join("experimentProject",JoinType.INNER);
                list.add(criteriaBuilder.equal(root.get("studentId") , studentId));
//                list.add(criteriaBuilder.equal(join.get("studentId") , studentId));
                list.add(criteriaBuilder.equal(join.get("classId") , classId));
                //实验发布时间小于当前时间
                list.add(criteriaBuilder.lessThanOrEqualTo(join.get("releaseTime") , new Date()));

                Predicate[] p = new Predicate[list.size()];
                query.where(criteriaBuilder.and(list.toArray(p)));

                return query.getRestriction();
            }
        };
        return takeExperimentDao.findAll(spec);
    }
}
