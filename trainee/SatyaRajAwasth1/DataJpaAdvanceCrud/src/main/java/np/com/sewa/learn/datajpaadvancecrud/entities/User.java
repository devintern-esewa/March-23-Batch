package np.com.sewa.learn.datajpaadvancecrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import np.com.sewa.learn.datajpaadvancecrud.entities.enums.Role;

import java.util.List;

@Getter
@Setter
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> listOfBorrowedBooks;
    private Role role;
}
