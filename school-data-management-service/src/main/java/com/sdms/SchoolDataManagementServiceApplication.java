package com.sdms;

import com.sdms.helper.ExcelWritter;
import com.sdms.helper.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SchoolDataManagementServiceApplication implements CommandLineRunner {
    @Autowired
    private PdfGenerator pdfGenerator;


    public static void main(String[] args) {
        SpringApplication.run(SchoolDataManagementServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        pdfGenerator.generatePdfReport();

    }
}
