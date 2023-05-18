package com.springboott.SBProject.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConf {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo){
        return args -> {
           Student nilima= new Student(

                        "Nilima",
                        "nilima@gmail.com",
                        LocalDate.of(1278, Month.JANUARY, 22)


                );

            Student nemo= new Student(

                    "Nemo",
                    "nemo@gmail.com",
                    LocalDate.of(1978, Month.AUGUST, 22)


            );

            repo.saveAll(
                    List.of(nilima, nemo)
            );

        };
    }
}
