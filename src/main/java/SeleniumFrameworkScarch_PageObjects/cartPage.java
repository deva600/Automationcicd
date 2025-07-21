package SeleniumFrameworkScarch_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkScarch_AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {

	WebDriver driver;
	//Create the constructor to perform login to URL //this will execute first 
	public cartPage(WebDriver driver)
	{
		//Initialize 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//
	//PageFactory way to call as above
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List< WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean verifyProductDisplay(String Productname)
	{
		
		Boolean match=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
        return match;
		
	}
	
	public SeleniumFrameworkScarch_PageObjects.checkOutPage goToCheckout()
	{
		checkoutEle.click();
		checkOutPage checkOutPage=new checkOutPage(driver);
		return checkOutPage;
	}
}
