package np.com.esewa.learn.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "publishers")
public class Publisher {
    @Id
    @Column(name = "publishers_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "publishers_name")
    private String name;
}
