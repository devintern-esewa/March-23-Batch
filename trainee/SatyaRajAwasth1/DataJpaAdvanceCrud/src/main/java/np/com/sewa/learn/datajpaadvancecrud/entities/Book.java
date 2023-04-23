package np.com.sewa.learn.datajpaadvancecrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "BOOKS")
public class Book {
    private String title;
    @Id
    private long ISBN;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Publisher publisher;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Author> authors;
    private LocalDate publicationYear;
}
