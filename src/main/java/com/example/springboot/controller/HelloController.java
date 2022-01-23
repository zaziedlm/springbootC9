package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Bootアプリの構造説明コントローラクラス
 * URL /hello のGETリクエストに応答して
 * Tymeleafテンプレートエンジンの機能により
 * hello.html にメッセージをレスポンスします
 * @author shuu-katao
 */
@Controller
public class HelloController {
    
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Tymeleaf");
        return "hello";
    }
}