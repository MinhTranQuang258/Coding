package com.sps.vn;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class StudySpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringBatchApplication.class, args);
	}
}
