package com.sdms.controller;

import com.sdms.entity.ClassDetails;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.StudentDetails;
import com.sdms.entity.TeacherDetails;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classDetails")
public class ClassDetailsController {

    @Autowired
    private ClassDetailsRepository classDetailsRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ClassDetails get() {
        ClassDetails details = new ClassDetails();
List<StudentDetails> studentDetails= Arrays.asList(new StudentDetails(),new StudentDetails()
);
        return ClassDetails.builder()
                .standard(12).
                classUNID("DD12").
                noOfStudents(12).
                presentStudents(334).
                year(5455L).
                commentList(studentDetails).
                classTeacherName(TeacherDetails.builder().build()).
                section(SectionDetails.builder().build()).build();
    }

    @GetMapping("/getAllClass")
    public List<ClassDetails> getAllClass() {
        return classDetailsRepository.findAll();
    }

    @PostMapping("/createClass")
    public ClassDetails createClass(@RequestBody ClassDetails detailsDTO) {
        return classDetailsRepository.save(detailsDTO);
    }

    @DeleteMapping("/deleteChildByID/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") Long userId) {
        studentRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}