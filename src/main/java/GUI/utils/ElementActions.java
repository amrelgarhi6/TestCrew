package GUI.utils;

import GUI.driver.BrowserFactory;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.testng.Assert.fail;

public class ElementActions {
     static WebDriver driver;
	public static Wait<WebDriver> wait = new WebDriverWait(BrowserFactory.getBrowser(), Duration.ofSeconds(Helper.TIMEOUT));



    public ElementActions(WebDriver driver) {
	ElementActions.driver = driver;
    }

//    @Step("Click on element: [{elementLocator}]")
    public static void clickElement(WebDriver driver, By elementLocator) {
	try {
	    // wait for the element to be clickable
	    Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	try {
	    // Log element text if not empty. Else, log clicking
	    if (!driver.findElement(elementLocator).getText().isBlank()) {
		Logger.logStep(
			"[Element Action] Click on [" + driver.findElement(elementLocator).getText() + "] Button");
	    } else {
		Logger.logStep("[Element Action] Click on element [" + elementLocator + "]");
	    }
	    // Now we click on the element! :D
	    driver.findElement(elementLocator).click();
	} catch (Exception exception) {
	    // Click using JavascriptExecutor in case of the click is not performed
	    // successfully and got an exception using the Selenium click method
	    try {
		((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
			driver.findElement(elementLocator));
	    } catch (Exception rootCauseException) {
		rootCauseException.initCause(exception);
		Logger.logStep(exception.getMessage());
		Logger.logStep(rootCauseException.getMessage());
		// Force fail the test case if couldn't perform the click
		fail("Couldn't click on the element:" + elementLocator, rootCauseException);
	    }
	}
    }
	public static String getText(WebDriver driver,@NotNull final By elementLocated) {
		String elementText = null;
		try {
			elementText = driver.findElement(elementLocated).getText();
		} catch (Exception e) {
		}
		return elementText;
	}

//    @Step("Type data: [{data}] on element: [{elementLocator}]")
    public static void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    // Clear before typing condition
	    if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
		Logger.logStep("[Element Action] Clear and Type [" + text + "] on element [" + elementLocator + "]");
		driver.findElement(elementLocator).clear();
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(text);
		// Type using JavascriptExecutor in case of the data is not typed successfully
		// using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
			    driver.findElement(elementLocator));
		}
	    } else {
		Logger.logStep("[Element Action] Type [" + text + "] on element [" + elementLocator + "]");
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(text);
		// Type using JavascriptExecutor in case of the data is not typed successfully
		// using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
		    String currentValue = driver.findElement(elementLocator).getAttribute("value");
		    ((JavascriptExecutor) driver).executeScript(
			    "arguments[0].setAttribute('value', '" + currentValue + text + "')",
			    driver.findElement(elementLocator));
		}
	    }
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	// Make sure that the data is inserted correctly to the field
	Assert.assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
		"The data is not inserted successfully to the field, the inserted data should be: [" + text
			+ "]; But the current field value is: ["
			+ driver.findElement(elementLocator).getAttribute("value") + "]");
    }
    ////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    private static void locatingElementStrategy(WebDriver driver, By elementLocator) {
	try {
	    // Wait for the element to be visible
	    Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	    // Scroll the element into view to handle some browsers cases
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
		    driver.findElement(elementLocator));
	    // Check if the element is displayed
	    if (!driver.findElement(elementLocator).isDisplayed()) {
		Logger.logStep("The element [" + elementLocator.toString() + "] is not Displayed");
		fail("The element [" + elementLocator + "] is not Displayed");
	    }
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

}
