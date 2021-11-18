package core.utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.RequestId;
import org.openqa.selenium.devtools.v95.network.model.ResourceType;
import java.util.ArrayList;
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
        List<WebResponse> data = new ArrayList<WebResponse>();
        devTools.addListener(Network.responseReceived(),response->
        {
            if(response.getType()== ResourceType.XHR) {

                RequestId requestId = response.getRequestId();
               String body = devTools.send(Network.getResponseBody(requestId)).getBody();

                //add to list
                WebResponse webresponse=new WebResponse();
                webresponse.requestUrl=response.getResponse().getUrl();
                webresponse.responseBody = body;
                webresponse.resourceType=response.getType().toString();
                data.add(webresponse);
            }

        });



        driver.get("https://anoopsimon.github.io/jokes/");
        Thread.sleep(2000);
        driver.quit();

        for (WebResponse r:data)
        {
            System.out.println(r.requestUrl);
            System.out.println(r.responseBody);
            System.out.println(r.resourceType);
        }

    }
}
