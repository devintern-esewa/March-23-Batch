package np.com.esewa.learn.springsecuritysample.service.user;

import np.com.esewa.learn.springsecuritysample.entity.User;
import np.com.esewa.learn.springsecuritysample.resources.dtos.UserDto;

/**
 * @author Thinkpad on 5/2/2023
 * @project SpringSecuritySample
 */
public interface UserService {
    public void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    User findUserByUsername(String username);

}
