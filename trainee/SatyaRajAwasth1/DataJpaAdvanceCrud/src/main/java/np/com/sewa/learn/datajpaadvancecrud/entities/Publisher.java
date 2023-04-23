package np.com.sewa.learn.datajpaadvancecrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "PUBLISHERS")
public class Publisher {
    @Id
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> listOfBooksPublished;
}
