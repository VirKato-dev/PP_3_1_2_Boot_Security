package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepo;
import ru.kata.spring.boot_security.demo.dao.UserRepo;
import ru.kata.spring.boot_security.demo.entities.RoleEntity;
import ru.kata.spring.boot_security.demo.entities.UserEntity;

@Service
public class RoleService implements MyService<RoleEntity> {
    private final RoleRepo dao;

    public RoleService(RoleRepo dao) {
        this.dao = dao;
    }

    @Override
//    @Transactional
    public void create(RoleEntity role) {
        dao.save(role);
    }

    @Override
//    @Transactional
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
//    @Transactional
    public void delete(RoleEntity role) {
        dao.delete(role);
    }

    @Override
//    @Transactional
    public void update(Long id, RoleEntity role) {
        role.setId(id);
        dao.save(role);
    }

    @Override
    public RoleEntity get(Long id) {
        return dao.findById(id).orElse(new RoleEntity());
    }

    @Override
    public Iterable<RoleEntity> getList() {
        return dao.findAll();
    }
}
