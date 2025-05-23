package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.SelectedProductPage;

public class SearchProductByCategoryTest extends BaseTest{

	public static void main(String args[]) throws InterruptedException {
		BaseTest base = new BaseTest();
		
		base.setUp();
		base.addwait();
		
		HomePage home  = new HomePage(base.getDriver());
		
		SelectedProductPage selProd = new SelectedProductPage(base.getDriver());
		
		CartPage cart = new CartPage(base.getDriver());
		
		home.serachByCategory("Fashion","Men Footwear", "All");
		
		home.sortBy("Price -- High to Low");
		
		home.selectProductSearchedByCategory("GEL-NIMBUS 26 Training & Gym Shoes For Men");
		
		home.switchToNewWindow();
		
		selProd.addToCart();
		
		cart.placeOrder();
		
		base.generateReportAndCleanup(SearchProductByCategoryTest.class);
	
	}

}
