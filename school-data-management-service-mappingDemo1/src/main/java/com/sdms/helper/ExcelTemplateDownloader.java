package com.sdms.helper;

import com.sdms.entity.TeacherDetails;
import com.sdms.repo.SectionRepository;
import com.sdms.repo.StudentRepository;
import com.sdms.repo.TeacherRepository;
import com.sdms.service.TeacherService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExcelTemplateDownloader {
    @Autowired
    private TeacherService userService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SectionRepository sectionRepository;


    public ByteArrayInputStream downloadTemplateForTeacherUpload() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("User Data ");

            Row header = sheet.createRow(0);
            AtomicInteger i = new AtomicInteger();
            List<String> headers = Arrays.asList("name", "age", "email", "contacts", "city", "pincode", "userType", "username", "password", "status", "salary");
            headers.forEach((head) -> {
                header.createCell(i.get()).setCellValue(head);
                i.set(i.get() + 1);
            });
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }


    public ByteArrayInputStream downloadTemplateForStudentUpload() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Student Data ");
            Row header = sheet.createRow(0);
            AtomicInteger i = new AtomicInteger();


            List<String> headers = Arrays.asList("city", "contact", "dob", "email", "name", "pincode", "status", "studunid");
            headers.forEach((head) -> {
                header.createCell(i.get()).setCellValue(head);
                i.set(i.get() + 1);
            });
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream downloadTemplateForSectionUpload() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Student Data ");
            Row header = sheet.createRow(0);
            AtomicInteger i = new AtomicInteger();
            List<String> headers = Arrays.asList("section_name");
            headers.forEach((head) -> {
                header.createCell(i.get()).setCellValue(head);
                i.set(i.get() + 1);
            });
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream exportTeacherDetails() throws IOException {
        List<TeacherDetails> allUsers = userService.getAllTeachers();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Contact");
            headerRow.createCell(4).setCellValue("Age");
            headerRow.createCell(5).setCellValue("Status");
            headerRow.createCell(6).setCellValue("Type");
            headerRow.createCell(7).setCellValue("City");
            headerRow.createCell(7).setCellValue("City");

            int rowIndex = 1;
            for (TeacherDetails user : allUsers) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getTeacherId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getContact());
                row.createCell(4).setCellValue(user.getAge());
                row.createCell(5).setCellValue(user.getStatus());
                row.createCell(6).setCellValue(user.getUserType());
                row.createCell(7).setCellValue(user.getCity());
                row.createCell(8).setCellValue(user.getSalary());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
