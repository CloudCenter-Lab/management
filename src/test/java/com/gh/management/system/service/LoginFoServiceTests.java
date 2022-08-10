package com.gh.management.system.service;

import com.gh.management.system.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YJL
 * @create 2022-08-10 12:42
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginFoServiceTests {

    @Autowired
    private LoginFoService loginFoService;

    @Test
    public void login(){
        User user = loginFoService.login("yu", "123456");
        System.out.println(user);
    }
}
