package core.utils.test.fakefiller;

import core.utils.CustomFormFiller;
import core.utils.FakeFiller;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.TreeMap;

public class FakeFillerCoreTests
{
    @Test
    public void verifyFakeFillerGetFlow()
    {
        String path=System.getProperty("user.dir")+ "/src/test/resources/TestData_FF.xlsx";
        String testCase= "TC01";

        List<TreeMap<String, String>> data= FakeFiller.getFlow(path,testCase);
        Assert.assertTrue("All rows in test datasheet should be captured. There are two records in file " + path,data.size() > 1);
        Assert.assertTrue("Test Case name should be captured as Anwser",
                data.stream().findFirst().get().containsKey("Answer"));

    }

    @Test
    public void verifyFormFiller()
    {
        String path=System.getProperty("user.dir")+ "/src/test/resources/TestData_FF.xlsx";
        String testCase= "TC01";

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
        driver.get("https://demoqa.com/automation-practice-form");

        CustomFormFiller cf= new CustomFormFiller();
        cf.fillForm(driver,path,testCase);
        driver.quit();
    }
}
