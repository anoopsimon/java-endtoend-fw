package core.utils;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class FakeFiller
{
    private static final Logger logger    = LoggerFactory.getLogger(FakeFiller.class);

    public static List<TreeMap<String, String>> getFlow(String dataSheet, String testCaseName)
    {
        logger.info("Reading  DataSheet : " + dataSheet);
        List<Row> datasheet = ExcelUtil.getSheet(dataSheet,"Sheet1");
        Row header=datasheet.stream().findFirst().get();
        List<TreeMap<String,String>> curatedData=new ArrayList<TreeMap<String,String>>();
        int rowIndex=0;
        for (Row row:datasheet.stream().skip(1).collect(Collectors.toList()))
        {
            rowIndex++;
            TreeMap<String, String> data = new TreeMap<String,String>();
            for (int index=0;index<row.getCellCount();index++)
            {
                String headerName = header.getCell(index).asString();
                if(headerName.equalsIgnoreCase(testCaseName)) headerName="Answer";
                data.put(headerName,row.getCell(index).asString());
            }
           logger.info("Data in Row " + rowIndex + ": " + data.toString());

            curatedData.add(data);
        }

        logger.info("Found  " + curatedData.size() + " records in datasheet for testcase : " + testCaseName);

        return curatedData;

    }
}
