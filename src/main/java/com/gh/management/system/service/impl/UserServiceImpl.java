package com.gh.management.system.service.impl;



import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.gh.management.system.mapper.UserMapper;

import com.gh.management.system.pojo.RespBean;
import com.gh.management.system.pojo.User;

import com.gh.management.system.service.UserService;

import com.gh.management.system.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YJL
 * @create 2022-08-26 10:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private static final Log LOG = Log.get();

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UserMapper userMapper;



    @Override
    public RespBean login(String username, String password) {
        //加载登录对象信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UsernameNotFoundException("用户名和密码异常");
        }

        //判断当前对象是否可用
        if(!userDetails.isEnabled()){
            return RespBean.error("用户状态异常");
        }

        //将用户的基本信息存放在security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //准备令牌
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> map = new HashMap<>();
        System.out.println(tokenHead);
        map.put("tokenHead",tokenHead);
        map.put("token",token);
        return RespBean.success("登录成功",map);
    }

    /**
     * 根据用户名查询对象
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }


}




