package np.com.esewa.learn.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/15/2023
 * @project shopping-cart-micro-services
 * Security Configuration class
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests()
                        .requestMatchers("/","/api/auth/login").permitAll()
                        .anyRequest()
                        .authenticated()

                        .and()
                        .oauth2Login()
                        .permitAll()
                        .and()
                        .formLogin()
                        .permitAll()

                        .and()
                        .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/api/auth/login");

                    return http.build();
                }

}
