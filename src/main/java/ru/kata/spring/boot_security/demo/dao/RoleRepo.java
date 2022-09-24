package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.RoleEntity;

@Repository
public interface RoleRepo extends CrudRepository<RoleEntity, Long> {
}
