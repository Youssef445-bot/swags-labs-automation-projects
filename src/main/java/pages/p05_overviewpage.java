package pages;

import Utilites.logsutils;
import Utilites.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class p05_overviewpage {

    private final By subtotal = By.xpath("//div[@class=\"summary_subtotal_label\"]");
    private final By tax = By.xpath("//div[@class=\"summary_tax_label\"]");
    private final By total = By.xpath("//div[@class=\"summary_total_label\"]");
    private final By finish = By.id("finish");
    private final WebDriver driver;

    public p05_overviewpage(WebDriver driver) {
        this.driver = driver;
    }

    public float getsubtotla() {
        return Float.parseFloat(utility.getdata(driver, subtotal).replace("Item total: $", ""));
    }

    public float gettax() {
        return Float.parseFloat(utility.getdata(driver, tax).replace("Tax: $", " "));
    }

    public Float gettotal() {

        return Float.parseFloat(utility.getdata(driver, total).replace("Total: $", " "));
    }

    public String totalitem() {

        logsutils.info(String.valueOf(getsubtotla() + gettax()));
        return String.valueOf(getsubtotla() + gettax());
    }

    public boolean comparethetoalprice() {

        return totalitem().equals(String.valueOf(gettotal()));
    }

    public p06_complate clickonfinish() {
        utility.clickonelement(driver, finish);
        return new p06_complate(driver);
    }
}
