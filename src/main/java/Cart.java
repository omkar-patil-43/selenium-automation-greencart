import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {
    
    // Utility method to validate expected vs actual products on Cart or Checkout page
    public static void validateProducts(List<WebElement> actualList, String[] expected, String context) {
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getText().equals(expected[i])) {
                System.out.println("Valid Product: " + expected[i] + " present in " + context);
            } else {
                System.out.println("Invalid Product: " + expected[i] + " present in " + context);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        // Launch browser
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        // Products to be added
        String[] itemToAdd = {"Cucumber - 1 Kg", "Beetroot - 1 Kg", "Potato - 1 Kg", "Mango - 1 Kg", "Pears - 1 Kg"};
        int productPrice = 0;
        int allProductsPrice = 0;

        // Add each product to cart and calculate total price
        for (String item : itemToAdd) {
            driver.findElement(By.xpath("//h4[text()='" + item + "']/following-sibling::div[@class='product-action']//button")).click();
            productPrice = Integer.parseInt(driver.findElement(By.xpath("//h4[text()='" + item + "']/following-sibling::p[@class='product-price']")).getText());
            allProductsPrice += productPrice;
        }

        // Verify cart item count and price
        int cartItems = Integer.parseInt(driver.findElement(By.xpath("//tr//td[text()='Items']/following-sibling::td[2]")).getText());
        int cartPrice = Integer.parseInt(driver.findElement(By.xpath("//tr//td[text()='Price']/following-sibling::td[2]")).getText());

        if (itemToAdd.length == cartItems && allProductsPrice == cartPrice) {
            System.out.println("Cart Items and Cart Price verified.");
        }

        // Open cart preview
        driver.findElement(By.xpath("//a[@class='cart-icon']")).click();

        // Validate products in cart preview
        List<WebElement> cartProdList = driver.findElements(By.xpath("//div[@class='cart-preview active']//p[@class='product-name']"));
        validateProducts(cartProdList, itemToAdd, "Cart");

        // Proceed to checkout
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();

        // Wait for checkout page to load and validate products
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//p[@class='product-name']")));

        List<WebElement> checkoutProdList = driver.findElements(By.xpath("//td//p[@class='product-name']"));
        validateProducts(checkoutProdList, itemToAdd, "Checkout Page");

        // Wait and apply promo code
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Enter promo code')]")));
        driver.findElement(By.xpath("//input[contains(@placeholder,'Enter promo code')]")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//button[@class='promoBtn']")).click();

        // Wait for promo confirmation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));

        // Place order
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        // Select country
        WebElement drop = driver.findElement(By.xpath("//div[@class='wrapperTwo']//div//select"));
        Select dropdown = new Select(drop);
        dropdown.selectByVisibleText("India");

        // Agree to terms and proceed
        driver.findElement(By.xpath("//input[@class='chkAgree']")).click();
        driver.findElement(By.xpath("//button[text()='Proceed']")).click();

        // Validate success message
        WebDriverWait w3 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successMsgElement = w3.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@class='wrapperTwo']//span")));

        String actualMsg = successMsgElement.getText();
        System.out.println("Actual Message: " + actualMsg);

        if (actualMsg.contains("Thank you, your order has been placed successfully")) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order failed or unexpected message.");
        }

        // Close the browser session
        driver.quit();
    }
}
