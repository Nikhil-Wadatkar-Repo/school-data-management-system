//package com.sdms.controller;
//
//import com.sdms.entity.TeacherDetails;
//import com.sdms.helper.EmailService;
//import com.sdms.service.TeacherService;
//
//import jakarta.mail.MessagingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/teachers")
//public class TeacherController {
//
//	@Autowired
//	private TeacherService teacherService;
//	@Autowired
//	private EmailService emailService;
//
//	@GetMapping
//	public TeacherDetails get() {
//		return new TeacherDetails();
//	}
//
//	@GetMapping("/getAll")
//	public List<TeacherDetails> getAllTeachers() {
//		return teacherService.getAllTeachers();
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<TeacherDetails> getTeacherById(@PathVariable("id") Long userId) {
//		Optional<TeacherDetails> user = teacherService.getTeacherById(userId);
//		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	}
//
//	@PostMapping("/create")
//	public TeacherDetails createTeacher(@RequestBody TeacherDetails user) {
//		return teacherService.saveTeacher(user);
//	}
//
//	@PutMapping("/update/{id}")
//	public ResponseEntity<TeacherDetails> updateTeacher(@PathVariable("id") Long userId, @RequestBody TeacherDetails user) {
//		if (!teacherService.getTeacherById(userId).isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
////        user.setTeacherId(userId);
//		TeacherDetails updatedTeacher = teacherService.saveTeacher(user);
//		return ResponseEntity.ok(updatedTeacher);
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long userId) {
//		if (!teacherService.getTeacherById(userId).isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		teacherService.deleteTeacher(userId);
//		return ResponseEntity.noContent().build();
//	}
//
//	@GetMapping("/send-email")
//	public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text,
//			@RequestParam String attachmentPath) {
//
//		try {
//			emailService.sendEmailWithAttachment(to, subject, text, attachmentPath);
//			return "Email sent successfully!";
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return "Failed to send email.";
//		}
//	}
//}