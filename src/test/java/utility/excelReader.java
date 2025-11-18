package utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class excelReader {

    public static Object[][] getdata(String sheetname) throws IOException {
        String sheetName = sheetname;  // ✅ hardcode or read from config

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testData.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum();
        int totalCols = sheet.getRow(0).getLastCellNum();

        Object[][] testData = new Object[totalRows][totalCols];
        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));

            }}
                workbook.close();
                fis.close();
                return testData;
            }


        }
