package com.example.backend.service.Interface;

import com.example.backend.entitiy.Practice;

/**
 * @Classname PracticeService
 * @Description TODO
 * @Date 2021/12/31 13:37
 * @Created by 86150
 */
public interface PracticeService {
    //添加对抗练习
    public void addPractice(Practice practice);
    //更新对抗练习
    public void updatePractice(Practice practice);
    //根据id删除对抗练习
    public void deletePractice(Integer id);
    //根据id判断是否存在对抗练习
    public boolean existPractice(Integer id);
    //根据对抗练习id查询对抗练习
    public Practice findPractice(Integer id);

}
