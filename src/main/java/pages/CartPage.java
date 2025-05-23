package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import utils.ScreenshotUtil;
import utils.PDFReportGenerator;

public class CartPage extends BaseTest {

    private WebDriver driver;
    List<WebElement> list = new ArrayList<>();

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCart() {
        try {
            driver.findElement(By.xpath("//a[@class='_9Wy27C']")).click();
            PDFReportGenerator.logStep("Clicked on Cart", "PASS", ScreenshotUtil.captureScreenshot(driver, "Click_Cart"));
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to click on Cart", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Click_Cart"));
        }
    }

    public int checkCartCount() {
        list = driver.findElements(By.xpath("//div[@class='_8X-K8p']//div[@class='x9LoV+']//div[1]//a[1]"));
        PDFReportGenerator.logStep("Checked cart count: " + list.size(), "PASS", ScreenshotUtil.captureScreenshot(driver, "Cart_Count"));
        return list.size();
    }

    public void checkCartProdNames() {
        list = driver.findElements(By.xpath("//div[@class='_8X-K8p']//div[@class='x9LoV+']//div[1]//a[1]"));
        for (WebElement option : list) {
            System.out.println("Product Name: " + option.getText());
        }
        PDFReportGenerator.logStep("Checked all product names in cart", "PASS", ScreenshotUtil.captureScreenshot(driver, "Cart_Product_Names"));
    }

    public void validateCartActualProducts() {
        List<String> actualProdList = ProductAddRemoveList.getProdList();
        list = driver.findElements(By.xpath("//div[@class='_8X-K8p']//div[@class='x9LoV+']//div[1]//a[1]"));

        for (WebElement option : list) {
            if (actualProdList.contains(option.getText())) {
                System.out.println("Correct product is present in the cart");
            } else {
                System.out.println("Wrong product added into the cart");
                System.out.println("Wrong Product Name: " + option.getText());
                PDFReportGenerator.logStep("Wrong product found in cart: " + option.getText(), "FAIL", ScreenshotUtil.captureScreenshot(driver, "Wrong_Product_In_Cart"));
                return;
            }
        }
        PDFReportGenerator.logStep("Validated all products in cart successfully", "PASS", ScreenshotUtil.captureScreenshot(driver, "Validate_Products_In_Cart"));
    }

    public void checkCartPriceProdCount() {
        String strcount = driver.findElement(By.xpath("//div[@class='k9WPjB']")).getText().replaceAll("[^\\d]", "");
        int itemcount = Integer.parseInt(strcount);
        int count = checkCartCount();

        if (itemcount == count) {
            PDFReportGenerator.logStep("Cart count matches item count", "PASS", ScreenshotUtil.captureScreenshot(driver, "Cart_Price_Count_Valid"));
        } else {
            PDFReportGenerator.logStep("Cart count does not match item count", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Cart_Price_Count_Invalid"));
        }
    }

    public void checkOriginalPriceDetails() {
        String strTotalPrice = driver.findElement(By.xpath("//div[@class='HRZecL'][1]//span[@class='b5rp0W']")).getText().replaceAll("[^\\d]", "");
        int totalPrice = Integer.parseInt(strTotalPrice);

        list = driver.findElements(By.xpath("//div[@class='_8X-K8p']//div[@class='x9LoV+']//span[@class='LAlF6k KkRCni']"));
        int calTotalPrice = 0;
        for (WebElement price : list) {
            String str = price.getText().replaceAll("[^\\d]", "");
            calTotalPrice += Integer.parseInt(str);
        }

        if (totalPrice == calTotalPrice) {
            PDFReportGenerator.logStep("Total price calculation is valid", "PASS", ScreenshotUtil.captureScreenshot(driver, "Total_Price_Valid"));
        } else {
            PDFReportGenerator.logStep("Total price calculation is invalid", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Total_Price_Invalid"));
        }
    }

    public void removeAllProductsFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> removeButtons = driver.findElements(By.xpath("//div[@class='sBxzFz' and text()='Remove']"));

        while (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sBxzFz fF30ZI A0MXnh' and text()='Remove']"))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='eIDgeN']")));
            removeButtons = driver.findElements(By.xpath("//div[@class='sBxzFz' and text()='Remove']"));
        }
        PDFReportGenerator.logStep("Removed all products from cart", "PASS", ScreenshotUtil.captureScreenshot(driver, "All_Products_Removed"));
    }

    public void removeSpecificProductsFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<String> prodToRemove = ProductAddRemoveList.getRemoveProdList();

        for (String prod : prodToRemove) {
            try {
                By removeBtnLocator = By.xpath("//a[@class='T2CNXf QqLTQ-' and text()='" + prod + "']" +
                        "//ancestor::div[@class='_8X-K8p']/following-sibling::div[@class='d+mEZR JefwG6']" +
                        "//div[@class='sBxzFz' and text()='Remove']");

                WebElement removeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(removeBtnLocator));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", removeBtn);
                Thread.sleep(500);

                try {
                    actions.moveToElement(removeBtn).pause(Duration.ofMillis(300)).click().perform();
                } catch (ElementClickInterceptedException e) {
                    js.executeScript("arguments[0].click();", removeBtn);
                }

                WebElement confirmRemove = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='sBxzFz fF30ZI A0MXnh' and text()='Remove']")));
                actions.moveToElement(confirmRemove).pause(Duration.ofMillis(300)).click().perform();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='eIDgeN']")));
                PDFReportGenerator.logStep("Removed specific product: " + prod, "PASS", ScreenshotUtil.captureScreenshot(driver, "Remove_" + prod));
            } catch (Exception e) {
                PDFReportGenerator.logStep("Failed to remove product: " + prod, "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Remove_" + prod));
            }
        }
    }

    public void validateCartisEmpty() {
        String msg = driver.findElement(By.xpath("//div[@class='s2gOFd']")).getText();
        if (msg.equals("Missing Cart items?")) {
            PDFReportGenerator.logStep("Cart is empty", "PASS", ScreenshotUtil.captureScreenshot(driver, "Cart_Empty"));
        } else {
            PDFReportGenerator.logStep("Cart is not empty", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Cart_Not_Empty"));
        }
    }

    public void placeOrder() {
        try {
            driver.findElement(By.xpath("//button//span[text()='Place Order']")).click();
            PDFReportGenerator.logStep("Clicked on Place Order", "PASS", ScreenshotUtil.captureScreenshot(driver, "Click_Place_Order"));
        } catch (Exception e) {
            PDFReportGenerator.logStep("Failed to click on Place Order", "FAIL", ScreenshotUtil.captureScreenshot(driver, "Error_Place_Order"));
        }
    }
}
