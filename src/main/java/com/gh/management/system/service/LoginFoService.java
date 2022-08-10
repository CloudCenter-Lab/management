package com.gh.management.system.service;

import com.gh.management.system.entity.User;

/**
 * @author YJL
 * @create 2022-08-10 12:12
 */
public interface LoginFoService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
