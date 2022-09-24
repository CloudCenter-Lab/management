package com.gh.management.system.controller;

import com.gh.management.system.domain.User;
import com.gh.management.system.domain.param.ResponseResult;
import com.gh.management.system.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author YJL
 * @create 2022-09-21 16:02
 */

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }


}
