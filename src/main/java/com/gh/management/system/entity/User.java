package com.gh.management.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author YJL
 * @since 2022-08-17
 */

@Data
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("电话号码")
      private String phone;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("性别")
      private String gender;

      @ApiModelProperty("是否可用")
      private String available;

      @ApiModelProperty("地址")
      private String address;

      @ApiModelProperty("头像")
      private String avatar;

      @ApiModelProperty("创建人")
      private String createdUser;

      @ApiModelProperty("创建时间")
      private LocalDateTime createdTime;


}
