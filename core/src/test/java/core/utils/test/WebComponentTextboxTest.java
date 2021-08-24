package core.utils.test;
import core.components.web.Textbox;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class WebComponentTextboxTest
{

    @Test
    public void TextboxTestWhenDriverNull()
    {
        WebDriver driver=null;
        Textbox textbox = new Textbox(driver, By.cssSelector("#input[name='login']"));
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> {
            textbox.click();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage="webdriver instance can't be null";
        Assert.assertTrue("error message should be => " + expectedMessage,actualMessage.equalsIgnoreCase(expectedMessage));
    }

    @Test
    public void TextboxTestWhenLocatorNull()
    {
        WebDriver driver= Mockito.mock(WebDriver.class);

        Textbox textbox = new Textbox(driver, null);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> {
            textbox.click();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage="By selector can't be null";

        Assert.assertTrue("error message should be => " + expectedMessage,actualMessage.equalsIgnoreCase(expectedMessage));
    }

    @Test
    public void VerifyTextboxClickWhenLocatorNotFound()
    {
        WebDriver driver= Mockito.mock(WebDriver.class);

        Textbox textbox = new Textbox(driver, By.cssSelector("#login"));
        Exception exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            textbox.click();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage="No web element found for selector";

        Assert.assertTrue("error message should be => " + expectedMessage,actualMessage.contains(expectedMessage));


    }
}
