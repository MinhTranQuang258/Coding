package com.sps.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class StudySpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringDataJpaApplication.class, args);
	}
}
