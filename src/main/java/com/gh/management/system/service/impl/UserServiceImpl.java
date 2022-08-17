package com.gh.management.system.service.impl;

import com.gh.management.system.entity.User;
import com.gh.management.system.mapper.UserMapper;
import com.gh.management.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YJL
 * @since 2022-08-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
