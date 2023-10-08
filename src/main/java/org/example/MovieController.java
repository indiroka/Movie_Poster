package org.example;

import org.example.DAO.MovieDao;
import org.example.DTO.MovieByYear;
import org.example.Entity.Movie;

import java.util.List;

public class MovieController {
  InputInformationConsole information = new InputInformationConsole();

    public void start() {
        while (true) {
            //Бесконечный цикл, который во первых показывает наше меню и получает от туда некоторый результат
            InputAction action = information.printMenu();
            //Зачем мы это делаем? Что отделать общение с пользователем, некоторую водную информацию который он вводит в отдельный класс
            switch (action) {
                case PRINT_MOVIES -> printMovie();
                case ADD_MOVIE -> addMovie();
                case DELETE_MOVIE -> deleteMovie();
                case UPDATE_MOVIE -> updateMovie();
                case FIND_BY_TITLE -> findByTitle();
                case FIND_BY_ACTOR_NAME -> findByActorName();
                case FIND_BY_DIRECTOR_NAME -> findByDirectorName();
                case FIND_BY_YEARS -> findByYear();
                case TOP_10 -> getTop10();
                case EXIT -> {
                            return;
                        }
                        default -> System.out.println("Неизвестный пункт меню");
            }
        }
    }

    private void getTop10() {
        try(MovieDao movieDao = new MovieDao()) {
            List<Movie> movies = movieDao.getTop10();
            information.printMovieList(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findByYear() {
        try(MovieDao movieDao = new MovieDao()) {
            MovieByYear movieByYear = information.getYears();// запросить информацию от пользователя
            List<Movie> movies = movieDao.filterByYear(movieByYear);
            information.printMovieList(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // dao - обратиться в базу данных и получить данные
        // вывести на печать
    }

    private void findByDirectorName() {
        try(MovieDao movieDao = new MovieDao()) {
            String name = information.getDirectorName();
           List<Movie> movies = movieDao.filterByDirectorName(name);
            information.printMovieList(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findByActorName() {
        try(MovieDao movieDao = new MovieDao()) {
            String name = information.getActorName();
            List<Movie> movies = movieDao.filterByActorName(name);
            information.printMovieList(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findByTitle() {
        try(MovieDao movieDao = new MovieDao()) {
            String title = information.getTitleName();
            List<Movie> movies = movieDao.filterByTitle(title);
            information.printMovieList(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMovie() {
        try(MovieDao movieDao = new MovieDao()) {
            Movie movie = information.addMovie();
            movieDao.insert(movie);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMovie() {
        try(MovieDao movieDao = new MovieDao()) {
            Integer id = information.chooseMovieForUpdate();
            Movie movie = movieDao.getById(id);

            information.updateMovie(movie);

            movieDao.update(movie);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMovie() {
        try(MovieDao movieDao = new MovieDao()) {
            int id = information.chooseMovieForDelete();
            Movie movie = movieDao.getById(id);
            movieDao.delete(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printMovie() {
        try(MovieDao movieDao = new MovieDao()) {
            List<Movie> movies = movieDao.getAll();
            information.printMovieList(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dummy(){
        System.out.println("Dummy method");
    }
}
