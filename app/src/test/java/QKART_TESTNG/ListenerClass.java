package QKART_TESTNG;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends QKART_Tests implements ITestListener{

    public void OnTestStart(ITestResult result){
        takeScreenshot(driver, "OnTestStart", result.getName());
    }

    public void OnTestSuccess(ITestResult result){
        takeScreenshot(driver, "OnTestSuccess", result.getName());
    }

    public void OnTestFailure(ITestResult result){
        takeScreenshot(driver, "OnTestFailure", result.getName());
    }

}