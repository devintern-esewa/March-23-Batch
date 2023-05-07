package np.com.esewa.learn.springsecuritysample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import np.com.esewa.learn.springsecuritysample.entity.enums.UserRole;

/**
 * @author Thinkpad on 5/2/2023
 * Entity Role for User Role
 */
@Getter
@Setter
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private byte id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role_name", unique = true)
    private UserRole userRoleName;
}
