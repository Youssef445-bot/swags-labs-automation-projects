package tests;

import Driverfactory.drivefactory;
import Utilites.datautil;
import Utilites.logsutils;
import listeners.Invokedresultlisteners;
import listeners.Itestresultlisteners;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.p01_loginpage;
import pages.p02_landingpage;
import pages.p03_cart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.drivefactory.getdriver;

@Listeners({Itestresultlisteners.class, Invokedresultlisteners.class})
public class Tc3_cartpage {
    @BeforeMethod
    public void setup() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : datautil.propertiesfile("enivroment", "browser");
        logsutils.info(System.getProperty("browser"));
        drivefactory.setup(browser);
        logsutils.info("the edge browser is opened");
        drivefactory.getdriver().get(datautil.propertiesfile("enviroment", "base_url"));
        logsutils.info("the edge browser is navigate to url");
        drivefactory.getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void comparethetoltalprice() throws FileNotFoundException {
        String totlaprice = new p01_loginpage(getdriver())
                .enterusername(datautil.gsonfile("validlogin", "username"))
                .enterpassword(datautil.gsonfile("validlogin", "password"))
                .clickonbutton()
                .addrandomnumber(3, 6)
                .gettotalpriceofselectedproduct();
        new p02_landingpage(getdriver()).clickoncarticon();
        Assert.assertTrue(new p03_cart(getdriver()).comparepriceing(totlaprice));
    }

    @AfterMethod
    public void quit() {
        drivefactory.quit();
    }
}
