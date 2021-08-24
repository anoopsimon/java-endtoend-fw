package core.utils;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.*;
import java.util.List;

public class ExcelUtil {

    public static List<Row> getSheet(String filePath, String sheetName)
    {
        try (InputStream is = new FileInputStream(new File(filePath)); ReadableWorkbook wb = new ReadableWorkbook(is))
        {
            Sheet sheet = wb.getSheets().filter(wSheet-> wSheet.getName().equalsIgnoreCase(sheetName)).findFirst().orElseThrow(() -> new RuntimeException("Sheet "+ sheetName + "not found in workbook"));
            return sheet.read();

        } catch (Exception e) {
            throw new RuntimeException("Failed to read sheet from excel " + e.getMessage());
        }
    }
}
