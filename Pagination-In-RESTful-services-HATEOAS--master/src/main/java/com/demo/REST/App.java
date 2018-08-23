package com.demo.REST;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.REST.pojo.Employee;
import com.demo.REST.repository.EmployeeRepository;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App 
{
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    } 
     @Bean 
     CommandLineRunner initialData(EmployeeRepository repository) {
        	return (args) -> {
        		repository.save(new Employee(101, "Mayur Tripathi", "Trainee"));
        		repository.save(new Employee(102, "Satyen Singh", "Trainer"));
        		repository.save(new Employee(103, "Alok Ranjan", "Trainee"));
        		repository.save(new Employee(104, "Gaurav", "Trainee"));
        		repository.save(new Employee(105, "Udit", "Trainee"));
        		repository.save(new Employee(106, "Rahul", "Trainee"));
        		repository.save(new Employee(107, "Ripu", "Trainee"));
        	};
        
     }
    
}