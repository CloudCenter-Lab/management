package com.gh.management.system.service;

import com.gh.management.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gh.management.system.domain.param.RespBean;

/**
 * @author YJL
 * @create 2022-08-26 10:45
 */

public interface UserService extends IService<User> {



    int saveUser(User user);

    RespBean updatePwd(String firPwd, String secPwd);
}
