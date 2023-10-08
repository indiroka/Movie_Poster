package org.example.DAO;

import org.example.Entity.Actor;
import org.example.Entity.Director;

import java.util.List;

public class DirectorDao extends EntityDao<Director> {


    @Override
    public Director getById(int id) {
        return em.find(Director.class, id);
    }

    @Override
    public List<Director> getAll() {
        return em.createQuery("from Director", Director.class).getResultList();
    }

    @Override
    public void update(Director entity) {
        try{
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void insert(Director entity) {
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Director entity) {
        try{
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
