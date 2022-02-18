package com.example.backend.service.Implement;/**
 * @Classname NoticeService
 * @Description TODO
 * @Date 2021/11/13 21:21
 * @Created by 86150
 */

import com.example.backend.dao.NoticeRepository;
import com.example.backend.entitiy.Notice;
import com.example.backend.service.Interface.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @ program: Helloworld
 * @ description:
 * @ author: YXJ
 * @ date: 2021-11-13 21:21:46
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeDao;

    @Override
    public void addNotice(Notice notice)
    {

        noticeDao.save(notice);
    }
    @Override
    public void deleteNotice(Notice notice)
    {
        noticeDao.delete(notice);
    }
    @Override
    public void deleteNotice(Integer id)
    {
        noticeDao.deleteById(id);
    }
    @Override
    public void updateNotice(Notice notice)
    {
        System.out.println(notice);
        noticeDao.save(notice);
    }
    @Override
    public boolean existNotice(Integer id)
    {
        return noticeDao.existsById(id);
    }
    @Override
    public boolean existNotice(Notice notice)
    {
        return noticeDao.existsById(notice.getNotice_id());
    }
    @Override
    public Notice findNotice(Integer id)
    {
        /*
         * 尝试使用JpaSpecifications 动态查询
         * 需要自定义Specification实现类
         *
         * Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
         * root： 查询的根对象
         * CriteriaQuery ： 顶层查询对象  自定义查询方式
         * CriteriaBuilder ： 查询构造器 封装构造条件
         */
        Specification<Notice> spec = new Specification<Notice>() {
            //实现Specification接口
            @Override//重写toPredicate方法
            public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较属性
                Path<Object> notice_id = root.get("notice_id");
                /*
                 * 2.构造查询条件
                 *    Predicate equal(Expression<?> var1, Expression<?> var2);
                 * var1 : 需要比较的属性(path)
                 * var2 ： 需要比较的值
                 */
                //借助CriteriaBuilder cb实现查询条件的构造
                return cb.equal(notice_id, id);
            }
        };

        return noticeDao.findOne(spec).orElse(new Notice());
//        return noticeDao.findById(id).orElse(new Notice());
    }
    @Override
    public List<Notice> findAllNotice()
    {
//        noticeDao.
//        return noticeDao.findAll(Sort.by(Sort.Direction.DESC , "release_time"));
        /*
         * 使用Sort 将查询结果按releaseTime降序排序
         */
        Sort sort =  Sort.by(Sort.Direction.DESC ,"releaseTime" );
        List<Notice> notices = noticeDao.findAll(sort);
        for(Notice notice : notices)
        {
            notice.setContent(null);
        }
        return notices;
    }
    @Override
    public void topNotice(Integer id)
    {
        Notice notice = this.findNotice(id);
        if(notice.getNotice_id() > 0)
        {
//            notice.setIs_top(true);
            notice.set_topping(true);
            noticeDao.save(notice);;
        }

    }
    @Override
    public void unTopNotice(Integer id)
    {
        Notice notice = this.findNotice(id);
        if(notice.getNotice_id() > 0)
        {
//            notice.setIs_top(false);
            notice.set_topping(false);
            noticeDao.save(notice);;

        }
    }
}
