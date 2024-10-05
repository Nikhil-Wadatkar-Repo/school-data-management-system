//package com.sdms.controller;
//
//import com.sdms.entity.SectionDetails;
//import com.sdms.helper.EmailService;
//import com.sdms.service.SectionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/sectionDetails")
//public class SectionDetailsController {
//
//    @Autowired
//    private SectionService teacherService;
//    @Autowired
//    private EmailService emailService;
//
//    @GetMapping
//    public SectionDetails get() {
//        return new SectionDetails();
//    }
//
//    @GetMapping("/getAllSections")
//    public List<SectionDetails> getAllSections() {
//        return teacherService.getAllSectionDetails();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SectionDetails> getSectionById(@PathVariable("id") Long sectionId) {
//        Optional<SectionDetails> user = teacherService.getSectionDetailsById(sectionId);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping("/createSection")
//    public SectionDetails createSection(@RequestBody SectionDetails sectionDetails) {
//        return teacherService.saveSectionDetails(sectionDetails);
//    }
//
//    @PutMapping("/updateSection/{id}")
//    public ResponseEntity<SectionDetails> updateSection(@PathVariable("id") Long sectionId, @RequestBody SectionDetails sectionDetails) {
//        if (!teacherService.getSectionDetailsById(sectionId).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
////        user.setTeacherId(userId);
//        SectionDetails updatedTeacher = teacherService.saveSectionDetails(sectionDetails);
//        return ResponseEntity.ok(updatedTeacher);
//    }
//
//    @DeleteMapping("/deleteSection/{id}")
//    public ResponseEntity<Void> deleteSection(@PathVariable("id") Long sectionId) {
//        if (!teacherService.getSectionDetailsById(sectionId).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        teacherService.deleteSectionDetails(sectionId);
//        return ResponseEntity.noContent().build();
//    }
//
//}