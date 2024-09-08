package com.sdms;

//import com.sdms.controller.UniversalController;
//import com.sdms.helper.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolDataManagementServiceApplication implements CommandLineRunner {
//	@Autowired
//	private UniversalController controller;

	public static void main(String[] args) {
		SpringApplication.run(SchoolDataManagementServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("jiii3311");
	}
}
