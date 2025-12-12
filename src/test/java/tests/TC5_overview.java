package tests;

import Driverfactory.drivefactory;
import Utilites.datautil;
import Utilites.logsutils;
import Utilites.utility;
import com.github.javafaker.Faker;
import listeners.Invokedresultlisteners;
import listeners.Itestresultlisteners;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Driverfactory.drivefactory.getdriver;

@Listeners({Itestresultlisteners.class, Invokedresultlisteners.class})
public class TC5_overview {
    private final String FIRSTNAME = datautil.gsonfile("information", "fname") + "-" + utility.gettimestamp();
    private final String LASTNAME = datautil.gsonfile("information", "lname") + "-" + utility.gettimestamp();
    private final String ZIPCODE = new Faker().number().digits(5);

    public TC5_overview() throws FileNotFoundException {
    }

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
    public void checkouttc() throws IOException {
        new p01_loginpage(getdriver())
                .enterusername(datautil.gsonfile("validlogin", "username"))
                .enterpassword(datautil.gsonfile("validlogin", "password"))
                .clickonbutton();
        new p02_landingpage(getdriver()).addallproducttocart()
                .clickoncarticon();
        new p03_cart(getdriver()).clickoncheckout();
        new p04_checkoutpage(getdriver()).fillingtheinformation(FIRSTNAME, LASTNAME, ZIPCODE).clickoncontinue();
        logsutils.info("firstname :" + FIRSTNAME + "lastname" + LASTNAME + "zipcode : " + ZIPCODE);
        Assert.assertTrue(new p05_overviewpage(getdriver()).comparethetoalprice());
    }

    @AfterMethod
    public void quit() {
        drivefactory.quit();
    }
}
