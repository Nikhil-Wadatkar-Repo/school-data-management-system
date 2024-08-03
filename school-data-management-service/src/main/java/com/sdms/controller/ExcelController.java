package com.sdms.controller;


import com.sdms.dto.ExcelFileInfo;
import com.sdms.helper.ExcelWritter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class ExcelController {
    @Autowired
    private ExcelWritter excelWritter;

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadExcelFile(@RequestBody ExcelFileInfo excelFileInfo) {
        try {
            ByteArrayInputStream bis = excelWritter.downloadTemplateForTeacherUpload(excelFileInfo);
            InputStreamResource fileResource = new InputStreamResource(bis);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+excelFileInfo.getFileName()+".xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(bis.available())
                    .body(fileResource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportUsersToExcel() throws IOException {
        ByteArrayInputStream in = excelWritter.exportUserDetails();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=UserBackup.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }
}
