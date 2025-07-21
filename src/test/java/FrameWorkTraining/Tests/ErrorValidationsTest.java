package FrameWorkTraining.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import FrameWorkTraining.TestComponents.baseTest;
import SeleniumFrameworkScarch_PageObjects.cartPage;
import SeleniumFrameworkScarch_PageObjects.productCatalogue;

public class ErrorValidationsTest extends baseTest {
	

	@Test
	public void loginError() throws InterruptedException {

		Landingpage.loginApplication("trai@shetty.com", "Welcom@123");
      //div[@aria-label='Incorrect email or password.']
        Assert.assertEquals(Landingpage.ErrorLogin(),"Incorrect email password.");        
		   }
	@Test(groups={"errorhandle"})
	public void productCatalogueValidation() throws InterruptedException {

		String Productname="ZARA COAT 3";
        productCatalogue productCatalogue=Landingpage.loginApplication("train@shetty.com", "Welcome@123");
	
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(Productname);
		cartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		//Assertion cannot be done in page object class
		Assert.assertFalse(match);
		
	}	
	}

