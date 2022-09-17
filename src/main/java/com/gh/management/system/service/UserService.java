package com.gh.management.system.service;

//import com.gh.management.system.pojo.RespBean;
import com.gh.management.system.pojo.RespBean;
import com.gh.management.system.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @author YJL
 * @create 2022-08-26 10:45
 */
public interface UserService extends IService<User> {

    RespBean login(String userName, String password);

    User getUserByUserName(String username);

}
