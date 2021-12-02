package core.components.web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown
{
    private WebDriver driver;
    private By dropdownLocator;
    private By optionsLocator;
    Select dropdown;
    public Dropdown(WebDriver driver, By dropdownLocator , By optionsLocator)
    {
        this.dropdownLocator=dropdownLocator;
        this.optionsLocator=optionsLocator;
         dropdown = new Select(driver.findElement(dropdownLocator));

    }

    /**
     * To define dropdown components rendered as SELECT
     * @param driver
     * @param dropdownLocator
     */
    public Dropdown(WebDriver driver, By dropdownLocator)
    {
        this.dropdownLocator=dropdownLocator;
        this.dropdown = new Select(driver.findElement(dropdownLocator));
    }

    public String selectByValue(String value)
    {
         this.dropdown.selectByValue(value);

        return value;
    }

    public String getFirstSelectedOption(String value)
    {
        return this.dropdown.getFirstSelectedOption().getText();
    }

}
