package com.gh.management.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gh.management.system.domain.User;
import com.gh.management.system.domain.param.RespBean;
import com.gh.management.system.mapper.UserMapper;
import com.gh.management.system.service.LoginService;
import com.gh.management.system.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author YJL
 * @create 2022-09-24 14:21
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Resource
    private UserMapper userMapper;

    /**
     * 登录返回token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public RespBean login(String username, String password) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password,
                userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确!");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员!");
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }
    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public User getAdminByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",
                username));
    }
}
