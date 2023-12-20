package GUI.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JLogger {
    private static final ThreadLocal<Logger> logger = new ThreadLocal<>();

    public static void logERROR(Class<?> c, String s) {
        logger.set(LogManager.getLogger(c));
        logger.get().log(Level.ERROR, s);
    }

    public static void logINFO(Class<?> c, String s) {
        logger.set(LogManager.getLogger(c));
        logger.get().log(Level.INFO, s);
    }

    public static void main(String[] args) {
        logINFO(Log4JLogger.class, "Amr ");
    }
}