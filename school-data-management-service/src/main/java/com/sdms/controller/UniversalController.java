package com.sdms.controller;

import com.sdms.dto.*;
import com.sdms.entity.*;
import com.sdms.helper.EmailService;
import com.sdms.service.ClassDetailsService;
import com.sdms.service.SectionService;
import com.sdms.service.TeacherService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/uni")
@RestController
@CrossOrigin
public class UniversalController {
    @Autowired
    private ClassDetailsService classDetailsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SectionService sectionService;

    @GetMapping("/getDistinctStandards")
    public List<Integer> getDistinctStandards() {
        return classDetailsService.getDistinctStandards();
    }

    @GetMapping("/getDistinctYears")
    public List<Long> getDistinctYears() {
        return classDetailsService.getDistinctYears();
    }

    @GetMapping("/getDistinctSections")
    public List<SectinoView> getDistinctSections() {
        return classDetailsService.getDistinctSections();
    }

    @GetMapping("/getSectionYearByStandard/{class}")
    public ResponseEntity<SectionYearListView> getSectionYearByStandard(@PathVariable("class") Integer clas) {
        return ResponseEntity.ok(classDetailsService.getSectionYearByStandard(clas));
    }


    @PostMapping("/getFilteredClass")
    public List<ClassDetailsView> getFilteredClass(@RequestBody FilteredClassReq filteredClassReq) {
        return classDetailsService.getFilteredClass(filteredClassReq);
    }
    @PostMapping("/getFilteredStudent")
    public List<StudentDetails> getFilteredStudent(@RequestBody FilteredClassReq filteredClassReq) {
        return classDetailsService.getFilteredStudents(filteredClassReq);
    }

    @GetMapping("/getAllClass")
    public List<ClassDetailsView> getAllClass() {
        return classDetailsService.getAllClassDetailsView();
    }

    @GetMapping("/getClassById/{id}")
    public ResponseEntity<ClassDetails> getClassById(@PathVariable("id") Long userId) {
        Optional<ClassDetails> user = classDetailsService.getClassDetailsById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getStudentsClassById/{id}")
    public ResponseEntity<List<StudentDetails>> getStudentsClassById(@PathVariable("id") Long userId) {
        List<StudentDetails> user = classDetailsService.getStudentsByClassId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{studid}")
    public ResponseEntity<StudentDetails> getStudentClassById(                                                              @PathVariable("studid") Long studid) {
        StudentDetails user = classDetailsService.getStudentById( studid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/createClass")
    public ClassDetails createClass(@RequestBody ClassDetailsDTO detailsDTO) {
        return classDetailsService.saveClassDetails(detailsDTO);
    }

    @PutMapping("/updateClass/{id}")
    public ResponseEntity<ClassDetails> updateClass(@PathVariable("id") Long userId, @RequestBody ClassDetailsDTO detailsDTO) {
        if (!classDetailsService.getClassDetailsById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
//        user.setTeacherId(userId);
        ClassDetails updatedTeacher = classDetailsService.saveClassDetails(detailsDTO);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/deleteClass/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") Long userId) {
        if (!classDetailsService.getClassDetailsById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        classDetailsService.deleteClassDetails(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/getAllSections")
    public List<SectionDetails> getAllSections() {
        return sectionService.getAllSectionDetails();
    }

    @GetMapping("/getSectionById/{id}")
    public ResponseEntity<SectionDetails> getSectionById(@PathVariable("id") Long sectionId) {
        Optional<SectionDetails> user = sectionService.getSectionDetailsById(sectionId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createSection")
    public SectionDetails createSection(@RequestBody SectionDetails sectionDetails) {
        return sectionService.saveSectionDetails(sectionDetails);
    }

    @PutMapping("/updateSection/{id}")
    public ResponseEntity<SectionDetails> updateSection(@PathVariable("id") Long sectionId, @RequestBody SectionDetails sectionDetails) {
        if (!sectionService.getSectionDetailsById(sectionId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
//        user.setTeacherId(userId);
        SectionDetails updatedTeacher = sectionService.saveSectionDetails(sectionDetails);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/deleteSection/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable("id") Long sectionId) {
        if (!sectionService.getSectionDetailsById(sectionId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sectionService.deleteSectionDetails(sectionId);
        return ResponseEntity.noContent().build();
    }


    @Autowired
    private TeacherService teacherService;


//    @GetMapping
//    public TeacherDetails getTeacher() {
//        return new TeacherDetails();
//    }

    @GetMapping("/getAllTeachers")
    public List<TeacherDetails> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/getTeacherById/{id}")
    public ResponseEntity<TeacherDetails> getTeacherById(@PathVariable("id") Long userId) {
        Optional<TeacherDetails> user = teacherService.getTeacherById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createTeacher")
    public TeacherDetails createTeacher(@RequestBody TeacherDetails user) {
        return teacherService.saveTeacher(user);
    }

    @PutMapping("/updateTeacher/{id}")
    public ResponseEntity<TeacherDetails> updateTeacher(@PathVariable("id") Long userId, @RequestBody TeacherDetails user) {
        if (!teacherService.getTeacherById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
//        user.setTeacherId(userId);
        TeacherDetails updatedTeacher = teacherService.saveTeacher(user);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/deleteTeacherByID/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long userId) {
        if (!teacherService.getTeacherById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        teacherService.deleteTeacher(userId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/send-email")
//    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text,
//                            @RequestParam String attachmentPath) {
//
//        try {
//            emailService.sendEmailWithAttachment(to, subject, text, attachmentPath);
//            return "Email sent successfully!";
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Failed to send email.";
//        }
//    }

//    public Class_1_Details

}
