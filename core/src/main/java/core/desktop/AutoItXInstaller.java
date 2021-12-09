package core.desktop;

import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import core.utils.OSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

public class AutoItXInstaller {
    private static final Logger log = LoggerFactory.getLogger(AutoItXInstaller.class);
    private static final Boolean JVM_ARCHITECH_32 = System.getProperty("sun.arch.data.model").contains("32");
    private static final String AUTOITX_DLL_TO_USE = OSUtils.getOSBitVersion()== 32 ? "autoit/AutoItX3.dll" : "autoit/AutoItX3_x64.dll";
    private static String dllFilepath = AutoItXInstaller.class.getClassLoader().getResource(AUTOITX_DLL_TO_USE).getPath();
    String JACOB_DLL_TO_USE = JVM_ARCHITECH_32 ? "autoit/jacob-1.19-x86.dll" : "autoit/jacob-1.19-x64.dll";


    public int registerAutoITX3DLL()
    {
        // 1. Check OS version and admin rights.
        log.info("Registering AutoITX3 DLL on this machine.");
        OSUtils.OSType os = OSUtils.getOperatingSystemType();
        String message="Sorry we can't set up register autoit dll on this machine.Only Windows is supported. OS found : " + os;
        String messageAdminError="User should be an admin to register autoit dll";

        if(os!= OSUtils.OSType.Windows){
            log.error(message);
            throw new RuntimeException(message);
        }
        if(!OSUtils.isAdmin())
        {
            log.error(messageAdminError);
            throw new RuntimeException(messageAdminError);
        }

        // 1. Register DLL
        String command = "/Windows/System32/regsvr32.exe " + dllFilepath.replaceFirst("/","") +" /s";
        log.info("Register DLL using command :" + command);
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            return process.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public AutoItX initAutoIt()
    {
        String path=this.getClass().getClassLoader().getResource(JACOB_DLL_TO_USE).getPath();

        System.setProperty(LibraryLoader.JACOB_DLL_PATH, path);
        AutoItX autoItX = new AutoItX();

        return autoItX; //11.0.11
    }


}
