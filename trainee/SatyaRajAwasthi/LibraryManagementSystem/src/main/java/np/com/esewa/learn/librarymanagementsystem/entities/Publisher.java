package np.com.esewa.learn.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "publishers")
public class Publisher {
    @Id
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> listOfBooksPublished;
}
