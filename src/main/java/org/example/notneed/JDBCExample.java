package org.example.notneed;

import org.example.Entity.Movie;
import org.postgresql.Driver;

import java.sql.*;

public class JDBCExample {
    private final String url = "jdbc:postgresql://localhost:5432/Movie";
    private final String user = "postgres";
    private final String password = "postgres";

    public JDBCExample() throws Exception{
        DriverManager.registerDriver(new Driver());
    }

    public static void main(String[] args) throws Exception{
        new JDBCExample().run();
    }

    public void run() throws Exception {
        deleteMovie();
    }
    private void insertMovie() throws Exception{
        try(Connection connection = connect()){
            PreparedStatement prepared = connection.prepareStatement("""
                    insert into movie(name, year, genre, rating, count) values( ?,?,?,?,?);
                    """);
            prepared.setString(1, "Name of Movie");
            prepared.setInt(2, 2010);
            prepared.setString(3, "Adventure");
            prepared.setDouble(4, 8.1);
            prepared.setInt(5, 12);
            prepared.execute();
        }
    }

    private void updateMovie() throws Exception{
        try(Connection connection = connect()){
            PreparedStatement prepared = connection.prepareStatement("""
                    update movie set name = ? where id = ?
                    """);
            prepared.setString(1, "New Name");
            prepared.setInt(2, 5);

            prepared.execute();
        }
    }
    private void deleteMovie() throws Exception{
        try(Connection connection = connect()){
            PreparedStatement prepared = connection.prepareStatement("""
                    delete from movie where id = ?
                    """);
            prepared.setInt(1, 5);
            prepared.execute();
        }
    }

    private void realAllMovies() throws Exception{
       try (Connection connection = connect()) {
           PreparedStatement prepared = connection.prepareStatement("select * from movie");
           ResultSet result = prepared.executeQuery();

           while (result.next()) {
               Movie movie = new Movie();
               movie.setId(result.getInt("id"));
               movie.setName(result.getString("name"));
               movie.setYear(result.getInt("year"));
               movie.setGenre(result.getString("genre"));
               movie.setRating(result.getDouble("rating"));
               movie.setCount(result.getInt("count"));
               System.out.println(movie);
           }
       }

    }

    public Connection connect() throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



/*    public void run() throws Exception {
// Через DriverManager нужно зарегистрировать наш postgresql и передать new Driver()
        DriverManager.registerDriver(new Driver());
//Для того что бы нам открыть соединение, мы используем класс Connection
        Connection connection = DriverManager.getConnection(url, user, password);
//Используется PreparedStatement для описание sql
        PreparedStatement preparedStatement = connection.prepareStatement("select 2134");
// Результатом запроса будет класс ResultSet, executeQuery - выполнить этот запрос после того как мы его запускаем
        ResultSet resultSet = preparedStatement.executeQuery();
// Для того что бы  переходить на строчку есть метод next() возвращает буленовскую переменную
        if (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        resultSet.close();
        connection.close();
    }*/
}
