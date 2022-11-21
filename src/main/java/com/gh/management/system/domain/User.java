package com.gh.management.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类")
public class User implements Serializable , UserDetails {
    /**
     * 主键
     */
    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @TableField(value = "username")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty("用户昵称")
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty("用户密码")
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("用户邮箱")
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("电话号码")
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @ApiModelProperty("用户性别")
    @TableField(value = "sex")
    private String sex;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户类型（0管理员，1普通用户）
     */
    @ApiModelProperty("用户权限")
    @TableField(value = "user_type")
    private String type;

    /**
     * 创建人的用户id
     */
    @ApiModelProperty("创建人")
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @ApiModelProperty("是否删除")
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}