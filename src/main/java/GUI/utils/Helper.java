package GUI.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.testng.Assert.fail;

public class Helper {
    public static final int TIMEOUT = Integer
	    .parseInt(PropertiesReader.getProperty("GUIconfigurations.properties", "webdriver.wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver) {
	return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public static void implicitWait(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }

    public static String getCurrentTime(String dateFormat) {
	String currentTime = "";
	try {
	    currentTime = new SimpleDateFormat(dateFormat).format(new Date());
	} catch (IllegalArgumentException e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	return currentTime;
    }

}
