package com.example.helloworld.dao;

import com.example.helloworld.entitiy.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.print.Doc;

/**
 * @Classname DocumentRepository
 * @Description TODO
 * @Date 2021/12/18 13:49
 * @Created by 86150
 */
public interface DocumentRepository extends JpaRepository<Document,Integer>,
                                            JpaSpecificationExecutor<Document> {
}
