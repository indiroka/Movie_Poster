package org.example.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class EntityDao<T> implements AutoCloseable{
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    EntityManager em;
    protected EntityDao() {
        em = factory.createEntityManager();
    }

    public abstract T getById(int id);
    public abstract List<T> getAll();
    public abstract void update(T entity);
    public abstract void insert(T entity);
    public abstract void delete(T entity);

    @Override
    public void close() throws Exception {
        if (em.isOpen()) {
            em.close();
        }
    }

}
