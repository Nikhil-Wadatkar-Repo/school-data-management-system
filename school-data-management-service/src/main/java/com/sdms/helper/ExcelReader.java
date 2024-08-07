package com.sdms.helper;

import com.sdms.entity.UserDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelReader {
	public List<UserDetails> readExcelFile(MultipartFile file) throws IOException {
		List<UserDetails> userList = new ArrayList<>();
		try (InputStream is = file.getInputStream()) {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
			int rowCount = sheet.getPhysicalNumberOfRows() - 1; // as its considering extra row
			Iterator<Row> rowIterator = sheet.iterator();
			// Skip header row
			int i = 0;
			if (rowIterator.hasNext()) {
				i += 1;
				rowIterator.next();
			}
			while (rowIterator.hasNext() && i < rowCount) {
				Row row = rowIterator.next();
				UserDetails user = new UserDetails();
				// see the order in which data is added in excel sheet
				user.setName(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "");
				user.setAge((int) row.getCell(1).getNumericCellValue());
				user.setEmail(row.getCell(2).getStringCellValue());
				user.setContact((long) row.getCell(3).getNumericCellValue());
				user.setCity(row.getCell(4).getStringCellValue());
				user.setPincode((long) row.getCell(5).getNumericCellValue());
				user.setUserType(row.getCell(6).getStringCellValue());
				user.setUsername(row.getCell(7).getStringCellValue());
				user.setPassword(row.getCell(8).getStringCellValue());
				user.setStatus(row.getCell(9).getStringCellValue());
				user.setSalary((int) row.getCell(10).getNumericCellValue());
				userList.add(user);
				i += 1;
			}
			workbook.close();
		}
		return userList;
	}
}
