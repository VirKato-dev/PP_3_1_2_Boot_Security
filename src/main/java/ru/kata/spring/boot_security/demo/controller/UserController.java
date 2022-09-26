package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userRepo) {
        this.userService = userRepo;
    }

    //--- READ

    /***
     * Данные текущего пользователя
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "user/show";
    }

    //--- UPDATE

    /***
     * Редактировать данные текущего пользователя
     */
    @GetMapping("/edit")
    public String editForm(Model model) {
        return "user/edit";
    }

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") UserEntity user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/user";
    }

    //--- DELETE

}
