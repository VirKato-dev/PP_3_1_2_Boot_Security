package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.dao.UserRepo;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.model.RegistrationForm;

@Controller
public class RegistrationController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "/auth/registration";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(Model model) {
        String pw = String.valueOf(model.getAttribute("password"));
        return "redirect:/";
    }
}
