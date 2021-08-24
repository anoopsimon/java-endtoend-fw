package core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 */
public class WebDriverUtil {

    private static WebDriver driver;
    public static WebDriver initializeSession(String url)
    {
        System.out.println("Launching web browser session...");

        driver = new ChromeDriver();
        driver.get(url);
        return driver;
    }

    public static void killSession()
    {
        System.out.println("Killing web browser session...");
        driver.quit();
    }
}
