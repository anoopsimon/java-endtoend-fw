package core.utils.test;

import autoitx4java.AutoItX;
import core.desktop.AutoItXInstaller;

import java.io.IOException;

public class AutoITTest {
    public static void main(String[] args) throws Exception {
        AutoItX x= new AutoItXInstaller().initAutoIt();
        x.run("calc.exe");
        Thread.sleep(1000);
        x.winClose("Calculator");
    }
}
