package pages;

import Utilites.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class p01_loginpage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginbutton = By.id("login-button");
    private final WebDriver driver;


    public p01_loginpage(WebDriver driver) {
        this.driver = driver;
    }

    public p01_loginpage enterusername(String usernametext) {
        utility.sendkeys(driver, username, usernametext);
        return this;
    }

    public p01_loginpage enterpassword(String passwordtext) {
        utility.sendkeys(driver, password, passwordtext);
        return this;
    }

    public p02_landingpage clickonbutton() {
        utility.clickonelement(driver, loginbutton);
        return new p02_landingpage(driver);
    }

    public Boolean asserloginTc(String expectedurl) {
        return driver.getCurrentUrl().equals(expectedurl);
    }

}
