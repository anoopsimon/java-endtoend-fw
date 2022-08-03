package core.utils.test;

import core.utils.ExcelUtil;
import io.restassured.response.ResponseBody;
import net.minidev.json.JSONUtil;
import org.dhatim.fastexcel.reader.Row;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class APITest
{
    @Test
    public void api(){
        String[] cols=new String[]{"FieldName","jsonpath"};
        String testcase="TC1";
        ArrayList<Map<String, String>> dataList=new ArrayList<Map<String,String>>();
        List<Row> data = ExcelUtil.getSheet("C:\\Users\\s4514\\IdeaProjects\\java-endtoend-fw\\core\\src\\test\\resources\\apidata.xlsx","Sheet1");
        for (int i = 0; i < data.size(); i++) {
            if(i==0) continue;
            Map mp =new TreeMap<String,String>();

            for (int j = 0; j < data.get(i).getCellCount(); j++) {
                Row cell = data.get(i);
                String cellHeader=data.get(0).getCell(j).getText();
                if(Arrays.stream(cols).collect(Collectors.toList()).contains(cellHeader) ||
                        cellHeader.equalsIgnoreCase(testcase)
                ){
                    String value=cell.getCell(j)==null ? "":cell.getCell(j).getRawValue();
                    //System.out.println(cellHeader+" :"+ value);
                    mp.put(cellHeader,value);
                }
            }
            System.out.println("-------------------------");
            dataList.add(mp);
        }
        System.out.println(dataList.get(0));
        Map<String, String> tData=dataList.get(0);
        String body=
                "{\n" +
                "    \"name\": \"Dileep\",\n" +
                "    \"job\": \"Sarath\"\n" +
                "}";

        System.out.println("API Test");
        ResponseBody response = given().body(body)
                .contentType("application/json")
                .post("https://reqres.in/api/users")
                .body();

        System.out.println(response.print());
    }
}
