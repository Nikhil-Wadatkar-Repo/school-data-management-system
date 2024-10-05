//package com.sdms.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//
//import com.sdms.entity.SectionDetails;
//import com.sdms.entity.StudentDetails;
//import com.sdms.repo.SectionRepository;
//import com.sdms.repo.StudentRepository;
//import com.sdms.repo.TeacherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.sdms.entity.TeacherDetails;
//import com.sdms.helper.ExcelUploaderReader;
//import com.sdms.helper.ExcelTemplateDownloader;
//
//@RestController
//public class ExcelController {
//    @Autowired
//    private ExcelTemplateDownloader excelWritter;
//    @Autowired
//    private ExcelUploaderReader excelReader;
//    @Autowired
//    private TeacherRepository teacherRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private SectionRepository sectionRepository;
//
//    @GetMapping("/downloadTemplateForTeacherUpload")
//    public ResponseEntity<Resource> downloadTemplateForTeacherUpload() {
//        try {
//            ByteArrayInputStream bis = excelWritter.downloadTemplateForTeacherUpload();
//            InputStreamResource fileResource = new InputStreamResource(bis);
//            String fileName = "UserTemplate_" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()
//                    + "-" + LocalDate.now().getYear();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(bis.available()).body(fileResource);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping("/downloadTemplateForStudentUpload")
//    public ResponseEntity<Resource> downloadTemplateForStudentUpload() {
//        try {
//            ByteArrayInputStream bis = excelWritter.downloadTemplateForStudentUpload();
//            InputStreamResource fileResource = new InputStreamResource(bis);
//            String fileName = "StudentTemplate_" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()
//                    + "-" + LocalDate.now().getYear();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(bis.available()).body(fileResource);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping("/downloadTemplateForSectionUpload")
//    public ResponseEntity<Resource> downloadTemplateForSectionUpload() {
//        try {
//            ByteArrayInputStream bis = excelWritter.downloadTemplateForSectionUpload();
//            InputStreamResource fileResource = new InputStreamResource(bis);
//            String fileName = "SectionTemplate_" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()
//                    + "-" + LocalDate.now().getYear();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(bis.available()).body(fileResource);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PostMapping("/uploadTeachers")
//    public List<TeacherDetails> uploadTeachers(@RequestParam("file") MultipartFile file) throws IOException {
//        List<TeacherDetails> userList = excelReader.uploadTeacher(file);
//
//        return userList;
//    }
//
//    @PostMapping("/uploadStudents")
//    public List<StudentDetails> uploadStudents(@RequestParam("file") MultipartFile file) throws IOException {
//        List<StudentDetails> userList = excelReader.uploadStudent(file);
//        return userList;
//    }
//
//    @PostMapping("/uploadSection")
//    public List<SectionDetails> uploadSection(@RequestParam("file") MultipartFile file) throws IOException {
//        List<SectionDetails> userList = excelReader.uploadSection(file);
//        return userList;
//    }
//
//    @GetMapping("/export")
//    public ResponseEntity<InputStreamResource> exportUsersToExcel() throws IOException {
//        ByteArrayInputStream in = excelWritter.exportTeacherDetails();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=UserBackup.xlsx");
//
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(new InputStreamResource(in));
//    }
//}
