package com.packt.carddatabase;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.packt.carddatabase.domain.*;


@SpringBootApplication
public class CarddatabaseApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(CarddatabaseApplication.class);
    
	@Autowired
	private CarRepository repository;
	
	@Autowired 
	private UserRepository urepository;

	 
	@Autowired
	private OwnerRepository orepository;
	
	 
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CarddatabaseApplication.class);
    }
	
	public static void main(String[] args) {
		//SpringApplication.run(CarddatabaseApplication.class, args);
		SpringApplication.run(CarddatabaseApplication.class, args);
		logger.info("Hello Spring Boot 2");
	} 
     
	@Bean

	CommandLineRunner runner() {

		return args -> {

			Owner owner1 = new Owner("John" , "Johnson");
			Owner owner2 = new Owner("Mary" , "Robinson");

			orepository.save(owner1);
			orepository.save(owner2);
  
			
			repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owner1));

			repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owner2));

			repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owner2));

			

			urepository.save(new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));

			urepository.save(new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN"));

		};

	}
} 

