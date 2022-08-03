package core.utils.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class NetworkCaptureTest {
    @Test
    public void networkCapture() throws Exception {
        System.out.println("Test");
        WebDriverManager.chromedriver().setup();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable( LogType.PERFORMANCE, Level.ALL );

        ChromeOptions o = new ChromeOptions();
        o.setCapability( "goog:loggingPrefs", logPrefs );

        WebDriver driver=new ChromeDriver(o);
        driver.get("https://anoopsimon.github.io/jokes/");
        Thread.sleep(3000);

        List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
        System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
        for (LogEntry entry : entries)
        {
            System.out.println(entry.toJson());
            System.out.println("---------------------------");
           // System.out.println(entry.getMessage());
        }

    }

    @Test
    public void bMP() throws Exception {
        //Proxy Operations
        WebDriverManager.edgedriver().setup();
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(); // can specify a port here if you like

        // get the selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        EdgeOptions options = new EdgeOptions();
       // options.("--ignore-certificate-errors");
    // replace 'somedirectory' with a suitable temp dir on your filesystem
        //options.addArguments("--user-data-dir=C:/Temp");
        //capabilities.setCapability(EdgeOptions, options);
        // if chromedriver isn't on your system path, you'll need to set this system property
        WebDriver driver = new EdgeDriver(options);
        proxy.newHar("anoopsimon.github.io");
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        driver.get("https://anoopsimon.github.io/jokes/");
        Thread.sleep(5000);
        proxy.stop();
        Har har = proxy.getHar();
        File harFile = new File("google.har");
        har.writeTo(harFile);
        for(HarEntry s :har.getLog().getEntries()){
            System.out.println(s.getResponse().getContent().getText());
        }
    }
}
