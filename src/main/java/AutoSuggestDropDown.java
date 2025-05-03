import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AutoSuggestDropDown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://www.globalsqa.com/demoSite/practice/autocomplete/combobox.html");
		
		
		String option  = "php";
		
		driver.findElement(By.xpath("//span[@class='custom-combobox']//input[1]")).sendKeys(option);
		Thread.sleep(3000);
		
		
		List<WebElement> list = driver.findElements(By.xpath("//ul[@id='ui-id-1']//li"));
		
		for(WebElement op: list) {
			if(op.getText().equalsIgnoreCase(option)) {
				op.click();
				break;
			}
		}
		
		driver.close();
		
		
	}

}
