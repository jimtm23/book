package com.example.book.helpers;

import org.springframework.transaction.annotation.Transactional;

public interface CrudService<T> {

    public T findById(String id);

    @Transactional
    public T save(T t);

    @Transactional
    public T edit(T t, String id);

    public Iterable<T> list();

    @Transactional
    public void delete(String id);
}
