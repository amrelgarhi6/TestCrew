package GUI.Pages;

import GUI.utils.BrowserActions;
import GUI.utils.ElementActions;
import GUI.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static GUI.utils.ElementActions.wait;

public class HomePage {

    WebDriver driver;

    //constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    String mainUrl = PropertiesReader.getProperty("environment.properties", "baseURL");



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static By homePageTitle() {return By.xpath("//h2[@class='mobile-hidden']");}
    public static By countryBtn() {return By.id("country");}
    private static By currentCountryName() {return By.id("country-name");}
    private static By countryName() {return By.xpath("//span[contains(@id,'contry-lable')]");}
    public static By countryTitle() {return By.id("country-title");}
    public static By BahrainCountryBtn() {return By.id("bh");}
    private static By KsaCountryBtn() {return By.id("sa");}
    private static By KuwaitCountryBtn() {return By.id("kw");}
    private static By planName() {return By.cssSelector(".plan-title");}
    private static By planPrice() {return By.xpath("//div[@class='price']//b");}
    private static By planCurrency() {return By.xpath("//div[@class='price']//i");}
    private static By signInBtn() {return By.id("signin");}






    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  ACTIONS  ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void openPortal(){
        BrowserActions.navigateToUrl(driver,mainUrl);
    }
    public void clickOnChangeCountryBtn(){
        ElementActions.clickElement(driver,countryBtn());
    }
    public void clickOnSignInBtn(){
        ElementActions.clickElement(driver,signInBtn());
    }


    public String getCountryTitlePopup(){
        return ElementActions.getText(driver,countryTitle());
    }
    public String[] validateAvailableCountries(){

        List<WebElement> countryLocator = driver.findElements(countryName());
        String [] country = new String[3];
        for (int i=0 ; i<countryLocator.size() ; i++) {
            country[i] = countryLocator.get(i).getText();
        }
        return country;
    }
    public String getHomePageTitle(){
        return ElementActions.getText(driver,homePageTitle());
    }
    public String getCurrentSelectedCountry(){
        return ElementActions.getText(driver,currentCountryName());
    }
    public void clickOnBahrainCountryBtn(){
        ElementActions.clickElement(driver,BahrainCountryBtn());
    }
    public void clickOnKsaCountryBtn(){
        ElementActions.clickElement(driver,KsaCountryBtn());
    }
    public void clickOnKuwaitCountryBtn(){
        ElementActions.clickElement(driver,KuwaitCountryBtn());
    }
    public String[] getAvailablePackagesText(){
        List<WebElement> plansLocator = driver.findElements(planName());
        String [] plans = new String[3];
        for (int i=0 ; i< plansLocator.size() ; i++) {
            plans[i] = plansLocator.get(i).getText();
        }
        return plans;
    }
    public String[] getPackagesPrice(){
        List<WebElement> pricesLocator = driver.findElements(planPrice());
        String [] prices = new String[3];

        for (int i=0 ; i<pricesLocator.size() ; i++) {
            prices[i] = pricesLocator.get(i).getText();
        }
        return prices;
    }
    public String[] getPackagesCurrency(){
        List<WebElement> currencyLocator = driver.findElements(planCurrency());
        String [] currency = new String[3];
        for (int i=0 ; i<currencyLocator.size() ; i++) {
            currency[i] = currencyLocator.get(i).getText();
        }
        return currency;
    }
}
