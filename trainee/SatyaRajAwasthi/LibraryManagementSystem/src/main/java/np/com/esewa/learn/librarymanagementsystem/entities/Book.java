package np.com.esewa.learn.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.BookStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "books")
public class Book {
    private String title;

    @Id
    private long isbn;

    @OneToMany // cascade all shouldn't be used here as changing values for one book will lead to change for all books
    private List<Genre> genres;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publishers_id")
    private Publisher publisher;

    @OneToMany(cascade = CascadeType.ALL)
    // not preferred to Join columns in case of collections, do have seperate table
    private List<Author> authors;

    @Column(name = "publication_year")
    private LocalDate publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status")
    private BookStatus bookStatus;
}
