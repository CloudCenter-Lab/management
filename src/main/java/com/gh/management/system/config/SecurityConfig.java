//package com.gh.management.system.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author YJL
// * @create 2022-09-01 11:50
// */
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //忽略对以下地址的安全校验 加入你不想security校验的接口即可
//        web.ignoring().antMatchers("/login",
//                "/logout",
//                "/css/**",
//                "/js/**",
//                "/index.html",
//                "favicon.ico",
//                "/doc.html",
//                "/webjars/**",
//                "/swagger-resource/**",
//                "/v2/api-docs/**");
//    }
//
//}
