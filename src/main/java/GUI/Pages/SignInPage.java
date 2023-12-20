package GUI.Pages;

import GUI.utils.BrowserActions;
import GUI.utils.ElementActions;
import GUI.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SignInPage {
    String mainUrl = PropertiesReader.getProperty("environment.properties", "baseURL");

    WebDriver driver;

    //constructor
    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static By loginPageTitle() {return By.xpath("//div[@id='login-page-form']/h3");}
    public static By usernameInputField() {return By.xpath("//input[@name='username']");}
    private static By nextBtn() {return By.xpath("//button[@type='submit']");}
    private static By invalidUserErrorMsg() {return By.xpath("//span[@class='error-msg-top']");}
    public static By loginRecommendationMsg() {return By.xpath("//span[@class='error-msg-top']/i");}



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  ACTIONS  ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void clickOnConfirmBtn(){
        ElementActions.clickElement(driver,nextBtn());
    }
    public void enterUserName(String invalidUserName){
        ElementActions.type(driver,usernameInputField(),invalidUserName,true);
    }
    public String getLLoginTitleText(){
        return ElementActions.getText(driver,loginPageTitle());
    }

    public String getErrorMessage(){
        return ElementActions.getText(driver,invalidUserErrorMsg());
    }
    public String getRecommendationMessage(){
        return ElementActions.getText(driver,loginRecommendationMsg());
    }

}
