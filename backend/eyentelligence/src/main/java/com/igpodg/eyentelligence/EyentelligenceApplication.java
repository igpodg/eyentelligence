package com.igpodg.eyentelligence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(repositoryBaseClass = JpaRefreshRepositoryImpl.class)
public class EyentelligenceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EyentelligenceApplication.class, args);
	}
}
