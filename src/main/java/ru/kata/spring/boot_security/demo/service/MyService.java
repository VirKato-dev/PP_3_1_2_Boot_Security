package ru.kata.spring.boot_security.demo.service;

public interface MyService<T> {
    void create(T t);
    T get(Long id);
    Iterable<T> getList();
    void update(Long id, T t);
    void delete(Long id);
    void delete(T t);
}
