package com.gh.management.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gh.management.system.pojo.User;
import com.gh.management.system.service.UserService;
import com.gh.management.system.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author YJL
 * @create 2022-08-26 10:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




