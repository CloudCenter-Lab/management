package com.gh.management.mapper;


import com.gh.management.entity.User;

public interface UserMapper {
    Integer insert(User user);


    User findByUsername(String username);

}
