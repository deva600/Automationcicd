package FrameWorkTraining.Tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFrameworkScarch_PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class Standalone {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\devad\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		String Productname="ZARA COAT 3";
		LandingPage Landingpage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("train@shetty.com");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Welcome@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> product=driver.findElements(By.cssSelector(".mb-3"));;
		WebElement prod=product.stream().filter(products->products.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
	    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	   List<WebElement> cartProducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
	   Boolean match=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
	   Assert.assertTrue(match);
	   System.out.println(match);
	   driver.findElement(By.cssSelector(".totalRow button")).click();
	  /*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
	   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")))); */
	   ///Above 2 lines of code also work actions is another way to do as shown below//
	   Actions a=new Actions(driver);
	   a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
	   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]"))));
	   driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	   Thread.sleep(5000);
	   driver.findElement(By.cssSelector(".action__submit")).click();
	   String confirmmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
	   Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
	   Thread.sleep(5000);
	   driver.quit();
	   }
	}
