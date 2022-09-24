package com.gh.management;


import com.gh.management.system.controller.UserController;
import com.gh.management.system.mapper.UserMapper;
import com.gh.management.system.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class ManagementApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserController userController;

    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }


    @Test
    public void TestDemo1(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void TestGetAll(){
        List<User> list = userController.findAll();
        System.out.println(list);

    }

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");

        System.out.println(encode);

//        System.out.println(passwordEncoder
//                .matches("1234",
//                        "$2a$10$v2GJw/XCecxxodyuOqiLDe6eGSklKmZUXdB5.0jzLBsthz.ETNX0G"));
    }

}
