package com.gh.management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class ManagementApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }


    @Test
    public void TestPassword(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String qqq = bCryptPasswordEncoder.encode("qqq");
        System.out.println(qqq);

    }
}
