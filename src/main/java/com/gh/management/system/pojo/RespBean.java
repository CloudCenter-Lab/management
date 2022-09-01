//package com.gh.management.system.pojo;
//
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// * @author YJL
// * @create 2022-08-29 8:48
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ApiModel(value="公共的返回对象", description="")
//public class RespBean {
//
//    @ApiModelProperty(value = "状态码")
//    private Integer code;
//    @ApiModelProperty(value = "提示信息")
//    private String message;
//    @ApiModelProperty(value = "对象")
//    private Object obj;
//
//    //封装成功的返回对象
//    public static RespBean success(String message){
//        return new RespBean(200,message,null);
//    }
//
//    //封装成功的返回对象
//    public static RespBean success(String message,Object obj){
//        return new RespBean(200,message,obj);
//    }
//
//    //封装成功的返回对象
//    public static RespBean error(String message){
//        return new RespBean(500,message,null);
//    }
//
//    //封装成功的返回对象
//    public static RespBean error(String message,Object obj){
//        return new RespBean(500,message,obj);
//    }
//}