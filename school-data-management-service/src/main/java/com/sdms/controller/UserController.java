package com.sdms.controller;

import com.sdms.entity.UserDetails;
import com.sdms.helper.EmailService;
import com.sdms.service.UserService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	@GetMapping
	public UserDetails get() {
		return new UserDetails();
	}

	@GetMapping("/getAllUsers")
	public List<UserDetails> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDetails> getUserById(@PathVariable("id") Long userId) {
		Optional<UserDetails> user = userService.getUserById(userId);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/createUser")
	public UserDetails createUser(@RequestBody UserDetails user) {
		return userService.saveUser(user);
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDetails> updateUser(@PathVariable("id") Long userId, @RequestBody UserDetails user) {
		if (!userService.getUserById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
//        user.setUserId(userId);
		UserDetails updatedUser = userService.saveUser(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
		if (!userService.getUserById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/send-email")
	public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text,
			@RequestParam String attachmentPath) {

		try {
			emailService.sendEmailWithAttachment(to, subject, text, attachmentPath);
			return "Email sent successfully!";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Failed to send email.";
		}
	}
}