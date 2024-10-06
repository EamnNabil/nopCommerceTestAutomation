package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file); // XSSFWorkbook for .xlsx files
        Sheet sheet = workbook.getSheet(sheetName);
        List<Object[]> data = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            Object[] rowData = new Object[row.getPhysicalNumberOfCells()];
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                rowData[i] = row.getCell(i).toString();
            }
            data.add(rowData);
        }

        workbook.close();
        file.close();

        return data.toArray(new Object[0][]);
    }
}

