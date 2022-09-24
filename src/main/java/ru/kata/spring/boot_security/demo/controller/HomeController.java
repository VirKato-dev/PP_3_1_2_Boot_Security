package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String show1(Model model) {
        return getView(model);
    }

    @GetMapping("/index")
    public String show2(Model model) {
        return getView(model);
    }

    private String getView(Model model) {
        model.addAttribute("message", "Список людей");
        model.addAttribute("url", "/users");
        return "index";
    }
}
