package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener  {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		try {
			extentReport = ExtentReporter.generateExtentReport();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("Execution of project Test Started ");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + "Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.PASS, testName + "Got Successfully Executing");
		System.out.println(testName + "Got Successfully Executing" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		System.out.println("Screenshot Taken");
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath=System.getProperty("user.dir")+"\\Scrrenshots\\" +testName+" .png";
		//		FileUtils.getFile(srcScreenshot, destinationScreenshotPath);
		try {
			FileHandler. copy(srcScreenshot,new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName +"Got Testcases Failure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, testName + "Got TestCases Skipped");

	}
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		//		System.out.println("Execution of project got Finished" );
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
