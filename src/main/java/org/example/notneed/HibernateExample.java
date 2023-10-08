package org.example.notneed;

import org.example.DAO.MovieDao;
import org.example.Entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateExample {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    EntityManager em = factory.createEntityManager();

    public void run() {
       List<Movie> movies = em.createQuery("select m from Movie m join m.directors d where d.name =: name", Movie.class)
                .setParameter("name", "Ridley")
                .getResultList();

        System.out.println("");
    }

    private Movie getById(int id) {
       return em.find(Movie.class, id);
    }

    private List<Movie> getAll() {
        return em.createQuery("from Movie", Movie.class).getResultList();
    }

    private void addMovie() {
        try {
            em.getTransaction().begin();

            Movie movie = new Movie();
            movie.setName("Новый фильм");
            movie.setYear(2001);
            movie.setGenre("Фантастика");
            movie.setRating(7.7);
            movie.setCount(10);
            // сохраняем через persist
            em.persist(movie);
            em.getTransaction().commit(); //commit Зафиксирует наши изменения в бд
            // em.flush(); // принудительная запись
        }catch (Exception e) {
            e.printStackTrace(); //Вывести ошибку
            em.getTransaction().rollback(); //Откатить
        }
    }
    private void editMovie() {
        try {
            em.getTransaction().begin();
            Movie movie = getById(6);
            movie.setRating(3.4);

            em.merge(movie); // передаем объект
            em.getTransaction().commit(); //commit Зафиксирует наши изменения в бд
            // em.flush(); // принудительная запись
        }catch (Exception e) {
            e.printStackTrace(); //Вывести ошибку
            em.getTransaction().rollback(); //Откатить
        }
    }

    private void deleteMovie() {
        try {
            em.getTransaction().begin();
            Movie movie = getById(6);


            em.remove(movie); // передаем объект
            em.getTransaction().commit(); //commit Зафиксирует наши изменения в бд
            // em.flush(); // принудительная запись
        }catch (Exception e) {
            e.printStackTrace(); //Вывести ошибку
            em.getTransaction().rollback(); //Откатить
        }
    }

    public static void main(String[] args) {
        new HibernateExample().run();
    }
}
