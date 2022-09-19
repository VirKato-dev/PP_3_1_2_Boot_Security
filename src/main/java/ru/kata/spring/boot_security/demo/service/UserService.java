package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepo;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class UserService implements MyService<User> {
    private final UserRepo dao;

    public UserService(UserRepo dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void create(User user) {
        dao.save(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(User user) {
        dao.delete(user);
    }

    @Transactional
    @Override
    public void update(long id, User user) {
        user.setId(id);
        dao.save(user);
    }

    @Override
    public User show(long id) {
        return dao.findById(id).orElse(new User());
    }

    @Override
    public Iterable<User> getList() {
        return dao.findAll();
    }
}
