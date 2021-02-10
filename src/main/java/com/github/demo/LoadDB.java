package com.github.demo;

import com.github.demo.model.Employee;
import com.github.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.save(new Employee("Bilbo", 20000, 20));
            employeeRepository.save(new Employee("Frodo", 30000, 30));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
        };
    }
}
