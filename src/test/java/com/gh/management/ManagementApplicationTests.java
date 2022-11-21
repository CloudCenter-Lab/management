package com.gh.management;


import com.gh.management.system.controller.UserController;
import com.gh.management.system.mapper.UserMapper;
import com.gh.management.system.domain.User;
import com.gh.management.system.service.UserService;
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

    @Autowired
    private UserService userService;


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
        String encode1 = passwordEncoder.encode("111");
        String encode2 = passwordEncoder.encode("222");
        String encode3 = passwordEncoder.encode("333");
        String encode4 = passwordEncoder.encode("444");
        String encode5 = passwordEncoder.encode("555");
        String encode6 = passwordEncoder.encode("666");
        String encode7 = passwordEncoder.encode("777");
        String encode8 = passwordEncoder.encode("888");
        String encode9 = passwordEncoder.encode("999");
        String encode10 = passwordEncoder.encode("10101");

        System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(encode3);
        System.out.println(encode4);
        System.out.println(encode5);
        System.out.println(encode6);
        System.out.println(encode7);
        System.out.println(encode8);
        System.out.println(encode9);
        System.out.println(encode10);




//        System.out.println(passwordEncoder
//                .matches("111",
//                        "$2a$10$lF6aI7mZFNKb5A2MI3XyEOy0DV7j81SOVDUAarWNIoutPJ1iP1PVi"));
    }


    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("12345");
        userService.save(user);
    }

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("12345");
        userService.saveUser(user);
    }
}
