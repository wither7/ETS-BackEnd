package com.example.helloworld;

import com.example.helloworld.enums.UserState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class HelloControllerApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        Connection connection = null;

        //获得数据库连接
        connection = dataSource.getConnection();
        System.out.println(connection);

        //关闭数据库
        connection.close();

    }

//    @Test
//    void enumTest()
//    {
//        System.out.println(UserState.DELETED);
//        System.out.println(UserState.DELETED.getValue());
//    }
}
