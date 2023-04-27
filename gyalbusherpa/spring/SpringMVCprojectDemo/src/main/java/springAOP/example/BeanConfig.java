package springAOP.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "springAOP.example")
@EnableAspectJAutoProxy
public class BeanConfig {
}
