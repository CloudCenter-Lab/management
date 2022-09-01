package com.gh.management.system.mapper;
import org.apache.ibatis.annotations.Param;

import com.gh.management.system.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author YJL
 * @create 2022-08-26 10:45
 * @Entity com.gh.management.system.pojo.User
 */
public interface UserMapper extends BaseMapper<User> {

    User getOneByUsername(@Param("username") String username);
}




