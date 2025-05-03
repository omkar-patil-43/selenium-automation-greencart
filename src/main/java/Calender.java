import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Calender {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		String date = "8";
		
		/*driver.findElement(By.id("second_date_picker")).click();
		
		driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td[not(contains(@class,'ui-datepicker-other-month') )]//a[text()='"+ date +"']")).click();
		
		*/
		//Selecting date in a particular month and year
		
		driver.findElement(By.id("second_date_picker")).click();
		
		String MonthYear = "April 2018";
		
		String[] list1= MonthYear.split(" ");
				
		String ExpectedMonth = list1[0];
		
		int ExpectedYear = Integer.parseInt(list1[1]);
		
		String CalMonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		
		String[] list2= CalMonthYear.split(" ");
		
		String ActualMonth = list2[0];
		
		int ActualYear = Integer.parseInt(list2[1]);
		
		//If the date-month-year provided are in past 
		if(ExpectedYear < ActualYear)
		{
			while(true) {
			CalMonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			list2 = CalMonthYear.split(" ");
			ActualMonth = list2[0];
			ActualYear = Integer.parseInt(list2[1]);
			
				if(ExpectedYear == ActualYear && ExpectedMonth.equals(ActualMonth)) {
					driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td[not(contains(@class,'ui-datepicker-other-month') )]//a[text()='"+ date +"']")).click();
					break;
				}
				else {
					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				}
			}
		}
		
		//Else If the date-month-year provided are in future 
		
		else if(ExpectedYear > ActualYear) {
			while(true) {
				CalMonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				list2 = CalMonthYear.split(" ");
				ActualMonth = list2[0];
				ActualYear = Integer.parseInt(list2[1]);
				
					if(ExpectedYear == ActualYear && ExpectedMonth.equals(ActualMonth)) {
						driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td[not(contains(@class,'ui-datepicker-other-month') )]//a[text()='"+ date +"']")).click();
						break;
					}
					else {
						driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
					}
				}
		}
		//Else If the date provided are in present month 
		else if(ExpectedYear == ActualYear && ExpectedMonth.equals(ActualMonth)) {
		
			driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td[not(contains(@class,'ui-datepicker-other-month') )]//a[text()='"+ date +"']")).click();	
				
		}
		
		Thread.sleep(5000);
		
		driver.quit();
		
	}

}
