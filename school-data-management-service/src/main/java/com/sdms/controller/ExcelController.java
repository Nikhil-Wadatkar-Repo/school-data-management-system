package com.sdms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sdms.entity.UserDetails;
import com.sdms.helper.ExcelReader;
import com.sdms.helper.ExcelWritter;

@RestController
public class ExcelController {
	@Autowired
	private ExcelWritter excelWritter;
	@Autowired
	private ExcelReader excelReader;

	@GetMapping("/downloadExcelFile")
	public ResponseEntity<Resource> downloadExcelFile() {
		try {
			ByteArrayInputStream bis = excelWritter.downloadTemplateForTeacherUpload();
			InputStreamResource fileResource = new InputStreamResource(bis);
			String fileName = "UserTemplate_" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()
					+ "-" + LocalDate.now().getYear();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
					.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(bis.available()).body(fileResource);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/upload")
	public List<UserDetails> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		List<UserDetails> userList = excelReader.readExcelFile(file);

		return userList;
	}

	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportUsersToExcel() throws IOException {
		ByteArrayInputStream in = excelWritter.exportUserDetails();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=UserBackup.xlsx");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(in));
	}
}
