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
    @Column(name = "users_id")
    private long id;

    @Column(name = "users_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "users_role")
    private Role role;
}
