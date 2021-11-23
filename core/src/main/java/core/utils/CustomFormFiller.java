package core.utils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFormFiller extends FakeFiller{
    private static final Logger logger = LoggerFactory.getLogger(CustomFormFiller.class);
    WebDriver driver;

//    @Override
//    public void fillForm(WebDriver driver , String filepath,String testcase)
//    {
//        System.out.println("Hello");
//
//        logger.info("Overridden");
//    }
}
