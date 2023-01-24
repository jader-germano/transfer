package com.example.transfer;

import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.transfer.repository")
@EntityScan(basePackages = {"com.example.transfer.model"})
@SpringBootApplication
public class TransferApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(TransferApplication.class);

	private final Environment env;

	public TransferApplication(Environment env) {
		this.env = env;
	}

	@Override
	public void run(String... args) {
		logger.info("{} Started!", env.getProperty("app.name"));
	}
	public static void main(String... args) {
		final SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(
				TransferApplication.class);
		appBuilder.profiles("dev").run(args);
	}

}
