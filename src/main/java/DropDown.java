import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://practice.expandtesting.com/dropdown");
		
		//implementing static dropdown using Select Class
		
		WebElement drop = driver.findElement(By.xpath("//select[@id='country']"));
		
		Select dropdown = new Select(drop);
		
		dropdown.selectByIndex(2);
		dropdown.selectByContainsVisibleText("American Samoa");
		dropdown.getFirstSelectedOption();
		
		
		
		//implementing static dropdown without Select Class
		
		List<WebElement> list = driver.findElements(By.xpath("//select[@id='country']// option"));
		
		System.out.println("Number of elements under country dropdown: "+list.size());
		
		System.out.println("Dropdown Elements are as below");
		
		for(WebElement option: list) {
			System.out.println(option.getText());
		}
		
		//Want to select country = India case-insensitive
		
		for(WebElement option: list) {
			if(option.getText().equalsIgnoreCase("india")) {
				option.click();
				System.out.println("Selected option is: "+option.getText());
				break;
			}
		}
		
		Thread.sleep(5000);
		
		driver.close();
	}
}
