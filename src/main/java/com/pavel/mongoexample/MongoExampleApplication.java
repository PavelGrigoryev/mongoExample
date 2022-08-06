package com.pavel.mongoexample;

import com.pavel.mongoexample.entity.Customer;
import com.pavel.mongoexample.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MongoExampleApplication implements CommandLineRunner {

    private final CustomerRepository repository;

    public MongoExampleApplication(CustomerRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoExampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();

        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));
        repository.save(new Customer("Alice", "Cooper"));

        log.warn("Customers found with findAll():");
        log.warn("-------------------------------");
        for (Customer customer : repository.findAll()) {
            log.warn(String.valueOf(customer));
        }
        log.warn("");

        log.warn("Customer found with findByFirstName('Alice'):");
        log.warn("--------------------------------");
        for (Customer customer : repository.findByFirstName("Alice")) {
            log.warn(String.valueOf(customer));
        }

        log.warn("Customers found with findByLastName('Smith'):");
        log.warn("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            log.warn(String.valueOf(customer));
        }
    }
}
