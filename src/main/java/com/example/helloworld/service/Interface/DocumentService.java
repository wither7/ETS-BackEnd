package com.example.helloworld.service.Interface;

import com.example.helloworld.entitiy.Document;
import com.example.helloworld.entitiy.Notice;

import java.util.List;

/**
 * @Classname DocumentService
 * @Description TODO
 * @Date 2021/12/18 13:51
 * @Created by 86150
 */
public interface DocumentService {
    // 添加文件
    public void addDocument(Document document);
    // 根据文件id删除文件
    public void deleteDocument(Integer fileId);
    //更新文件信息
    public void updateDocument(Document document);
    //根据文件id查询文件
    public Document findDocument(Integer fileId);
    //根据文件id判断文件是否存在
    public boolean existDocument(Integer fileId);
    //查询所有文件
    public List<Document> findAllDocument();
    //根据班级和类别查询文件
    public List<Document> findAllDocumentByClassIdAndCategory(Integer classId , String category);
}
