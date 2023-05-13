package np.com.esewa.learn.springsecuritysample.service.security;

import np.com.esewa.learn.springsecuritysample.entity.User;
import np.com.esewa.learn.springsecuritysample.service.user.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Thinkpad on 5/4/2023
 * @project SpringSecuritySample
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        String emailPattern = "^(.+)@(\\S+)$";
        User user = Pattern.matches(emailPattern, usernameOrEmail) ?
                userService.findUserByEmail(usernameOrEmail) :
                userService.findUserByUsername(usernameOrEmail);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(String.valueOf(role.getUserRoleName())))
                            .collect(Collectors.toList())
            );
        }
        else {
            throw new UsernameNotFoundException("Invalid credentials");
        }

    }
}
