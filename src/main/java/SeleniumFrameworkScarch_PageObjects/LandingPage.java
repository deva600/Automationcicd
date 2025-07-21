package SeleniumFrameworkScarch_PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkScarch_AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	//Create the constructor to perform login to URL //this will execute first 
	public LandingPage(WebDriver driver)
	{
		//Initialise to parent class using super keyword
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement UserEmail=driver.findElement(By.id("userEmail"));
	//PageFactory way to call as above
	@FindBy(id="userEmail")
	WebElement UserEmail;
	
	@FindBy(css="input[type='password")
	WebElement Password;
	
	@FindBy(xpath="//input[@name='login']")
	WebElement Submit;
	
	@FindBy(id="toast-container")
	WebElement Error;
	
	//Create one action method for perform the action on the webpage///
	//Import points to remember Page object class have only page elements not the data required for the elememts///
	
	public productCatalogue loginApplication(String Useremail,String password)
	{
		UserEmail.sendKeys(Useremail);
		Password.sendKeys(password);
		Submit.click();
		productCatalogue productCatalogue=new productCatalogue(driver);
		return productCatalogue;
	}
	public String ErrorLogin()
	{
		return Error.getText();
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
}
