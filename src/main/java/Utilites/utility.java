package Utilites;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class utility {
    private static final String screenshot_path = "test-outputs/screenshots/";

    // Todo: clickon element
    public static void clickonelement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //Todo: send data
    public static void sendkeys(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    //Todo:get by webelement
    public static WebElement webElement(WebDriver driver, By locator) {

        return driver.findElement(locator);
    }

    public static String getdata(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(webElement(driver, locator)));
        return driver.findElement(locator).getText();

    }

    //Todo:Srcolling
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoView", webElement(driver, locator));

    }

    //Todo:general wait
    public static WebDriverWait generalwait(WebDriver driver) {

        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Todo:take screenshot
    public static void takingscreenshot(String screenshotname, WebDriver driver) throws IOException {

        File screenshotsrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotfile = new File(screenshot_path + screenshotname + ".png");
        FileUtils.copyFile(screenshotfile, screenshotsrc);
        Allure.addAttachment(screenshotname, Files.newInputStream(Path.of(screenshotfile.getPath())));


    }

    //Todo:timestemp
    public static String gettimestamp() {

        return new SimpleDateFormat("yyy-MM-dd-h-m-ssa").format(new Date());

    }

    //Todo:slecting dropdown
    public static void selectfromdropdow(WebDriver driver, By locator, String option) {
        new Select(webElement(driver, locator)).selectByVisibleText(option);

    }

    //Todo:take full screenshots
    public static void takefullscreenshot(WebDriver driver, By locator) {
        Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                .highlight(webElement(driver, locator))
                .save(screenshot_path);

    }

    //Todo:generaterandomnumber
    public static int ggeneraterandomnumber(int upperpound) {

        return new Random().nextInt(upperpound) + 1;
    }

    public static Set<Integer> generateunquienumber(int numberneeded, int totalnumberofproduct) {

        Set<Integer> generatenumbers = new HashSet<>();
        while (generatenumbers.size() < numberneeded) {

            int randomnumber = ggeneraterandomnumber(totalnumberofproduct);
            generatenumbers.add(randomnumber);
        }
        return generatenumbers;
    }


    //todo:verfiyurl
    public static Boolean verfifycarticon(WebDriver driver, String expectedurl) {
        try {
            utility.generalwait(driver).until(ExpectedConditions.urlToBe(expectedurl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static File gelastfileslogs(String filepath) {
        File folder = new File(filepath);
        File[] files = folder.listFiles();
        if (files.length == 0) {
            return null;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    public static Set<Cookie> getallcookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restorecookies(WebDriver driver, Set<Cookie> cookies) {

        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    public static String injectionapi() {
        String response = RestAssured.given()
                .contentType("application/json")
                .body("{\"username\":\"youssef12345 \",\"password\":\"b3p5NDU2ODUy\"}")
                .when()
                .post("https://api.demoblaze.com/signup")
                .then()
                .extract()
                .body()
                .asString();
        System.out.println(response);
        return response;
    }
}
