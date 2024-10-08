package com.sdms.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ExcelFileInfo {
    private String fileName;
    private String dataSheetName;
    private List<String> headers;
}
