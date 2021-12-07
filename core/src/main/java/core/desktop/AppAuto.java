package core.desktop;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.Window;

import java.io.IOException;
import java.util.regex.Pattern;

public class AppAuto {
    public static void main(String[] args) throws Exception {
        UIAutomation automation = UIAutomation.getInstance();
        // Build the application details up, ready for launching
        Application application = automation.launch("calc.exe");
        application.waitForInputIdle(5000);
        Thread.sleep(1000);
        Window window = automation.getWindow("Calculator");
        window.getButton("Four").click();
        window.getButton("Plus").click();
        window.getButton("Five").click();
        window.getButton("Equals").click();

        String output=window.getTextBox(Pattern.compile("Display.*")).getName();
        System.out.println("output" + output);
        window.close();
        application.end();

    }
}
