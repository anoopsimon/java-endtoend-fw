package core.components.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class Button extends WebCommandsUtil
{
    private WebDriver driver=null;
    By selector = null;

    public Button(WebDriver driver,By selector)
    {
        super(driver);
        this.driver=driver;
        this.selector=selector;
    }

    public Map<String,String> getAttributes()
    {
        return getAttributes(selector);
    }


}
