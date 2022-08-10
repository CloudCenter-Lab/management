package com.gh.management.system.service.impl;


import com.gh.management.system.entity.User;
import com.gh.management.system.mapper.UserMapper;
import com.gh.management.system.service.UserService;
import com.gh.management.system.service.ex.InsertException;
import com.gh.management.system.service.ex.UsernameDuplicateException;
import com.gh.management.system.util.GetMd5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.UUID;
/**
 * @author YJL
 * @create 2022-08-10 12:08
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public void reg(User user) {
        // 根据参数user对象获取注册的用户名
        String username = user.getUsername();
        // 调用持久层的User findByUsername(String username)方法，根据用户名查询用户数据
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("尝试注册的用户名[" + username + "]已经被占用");
        }


        // 获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //加密密码
        String md5Password = GetMd5Password.getMd5Password(user.getPassword(), salt);
        //设置密码
        user.setPassword(md5Password);
        // 设置盐值
        user.setSalt(salt);
        // 设置数据：setAvailable(1)
        user.setAvailable(1);
        // 补全数据：4项日志属性
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        // 创建当前时间对象
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

}
