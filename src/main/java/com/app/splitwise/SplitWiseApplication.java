package com.app.splitwise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SplitWiseApplication.class, args);
		System.out.println("SplitWise Application");
	}

	@Override
	public void run(String... args) throws Exception {
     System.out.println(passwordEncoder.encode("Ankit@123"));
	}
}
