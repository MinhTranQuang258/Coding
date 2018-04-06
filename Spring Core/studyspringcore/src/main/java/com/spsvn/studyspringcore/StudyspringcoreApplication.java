package com.spsvn.studyspringcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StudyspringcoreApplication {

	public static void main(String[] args) {
		SpringApplication sa= new SpringApplication(StudyspringcoreApplication.class);
		ConfigurableApplicationContext context= sa.run(args);
		Circle circle= context.getBean(Circle.class);
		circle.draw();
		circle.publishPoint();
	}
}
