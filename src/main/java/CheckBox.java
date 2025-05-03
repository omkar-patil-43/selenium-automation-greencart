import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CheckBox {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		WebElement ele = driver.findElement(By.id("checkBoxOption1"));
		
		ele.click();
		
		if(ele.isSelected()==true) {
			System.out.println("First checkbox is selected");
		}
	
		Assert.assertTrue(ele.isSelected());
		
		int totalcheck = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
		
		System.out.println("Total checkboxes on webpage: "+totalcheck);
		
		Assert.assertEquals(totalcheck, 3);
				
		driver.close();
	}

}
