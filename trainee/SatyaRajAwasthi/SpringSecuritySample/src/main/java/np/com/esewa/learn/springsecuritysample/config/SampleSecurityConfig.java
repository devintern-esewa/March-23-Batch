package np.com.esewa.learn.springsecuritysample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                                        .permitAll()
                                        .and()
                                        .logout()
                                        .and();
//                                        .httpBasic();

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }
}
