//package com.sdms.helper;
//
//import com.sdms.entity.SectionDetails;
//import com.sdms.entity.StudentDetails;
//import com.sdms.entity.TeacherDetails;
//import com.sdms.repo.SectionRepository;
//import com.sdms.repo.StudentRepository;
//import com.sdms.repo.TeacherRepository;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Component
//public class ExcelUploaderReader {
//    @Autowired
//    private TeacherRepository teacherRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private SectionRepository sectionRepository;
//
//    public List<TeacherDetails> uploadTeacher(MultipartFile file) throws IOException {
//        List<TeacherDetails> userList = new ArrayList<>();
//        try (InputStream is = file.getInputStream()) {
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
//            int rowCount = sheet.getPhysicalNumberOfRows() - 1; // as its considering extra row
//            Iterator<Row> rowIterator = sheet.iterator();
//            // Skip header row
//            int i = 0;
//            if (rowIterator.hasNext()) {
//                i += 1;
//                rowIterator.next();
//            }
//            while (rowIterator.hasNext() && i < rowCount) {
//                Row row = rowIterator.next();
//                TeacherDetails user = new TeacherDetails();
//                // see the order in which data is added in excel sheet
////				name	age	email	contacts	city	pincode	userType	username	password	status	salary
//
//                user.setName(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "");
//                user.setAge((int) row.getCell(1).getNumericCellValue());
//                user.setEmail(row.getCell(2).getStringCellValue());
//                user.setContact((long) row.getCell(3).getNumericCellValue());
//                user.setCity(row.getCell(4).getStringCellValue());
//                user.setPincode((long) row.getCell(5).getNumericCellValue());
//                user.setUserType(row.getCell(6).getStringCellValue());
//                user.setUsername(row.getCell(7).getStringCellValue());
//                user.setPassword(row.getCell(8).getStringCellValue());
//                user.setStatus(row.getCell(9).getStringCellValue());
//                user.setSalary((int) row.getCell(10).getNumericCellValue());
//                userList.add(user);
//                i += 1;
//            }
//            workbook.close();
//        }
//        try {
//            teacherRepository.saveAll(userList);
//            System.out.println("Uploaded successfully");
//        } catch (RuntimeException e) {
//            System.out.println(" Error while uploading the teachers");
//        }
//        return userList;
//    }
//
//    public List<StudentDetails> uploadStudent(MultipartFile file) throws IOException {
//
//        List<StudentDetails> userList = new ArrayList<>();
//        try (InputStream is = file.getInputStream()) {
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
//            int rowCount = sheet.getPhysicalNumberOfRows() - 1; // as its considering extra row
//            Iterator<Row> rowIterator = sheet.iterator();
//            // Skip header row
//            int i = 0;
//            if (rowIterator.hasNext()) {
//                i += 1;
//                rowIterator.next();
//            }
//            while (rowIterator.hasNext() && i < rowCount) {
//                Row row = rowIterator.next();
//                StudentDetails user = new StudentDetails();
//                // see the order in which data is added in excel sheet
////				city	contact	dob	email	name	pincode	status	studunid
//
//                user.setCity(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "");
//                user.setContact((long) row.getCell(1).getNumericCellValue());
//                user.setDob(row.getCell(2).getStringCellValue());
//                user.setEmail(row.getCell(3).getStringCellValue());
//                user.setName(row.getCell(4).getStringCellValue());
//                user.setPincode((long) row.getCell(5).getNumericCellValue());
//                user.setStatus(row.getCell(6).getStringCellValue());
//                user.setStudUNID(row.getCell(7).getStringCellValue());
//                userList.add(user);
//                i += 1;
//            }
//            workbook.close();
//        }
//        try {
//            studentRepository.saveAll(userList);
//            System.out.println("Uploaded successfully");
//        } catch (RuntimeException e) {
//            System.out.println(" Error while uploading the Students");
//        }
//        return userList;
//    }
//
//    public List<SectionDetails> uploadSection(MultipartFile file) throws IOException {
//
//        List<SectionDetails> userList = new ArrayList<>();
//        try (InputStream is = file.getInputStream()) {
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
//            int rowCount = sheet.getPhysicalNumberOfRows() - 1; // as its considering extra row
//            Iterator<Row> rowIterator = sheet.iterator();
//            // Skip header row
//            int i = 0;
//            if (rowIterator.hasNext()) {
//                i += 1;
//                rowIterator.next();
//            }
//            while (rowIterator.hasNext() && i < rowCount) {
//                Row row = rowIterator.next();
//                SectionDetails user = new SectionDetails();
//                // see the order in which data is added in excel sheet
////				city	contact	dob	email	name	pincode	status	studunid
//
//                user.setSectionName(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "");
//                userList.add(user);
//                i += 1;
//            }
//            workbook.close();
//        }
//        return userList;
//    }
//}
