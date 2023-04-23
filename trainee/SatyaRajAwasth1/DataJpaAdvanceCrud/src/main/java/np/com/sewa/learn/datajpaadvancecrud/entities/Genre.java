package np.com.sewa.learn.datajpaadvancecrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "GENRES")
public class Genre {
    @Id
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> listOfBooks;
}
