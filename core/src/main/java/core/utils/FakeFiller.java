package core.utils;

import org.dhatim.fastexcel.reader.Row;

import java.util.List;
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
        for (Row r:datasheet.stream().skip(1).collect(Collectors.toList()))
        {
            for (int index=0;index<r.getCellCount();index++)
            {
                System.out.println("Row >" + index);

                System.out.println("Column Name :" +header.getCell(index).asString() + "||"  +r.getCell(index).asString());
            }
        }
    }
}
