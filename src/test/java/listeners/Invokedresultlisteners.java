package listeners;

import Utilites.logsutils;
import Utilites.utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import pages.p02_landingpage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static Driverfactory.drivefactory.getdriver;

public class Invokedresultlisteners implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

        File logsfile = utility.gelastfileslogs(logsutils.log_path);
        try {
            Allure.addAttachment("logs.log", Files.readString(logsfile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (testResult.getStatus() == ITestResult.FAILURE) {
            try {
                //  utility.takingscreenshot(testResult.getName(), getdriver());
                utility.takefullscreenshot(getdriver(), new p02_landingpage(getdriver()).getnumberofcart());
            } catch (Exception e) {
                logsutils.error(e.getMessage());
            }

        }


    }
}
