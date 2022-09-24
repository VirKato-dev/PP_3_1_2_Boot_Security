package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.service.MyService;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDetailsService userDetailsService;

    private final MyService<UserEntity> userService;

    public UsersController(MyService<UserEntity> userRepo, @Qualifier("jpa") UserDetailsService userDetailsService) {
        this.userService = userRepo;
        this.userDetailsService = userDetailsService;
        addUsers();
    }

    //--- CREATE

    /***
     * Подготовить объект User для сохранения в базу
     */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") UserEntity user) {
        return "users/new";
    }

    /***
     * Сохранить в базу
     */
    @PostMapping
    public String create(@ModelAttribute("user") UserEntity user) {
        userService.create(user);
        return "redirect:users";
    }

    //--- READ

    /***
     * Получить всех пользователей
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "users/all";
    }

    /***
     * Получить одного пользователя
     */
    @GetMapping("/{id}")
    public String read(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "users/show";
    }

    //--- UPDATE

    /***
     * Подготовить изменения для объекта User
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable() Long id) {
        model.addAttribute("user", userService.get(id));
        return "users/edit";
    }

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") UserEntity user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    //--- DELETE

    /***
     * Удалить пользователя (подготовки объекта User не требуется)
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    //--- TECHNICAL

    /***
     * Подготовим список пользователей в базе
     */
    private void addUsers() {
//        userService.create(new UserEntity());
//        userService.create(new UserEntity());
    }

}
