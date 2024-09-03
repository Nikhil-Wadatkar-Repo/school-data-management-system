package com.sdms.controller;

import com.sdms.dto.*;
import com.sdms.entity.*;
import com.sdms.helper.EmailService;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.ExamRepo;
import com.sdms.repo.SectionRepository;
import com.sdms.repo.StudentRepository;
//import com.sdms.service.ClassDetailsService;
import com.sdms.service.SectionService;
import com.sdms.service.TeacherService;
import com.sdms.service.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RequestMapping
@RestController
@CrossOrigin
public class UniversalController {
    @Autowired
    private ClassDetailsRepository classDetailsRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExamRepo examRepo;

    @GetMapping("/saveDetails")
    public List<ClassDetails> saveClass() {
        ClassDetails save1 = new ClassDetails(),save2 =new ClassDetails();
        try {
            ExamDetails examDetails1 = ExamDetails.builder()
                    .examName("Unit test 1")
                    .subject1Name("sub1")
                    .subject1ObtainedMarks(10)
                    .subject1totalMarks(100)
                    .build();
            ExamDetails examDetails2 = ExamDetails.builder()
                    .examName("Unit test 2")
                    .subject1Name("sub1")
                    .subject1ObtainedMarks(10)
                    .subject1totalMarks(100)
                    .subject2Name("sub2")
                    .subject2ObtainedMarks(10)
                    .subject2totalMarks(100)
                    .build();

            List<ExamDetails> exams = Arrays.asList(examDetails1, examDetails2);

            StudentDetails studentDetails1 = StudentDetails.builder()
                    .exams(exams)
                    .name("Ankur")
                    .city("Morshi")
                    .dob("04/06/1993")
                    .contact(123L)
                    .email("ankur@test.com")
                    .pincode(111250l)
                    .studUNID("ANK!123")
                    .status("active")
                    .build();
            StudentDetails studentDetails2 = StudentDetails.builder()
                    .exams(exams)
                    .name("Dhanu")
                    .city("Morshi")
                    .dob("04/06/1993")
                    .contact(123L)
                    .email("dhanu@test.com")
                    .pincode(111250l)
                    .studUNID("DHA!123")
                    .status("active")
                    .build();
            SectionDetails sectionDetails1 = SectionDetails.builder()
                    .year(2025)
                    .sectionName("A")
                    .status("active")
                    .students(Arrays.asList(studentDetails1, studentDetails2))
                    .build();
            SectionDetails sectionDetails2 = SectionDetails.builder()
                    .year(2025)
                    .sectionName("A")
                    .status("active")
                    .students(Arrays.asList(studentDetails1, studentDetails2))
                    .build();

            ClassDetails classDetails = ClassDetails.builder()
                    .classUNID("A1")
                    .year(2012L)
                    .presentStudents(20)
                    .noOfStudents(2)
                    .standard(12)
                    .sections(Arrays.asList(sectionDetails1, sectionDetails2))
                    .build();
            ClassDetails classDetails1 = ClassDetails.builder()
                    .classUNID("A2")
                    .year(2042L)
                    .presentStudents(50)
                    .noOfStudents(2)
                    .standard(15)
                    .sections(Arrays.asList(sectionDetails1, sectionDetails2))
                    .build();
             save1 = classDetailsRepository.save(classDetails);
            System.out.println("Class 1 saved");
             save2 = classDetailsRepository.save(classDetails1);
            System.out.println("Class 2 saved");


        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return Arrays.asList(save1,save2);
    }

    public  SectionDetails createNewSection(NewSectionDTO dto){
        return sectionRepository.save(SectionDetails.builder().status("active").year(dto.getYear()).sectionName(dto.getSectionName()).build());
    }
    public SectionDetails updateSectionDetails(SectionDetails newsectionDetails){
        return getSectionDetailsById(newsectionDetails.getSectionID());
    }

    private SectionDetails getSectionDetailsById(Integer sectionId) {
        Optional<SectionDetails> optionalSectionDetails = sectionRepository.findById((long) sectionId);
        SectionDetails sectionDetails =optionalSectionDetails.isPresent()?optionalSectionDetails.get():null;
        return sectionDetails;
    }
}
