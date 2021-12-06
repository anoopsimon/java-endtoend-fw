package core.utils;
import core.data.RandomData;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class CustomFormFiller extends FakeFiller{
    private static final Logger logger = LoggerFactory.getLogger(CustomFormFiller.class);
    WebDriver driver;
    String p= this.getClass().getClassLoader().getResource("fakefiller.properties").getPath();

    public static void main(String[] args)
    {
        System.out.println(new CustomFormFiller().p);
    }

    @Override
    public void fillForm(WebDriver driver , String filepath,String testcase)
    {
        System.out.println("Hello");

        logger.info("Overridden");
    }

    @Override
    public String getAnswer(String keyword){
        String answer= super.getAnswer(keyword);
        if(!answer.isEmpty()) return answer;

        //Execute this block only if parent method doesn't contain keyword
            switch (keyword.toUpperCase(Locale.ROOT))
            {
                case "RANDOM_MOBILE":
                    return RandomData.mobile().cellPhone();
                default:
                    throw new RuntimeException("Keyword '" +keyword+ "' not supported - Overridden code.");
            }

    }
}
