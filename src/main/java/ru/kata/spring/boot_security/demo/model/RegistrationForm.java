package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.entities.RoleEntity;
import ru.kata.spring.boot_security.demo.entities.UserEntity;

import java.util.Collections;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return new UserEntity(username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone,
                Collections.singleton(new RoleEntity("USER")));
    }
}
