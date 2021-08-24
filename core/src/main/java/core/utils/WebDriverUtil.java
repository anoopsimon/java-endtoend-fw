package core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 */
public class WebDriverUtil {

    private static WebDriver driver;
    public static void initializeSession()
    {
        System.out.println("Launching web browser session...");

        driver = new ChromeDriver();
        driver.get("https://www.bing.com");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public static void killSession()
    {
        System.out.println("Killing web browser session...");
        driver.quit();
    }
}
