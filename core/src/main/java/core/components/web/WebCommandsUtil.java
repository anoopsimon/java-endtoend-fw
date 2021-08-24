package core.components.web;

import org.openqa.selenium.*;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WebCommandsUtil
{
    private WebDriver driver;

    public WebCommandsUtil(WebDriver driver)
    {
        this.driver=driver;
    }

    protected void validate(WebDriver driver,By selector){
        if(driver==null) throw new RuntimeException("webdriver instance can't be null");
        if(selector==null) throw new RuntimeException("By selector can't be null");
    }

    public WebElement $(By locator) {
        validate(driver,locator);
        if (IsElementPresent(locator))
            return driver.findElement(locator);

        try {
            throw new NoSuchElementException("No web element found for selector => " + locator);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public boolean IsElementPresent(By locator) {
        boolean flag = driver.findElements(locator).size() >= 1;
        return flag;
    }

    public void click(By locator){
        $(locator).click();
    }


    public Object executeScript(By locator,String script)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        return  jse.executeScript(script, $(locator));
    }

    protected void highlight(By locator)
    {
        String script = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
        executeScript(locator,script);

    }

    public Map<String,String> getAttributes(By locator)
    {
        String script = "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;";
        return (Map<String,String>)executeScript(locator,script);
    }

    public WebElement $(By locator,Predicate<WebElement> filterStream)
    {
        Stream stream = driver.findElements(locator).stream();
       return (WebElement) stream.filter(filterStream).findFirst().get();
    }

}
