package tests;

import java.util.ArrayList;
import java.util.List;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.SelectedProductPage;

public class AddRemoveAllCartProductsTest extends BaseTest {
	 static private List<String> prodList = new ArrayList<String>();
	 public static void main(String[] args) throws InterruptedException {
  	 BaseTest base = new BaseTest();
        base.setUp();
        base.addwait();

      
      prodList.add("Apple iPhone 13 (Midnight, 128 GB)");
      prodList.add("SAMSUNG Galaxy M35 5G (Moonlight Blue, 128 GB)");
     
      HomePage home = new HomePage(base.getDriver());
      SelectedProductPage selProd = new SelectedProductPage(base.getDriver());

      for (String prod : prodList) {
          home.searchAndAddProduct(prod);
          //Thread.sleep(3000); // Wait for new window to load
          selProd.addToCart();
          Thread.sleep(5000);
          base.getDriver().close(); // Close child window
          home.switchToOldWindow(); // Go back to parent
          //Thread.sleep(3000);
      }
     // Thread.sleep(3000);
      //CartPage code from here
      
      CartPage cart = new CartPage(base.getDriver());
      
      cart.clickCart();
      	
      cart.removeAllProductsFromCart();
      
      cart.validateCartisEmpty();
      
      base.generateReportAndCleanup(AddRemoveAllCartProductsTest.class);
    
  }

  public static List<String>getProdList(){
	   return prodList;
  }
	
}
