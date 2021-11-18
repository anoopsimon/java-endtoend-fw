package core.utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.Headers;
import org.openqa.selenium.devtools.v95.network.model.RequestId;
import org.openqa.selenium.devtools.v95.network.model.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class WebDriverDemo
{


    static ChromeDriver chromeDriver;
    static DevTools devTools;
    public static void main(String[] args) throws InterruptedException {
        performanceMetricsExample();
    }

    public static void performanceMetricsExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        List<HashMap<String,String>> data = new ArrayList<>();
        devTools.addListener(Network.responseReceived(),response->
        {
            if(response.getType()== ResourceType.XHR) {

                RequestId requestId = response.getRequestId();

                System.out.println("************************");
                System.out.println("URL : " + response.getResponse().getUrl());
                Headers headers = response.getResponse().getHeaders();
                System.out.println(headers);
                System.out.println(response.getType().toJson());
                String body = devTools.send(Network.getResponseBody(requestId)).getBody();
                System.out.println("Body :" + body);
                System.out.println("************************");


                //add to list
                HashMap map = new HashMap<String,Object>();
                map.put("url","");
            }

        });



        driver.get("https://anoopsimon.github.io/jokes/");
        Thread.sleep(2000);
        driver.quit();

    }
}
