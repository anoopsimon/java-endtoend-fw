package core.components.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class Textbox extends SeleniumWrapper
{
    private WebDriver driver=null;
    By selector=null;

    public Textbox(WebDriver driver,By selector)
    {
        super(driver);
        this.driver=driver;
        this.selector=selector;
    }

    public void setText(String text)
    {
        $(selector).sendKeys(text);
    }

    public void setText(String value,boolean clear,boolean click)
    {
        WebElement element =  $(selector);
        if(clear) element.clear();
        if(click) element.click();

        element.sendKeys(value);
    }

    public void click()
    {
        $(selector).click();
    }
    public String getText()
    {
        return $(selector).getText();
    }

    public String getAttribute(String attribute)
    {
        return $(selector).getAttribute(attribute);
    }

    public void highlight()
    {
         highlight(selector);
    }

    public Map<String,String> getAttributes()
    {
        return getAttributes(selector);
    }



}
