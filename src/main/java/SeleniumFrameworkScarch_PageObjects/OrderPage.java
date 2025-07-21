package SeleniumFrameworkScarch_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkScarch_AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	//Create the constructor to perform login to URL //this will execute first 
	public OrderPage(WebDriver driver)
	{
		//Initialize 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//
	//PageFactory way to call as above
	@FindBy(css="tr td:nth-child(3)")
	List< WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean verifyOrderDisplay(String Productname)
	{
		
		Boolean match=productNames.stream().anyMatch(productName->productName.getText().equalsIgnoreCase(Productname));
        return match;       
		
	}
}
