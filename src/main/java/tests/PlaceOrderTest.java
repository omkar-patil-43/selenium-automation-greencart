package tests;

import java.util.ArrayList;
import java.util.List;

import base.BaseTest;
import pages.HomePage;
import pages.SelectedProductPage;
import pages.CartPage;

public class PlaceOrderTest extends BaseTest {

    public static void main(String[] args) throws InterruptedException {
    	  BaseTest base = new BaseTest();
          base.setUp();
          base.addwait();

        List<String> prodList = new ArrayList<String>();
        prodList.add("Apple iPhone 13 (Midnight, 128 GB)");
        prodList.add("SAMSUNG Galaxy M35 5G (Moonlight Blue, 128 GB)");


        HomePage home = new HomePage(base.getDriver());
        SelectedProductPage selProd = new SelectedProductPage(base.getDriver());

        for (String prod : prodList) {
            home.searchAndAddProduct(prod);
            Thread.sleep(3000); // Wait for new window to load
            selProd.addToCart();
            Thread.sleep(5000);
            base.getDriver().close(); // Close child window
            home.switchToOldWindow(); // Go back to parent
            Thread.sleep(3000);
        }
        
        //CartPage code from here
        
        CartPage cart = new CartPage(base.getDriver());
        
        cart.clickCart();
        
        cart.checkCartProdNames();

        base.tearDown();
        
        base.generateReportAndCleanup(PlaceOrderTest.class);
        
    }

}
