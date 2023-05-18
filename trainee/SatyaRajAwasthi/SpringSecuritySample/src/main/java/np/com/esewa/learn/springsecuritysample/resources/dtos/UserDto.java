package np.com.esewa.learn.springsecuritysample.resources.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author SatyaRajAwasth1 on 5/2/2023
 * A helper DTO class for user to carry data on requests and responses
 * Primarly for during registeration process
 */
@Getter
@Setter
public class UserDto {

    @NotEmpty(message = "Username cannot be empty.")
    private String username;
    private String email;
    private String password;
}
