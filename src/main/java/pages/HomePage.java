package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ScreenshotUtil;
import utils.PDFReportGenerator;

import base.BaseTest;

public class HomePage extends BaseTest {
    private WebDriver driver;
    Set<String> allWindows;
    String parentWindow;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String productName) {
        try {
            WebElement searchBox = driver.findElement(By.xpath("//div[@class='MoPwtI' or @class='_2SmNnR']//input"));
            searchBox.sendKeys(Keys.CONTROL + "a");
            searchBox.sendKeys(Keys.DELETE);
            searchBox.sendKeys(productName);
            driver.findElement(By.xpath("//button[@class='MJG8Up' or @class='_2iLD__']")).click();

            String path = ScreenshotUtil.captureScreenshot(driver, "SearchProduct_" + productName);
            PDFReportGenerator.logStep("Searched for product: " + productName, "PASS", path);

        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "Error_SearchProduct");
            PDFReportGenerator.logStep("Failed to search product: " + productName, "FAIL", path);
        }
    }

    public void clickonSearchedProduct(String productName) {
        try {
            driver.findElement(By.xpath("//div[@class='KzDlHZ' and text()='" + productName + "']")).click();
            String path = ScreenshotUtil.captureScreenshot(driver, "ClickProduct_" + productName);
            PDFReportGenerator.logStep("Clicked on product: " + productName, "PASS", path);
        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "Error_ClickProduct");
            PDFReportGenerator.logStep("Failed to click product: " + productName, "FAIL", path);
        }
    }

    public void switchToNewWindow() {
        try {
            parentWindow = driver.getWindowHandle();
            allWindows = driver.getWindowHandles();

            for (String window : allWindows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            String path = ScreenshotUtil.captureScreenshot(driver, "SwitchToNewWindow");
            PDFReportGenerator.logStep("Switched to new window", "PASS", path);
        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "Error_SwitchToNewWindow");
            PDFReportGenerator.logStep("Failed to switch to new window", "FAIL", path);
        }
    }

    public void switchToOldWindow() {
        driver.switchTo().window(parentWindow);
    }

    public void searchAndAddProduct(String productName) {
        searchProduct(productName);
        clickonSearchedProduct(productName);
        switchToNewWindow();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void serachByCategory(String Category, String SubCategory, String SubSubCategory) {
        try {
            Actions actions = new Actions(driver);

            actions.moveToElement(driver.findElement(By.xpath("//span[@class='_1XjE3T']//span[text()='" + Category + "']"))).build().perform();
            actions.moveToElement(driver.findElement(By.xpath("//a[@class='_1BJVlg'and text()='" + SubCategory + "']"))).build().perform();
            driver.findElement(By.xpath("//a[@class='_3490ry' and text()='" + SubSubCategory + "']")).click();

            String path = ScreenshotUtil.captureScreenshot(driver, "SearchByCategory_" + SubSubCategory);
            PDFReportGenerator.logStep("Searched by category: " + Category + " > " + SubCategory + " > " + SubSubCategory, "PASS", path);
        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "Error_SearchByCategory");
            PDFReportGenerator.logStep("Failed to search by category", "FAIL", path);
        }
    }

    public void sortBy(String SortType) {
        try {
            driver.findElement(By.xpath("//div[@class='sHCOk2']//div[text()='" + SortType + "']")).click();
            String path = ScreenshotUtil.captureScreenshot(driver, "SortBy_" + SortType);
            PDFReportGenerator.logStep("Sorted by: " + SortType, "PASS", path);
        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "Error_SortBy");
            PDFReportGenerator.logStep("Failed to sort by: " + SortType, "FAIL", path);
        }
    }

    public void selectProductSearchedByCategory(String ProdName) {
        try {
            driver.findElement(By.xpath("//a[@title='" + ProdName + "']")).click();
            String path = ScreenshotUtil.captureScreenshot(driver, "SelectProduct_" + ProdName);
            PDFReportGenerator.logStep("Selected product from category: " + ProdName, "PASS", path);
        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "Error_SelectProduct");
            PDFReportGenerator.logStep("Failed to select product: " + ProdName, "FAIL", path);
        }
    }
}