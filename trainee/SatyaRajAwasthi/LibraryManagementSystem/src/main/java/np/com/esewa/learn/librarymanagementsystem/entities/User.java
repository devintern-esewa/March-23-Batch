package np.com.esewa.learn.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.Role;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> listOfBorrowedBooks;
    @Enumerated(EnumType.STRING)
    private Role role;
}
