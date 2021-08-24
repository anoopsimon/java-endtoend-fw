package core.utils.test;

import core.components.web.Button;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class WebComponentButtonTest
{
    @Test
    public void ButtonTestWhenDriverNull()
    {
        WebDriver driver=null;
        Button button = new Button(driver, By.cssSelector("#button"));
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> {
            button.click();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage="webdriver instance can't be null";
        Assert.assertTrue("error message should be => " + expectedMessage,actualMessage.equalsIgnoreCase(expectedMessage));
    }

    @Test
    public void ButtonTestWhenLocatorNull()
    {
        WebDriver driver= Mockito.mock(WebDriver.class);

        Button button = new Button(driver, null);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> {
            button.click();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage="By selector can't be null";

        Assert.assertTrue("error message should be => " + expectedMessage,actualMessage.equalsIgnoreCase(expectedMessage));
    }

    @Test
    public void VerifyButtonClickWhenLocatorNotFound()
    {
        WebDriver driver= Mockito.mock(WebDriver.class);

        Button button = new Button(driver, By.cssSelector("#login"));
        Exception exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            button.click();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage="No web element found for selector";

        Assert.assertTrue("error message should be => " + expectedMessage,actualMessage.contains(expectedMessage));


    }
}
