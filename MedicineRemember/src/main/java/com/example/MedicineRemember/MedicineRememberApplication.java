package com.example.MedicineRemember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicineRememberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineRememberApplication.class, args);
	}

}
