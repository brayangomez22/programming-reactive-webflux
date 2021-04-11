package com.example.demo.controller;

import com.example.demo.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(final Article article) {
        return "index";
    }

}