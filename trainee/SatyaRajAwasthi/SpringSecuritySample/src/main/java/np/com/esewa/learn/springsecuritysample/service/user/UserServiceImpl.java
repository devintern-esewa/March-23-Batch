package np.com.esewa.learn.springsecuritysample.service.user;

import np.com.esewa.learn.springsecuritysample.entity.Role;
import np.com.esewa.learn.springsecuritysample.entity.User;
import np.com.esewa.learn.springsecuritysample.entity.enums.UserRole;
import np.com.esewa.learn.springsecuritysample.repository.UserRepository;
import np.com.esewa.learn.springsecuritysample.resources.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SatyaRajAwasth1 on 5/2/2023
 * Implementation class for user service
 */

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User userToBeSaved = new User();
        userToBeSaved.setEmail(userDto.getEmail());
        userToBeSaved.setPassword(passwordEncoder.encode(userDto.getPassword())); // store password in bycrypted form
        userToBeSaved.setUsername(userDto.getUsername());

        List<Role> userRoles = new ArrayList<>();
        Role role = new Role();
        role.setUserRoleName(UserRole.USER);
        userToBeSaved.setRoles(userRoles);

        userRepository.save(userToBeSaved);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
