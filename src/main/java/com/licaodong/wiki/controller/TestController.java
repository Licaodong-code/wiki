package com.licaodong.wiki.controller;

import com.licaodong.wiki.domain.Test;
import com.licaodong.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@Controller
//http://127.0.0.1:8080/hello
public class TestController {
    /*
        GET POST PUT DELETE

     */
//    @RequestMapping("/hello")
    @Value("${test.hello:TEST}")
    private String testHello;
    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String hello(){
        return "HELLO　WORLD!"+testHello;
    }

    @PostMapping("/helloPost")
    public String helloPost(String name){
        return "HELLO　WORLD POST!" + name;
    }

    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
}
