package org.example.DAO;

import org.example.DTO.MovieByYear;
import org.example.Entity.Director;
import org.example.Entity.Movie;

import java.util.List;

public class MovieDao extends EntityDao<Movie> {
    @Override
    public Movie getById(int id) {
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> getAll() {
        return em.createQuery("from Movie", Movie.class).getResultList();
    }

    @Override
    public void update(Movie entity) {
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
    public void insert(Movie entity) {
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
    public void delete(Movie entity) {
        try{
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public List<Movie> filterByYear(MovieByYear years) {
       return em.createNamedQuery("Movie.getFilteredByYear", Movie.class)
                .setParameter("start", years.getStartYear())
                .setParameter("end", years.getEndYear())
                .getResultList();
    }

    public List<Movie> getTop10() {
        return em.createQuery("from Movie m order by m.rating desc", Movie.class)
                .setMaxResults(10)
                .getResultList();
    }

    public List<Movie> filterByDirectorName(String name) {
        return em.createQuery("select m from Movie m join m.directors d where d.name =: name", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Movie> filterByActorName(String name) {
        return em.createQuery("select m from Movie m join m.actors a where a.name =: name", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Movie> filterByTitle(String title) {
        return em.createQuery("select m from Movie m where m.name =: name", Movie.class)
                .setParameter("name", title)
                .getResultList();
    }

}
