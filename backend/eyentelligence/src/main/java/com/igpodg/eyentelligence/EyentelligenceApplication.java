package com.igpodg.eyentelligence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class EyentelligenceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EyentelligenceApplication.class, args);
	}
}
