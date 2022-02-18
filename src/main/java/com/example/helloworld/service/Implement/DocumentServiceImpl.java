package com.example.helloworld.service.Implement;/**
 * @Classname DocumentServiceImpl
 * @Description TODO
 * @Date 2021/12/18 14:00
 * @Created by 86150
 */

import com.example.helloworld.dao.DocumentRepository;
import com.example.helloworld.entitiy.Document;
import com.example.helloworld.service.Interface.DocumentService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-18 14:00:05
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentDao;
    @Autowired
    private QiniuServiceImpl qiniuService;
    @Override
    public void addDocument(Document document) {
        documentDao.save(document);
    }

    @Override
    public void deleteDocument(Integer fileId) {

        documentDao.deleteById(fileId);
    }

    @Override
    public void updateDocument(Document document) {
        documentDao.save(document);
    }

    @Override
    public Document findDocument(Integer fileId) {
        return documentDao.findById(fileId).orElse(null);
    }

    @Override
    public boolean existDocument(Integer fileId) {
        return documentDao.existsById(fileId);
    }

    @Override
    public List<Document> findAllDocument() {
        return documentDao.findAll();
    }

    @Override
    public List<Document> findAllDocumentByClassIdAndCategory( Integer classId , String category) {
        Specification<Document> spec= new Specification<Document>() {
            @Override
            public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate classId1 = criteriaBuilder.equal(root.get("classId"), classId);
                Predicate category1 = criteriaBuilder.equal(root.get("category"), category);

                return criteriaBuilder.and(category1,classId1);
            }
        };
        return documentDao.findAll(spec);
    }
}
