package com.babin.sportinggoodsstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Main controller

@Controller
public class MainController {

    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error") // перенаправляем пользователя на страницу 404
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
