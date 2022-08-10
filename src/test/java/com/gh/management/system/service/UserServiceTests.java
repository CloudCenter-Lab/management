package com.gh.management.system.service;


import com.gh.management.system.entity.User;
import com.gh.management.system.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;



    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("yu");
            user.setPassword("123456");
            user.setGender(1);
            user.setPhone("1888888888");
            user.setEmail("yu@qq.com");
            user.setAvatar("xxxx");
            userService.reg(user);
            System.out.println("注册成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
