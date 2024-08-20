package com.sdms.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmailDTO {
	private String to, subject, body, filePath;
}
