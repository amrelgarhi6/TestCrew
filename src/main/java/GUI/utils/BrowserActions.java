package GUI.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class BrowserActions {

    public static void navigateToUrl(WebDriver driver,String url) {
	try {
	    Logger.logStep("[Browser Action] Navigate to URL [" + url + "]");
	    driver.navigate().to(url);
	    ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    public static void maximizeWindow(WebDriver driver) {
	try {
	    Logger.logStep("[Browser Action] Maximize the Browser Window");
	    driver.manage().window().maximize();
	} catch (Exception e) {
	    Logger.logMessage(e.getMessage());
	}
    }

    public static void setWindowResolution(WebDriver driver) {
	String width = PropertiesReader.getProperty("GUIconfigurations.properties", "width");
	String height = PropertiesReader.getProperty("GUIconfigurations.properties", "height");
	try {
	    Logger.logStep(
		    "[Browser Action] Set Window Resolution as Width [" + width + "] and Height [" + height + "]");
	    Dimension dimension = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
	    driver.manage().window().setSize(dimension);
	} catch (Exception e) {
	    Logger.logMessage(e.getMessage());
	}
    }

}
