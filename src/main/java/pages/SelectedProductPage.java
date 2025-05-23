package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BaseTest;
import utils.ScreenshotUtil;
import utils.PDFReportGenerator;

public class SelectedProductPage extends BaseTest {

    private WebDriver driver;

    public SelectedProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getproductName() {
        try {
            String name = driver.findElement(By.xpath("//span[@class='VU-ZEz']")).getText();
            PDFReportGenerator.logStep("Captured product name: " + name, "PASS", ScreenshotUtil.captureScreenshot(driver, "Product_Name"));
            return name;
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to get product name", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Product_Name"));
            return null;
        }
    }

    public int getProdPrice() {
        try {
            String price = driver.findElement(By.xpath("//div[@class='UOCQB1']//div[1]//div[2]")).getText().replaceAll("[^\\d]", "");
            PDFReportGenerator.logStep("Captured original product price", "PASS", ScreenshotUtil.captureScreenshot(driver, "Product_Price"));
            return Integer.parseInt(price);
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to get product price", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Product_Price"));
            return 0;
        }
    }

    public int getProdDiscountedPrice() {
        try {
            String price = driver.findElement(By.xpath("//div[@class='UOCQB1']//div[1]//div[1]")).getText().replaceAll("[^\\d]", "");
            PDFReportGenerator.logStep("Captured discounted product price", "PASS", ScreenshotUtil.captureScreenshot(driver, "Discounted_Price"));
            return Integer.parseInt(price);
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to get discounted price", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Discounted_Price"));
            return 0;
        }
    }

    public int getDiscount() {
        try {
            String discount = driver.findElement(By.xpath("//div[@class='UOCQB1']//div[1]//div[3]//span")).getText().replaceAll("[^\\d]", "");
            PDFReportGenerator.logStep("Captured discount percentage", "PASS", ScreenshotUtil.captureScreenshot(driver, "Product_Discount"));
            return Integer.parseInt(discount);
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to get discount", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Product_Discount"));
            return 0;
        }
    }

    public void addToCart() {
        try {
            driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2 JTo6b7' or text()='Add to cart']")).click();
            PDFReportGenerator.logStep("Clicked on Add to Cart", "PASS", ScreenshotUtil.captureScreenshot(driver, "Click_Add_To_Cart"));
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to click Add to Cart", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Add_To_Cart"));
        }
    }
}
