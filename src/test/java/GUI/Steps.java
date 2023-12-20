package GUI;

import GUI.Pages.HomePage;
import GUI.Pages.SignInPage;
import GUI.driver.BrowserFactory;
import GUI.utils.JsonFileManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;

public class Steps {

    /////////////////////////////////////////// login page /////////////////////////////////////////////////////////////
    private static final JsonFileManager loginPageData = new JsonFileManager("src/test/resources/testDataFiles/LoginPageData.json");
    String loginPageTitle = loginPageData.getTestData("common.loginPageTitle");
    String errorMessage = loginPageData.getTestData("common.errorMessage");
    String recommendationMessage = loginPageData.getTestData("common.recommendationMessage");
    String invalidUserName = loginPageData.getTestData("common.invalidUserName");


    /////////////////////////////////////////// home page /////////////////////////////////////////////////////////////
    private static final JsonFileManager homePageData = new JsonFileManager("src/test/resources/testDataFiles/homePageData.json");
    protected static WebDriver driver;
    String homePageTitle = homePageData.getTestData("common.homePageTitle");
    String selectCountryTitle = homePageData.getTestData("common.selectCountryTitle");
    String KuwaitCountry = homePageData.getTestData("common.KuwaitCountry");
    String BahrainCountry = homePageData.getTestData("common.BahrainCountry");
    String KSACountry = homePageData.getTestData("common.KSACountry");
    String packages = homePageData.getTestData("common.packages");
    String countries = homePageData.getTestData("common.Countries");
    String currency_Bahrain = homePageData.getTestData("BahrainCountry.countryCurrency");
    String currency_Ksa = homePageData.getTestData("KsaCountry.countryCurrency");
    String currency_Kuwait = homePageData.getTestData("KuwaitCountry.countryCurrency");

    String prices_Bahrain = homePageData.getTestData("BahrainCountry.BahrainPrices");
    String prices_Ksa = homePageData.getTestData("KsaCountry.KsaPrices");
    String prices_Kuwait = homePageData.getTestData("KuwaitCountry.KuwaitPrices");


    @Given("open app portal on the home page")
    public void open_app_portal_on_the_home_page() {
        driver = BrowserFactory.getBrowser();
        new HomePage(driver).openPortal();
    }
    @Then("click on Sign-In button to navigate into Login page")
    public void click_on_sign_in_button_to_navigate_into_login_page() {
        new HomePage(driver).clickOnSignInBtn();
    }

    @Then("validate landing on the home page successfully")
    public void validate_landing_on_the_home_page_successfully() {
        Assert.assertEquals(new HomePage(driver).getHomePageTitle(), homePageTitle);
    }

    @Given("click on Change Country above the page")
    public void click_on_change_country_above_the_page() {
        new HomePage(driver).clickOnChangeCountryBtn();
    }

    @Then("validate landing on the change country popup screen displayed successfully")
    public void validate_landing_on_the_change_country_popup_screen_displayed_successfully() {
        Assert.assertEquals(new HomePage(driver).getCountryTitlePopup(), selectCountryTitle);
    }

    @Then("validate all available countries in the list")
    public void validate_all_available_countries_in_the_list() {
        String actualResult = Arrays.toString(new HomePage(driver).validateAvailableCountries());
        Assert.assertEquals(actualResult,countries);
    }

    @Then("change the selected country to Bahrain")
    public void change_the_selected_country_to_bahrain() {
        new HomePage(driver).clickOnBahrainCountryBtn();
    }

    @Then("validate landing on the home page again successfully")
    public void validate_landing_on_the_home_page_again_successfully() {
        Assert.assertEquals(new HomePage(driver).getHomePageTitle(), homePageTitle);
    }

    @Then("validate package names displayed correctly and with the proper order")
    public void validate_package_names_displayed_correctly_and_with_the_proper_order() {
        String actualRes = Arrays.toString(new HomePage(driver).getAvailablePackagesText());
        Assert.assertEquals(actualRes,packages);
    }

    @Then("validate current country changes to Bahrain on homePage successfully")
    public void validate_current_country_changes_to_bahrain_on_home_page_successfully() {
        String currentSelectedCountry= new HomePage(driver).getCurrentSelectedCountry();
        Assert.assertEquals(currentSelectedCountry, BahrainCountry);
    }

    @Then("validate the currency displayed properly and relative to the selected country 'Bahrain'")
    public void validate_the_currency_displayed_properly_and_relative_to_the_selected_country() {
        String actualRes = Arrays.toString(new HomePage(driver).getPackagesCurrency());
        Assert.assertEquals(actualRes,currency_Bahrain);
    }

    @Then("validate prices displayed correctly relative to selected country Bahrain")
    public void validate_prices_displayed_correctly_relative_to_selected_country() {
        String actualRes = Arrays.toString(new HomePage(driver).getPackagesPrice());
        Assert.assertEquals(actualRes,prices_Bahrain);
    }

    //////////////////////////////////////// KSA ///////////////////////////////

    @Then("change the selected country to Ksa")
    public void change_the_selected_country_to_ksa() {
        new HomePage(driver).clickOnKsaCountryBtn();
    }

    @Then("validate prices displayed correctly relative to selected country Ksa")
    public void validate_prices_displayed_correctly_relative_to_selected_country_ksa() {
        String actualRes = Arrays.toString(new HomePage(driver).getPackagesPrice());
        Assert.assertEquals(actualRes,prices_Ksa);
    }

    @Then("validate the currency displayed properly and relative to the selected country Ksa")
    public void validate_the_currency_displayed_properly_and_relative_to_the_selected_country_ksa() {
        String actualRes = Arrays.toString(new HomePage(driver).getPackagesCurrency());
        Assert.assertEquals(actualRes,currency_Ksa);
    }

    @Then("validate current country changes to Ksa on homePage successfully")
    public void validate_current_country_changes_to_ksa_on_home_page_successfully() {
        Assert.assertEquals(new HomePage(driver).getCurrentSelectedCountry(), KSACountry);
    }


    /////////////////////////////////// Kuwait ////////////////////////////////////


    @Then("change the selected country to Kuwait")
    public void change_the_selected_country_to_kuwait() {
        new HomePage(driver).clickOnKuwaitCountryBtn();
    }

    @Then("validate prices displayed correctly relative to selected country Kuwait")
    public void validate_prices_displayed_correctly_relative_to_selected_country_kuwait() {
        String actualRes = Arrays.toString(new HomePage(driver).getPackagesPrice());
        Assert.assertEquals(actualRes,prices_Kuwait);
    }

    @Then("validate the currency displayed properly and relative to the selected country Kuwait")
    public void validate_the_currency_displayed_properly_and_relative_to_the_selected_country_kuwait() {
        String actualRes = Arrays.toString(new HomePage(driver).getPackagesCurrency());
        Assert.assertEquals(actualRes,currency_Kuwait);
    }

    @Then("validate current country changes to Kuwait on homePage successfully")
    public void validate_current_country_changes_to_kuwait_on_home_page_successfully() {
        Assert.assertEquals(new HomePage(driver).getCurrentSelectedCountry(), KuwaitCountry);
    }

    ///////////////////////////// Login steps /////////////////////////////////////////////////////////////////////////



    @Then("validate landing on Login page successfully")
    public void validate_landing_on_login_page_successfully() {
        String actualTitle = new SignInPage(driver).getLLoginTitleText();
        Assert.assertEquals(actualTitle,loginPageTitle);
    }
    @Then("Enter an invalid email")
    public void enter_an_invalid_email() {
        new SignInPage(driver).enterUserName(invalidUserName);
    }
    @Then("click on Next button to validate the email")
    public void click_on_next_button_to_validate_the_email() {
        new SignInPage(driver).clickOnConfirmBtn();
    }
    @Then("validate an error message displayed successfully and notify the user with correct format and instructions to proceed")
    public void validate_an_error_message_displayed_successfully_and_notify_the_user_with_correct_format_and_instructions_to_proceed() {
        Assert.assertEquals(new SignInPage(driver).getErrorMessage()+new SignInPage(driver).getRecommendationMessage(),errorMessage);
    }
}
