package FrameWorkTraining.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWorkTraining.TestComponents.baseTest;
import SeleniumFrameworkScarch_PageObjects.ConfirmationPage;
import SeleniumFrameworkScarch_PageObjects.OrderPage;
import SeleniumFrameworkScarch_PageObjects.cartPage;
import SeleniumFrameworkScarch_PageObjects.checkOutPage;
import SeleniumFrameworkScarch_PageObjects.productCatalogue;

public class SubmitOrderTest extends baseTest {
	//private static final String Productname = null;
	String Productname="ZARA COAT 3";
	@Test(dataProvider= "getData",groups={"purchase"})
	public void Submit_Order(HashMap<String,String> input ) throws InterruptedException {

		//String Productname="ZARA COAT 3";
        productCatalogue productCatalogue=Landingpage.loginApplication(input.get("user"),input.get("password"));
	
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("Productname"));
		
		cartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("Productname"));
		//Assertion cannot be done in page object class
		Assert.assertTrue(match);
		
		checkOutPage checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		
		ConfirmationPage ConfirmationPage=checkOutPage.submitOrder();
		String confirmmsg=ConfirmationPage.verifyConfirmationMsg();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
	    
	   }
	
//To verify the product is displayed Zara Coat3:
	
@Test (dependsOnMethods= {"Submit_Order"})

public void orderHistoryTest()
{
	//To verify the product is displayed Zara Coat3:
	productCatalogue productCatalogue=Landingpage.loginApplication("train@shetty.com", "Welcome@123");
	OrderPage Orderpage=productCatalogue.goToOrderPage();
	Assert.assertTrue(Orderpage.verifyOrderDisplay(Productname));
	
}
	
//Place order with different data eg:ADIDAS ORIGINAL-Products	
	@Test(groups={"purchase"})
	public void Submit1_Order() throws InterruptedException {

		String Productname="ADIDAS ORIGINAL";
        productCatalogue productCatalogue=Landingpage.loginApplication("train@shetty.com", "Welcome@123");
	
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(Productname);
		
		cartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(Productname);
		//Assertion cannot be done in page object class
		Assert.assertTrue(match);
		
		checkOutPage checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		
		ConfirmationPage ConfirmationPage=checkOutPage.submitOrder();
		String confirmmsg=ConfirmationPage.verifyConfirmationMsg();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
	    
	   }
	
	public String getScreenShot(String testCaseName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"//reports"+ testCaseName+".png");
		FileUtils.copyFile(source, des);
		return System.getProperty("user.dir")+"//reports"+ testCaseName+".png";
		
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("user", "train@shetty.com");
		map.put("password", "Welcome@123");
		map.put("Productname", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("user", "train@shetty.com");
		map1.put("password", "Welcome@123");
		map1.put("Productname", "ADIDAS ORIGINAL"); */
		
		List<HashMap<String,String>> data= getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//FrameWorkTraining//data//purchaseOrder.jason");
		
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
	}

	}


