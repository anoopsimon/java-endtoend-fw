package core.components.web;

import org.openqa.selenium.*;

public class WebCommandsUtil
{
    private WebDriver driver;

    public WebCommandsUtil(WebDriver driver)
    {
        this.driver=driver;
    }

    protected void validate(WebDriver driver,By selector){
        if(driver==null) throw new RuntimeException("webdriver instance can't be null");
        if(selector==null) throw new RuntimeException("By selector  can't be null");
    }

    public WebElement $(By locator) {
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


    public String executeScript(By locator,String script)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(script, $(locator));

        return (String)jse.executeScript(script, $(locator));
    }
}
