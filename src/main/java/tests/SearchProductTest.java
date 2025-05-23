package tests;


import base.BaseTest;
import pages.HomePage;
import pages.SelectedProductPage;
import utils.FileUtil;
import utils.PDFReportGenerator;

public class SearchProductTest extends BaseTest {
	
    public static void main(String[] args) {
        BaseTest base = new BaseTest();
        base.setUp();
        base.addwait();
        
        String prodName = "Apple iPhone 13 (Midnight, 128 GB)";
        
        HomePage home = new HomePage(base.getDriver());
        
        SelectedProductPage SelProd = new SelectedProductPage(base.getDriver());
        
        home.searchAndAddProduct(prodName);
        
        //validate product name
      //  Assert.assertEquals(SelProd.getproductName(), prodName);
        
        //validate original product price
       // Assert.assertEquals(SelProd.getProdPrice(), 49900);
        
        //validate discounted product price
      //  Assert.assertEquals(SelProd.getProdDiscountedPrice(), 44999);
        
        base.tearDown();
        
        base.generateReportAndCleanup(SearchProductTest.class);


    }
}

