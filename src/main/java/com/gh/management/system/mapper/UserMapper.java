package com.gh.management.system.mapper;


import com.gh.management.system.entity.User;

/**
 * @author YJL
 * @create 2022-08-10 12:08
 */

public interface UserMapper {

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    Integer insert(User user);


    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

}
