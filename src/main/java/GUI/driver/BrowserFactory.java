package GUI.driver;

import GUI.utils.BrowserActions;
import GUI.utils.Helper;
import GUI.utils.Logger;
import GUI.utils.PropertiesReader;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.fail;

public class BrowserFactory extends AbstractTestNGCucumberTests {
//    private static WebDriver driver;
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String propertiesFileName = "GUIconfigurations.properties";
    private static final String browserTypeProperty = PropertiesReader.getProperty(propertiesFileName, "browser.type");
    private static final String executionTypeProperty = PropertiesReader.getProperty(propertiesFileName, "execution.type");

    public enum BrowserType {
	MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome"), FROM_PROPERTIES(browserTypeProperty);

	private final String value;

	BrowserType(String type) {
	    this.value = type;
	}

	private String getValue() {
	    return value;
	}
    }

    public enum ExecutionType {
	LOCAL("Local"), REMOTE("Remote"), LOCAL_HEADLESS("Local Headless"), FROM_PROPERTIES(executionTypeProperty);

	private final String value;

	ExecutionType(String type) {
	    this.value = type;
	}

	private String getValue() {
	    return value;
	}
    }

    public static WebDriver getBrowser() {
	return getBrowser(BrowserType.FROM_PROPERTIES, ExecutionType.FROM_PROPERTIES);
    }

    public static synchronized WebDriver getBrowser(BrowserType browserType, ExecutionType executionType) {
	Logger.logStep("Initialize [" + browserType.getValue() + "] Browser and the Execution Type is ["
		+ executionType.getValue() + "]");
	// Local execution condition
	if (executionType == ExecutionType.LOCAL || (executionType == ExecutionType.FROM_PROPERTIES
		&& executionTypeProperty.equalsIgnoreCase("local"))) {
	    if (browserType == BrowserType.GOOGLE_CHROME || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		Helper.implicitWait(driver.get());
		if (PropertiesReader.getProperty(propertiesFileName, "maximize").equalsIgnoreCase("true")) {
		    BrowserActions.maximizeWindow(driver.get());
		} else {
		    BrowserActions.setWindowResolution(driver.get());
		}
	    } else if (browserType == BrowserType.MOZILLA_FIREFOX || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver());
		Helper.implicitWait(driver.get());
		if (PropertiesReader.getProperty(propertiesFileName, "maximize").equalsIgnoreCase("true")) {
		    BrowserActions.maximizeWindow(driver.get());
		} else {
		    BrowserActions.setWindowResolution(driver.get());
		}
	    } else {
		String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
			+ "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
		Logger.logMessage(warningMsg);
		fail(warningMsg);
		throw new NullPointerException(warningMsg);
	    }
	}
	// Local Headless execution condition
	else if (executionType == ExecutionType.LOCAL_HEADLESS || (executionType == ExecutionType.FROM_PROPERTIES
		&& executionTypeProperty.equalsIgnoreCase("local headless"))) {
	    if (browserType == BrowserType.GOOGLE_CHROME || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver(getChromeOptions()));
		Helper.implicitWait(driver.get());
	    } else if (browserType == BrowserType.MOZILLA_FIREFOX || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver(getFirefoxOptions()));
		Helper.implicitWait(driver.get());
	    } else {
		String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
			+ "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
		Logger.logMessage(warningMsg);
		fail(warningMsg);
		throw new NullPointerException(warningMsg);
	    }
	} else {
	    String warningMsg = "The driver is null! because the execution type [" + executionTypeProperty
		    + "] is not valid/supported; Please choose a valid execution type from the given choices in the properties file";
	    Logger.logMessage(warningMsg);
	    fail(warningMsg);
	    throw new NullPointerException(warningMsg);
	}
	return driver.get();
    }

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    private static ChromeOptions getChromeOptions() {
	ChromeOptions chOptions = new ChromeOptions();
	chOptions.setHeadless(true);
	chOptions.addArguments("--window-size=1920,1080");
	return chOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
	FirefoxOptions ffOptions = new FirefoxOptions();
	ffOptions.setHeadless(true);
	ffOptions.addArguments("--window-size=1920,1080");
	return ffOptions;
    }

}
