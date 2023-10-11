package com.arcad.employeebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
