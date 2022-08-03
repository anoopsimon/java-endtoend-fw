package core.utils.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.utils.ExcelUtil;
import net.minidev.json.JSONUtil;
import net.minidev.json.parser.JSONParser;
import org.dhatim.fastexcel.reader.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests {

    @Test
    public void getTestData(){
        //reqres.in/api/users?page=2
        when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("per_page",equalTo(6));


    }

    @Test
    public void postTestData() throws Exception {
        String path=System.getProperty("user.dir")+ "/src/test/resources/apidata.xlsx";
        String jsonFile=System.getProperty("user.dir")+ "/src/test/resources/requestbody.json";
        List<Row> file=ExcelUtil.getSheet(path,"Sheet1");
        JSONParser jsonObject = new JSONParser();
        JsonObject obj= (JsonObject) jsonObject.parse(new FileInputStream(new File(jsonFile)));
        for (Row row: file )
        {
            for (int cellIndex = 0; cellIndex < row.getCellCount(); cellIndex++)
            {
                String value= (row.getCell(cellIndex)==null) ? null :row.getCell(cellIndex).getText();
                System.out.println(value);
            }


        }
        System.out.println(obj);
//        String body="{\n" +
//                "    \"email\": \"eve.holt@reqres.in\",\n" +
//                "    \"password\": \"pistol\"\n" +
//                "}";
//
//        Response response = given()
//                .header("Content-type", "application/json")
//                .and()
//                .body(body)
//                .when()
//                .post("https://reqres.in/api/register")
//                .then()
//                .extract().response();
//
//
//
//        System.out.println("Response :" + response.body().prettyPrint());

       // response.assertThat().body("name",equalTo("IVuser002"));
        //ExcelUtil.


    }
}
