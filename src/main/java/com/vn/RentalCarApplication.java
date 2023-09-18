package com.vn;

import com.vn.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class RentalCarApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalCarApplication.class, args);
	}
}
	