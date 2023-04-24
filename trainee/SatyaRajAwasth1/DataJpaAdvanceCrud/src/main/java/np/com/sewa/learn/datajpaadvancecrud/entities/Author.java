package np.com.sewa.learn.datajpaadvancecrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "authors")
public class Author {
    private String name;
    @Id
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> listOfBooksWritten;
}
