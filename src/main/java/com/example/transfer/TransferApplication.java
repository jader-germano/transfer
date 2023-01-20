package com.example.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.transfer.repository")
@EntityScan(basePackages = {"com.example.transfer.model"})
@SpringBootApplication
public class TransferApplication {

	public static void main(String... args) {
		final SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(
				TransferApplication.class);
		appBuilder.profiles("common").run(args);

		//Creating the Logger object
		Logger logger = LoggerFactory.getLogger(TransferApplication.class);

		logger.info("TransferApp Started!");
	}

}
