package core.utils.test.accessibility;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import core.utils.CustomFormFiller;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class AccessibilityTest {
    WebDriver driver;
    @Test
    public void verifyFormFiller() throws FileNotFoundException {

        WebDriverManager.chromedriver().setup();
         driver=new ChromeDriver();

        driver.get("https://www.google.com/");
        String reportFile="AccessibilityReport";
        //driver.get("https://www.amazon.com");
        AxeBuilder builder = new AxeBuilder();
        Results results = builder.analyze(driver);
        List<Rule> violations = results.getViolations();
        if (violations.size() == 0)
        {
            //Assert.assertTrue(true, "No violations found");
        }
        else
        {
            JsonParser jsonParser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            AxeReporter.writeResultsToJsonFile(reportFile, results);
            JsonElement jsonElement = jsonParser.parse(new FileReader(reportFile + ".json"));
            String prettyJson = gson.toJson(jsonElement);
            AxeReporter.writeResultsToTextFile(reportFile, prettyJson);
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
