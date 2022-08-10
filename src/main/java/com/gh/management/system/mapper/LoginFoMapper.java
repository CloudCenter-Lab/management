package com.gh.management.system.mapper;

import com.gh.management.system.entity.User;

/**
 * @author YJL
 * @create 2022-08-10 14:32
 */
public interface LoginFoMapper {

    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
