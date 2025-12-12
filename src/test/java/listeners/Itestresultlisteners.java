package listeners;

import Utilites.logsutils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Itestresultlisteners implements ITestListener {
    public void onTestStart(ITestResult result) {
        logsutils.info("Testcase " + result.getName() + "started");

    }

    public void onTestFailure(ITestResult result) {
        logsutils.info("Testcase " + result.getName() + "started");
    }

    public void onTestSkipped(ITestResult result) {
        logsutils.info("Testcase " + result.getName() + "  started ");
    }
}
