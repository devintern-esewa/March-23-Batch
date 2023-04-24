package np.com.sewa.learn.datajpaadvancecrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import np.com.sewa.learn.datajpaadvancecrud.entities.enums.BookStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "books")
public class Book {
    private String title;
    @Id
    private long isbn;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Publisher publisher;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Author> authors;
    @Column(name = "publication_year")
    private LocalDate publicationYear;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus bookStatus;
}
