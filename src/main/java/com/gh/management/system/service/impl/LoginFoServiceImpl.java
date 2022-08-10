package com.gh.management.system.service.impl;

import com.gh.management.system.entity.User;
import com.gh.management.system.mapper.LoginFoMapper;
import com.gh.management.system.mapper.UserMapper;
import com.gh.management.system.service.LoginFoService;
import com.gh.management.system.service.ex.PasswordNotMatchException;
import com.gh.management.system.service.ex.UserNotFoundException;
import com.gh.management.system.util.GetMd5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YJL
 * @create 2022-08-10 12:13
 */
@Service
public class LoginFoServiceImpl implements LoginFoService {

    @Autowired
    private LoginFoMapper loginFoMapper;

    @Override
    public User login(String username, String password) {
        // 调用userMapper的findByUsername()方法，根据参数username查询用户数据
        User result = loginFoMapper.findByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在！");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getAvailable() == 0) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在！");
        }

        // 从查询结果中获取盐值
        String salt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = GetMd5Password.getMd5Password(password, salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("用户密码错误！");
        }

        // 创建新的User对象
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        // 返回新的user对象
        return user;
    }
}
