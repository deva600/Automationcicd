package SeleniumFrameworkScarch_AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkScarch_PageObjects.OrderPage;
import SeleniumFrameworkScarch_PageObjects.cartPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	 //driver.findElement(By.cssSelector("")).click();
	@FindBy(css="[routerlink*='cart']")
    WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
    WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public cartPage goToCartPage()
	{
		cart.click();
		cartPage cartPage=new cartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage OrderPage=new OrderPage(driver);
		return OrderPage;
	}
	
	
	public void waitForElementToDisappear(WebElement Ele) throws InterruptedException
	{
	    Thread.sleep(3000);
	    //Instead of below code adding this hard thread improved the performance of the script
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(Ele));
	}
}
