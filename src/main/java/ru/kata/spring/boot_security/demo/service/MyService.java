package ru.kata.spring.boot_security.demo.service;

public interface MyService<T> {
    void create(T t);
    T show(long id);
    Iterable<T> getList();
    void update(long id, T t);
    void delete(long id);
    void delete(T t);
}
