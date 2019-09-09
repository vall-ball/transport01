package ru.vallball.transport01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SpringDataRestApplication {
	public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
	}

}
