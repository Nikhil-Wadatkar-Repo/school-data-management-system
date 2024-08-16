package com.sdms.controller;

import com.sdms.dto.ClassDetailsDTO;
import com.sdms.dto.ClassDetailsView;
import com.sdms.entity.ClassDetails;
import com.sdms.helper.EmailService;
import com.sdms.service.ClassDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/classDetails")
public class ClassDetailsController {

    @Autowired
    private ClassDetailsService teacherService;
    @Autowired
    private EmailService emailService;

    @GetMapping
    public ClassDetails get() {
        return new ClassDetails();
    }

    @GetMapping("/getAllClass")
    public List<ClassDetailsView> getAllClass() {
        return teacherService.getAllClassDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDetails> getTeacherById(@PathVariable("id") Long userId) {
        Optional<ClassDetails> user = teacherService.getClassDetailsById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createClass")
    public ClassDetails createClass(@RequestBody ClassDetailsDTO detailsDTO) {
        return teacherService.saveClassDetails(detailsDTO);
    }

    @PutMapping("/updateClass/{id}")
    public ResponseEntity<ClassDetails> updateClass(@PathVariable("id") Long userId, @RequestBody ClassDetailsDTO detailsDTO) {
        if (!teacherService.getClassDetailsById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
//        user.setTeacherId(userId);
        ClassDetails updatedTeacher = teacherService.saveClassDetails(detailsDTO);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/deleteClass/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") Long userId) {
        if (!teacherService.getClassDetailsById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        teacherService.deleteClassDetails(userId);
        return ResponseEntity.noContent().build();
    }

}