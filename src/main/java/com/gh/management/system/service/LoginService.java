package com.gh.management.system.service;

import com.gh.management.system.domain.User;
import com.gh.management.system.domain.param.RespBean;

/**
 * @author YJL
 * @create 2022-09-24 14:20
 */

public interface LoginService {


     /**
      * 登录
      * @param username
      * @param password
      * @return
      */
     RespBean login(String username, String password);


     /**
      * 根据用户名获取用户
      * @param username
      */
     User getAdminByUserName(String username);
}
