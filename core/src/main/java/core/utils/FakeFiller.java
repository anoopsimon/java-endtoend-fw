package core.utils;

import core.components.web.Button;
import core.components.web.Textbox;
import core.constants.KeyWords;
import org.dhatim.fastexcel.reader.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public abstract class FakeFiller {

    private static final Logger logger = LoggerFactory.getLogger(FakeFiller.class);
    private WebDriver driver;
    TreeMap<String, String> currentRow;

    /**
     * Get data flow for a given testcase.
     *
     * @param dataSheet
     * @param testCaseName
     * @return
     */
    public static List<TreeMap<String, String>> getFlow(String dataSheet, String testCaseName) {
        logger.info("Loaded workbook : " + dataSheet);

        List<TreeMap<String, String>> curatedData = new ArrayList<TreeMap<String, String>>();
        for (String sheet:ExcelUtil.sheetNames(dataSheet))
        {
            logger.info("Reading  DataSheet : " + sheet);

            List<Row> datasheet = ExcelUtil.getSheet(dataSheet, sheet);
            Row header = datasheet.stream().findFirst().get();
            int rowIndex = 0;
            for (Row row : datasheet.stream().skip(1).collect(Collectors.toList())) {
                rowIndex++;
                TreeMap<String, String> data = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
                for (int index = 0; index < row.getCellCount(); index++) {
                    String headerName = header.getCell(index).asString();
                    String answer= row.getCell(index)!=null ? row.getCell(index).asString() :"";

                    if (headerName.equalsIgnoreCase(testCaseName)) headerName = "Answer";
                    data.put(headerName, answer);
                }
                logger.info("Data in Row " + rowIndex + ": " + data);
                curatedData.add(data);
            }

            logger.info("Found  " + curatedData.size() + " records in datasheet for testcase : " + testCaseName);
        }


        return curatedData;

    }

    public void fillForm(WebDriver driver, String filepath, String testcase) {
        this.driver = driver;
        logger.info("Fill form with data.");
        List<TreeMap<String, String>> flow = getFlow(filepath, testcase);
        flow.forEach(row -> {
            logger.info("Processing Question " + row.get("question") + ". Full Data : " + row);
            String controlType = row.get(KeyWords.ControlType);
            this.currentRow = row;
            switch (controlType.toUpperCase(Locale.ROOT)) {
                case "BUTTON":
                    processButtonElement();
                    break;
                case "TEXTBOX":
                    processTextbox();
                    break;
                case "CUSTOM":
                    processCustomControl();
                    break;
            }

        });
    }

    private By getLocator()
    {
        String locator = this.currentRow.get(KeyWords.Locator);
        String locatorType=this.currentRow.get(KeyWords.LocatorType);
        String question=this.currentRow.get(KeyWords.Question);
        String message="Sorry , but Locator value is mandatory for question " + question;
        if(locator.isEmpty()) {
            logger.error(message);
            throw new RuntimeException(message);
        }
        switch (locatorType.toLowerCase(Locale.ROOT))
        {
            case "css":
            case "cssselector":
                return By.cssSelector(locator);
             case "xpath":
                return By.xpath(locator);
            case "classname":
                return By.className(locator);
            case "id":
                return By.id(locator);
            case "linkedtext":
                return By.linkText(locator);
            case "tagname":
                return By.tagName(locator);
            case "partiallinktext":
                return By.partialLinkText(locator);
                default:
                return deriveLocatorType();
        }
    }


    private By deriveLocatorType() {
        String locator=this.currentRow.get(KeyWords.Locator);
        String question=this.currentRow.get(KeyWords.Question);
        logger.error("Locator Type is not provided for question "+ question +
                ". Hence determining locator type from locator value. (only XPATH and CSS Selector can be derived)");

        By defaultType = locator.startsWith("/")?By.xpath(locator):By.cssSelector(locator);
        logger.info("Locator Type derived as " + defaultType.getClass().getTypeName());
        return defaultType;
    }

    private void processButtonElement() {
        //isShadow ?
        String locator=this.currentRow.get(KeyWords.Locator);
        Button button = new Button(this.driver, getLocator());
        String[] answers = new String[]{"Yes", "True", "Y"};
        String answer=this.currentRow.get(KeyWords.Answer);
        if (Arrays.stream(answers).anyMatch(x -> x.equalsIgnoreCase(answer))) {
            button.click();
            return;
        }
        logger.info("Ignoring the click on element "+ this.currentRow.get(KeyWords.Locator) + "because answer provided was : "+ answer );
    }

    private void processTextbox() {
        //isShadow ?
        String value = this.currentRow.get(KeyWords.Answer);
        if (value.isEmpty()) return;
        Textbox textbox = new Textbox(this.driver, getLocator());
        textbox.click();
        textbox.setText(value);

    }

    private void processCustomControl() {
        WebElement element = driver.findElement(getLocator());
        element.click();
    }


}
