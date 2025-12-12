package tests;

import Driverfactory.drivefactory;
import Utilites.datautil;
import Utilites.logsutils;
import Utilites.utility;
import listeners.Invokedresultlisteners;
import listeners.Itestresultlisteners;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.p01_loginpage;
import pages.p02_landingpage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static Driverfactory.drivefactory.getdriver;
import static Utilites.utility.getallcookies;
import static Utilites.utility.restorecookies;

@Listeners({Itestresultlisteners.class, Invokedresultlisteners.class})

public class Tc2_landingpage {
    Set<Cookie> cookies;

    @BeforeClass
    public void validlogin() throws IOException {
        drivefactory.setup(datautil.propertiesfile("enviroment", "browser"));
        logsutils.info("the edge browser is opened");
        drivefactory.getdriver().get(datautil.propertiesfile("enviroment", "base_url"));
        logsutils.info("the edge browser is navigate to url");
        drivefactory.getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        new p01_loginpage(getdriver())
                .enterusername(datautil.gsonfile("validlogin", "username"))
                .enterpassword(datautil.gsonfile("validlogin", "password"))
                .clickonbutton();
        cookies = getallcookies(getdriver());
        drivefactory.quit();
    }

    @BeforeMethod
    public void setup() throws IOException {
        drivefactory.setup(datautil.propertiesfile("enviroment", "browser"));
        logsutils.info("the edge browser is opened");
        drivefactory.getdriver().get(datautil.propertiesfile("enviroment", "base_url"));
        logsutils.info("the edge browser is navigate to url");
        drivefactory.getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        restorecookies(getdriver(), cookies);
        drivefactory.getdriver().get(datautil.propertiesfile("enviroment", "home_url"));
    }


    @Test
    public void addrandomnumbertocart() throws FileNotFoundException {

        new p02_landingpage(getdriver()).addrandomnumber(3, 6);
        Assert.assertTrue(new p02_landingpage(getdriver()).comparetheaddnumberofproductwithcart());

    }

    @Test
    public void verfiythecartpage() throws IOException {
        new p02_landingpage(getdriver())
                .clickoncarticon();
        Assert.assertTrue(utility.verfifycarticon(getdriver(), datautil.propertiesfile("enviroment", "cart_page")));
    }

    @AfterMethod
    public void quit() {
        drivefactory.quit();

    }

    @AfterClass
    public void quitcookie() {

        cookies.clear();
    }
}
