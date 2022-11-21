package com.gh.management.system.domain.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author YJL
 * @create 2022-09-26 9:56
 */
@Data
@ApiModel(value="登录参数对象", description="")
public class LoginParam {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}