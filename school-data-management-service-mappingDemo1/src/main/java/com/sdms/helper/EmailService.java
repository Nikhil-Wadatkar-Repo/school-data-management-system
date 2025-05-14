package com.sdms.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {
	  @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendEmailWithAttachment(String to, String subject, String text, String attachmentPath) throws MessagingException {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(text);

	        // Add attachment
			if(!attachmentPath.isEmpty()) {
				Resource file = new ClassPathResource(attachmentPath);
				helper.addAttachment(file.getFilename(), file);
			}

	        javaMailSender.send(mimeMessage);
	    }
}
