package core.utils;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelUtil {

    /**
     * Read an excel workbook , and return the sheet provided in sheetName parameter
     * @param filePath
     * @param sheetName
     * @return sheet as a list of rows
     */
    public static List<Row> getSheet(String filePath, String sheetName)
    {
        try (InputStream is = new FileInputStream(filePath); ReadableWorkbook wb = new ReadableWorkbook(is))
        {
            Sheet sheet = wb.getSheets().filter(wSheet-> wSheet.getName().equalsIgnoreCase(sheetName)).findFirst().orElseThrow(() -> new RuntimeException("Sheet "+ sheetName + "not found in workbook"));
            return sheet.read();

        } catch (Exception e) {
            throw new RuntimeException("Failed to read sheet from excel =>" + e.getMessage());
        }
    }

    public static List<String> sheetNames(String filePath){
        try (InputStream is = new FileInputStream(filePath); ReadableWorkbook wb = new ReadableWorkbook(is))
        {
            return wb.getSheets().map(x->x.getName()).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to read sheet from excel =>" + e.getMessage());
        }
    }

    /**
     * get all file names
     * @param filePath
     * @return
     */
    public static ReadableWorkbook getWorkbook(String filePath)
    {
        try (InputStream is = new FileInputStream(filePath); ReadableWorkbook wb = new ReadableWorkbook(is))
        {
            return wb;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read workbook => " + e.getMessage());
        }
    }
}
