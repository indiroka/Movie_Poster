package org.example.DAO;

import org.example.Entity.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ActorDao extends EntityDao<Actor>  {
    public Actor getById(int id) {
        return em.find(Actor.class, id);
    }

    public List<Actor> getAll() {
        return em.createQuery("from Actor", Actor.class).getResultList();
    }

    public void update(Actor entity) {
        try{
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void insert(Actor entity) {
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void delete(Actor entity) {
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
