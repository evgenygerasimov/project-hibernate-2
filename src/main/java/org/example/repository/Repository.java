package org.example.repository;

import org.hibernate.Session;

import java.util.List;

public interface Repository<T> {

    void save(Session session, T entity);

    void delete(Session session, T entity);

    List<T> findAll(Session session);

    T findById(Session session, Long id);

    void update(Session session, T entity);
}
