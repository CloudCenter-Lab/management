package com.gh.management.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.management.system.domain.User;
import com.gh.management.system.domain.param.RespBean;
import com.gh.management.system.service.UserService;
import com.gh.management.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author YJL
 * @create 2022-08-26 10:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired UserMapper userMapper;

    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public int saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userMapper.insert(user);
    }

    @Override
    public RespBean updatePwd(String firPwd, String secPwd) {

        return RespBean.success("修改密码成功");
    }
}






