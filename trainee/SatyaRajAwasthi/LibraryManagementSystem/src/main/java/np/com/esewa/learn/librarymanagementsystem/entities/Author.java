package np.com.esewa.learn.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "authors")
public class Author {
    @Id
    @Column(name = "authors_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "authors_name")
    private String name;

    @Column(unique = true, name = "authors_email")
    private String email;
}
