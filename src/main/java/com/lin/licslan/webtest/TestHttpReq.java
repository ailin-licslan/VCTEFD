package com.lin.licslan.webtest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHttpReq {
    @GetMapping("/x")
    public String get(){
        return "Hello world!";

    }
}
