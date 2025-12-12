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

import java.io.IOException;
import java.time.Duration;

import static Driverfactory.drivefactory.getdriver;

@Listeners({Itestresultlisteners.class, Invokedresultlisteners.class})
public class Tc1_loginpage {

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        //condition ? true : false

        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : datautil.propertiesfile("enviroment", "browser");
        logsutils.info(System.getProperty("browser"));
        drivefactory.setup(browser);
        logsutils.info("the edge browser is opened");
        drivefactory.getdriver().get(datautil.propertiesfile("enviroment", "base_url"));
        logsutils.info("the edge browser is navigate to url");
        drivefactory.getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void validlogin() throws IOException {
        new p01_loginpage(getdriver())
                .enterusername(datautil.gsonfile("validlogin", "username"))
                .enterpassword(datautil.gsonfile("validlogin", "password"))
                .clickonbutton();
        Assert.assertTrue(new p01_loginpage(getdriver()).asserloginTc(datautil.propertiesfile("enviroment", "home_url")));
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        drivefactory.quit();

    }
}
