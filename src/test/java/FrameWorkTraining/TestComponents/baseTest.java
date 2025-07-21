package FrameWorkTraining.TestComponents;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkScarch_PageObjects.LandingPage;

public class baseTest  {
	
	public WebDriver driver;
	public LandingPage Landingpage;

	public  WebDriver intializeDriver() throws IOException
	{
		
		//Properties Class//
		Properties prop=new Properties();
		//FileInputStream fis=new FileInputStream("C:\\Users\\devad\\eclipse-workspace\\SeleniumFrameworkScarch\\src\\main\\java\\FrameWorkTraining\\Resources\\GlobalData.properties");
		//Above path can be delcare as below// to avoid hard coding local path we used user.dir so anyone can use the code
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\FrameWorkTraining\\Resources\\GlobalData.properties");

		prop.load(fis);
		String Browsername=prop.getProperty("browser");
		if(Browsername.equalsIgnoreCase("chrome"))
		{
	    //System.setProperty("webdriver.chrome.driver","C:\\Users\\devad\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			
		driver=new ChromeDriver();
		
		}
		else if(Browsername.equalsIgnoreCase("firefox"))
		{
			//write firefoxdriver//
			//Done have it so not typing the code
			//WebDriver driver=new geckoDriver()
			
		}
		else if (Browsername.equalsIgnoreCase("Edge"))
		{
		driver=new EdgeDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
		
	public List<HashMap<String, String>> getJasonDataToMap(String Filepath) throws IOException
	{
		//Read Jason to String 
	
		@SuppressWarnings("deprecation")
		String jasoncontent=FileUtils.readFileToString(new File(Filepath));
		//String to HashMap-> need Jackson Databind to be added in dependency//
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jasoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;	
		}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"//reports"+ testCaseName+".png");
		FileUtils.copyFile(source, des);
		return System.getProperty("user.dir")+"//reports"+ testCaseName+".png";
		
	}
    @BeforeMethod(alwaysRun=true)
	public LandingPage loginApplication() throws IOException
	{
		driver=intializeDriver();
		Landingpage=new LandingPage(driver);
		Landingpage.goTo();
		return Landingpage;
	}
    @AfterMethod(alwaysRun=true)
    public void teardown() throws InterruptedException
    {
    	Thread.sleep(5000);
	    driver.quit();
    }
    
}
