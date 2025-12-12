package Driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class drivefactory {

    public static final ThreadLocal<WebDriver> driverThreadLocal=new ThreadLocal<>();

    public static void setup(String browser){
        switch (browser.toLowerCase()){
            case "firefox" :
                driverThreadLocal.set(new FirefoxDriver());
                break;
            case "chrome":
                driverThreadLocal.set(new ChromeDriver());
                break;
            default:
                EdgeOptions options=new EdgeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(options));
        }


        }
    public static WebDriver getdriver(){
return driverThreadLocal.get();
    }
    public static void quit(){
getdriver().quit();
driverThreadLocal.remove();
    }
}
