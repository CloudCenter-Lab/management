package com.gh.management.system.controller;

import com.gh.management.system.domain.User;
import com.gh.management.system.domain.param.LoginParam;
import com.gh.management.system.domain.param.RespBean;
import com.gh.management.system.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


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
    public RespBean login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam.getUsername(),loginParam.getPassword());
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public RespBean logout(){
        return RespBean.success("退出成功");
    }


    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/user/info")
    public User getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        User user = loginService.getAdminByUserName(username);
        user.setPassword(null);
        return user;
    }


}
