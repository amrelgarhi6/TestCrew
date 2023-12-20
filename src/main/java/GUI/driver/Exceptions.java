package GUI.driver;


import GUI.utils.Log4JLogger;

import java.util.Arrays;

public class Exceptions {
    public static void handle(Class<?> c, Exception exception) {
        Log4JLogger.logERROR(c, "StackTrace: " + Arrays.toString(exception.getStackTrace()) + "Error Cause: " + exception.getCause());
    }
}