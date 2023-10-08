package org.example;
import org.example.DTO.MovieByYear;
import org.example.Entity.Actor;
import org.example.Entity.Director;
import org.example.Entity.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputInformationConsole { // класс отвечает только общением с пользователем
    private Scanner scanner;
    private Map<Integer, InputAction> map = new HashMap<>();

    public InputInformationConsole() {
        scanner = new Scanner(System.in);
        map.put(1, InputAction.PRINT_MOVIES);
        map.put(2, InputAction.ADD_MOVIE);
        map.put(3, InputAction.DELETE_MOVIE);
        map.put(4, InputAction.UPDATE_MOVIE);
        map.put(5, InputAction.FIND_BY_TITLE);
        map.put(6, InputAction.FIND_BY_ACTOR_NAME);
        map.put(7, InputAction.FIND_BY_DIRECTOR_NAME);
        map.put(8, InputAction.FIND_BY_YEARS);
        map.put(9, InputAction.TOP_10);
        map.put(10, InputAction.EXIT);

    }

    public InputAction printMenu() {
        while (true) {
            System.out.println("Выберите пункт меню");
            System.out.println("1. Вывести фильмы на печать");
            System.out.println("2. Добавить новый фильм");
            System.out.println("3. Удалить фильм");
            System.out.println("4. Обноавить данные о фильме");
            System.out.println("5. Поиск по названию");
            System.out.println("6. Поиск по имени актера");
            System.out.println("7. Поиск по имени режиссера");
            System.out.println("8.Поиск по годам");
            System.out.println("9. Вывести топ-10 фильмов");
            System.out.println("10. Выход");

            int action = Integer.parseInt(scanner.nextLine());
            if (action >= 1 && action <= 10) {
                return map.get(action);
            }
            System.out.println("Неизвестный пункт меню");
        }
    }

    public MovieByYear getYears() {
        MovieByYear movieByYear = new MovieByYear();

        System.out.println("Введите начальный год:");
        movieByYear.setStartYear(Integer.parseInt(scanner.nextLine()));
        System.out.println("Введите конечный год:");
        movieByYear.setEndYear(Integer.parseInt(scanner.nextLine()));

        return movieByYear;
    }

    public String getActorName(){
        System.out.println("Введите имя актера");
        return scanner.nextLine();
    }

    public String getDirectorName(){
        System.out.println("Введите имя режиссера");
        return scanner.nextLine();
    }

    public String getTitleName(){
        System.out.println("Введите название фильма");
        return scanner.nextLine();
    }

    public  void printMovieList(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public Integer chooseMovieForDelete() {
        System.out.println("Введите ID фильма для удаления");
        return Integer.parseInt(scanner.nextLine());
    }

    public Movie addMovie() {
        Movie movie = new Movie();
        getInformationAboutMovie(movie);

        while (true) {
            System.out.println("Введите имя и фамилию режиссера или end для завершение ввода");

            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }

            String[] data = input.split(" ");
            Director director = new Director();
            director.setName(data[0]);
            director.setSurname(data[1]);
            director.setMovie(movie);
            movie.getDirectors().add(director);
        }

        while (true) {
            System.out.println("Введите имя и фамилию актера или end для завершение ввода");

            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }

            String[] data = input.split(" ");
            Actor actor = new Actor();
            actor.setName(data[0]);
            actor.setSurname(data[1]);
            actor.setMovie(movie);
            movie.getActors().add(actor);
        }

        return movie;

    }

    public Movie updateMovie(Movie movie) {
        getInformationAboutMovie(movie);

        for (Director director : movie.getDirectors()) {
            System.out.println("Введите новое имя и фамилию режиссера");
            String[] data = scanner.nextLine().split(" ");
            director.setName(data[0]);
            director.setSurname(data[1]);
        }

        for (Actor actor : movie.getActors()) {
            System.out.println("Введите новое имя и фамилию актера");
            String[] data = scanner.nextLine().split(" ");
            actor.setName(data[0]);
            actor.setSurname(data[1]);
        }
        return movie;
    }

    private void getInformationAboutMovie(Movie movie) {
        System.out.println("Введите название фильма");
        movie.setName(scanner.nextLine());

        System.out.println("Введите год фильма");
        movie.setYear(Integer.parseInt(scanner.nextLine()));

        System.out.println("Введите жанр фильма");
        movie.setGenre(scanner.nextLine());

        System.out.println("Введите рейтинг фильма");
        movie.setRating(Double.parseDouble(scanner.nextLine()));

        System.out.println("Введите количество на складе");
        movie.setCount(Integer.parseInt(scanner.nextLine()));
    }

    public Integer chooseMovieForUpdate() {
        System.out.println("Введите ID для редактирования фильма");
        return Integer.parseInt(scanner.nextLine());
    }

}

