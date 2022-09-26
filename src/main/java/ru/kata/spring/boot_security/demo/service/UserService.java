package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepo;
import ru.kata.spring.boot_security.demo.entities.UserEntity;

@Service
public class UserService implements MyService<UserEntity>, UserDetailsService {
    private final UserRepo dao;

    public UserService(UserRepo dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void create(UserEntity user) {
        dao.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(UserEntity user) {
        dao.delete(user);
    }

    @Override
    @Transactional
    public void update(Long id, UserEntity user) {
        user.setId(id);
        dao.save(user);
    }

    @Override
    public UserEntity get(Long id) {
        return dao.findById(id).orElse(new UserEntity());
    }

    public UserEntity getByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public Iterable<UserEntity> getList() {
        return dao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = dao.findByUsername(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User ‘" + username + "’ not found");
    }
}
