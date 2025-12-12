package pages;

import Utilites.logsutils;
import Utilites.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class p02_landingpage {
    static float totalprice = 0;
    private final By addallproducttocart = By.xpath("//button[@class]");
    private final By numberofproductiniconcart = By.className("//*[@id=\"shopping_cart_container\"]/a/span");
    private final By numberofselectedproduct = By.xpath("//button[.='Remove']");
    private final By carticon = By.className("shopping_cart_link");
    private final By thepriceofselectedproductlocatores = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final WebDriver driver;
    private List<WebElement> productselected;
    private List<WebElement> allproduct;

    public p02_landingpage(WebDriver driver) {
        this.driver = driver;
    }

    public By getnumberofcart() {
        return numberofproductiniconcart;
    }

    public p02_landingpage addallproducttocart() {
        allproduct = driver.findElements(By.xpath("//button[@class]"));
        logsutils.info("the number of add produce" + allproduct.size());
        for (int i = 1; i <= allproduct.size(); i++) {
            By addallproducttocart = By.xpath("(//button[@class])[" + i + "]");
            utility.clickonelement(driver, addallproducttocart);
        }


        return this;
    }

    public String getnumberofcardproductinicon() {
        logsutils.info("the number of card icon " + numberofproductiniconcart);
        try {
            return utility.getdata(driver, numberofproductiniconcart);
        } catch (Exception e) {
            logsutils.error(e.getMessage());
            return "0";
        }
    }

    public String getnumberofprocuctselected() {
        try {
            productselected = driver.findElements(numberofproductiniconcart);
            return String.valueOf(productselected.size());
        } catch (Exception e) {
            logsutils.error(e.getMessage());
            return "0";
        }
    }

    public p02_landingpage addrandomnumber(int numberneeded, int totalnumberofproduct) {

        Set<Integer> randomnumbers = utility.generateunquienumber(numberneeded, totalnumberofproduct);
        for (int random : randomnumbers) {
            logsutils.info("randomnumber" + random);
            By addallproducttocart = By.xpath("(//button[@class])[" + random + "]");
            utility.clickonelement(driver, addallproducttocart);
        }
        return this;
    }

    public Boolean comparetheaddnumberofproductwithcart() {

        return getnumberofcardproductinicon().equals(getnumberofprocuctselected());

    }

    public p03_cart clickoncarticon() {

        utility.clickonelement(driver, carticon);
        return new p03_cart(driver);
    }


    public String gettotalpriceofselectedproduct() {
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

}
