package pages;

import Utilites.logsutils;
import Utilites.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class p03_cart {
    static float totalprice = 0;
    private final By thepriceofselectedproductlocatores = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final WebDriver driver;
    private final By checkout = By.id("checkout");

    public p03_cart(WebDriver driver) {
        this.driver = driver;
    }

    public p04_checkoutpage clickoncheckout() {
        utility.clickonelement(driver, checkout);
        return new p04_checkoutpage(driver);

    }

    public String gettotalpriceo() {
        try {
            List<WebElement> priceofselectedproduct = driver.findElements(thepriceofselectedproductlocatores);
            for (int i = 1; i < priceofselectedproduct.size(); i++) {
                By thepriceofproductselected = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"][" + i + "]");
                String fulltext = utility.getdata(driver, thepriceofproductselected);
                totalprice += Float.parseFloat(fulltext.replace("$", ""));
            }
            logsutils.info("the total price = " + totalprice);
            return String.valueOf(totalprice);
        } catch (Exception e) {
            logsutils.error(e.getMessage());
            return "0";
        }
    }

    public boolean comparepriceing(String price) {
        return gettotalpriceo().equals(price);
    }
}
