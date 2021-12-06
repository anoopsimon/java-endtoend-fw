package core.desktop;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.Window;

import java.io.IOException;

public class AppAuto {
    public static void main(String[] args) throws Exception {
        UIAutomation automation = UIAutomation.getInstance();
        // Build the application details up, ready for launching
        Application application = automation.launch("calc.exe");
        application.waitForInputIdle(5000);
        Window window = automation.getWindow("Calculator");
        window.getButton("Four").click();
        window.getButton("Plus").click();
        window.getButton("Five").click();
        window.getButton("Equals").click();
        //window.getButton(0);
        //window.getButton("7");
        //String output=window.getEditBox(0).getText();
        //System.out.println("output" + output);


    }
}
