package com.gh.management.system.controller;

import com.gh.management.system.entity.User;
import com.gh.management.system.service.LoginFoService;
import com.gh.management.system.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author YJL
 * @create 2022-08-10 15:32
 */

//处理用户登录
@RestController
@RequestMapping("users")
public class LoginFoController extends BaseController {
    @Autowired
    LoginFoService loginFoService;

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password) {
        // 调用业务对象的方法执行登录，并获取返回值
        User data = loginFoService.login(username, password);


        return new JsonResult<User>(OK, data);
    }

}
