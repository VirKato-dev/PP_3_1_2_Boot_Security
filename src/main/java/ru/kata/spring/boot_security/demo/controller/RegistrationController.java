package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.dao.UserRepo;
import ru.kata.spring.boot_security.demo.model.RegistrationForm;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "/auth/registration";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form) {
        userService.create(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(Model model) {
        String pw = String.valueOf(model.getAttribute("password"));
        System.out.println(pw);
        return "redirect:/";
    }
}
