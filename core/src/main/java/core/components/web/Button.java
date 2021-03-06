package core.components.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class Button extends SeleniumWrapper
{
    private WebDriver driver=null;
    By selector = null;

    public Button(WebDriver driver,By selector)
    {
        super(driver);
        this.driver=driver;
        this.selector=selector;
    }

    public void getText()
    {
        $(selector).getText();
    }

    public void highlight()
    {
        highlight(selector);
    }

    public void click()
    {
        click(selector);
    }

    public Map<String,String> getAttributes()
    {
        return getAttributes(selector);
    }


}
