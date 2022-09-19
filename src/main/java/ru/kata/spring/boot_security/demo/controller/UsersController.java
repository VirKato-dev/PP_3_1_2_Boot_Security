package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyService;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final MyService<User> userService;

    public UsersController(MyService<User> userRepo) {
        this.userService = userRepo;
        addUsers();
    }

    //--- CREATE

    /***
     * Подготовить объект User для сохранения в базу
     */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "users/new";
    }

    /***
     * Сохранить в базу
     */
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
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
    public String read(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }

    //--- UPDATE

    /***
     * Подготовить изменения для объекта User
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    //--- DELETE

    /***
     * Удалить пользователя (подготовки объекта User не требуется)
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    //--- TECHNICAL

    /***
     * Подготовим список пользователей в базе
     */
    private void addUsers() {
        userService.create(new User("Степан", "Иванов"));
        userService.create(new User("Иван", "Петров"));
        userService.create(new User("Света", "Вихрь"));
        userService.create(new User("Алина", "Хац"));
    }

}
