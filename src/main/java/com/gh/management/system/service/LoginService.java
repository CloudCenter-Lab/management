package com.gh.management.system.service;

import com.gh.management.system.domain.User;
import com.gh.management.system.domain.param.ResponseResult;

/**
 * @author YJL
 * @create 2022-09-24 14:20
 */

public interface LoginService {
     ResponseResult login(User user) ;
}
