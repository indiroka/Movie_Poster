package org.example.Entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({ // миновные запросы
        @NamedQuery(name = "Movie.getCount", query = "select count(m) from Movie m"),
        @NamedQuery(name = "Movie.getFilteredByYear", query = "from Movie m where m.year between :start and :end"),
        @NamedQuery(name = "Movie.getById", query = "from Movie m where m.id = :id")

})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer year;
    private String genre;
    private Double rating;
    private Integer count;

    @OneToMany(mappedBy = "movie", orphanRemoval = true) //orphanRemoval = true если удаляем актера он будет удален и с бд
    @Cascade(org.hibernate.annotations.CascadeType.ALL) // для каких типов будет выполенена транзакция
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY) //fetch = FetchType.LAZY
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Director> directors = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", count=" + count +
                '}';
    }
}
