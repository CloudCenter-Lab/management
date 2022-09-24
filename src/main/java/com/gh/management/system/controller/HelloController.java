package com.gh.management.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YJL
 * @create 2022-09-22 21:11
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }


}
