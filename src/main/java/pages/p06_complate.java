package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class p06_complate {
    private final WebDriver driver;
    private final By thanksmessage = By.tagName("h2");

    public p06_complate(WebDriver driver) {
        this.driver = driver;
    }

    public boolean visbilityofthanksmessage() {
        return driver.findElement(thanksmessage).getText().equals("Thank you for your order!");
    }
}
