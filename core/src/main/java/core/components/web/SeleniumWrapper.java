package core.components.web;

import core.utils.Waiter;
import org.openqa.selenium.*;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SeleniumWrapper extends Waiter
{
    private WebDriver driver;

    public SeleniumWrapper(WebDriver driver)
    {
        this.driver=driver;
    }

    protected void validate(WebDriver driver,By selector){
        if(driver==null) throw new RuntimeException("webdriver instance can't be null");
        if(selector==null) throw new RuntimeException("By selector can't be null");
    }

    public WebElement $(By selector) {
        validate(driver,selector);
        if (IsElementPresent(selector)){
            WebElement element = driver.findElement(selector);
            highlight(element);
            return element;
        }
        try {
            throw new NoSuchElementException("No web element found for selector => " + selector);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public boolean IsElementPresent(By selector) {
        boolean flag = driver.findElements(selector).size() >= 1;
        return flag;
    }

    public void click(By selector){
        $(selector).click();
    }


    public Object executeScript(By selector,String script)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        return  jse.executeScript(script, $(selector));
    }

    public Object executeScript(WebElement element,String script)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        return  jse.executeScript(script, element);
    }

    protected void highlight(By selector)
    {
        String script = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
        executeScript(selector,script);

    }

    protected void highlight(WebElement element)
    {
        String script = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
        executeScript(element,script);

    }

    public Map<String,String> getAttributes(By selector)
    {
        String script = "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;";
        return (Map<String,String>)executeScript(selector,script);
    }

    public WebElement $(By selector,Predicate<WebElement> filterStream)
    {
        Stream stream = driver.findElements(selector).stream();
       return (WebElement) stream.filter(filterStream).findFirst().get();
    }

}
