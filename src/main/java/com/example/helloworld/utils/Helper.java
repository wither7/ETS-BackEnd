package com.example.helloworld.utils;/**
 * @Classname Helper
 * @Description TODO
 * @Date 2021/12/27 21:49
 * @Created by 86150
 */

import java.util.Calendar;
import java.util.Date;

/**
 * @ program: ETS-BackEnd
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-27 21:49:25
 */
public class Helper {

    
    //帕努单两个Date类型是否在同一天
    public static boolean isSameDay(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

}
