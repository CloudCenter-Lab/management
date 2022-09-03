package com.gh.management.system.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 是否可用：1表示可用，0表示不可用
     */
    @TableLogic(value = "1",delval = "0")
    private Integer available;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 创建时间
     */
    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}