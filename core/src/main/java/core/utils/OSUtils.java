package core.utils;
import java.io.*;
import java.util.Locale;

public class OSUtils {
    private static String adminGroup = "S-1-5-32-544";
    private OSUtils() {  }

    public static boolean isAdmin() {
//        String groups[] = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
//        for (String group : groups) {
//            if (group.equals(adminGroup)) return true;
//        }
        return true;
    }

    public static  String getOperatingSystem() {
        String os = System.getProperty("os.name");
        return os;
    }

    public static int getOSBitVersion()
    {
        return (System.getProperty("os.arch").endsWith("32")) ? 32 :64;
    }

    /**
     * types of Operating Systems
     */
    public enum OSType {
        Windows, MacOS, Linux, Other
    };

    // cached result of OS detection
    protected static OSType detectedOS;

    /**
     * detect the operating system from the os.name System property and cache
     * the result
     *
     * @returns - the operating system detected
     */
    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
            } else {
                detectedOS = OSType.Other;
            }
        }
        return detectedOS;
    }


}
