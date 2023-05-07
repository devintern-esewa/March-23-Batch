package np.com.esewa.learn.springsecuritysample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author SatyaRajAwasth1 on 4/30/2023
 * @project SpringSecuritySample
 */

@Configuration
@EnableWebSecurity
public class SampleSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                            try {
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers("/api", "api/hi","/register").permitAll()
                                        .anyRequest().authenticated()
                                        .and()

                                        .formLogin()
                                        .loginPage("/login")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        .permitAll()
                                        .and()

                                        .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .logoutSuccessHandler(logout.getLogoutSuccessHandler())
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                        )

                                        .httpBasic(Customizer.withDefaults());

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }

    // for checking username and password with in memory databse h2 db
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails userDetails = User.builder()
                .username("satya")
                .password(passwordEncoder().encode("password"))
                .roles()
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
