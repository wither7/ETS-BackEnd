package com.example.backend.service.Implement;/**
 * @Classname PracticeServiceImpl
 * @Description TODO
 * @Date 2021/12/31 14:12
 * @Created by 86150
 */

import com.example.backend.dao.PracticeRepository;
import com.example.backend.entitiy.Practice;
import com.example.backend.service.Interface.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-31 14:12:06
 */
@Service
public class PracticeServiceImpl implements PracticeService {
    @Autowired
    private PracticeRepository practiceDao;

    @Override
    public void addPractice(Practice practice) {
        practiceDao.save(practice);
    }


    public Practice addAndFlushPractice(Practice practice) {
        return practiceDao.saveAndFlush(practice);
    }

    @Override
    public void updatePractice(Practice practice) {
        practiceDao.save(practice);
    }

    @Override
    public void deletePractice(Integer id) {
        practiceDao.deleteById(id);
    }

    @Override
    public boolean existPractice(Integer id) {
        return practiceDao.existsById(id);
    }

    @Override
    public Practice findPractice(Integer id) {
        return practiceDao.findById(id).orElse(null);
    }

    public List<Practice> findAllPracticeByClassId(Integer classId)
    {
        Specification<Practice> spec = new Specification<Practice>() {
            @Override
            public Predicate toPredicate(Root<Practice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("classId") , classId);
            }
        };
        return practiceDao.findAll(spec);
    }
}
