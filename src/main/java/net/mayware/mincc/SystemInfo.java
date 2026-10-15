package net.mayware.mincc;

/**
 * 
 * @author May Fontenot (may@mayware.net)
 */
public class SystemInfo {
    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }
}