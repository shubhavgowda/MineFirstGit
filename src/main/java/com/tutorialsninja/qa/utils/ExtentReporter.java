package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() throws Throwable {
		ExtentReports extentReport=new ExtentReports();

		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Shubha V Gowda's First Framework");
		sparkReporter.config().setDocumentTitle("TN AUTOMATION REPORT");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy  hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		Properties configProp=new Properties();
		File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis=new FileInputStream(configPropFile);
		configProp.load(fis);


		extentReport.setSystemInfo("Application URL" , configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email ID", configProp.getProperty("validEmailID"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		 
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User-Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
		
	}
}
