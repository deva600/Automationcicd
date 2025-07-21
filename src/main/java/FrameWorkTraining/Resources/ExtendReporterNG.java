package FrameWorkTraining.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

	
public static ExtentReports getreportObject()
{
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter Reporter= new ExtentSparkReporter(path);
	Reporter.config().setReportName("Web Automation Results");
	Reporter.config().setDocumentTitle("Test Results");
	
	ExtentReports Extent=new ExtentReports();
    Extent.attachReporter(Reporter);
    Extent.setSystemInfo("Tester", "Devadirajan");
    return  Extent;
}

}
