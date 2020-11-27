package service;

import exception.NotFoundException;

public interface IService<T> {
    Iterable<T> findAll();

    T findById(Long id) throws NotFoundException;

    void update(T model);
    void save(T model);

    void remove(Long id);
}
