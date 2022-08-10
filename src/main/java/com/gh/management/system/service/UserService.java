package com.gh.management.system.service;


import com.gh.management.system.entity.User;

/**
 * @author YJL
 * @create 2022-08-10 12:08
 */

/** 处理用户数据的业务层接口 */
public interface UserService {
    /**
     * 用户注册
     * @param user 用户数据
     */
    void reg(User user);

}