package com.sdms;

import com.sdms.controller.ManyToOneControllerTest;
import com.sdms.helper.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolDataManagementServiceApplication {
    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private ManyToOneControllerTest test;

    public static void main(String[] args) {
        SpringApplication.run(SchoolDataManagementServiceApplication.class, args);
    }


}
