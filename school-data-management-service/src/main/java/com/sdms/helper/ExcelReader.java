package com.sdms.helper;

import com.sdms.entity.UserDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public List<UserDetails> readExcelFile(String filePath) throws IOException {
        List<UserDetails> userList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                UserDetails user = new UserDetails();

                // Assuming columns are in the following order: userId, name, age, email, username, password, usertype
                user.setUserId((long) row.getCell(0).getNumericCellValue());
                user.setName(row.getCell(1).getStringCellValue());
                user.setAge((int) row.getCell(2).getNumericCellValue());
                user.setEmail(row.getCell(3).getStringCellValue());
                user.setUsername(row.getCell(4).getStringCellValue());
                user.setPassword(row.getCell(5).getStringCellValue());
                user.setUserType(row.getCell(6).getStringCellValue());

                userList.add(user);
            }
        }
        return userList;
    }
}
