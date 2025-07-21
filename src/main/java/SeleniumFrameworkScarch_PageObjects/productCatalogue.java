package SeleniumFrameworkScarch_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkScarch_AbstractComponents.AbstractComponents;

public class productCatalogue extends AbstractComponents {

	WebDriver driver;
	//Create the constructor to perform login to URL //this will execute first 
	public productCatalogue(WebDriver driver)
	{
		//Initialize 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//List<WebElement> product=driver.findElements(By.cssSelector(".mb-3"));
	//PageFactory way to call as above
	@FindBy(css=".mb-3")
	List< WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastmsg=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	
	
	//Import points to remember Page object class have only page elements not the data required for the elememts///
	
	public WebElement getproductName(String Productname )
	{
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
		return prod;
		
	}
	
	//Create one action method for perform the action on the webpage///
	
	public void addProductToCart(String Productname ) throws InterruptedException 
	{
		WebElement prod=getproductName(Productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmsg);
		waitForElementToDisappear(spinner);
     }
}
