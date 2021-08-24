package core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Hello world!
 */
public class WebDriverUtil {

    private  WebDriver driver;

    public  WebDriver initChromeLocal(String url)
    {
        if(isBlank(System.getProperty("webdriver.chrome.driver", "")))
            throw new RuntimeException("Chrome driver must be set in System property as 'webdriver.chrome.driver'");

        System.out.println("Launching web browser session...");

        ChromeOptions chromeOptions=new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();

        driver.get(url);
        return driver;
    }

    /**
     * Initialize Chrome Browser session in Selenium Grid infrastructure
     * @param gridUrl
     * @param chromeOptions
     * @return
     */
    public WebDriver initChromeInGrid(String gridUrl, ChromeOptions chromeOptions)
    {
        try
        {
            driver = new RemoteWebDriver(new URL(gridUrl),chromeOptions);
            driver.manage().window().maximize();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    /**
     * Terminate Webdriver session
     */
    public void killSession()
    {
        System.out.println("Killing web browser session...");
        if(driver==null) return;

        driver.quit();
    }
}
