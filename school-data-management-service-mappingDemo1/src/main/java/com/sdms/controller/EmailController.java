package com.sdms.controller;

import com.sdms.helper.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;
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
