package com.example.helloworld.service.Implement;/**
 * @Classname TakePracticeServiceImpl
 * @Description TODO
 * @Date 2021/12/31 14:39
 * @Created by 86150
 */

import com.example.helloworld.dao.TakePracticeRepository;
import com.example.helloworld.entitiy.ExperimentProject;
import com.example.helloworld.entitiy.JoinPK.TakePracticePK;
import com.example.helloworld.entitiy.TakeExperiment;
import com.example.helloworld.entitiy.TakePractice;
import com.example.helloworld.service.Interface.TakePracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-31 14:39:24
 */
@Service
public class TakePracticeServiceImpl implements TakePracticeService {
    @Autowired
    private TakePracticeRepository takePracticeDao;


    @Override
    public void addTakePractice(TakePractice takePractice) {
        takePracticeDao.save(takePractice);
    }

    @Override
    public void updateTakePractice(TakePractice takePractice) {
        takePracticeDao.save(takePractice);
    }

    @Override
    public void deleteTakePractice(TakePracticePK takePracticePK) {
        takePracticeDao.deleteById(takePracticePK);
    }

    @Override
    public boolean existTakePractice(TakePracticePK takePracticePK) {
        return takePracticeDao.existsById(takePracticePK);
    }

    @Override
    public TakePractice findTakePractice(TakePracticePK takePracticePK) {
        return takePracticeDao.findById(takePracticePK).orElse(null);
    }

    @Override
    public List<TakePractice> findTakePracticeByStudentId(Integer studentId) {
        Specification<TakePractice> spec = new Specification<TakePractice>() {
            @Override
            public Predicate toPredicate(Root<TakePractice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> studentId1 = root.get("studentId");
                return criteriaBuilder.equal(studentId1 , studentId);
            }
        };
        return takePracticeDao.findAll(spec);
    }

    @Override
    public List<TakePractice> findTakePracticeByPracticeId(Integer practiceId) {
        Specification<TakePractice> spec = new Specification<TakePractice>() {
            @Override
            public Predicate toPredicate(Root<TakePractice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> practiceId1 = root.get("practiceId");
                return criteriaBuilder.equal(practiceId1 , practiceId);
            }
        };
        return takePracticeDao.findAll(spec);
    }
    //分组
    public void divGroups(Integer practiceId) {
        /*
         * 1.正常分 i对应的组为i%div
         * 2.n次循环 每次随机一个index与i交换组号
         */
        Random random = new Random();

        List<TakePractice> takePractices = findTakePracticeByPracticeId(practiceId);
        int n = takePractices.size();
        int div = n / 3;
        if(div <= 0 )
            div = 1;
        for (int i = 0; i < n; i++) {

            takePractices.get(i).setGroupId(i % div);

        }
        for(int i=0;i<n;i++)
        {
            int randomIndex = random.nextInt(n);
            int tmp = takePractices.get(i).getGroupId();
            takePractices.get(i).setGroupId(takePractices.get(randomIndex).getGroupId());
            takePractices.get(randomIndex).setGroupId(tmp);
        }
        for (TakePractice takePractice : takePractices) {
            updateTakePractice(takePractice);
        }
    }


    //更新某学生对抗练习的组内成绩
    public void updateGroupScore(TakePracticePK takePracticePK)
    {
        TakePractice takePractice = this.findTakePractice(takePracticePK);

        Specification<TakePractice> spec = new Specification<TakePractice>() {
            @Override
            public Predicate toPredicate(Root<TakePractice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                list.add(criteriaBuilder.equal(root.get("groupId") , takePractice.getGroupId()));
                list.add(criteriaBuilder.equal(root.get("practiceId") , takePracticePK.getPracticeId()));
                Predicate[] p = new Predicate[list.size()];
                query.where(criteriaBuilder.and(list.toArray(p)));
                //成绩降序  时间升序
                query.orderBy(criteriaBuilder.desc(root.get("paperScore").as(Double.class)),
                        criteriaBuilder.asc(root.get("useTime").as(Integer.class)));
                return query.getRestriction();

            }
        };
        List<TakePractice> group = takePracticeDao.findAll(spec);

        int i = group.indexOf(takePractice);
        Double groupScore = 0.0;
        switch (i)
        {
            case 0:
                groupScore = 100.0;
                break;
            case 1:
                groupScore = 90.0;
                break;
            case 2:
                groupScore = 80.0;
                break;
            case 3:
                groupScore = 78.0;
                break;
            case 4:
                groupScore = 75.0;
                break;
            default:
                groupScore = 0.0;
                break;
        }
        takePractice.setGroupScore(groupScore);
        updateTakePractice(takePractice);
    }
}
