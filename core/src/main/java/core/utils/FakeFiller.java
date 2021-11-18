package core.utils;

import org.dhatim.fastexcel.reader.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FakeFiller
{
    public static void main(String[] args) {
        //impl here
        getFlow("","");
    }

    public static void getFlow(String dataSheet,String testCaseName)
    {

        String path=System.getProperty("user.dir")+"/core/src/main/java/core/utils/Book1.xlsx";
        System.out.println(path);
        List<Row> datasheet = ExcelUtil.getSheet(path,"Sheet1");
        Row header=datasheet.stream().findFirst().get();
        List<TreeMap> curatedData=new ArrayList<>();
        for (Row row:datasheet.stream().skip(1).collect(Collectors.toList()))
        {
            TreeMap<String, String> data = new TreeMap<String,String>();
            for (int index=0;index<row.getCellCount();index++)
            {
                System.out.println("Row >" + index);
                System.out.println("Column Name :" +header.getCell(index).asString() + "||"  +row.getCell(index).asString());
                data.put(header.getCell(index).asString(),row.getCell(index).asString());
            }
            curatedData.add(data);
        }
    }
}
