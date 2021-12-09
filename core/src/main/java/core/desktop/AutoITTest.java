package core.desktop;

import autoitx4java.AutoItX;

import java.io.IOException;

public class AutoITTest {
    public static void main(String[] args) throws Exception {
         AutoItXInstaller ai= new AutoItXInstaller();
         int output = ai.registerAutoITX3DLL();
        System.out.println("DLL Registration Status :" + output);
        AutoItX desktopSession = ai.initAutoIt();
        desktopSession.run("calc.exe");
        Thread.sleep(5000);
        desktopSession.winActive("Calculator");
        desktopSession.winClose("Calculator");


    }
}
