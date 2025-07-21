package SeleniumFrameworkScarch_PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkScarch_AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents{
    WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCounty;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String CountryName) 
	{
		Actions a=new Actions(driver);
		 a.sendKeys(country,CountryName).build().perform();
		 waitForElementToAppear(results);
		 selectCounty.click();

	}

	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);  
	}
	

}
