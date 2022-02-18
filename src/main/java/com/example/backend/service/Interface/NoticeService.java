package com.example.backend.service.Interface;

import com.example.backend.entitiy.Notice;

import java.util.List;

/**
 * @Classname NoticeService
 * @Description TODO
 * @Date 2021/11/27 18:18
 * @Created by 86150
 */
public interface NoticeService {
    //添加公告
    public void addNotice(Notice notice);
    //删除公告
    public void deleteNotice(Notice notice);
    //根据公告id删除公告
    public void deleteNotice(Integer id);
    //更新公告
    public void updateNotice(Notice notice);
    //根据公告id判断公告是否存在
    public boolean existNotice(Integer id);
    //判断公告是否存在
    public boolean existNotice(Notice notice);
    //根据公告id查询公告
    public Notice findNotice(Integer id);
    //查询所有公告
    public List<Notice> findAllNotice();
    //置顶公告
    public void topNotice(Integer id);
    //取消置顶公告
    public void unTopNotice(Integer id);
}
