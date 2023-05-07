package np.com.esewa.learn.springsecuritysample.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Thinkpad on 5/2/2023
 * Entity User
 */

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_name")
    private String username;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;

    public User(String username, String email, String password, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
