package com.example.memberproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 첫번째 도메인, localhost 8080으로 들어오면 이 메소드가 호출
    public String home() {
        return "home"; // home.html이 호출
    }
}
