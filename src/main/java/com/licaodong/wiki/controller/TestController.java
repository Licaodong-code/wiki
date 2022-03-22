package com.licaodong.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
//http://127.0.0.1:8080/hello
public class TestController {
    /*
        GET POST PUT DELETE

     */
//    @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello(){
        return "HELLO　WORLD!";
    }
}
