package core.utils.test.applitools;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.util.List;

public class VIsualRegression
{
    private Eyes eyes;
    private static ClassicRunner runner;

    WebDriver driver;
    @Test
    public void verifyFormFiller() throws FileNotFoundException {

        String applitoolsApiKey = System.getenv("APPLITOOLS_API_KEY");
        Configuration config = new Configuration();

        config.setApiKey(applitoolsApiKey);

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();

        eyes = new Eyes(runner);
        eyes.open(
                driver,                             // WebDriver object to "watch"
                "ACME Bank Web App",                // The name of the app under test
                "My FirstTest",          // The name of the test case
                new RectangleSize(1024, 768));      // The viewport size for the local browser
        driver.get("https://www.google.com/");

        eyes.check(Target.window().fully().withName("Login page"));
        eyes.closeAsync();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        System.out.println(allTestResults);

    }
}
