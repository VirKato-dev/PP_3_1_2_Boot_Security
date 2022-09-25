package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.entities.RoleEntity;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.model.RegistrationForm;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        createRoles();
    }

    @GetMapping("/register")
    public String registerForm() {
        return "/auth/registration";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form, Model model) {
        if (userService.getByUsername(form.getUsername()) == null) {
            userService.create(form.toUser(roleService, passwordEncoder));
            return "redirect:/login";
        }
        model.addAttribute("message", "Логин уже используется");
        return "auth/registration";
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

    private void createRoles() {
        if (roleService.getRepo().count() < 2) {
            roleService.getRepo().saveAll(Set.of(
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_ADMIN")));
            userService.create(new UserEntity("admin", passwordEncoder.encode("admin"),
                    "ADMIN", "", "", "", "", "", Set.of(
                    roleService.getRepo().getRoleEntityByRole("ROLE_ADMIN"),
                    roleService.getRepo().getRoleEntityByRole("ROLE_USER"))));
        }
    }
}
