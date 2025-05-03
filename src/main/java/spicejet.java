import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class spicejet {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("https://www.spicejet.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']")).click();
		driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//div//div//input[1]")).sendKeys("Pune");
		driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']//div//div//input[1]")).clear();
		driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']//div//div//input[1]")).sendKeys("Mumbai");

		driver.findElement(By.xpath("//div[@data-testid='undefined-calendar-day-27']")).click();
		String str=driver.findElement(By.xpath("//div[@data-testid='return-date-dropdown-label-test-id']")).getDomAttribute("style");
		
		if(str.contains("rgb(238, 238, 238)")) {
			System.out.println("button is disabled");
		}
		
		driver.findElement(By.xpath("//div[@data-testid='home-page-travellers']")).click();
		driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();
		driver.findElement(By.xpath("//div[@data-testid='home-page-travellers-done-cta']")).click();
		Thread.sleep(3000);
		
	
		driver.findElement(By.xpath("//div[text()='INR']//parent::div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']")).click();
		
		List<WebElement> list = driver.findElements(By.xpath("//div[text()='USD']//parent::div[@class='css-1dbjc4n r-1habvwh r-1loqt21 r-1777fci r-1mi0q7o r-1yt7n81 r-m611by r-1otgn73']//parent::div[@class='css-1dbjc4n']//div"));
		
		for(WebElement option: list) {
			if(option.getText().equals("USD")) {
				option.click();
				break;
			}
		}
	}

}
