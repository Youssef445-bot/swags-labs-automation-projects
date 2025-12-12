package pages;

import Utilites.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class p04_checkoutpage {

    private final WebDriver driver;
    private final By firstname = By.id("first-name");
    private final By secondname = By.id("last-name");
    private final By zipcode = By.id("postal-code");
    private final By continuebutton = By.id("continue");

    public p04_checkoutpage(WebDriver driver) {
        this.driver = driver;
    }

    public p04_checkoutpage fillingtheinformation(String fname, String lname, String zipcodetext) {

        utility.sendkeys(driver, firstname, fname);
        utility.sendkeys(driver, secondname, lname);
        utility.sendkeys(driver, zipcode, zipcodetext);
        return this;
    }

    public p05_overviewpage clickoncontinue() {
        utility.clickonelement(driver, continuebutton);
        return new p05_overviewpage(driver);
    }

}

