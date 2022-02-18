package com.example.helloworld.dao;

import com.example.helloworld.entitiy.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @Classname NoticeDao
 * @Description TODO
 * @Date 2021/11/13 21:18
 * @Created by 86150
 */
public interface NoticeRepository extends JpaRepository<Notice , Integer> , JpaSpecificationExecutor<Notice> {
    //使用@Query 新增自己需要的dao操作
//    @Query(value = "select new com.example.helloworld.entitiy.NoticeQueryOutput(n.uid,n.publisher_id,n.title,n.is_topping,n.release_time)" +
//            "from Notice n" +
//            "ORDER BY n.release_time desc")
//    List<NoticeQueryOutput> findAllNoticeExceptContent();

    @Query(value = "select notice_id,publisher_id,title,is_topping,release_time " +
            "from Notice ORDER BY release_time desc" , nativeQuery = true)
    public List<Map<String,Object>> findAllNoticeExceptContent();

}
