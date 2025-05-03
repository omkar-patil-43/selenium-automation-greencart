import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CartOptimized {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        String[] itemsToAdd = {"Cucumber - 1 Kg", "Beetroot - 1 Kg", "Potato - 1 Kg", "Mango - 1 Kg", "Pears - 1 Kg"};

        int expectedTotalPrice = 0;

        for (String item : itemsToAdd) {
            driver.findElement(By.xpath("//h4[text()='" + item + "']/following-sibling::div[@class='product-action']//button")).click();
            int price = Integer.parseInt(getText(driver, By.xpath("//h4[text()='" + item + "']/following-sibling::p[@class='product-price']")));
            expectedTotalPrice += price;
        }

        int cartItems = Integer.parseInt(getText(driver, By.xpath("//tr//td[text()='Items']/following-sibling::td[2]")));
        int cartPrice = Integer.parseInt(getText(driver, By.xpath("//tr//td[text()='Price']/following-sibling::td[2]")));

        if (itemsToAdd.length == cartItems && expectedTotalPrice == cartPrice) {
            System.out.println("Cart Items and Cart Price are verified");
        }

        driver.findElement(By.xpath("//a[@class='cart-icon']")).click();

        List<WebElement> cartProdList = driver.findElements(By.xpath("//div[@class='cart-preview active']//p[@class='product-name']"));
        validateProductList(cartProdList, itemsToAdd, "Cart");

        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();

        List<WebElement> checkoutProdList = driver.findElements(By.xpath("//td//p[@class='product-name']"));
        validateProductList(checkoutProdList, itemsToAdd, "Checkout");

        driver.findElement(By.xpath("//input[contains(@placeholder,'Enter promo code')]")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        selectDropdownByVisibleText(driver, By.xpath("//div[@class='wrapperTwo']//div//select"), "India");

        driver.findElement(By.xpath("//input[@class='chkAgree']")).click();
        driver.findElement(By.xpath("//button[text()='Proceed']")).click();

        String expectedSuccess = "Thank you, your order has been placed successfully You'll be redirected to Home page shortly!!";
        String actualMsg = getText(driver, By.xpath("//div[@class='wrapperTwo']//span"));

        if (expectedSuccess.equals(actualMsg)) {
            System.out.println("Order is placed successfully");
        }

        Thread.sleep(5000);
        driver.quit();
    }

    private static String getText(WebDriver driver, By locator) {
        return driver.findElement(locator).getText();
    }

    private static void validateProductList(List<WebElement> actualList, String[] expectedItems, String context) {
        for (int i = 0; i < actualList.size(); i++) {
            String actual = actualList.get(i).getText();
            String expected = expectedItems[i];
            if (actual.equals(expected)) {
                System.out.println("Valid Product '" + expected + "' Present in " + context);
            } else {
                System.out.println("Invalid Product. Expected: '" + expected + "' but found: '" + actual + "' in " + context);
            }
        }
    }

    private static void selectDropdownByVisibleText(WebDriver driver, By locator, String visibleText) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }
}
