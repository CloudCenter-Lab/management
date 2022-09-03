package com.gh.management.system.controller;

import cn.hutool.core.util.StrUtil;
//import com.gh.management.system.pojo.RespBean;
//import com.gh.management.system.pojo.User;
//import com.gh.management.system.pojo.UserLoginParam;
import com.gh.management.system.pojo.dto.UserDTO;
import com.gh.management.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @author YJL
 * @create 2022-08-29 9:01
 */

@RestController
@Api(value = "登录")
public class LoginController {



    @Resource
    private UserService userService;

    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){
        if (StrUtil.isNotBlank(userDTO.getUsername())||StrUtil.isNotBlank(userDTO.getPassword())){
            return userService.login(userDTO);

        }
        return false;
    }
//
//    @ApiOperation(value = "登录接口")
//    @PostMapping("/login")
//    public RespBean login(UserLoginParam loginParam){
//        return userService.login(loginParam.getUserName(),loginParam.getPassword());
//    }
//
//    @ApiOperation(value = "获取当前登录用户的信息")
//    @GetMapping("/user/info")
//    public User getUserInfo(Principal principal){
//        if (null==principal){
//            return null;
//        }
//        String username = principal.getName();
//        User user = userService.getUserByUserName(username);
//        user.setPassword(null);
//        return user;
//    }
//
//    @ApiOperation(value = "退出登录")
//    @GetMapping("/logout")
//    public RespBean logout(){
//        return RespBean.success("退出成功");
//    }

}


