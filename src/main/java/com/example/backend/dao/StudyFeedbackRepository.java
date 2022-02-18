package com.example.backend.dao;

import com.example.backend.entitiy.StudyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname StudyFeedbackRepository
 * @Description TODO
 * @Date 2021/12/30 15:28
 * @Created by 86150
 */
public interface StudyFeedbackRepository extends JpaRepository<StudyFeedback,Integer>
                        ,JpaSpecificationExecutor<StudyFeedback> {
}
