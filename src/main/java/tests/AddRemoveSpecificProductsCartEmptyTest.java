package tests;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.ProductAddRemoveList;
import pages.SelectedProductPage;

public class AddRemoveSpecificProductsCartEmptyTest {
	 public static void main(String[] args) throws InterruptedException {
 	   BaseTest base = new BaseTest();
       base.setUp();
       base.addwait();
    
     HomePage home = new HomePage(base.getDriver());
     SelectedProductPage selProd = new SelectedProductPage(base.getDriver());

     for (String prod : ProductAddRemoveList.getProdList()) {
         home.searchAndAddProduct(prod);
         //Thread.sleep(3000); // Wait for new window to load
         selProd.addToCart();
         Thread.sleep(3000);
         base.getDriver().close(); // Close child window
         home.switchToOldWindow(); // Go back to parent
         //Thread.sleep(3000);
     }
     
     //CartPage code from here
     
     CartPage cart = new CartPage(base.getDriver());
     
     cart.clickCart();
     
     Thread.sleep(3000);
     
     cart.removeSpecificProductsFromCart(); 
     
     cart.validateCartisEmpty();
     
     base.generateReportAndCleanup(AddRemoveSpecificProductsCartEmptyTest.class);
 }

}
