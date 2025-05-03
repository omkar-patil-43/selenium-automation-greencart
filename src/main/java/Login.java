import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.xpath("//form//input[@id='inputUsername']")).sendKeys("TEST");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//form//input[@type='password']")).sendKeys("test");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='checkbox-container']//span[1]//input[@id='chkboxOne']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='checkbox-container']//span[2]//input[@id='chkboxTwo']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//form[@class='form']//button[@class='submit signInBtn' and @type='submit']")).click();
		
		Thread.sleep(2000);
		String errormsg = driver.findElement(By.xpath("//form[@class='form']//p[@class='error']")).getText();
		if(errormsg.equals("* Incorrect username or password")) {
			driver.findElement(By.xpath("//div[@class='forgot-pwd-container']//a")).click();
		}
		
		//reset password process
		
		driver.findElement(By.xpath("//form//input[@placeholder='Name']")).sendKeys("Test");
		driver.findElement(By.xpath("//form//input[@placeholder='Email']")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//form//input[contains(@placeholder,'Number')]")).sendKeys("1234567890");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//form//div[@class='forgot-pwd-btn-conainer']//button[2]")).click();
		
		String str = driver.findElement(By.xpath("//form//p[@class='infoMsg']")).getText();
		
		//extracting password from message
		String pass = "";
	        for (int i = 0; i < str.length(); i++) {
	            if (str.charAt(i) == '\'') {
	                int j = i + 1;
	                while (str.charAt(j) != '\'') {
	                    pass += str.charAt(j);
	                    j++;
	                }
	                break;
	            }
	        }
	    Thread.sleep(2000);
	    
	    //redirecting back to login page with password
	    driver.findElement(By.xpath("//form//div[@class='forgot-pwd-btn-conainer']//button[1]")).click();     
		
	    driver.findElement(By.xpath("//form//input[@id='inputUsername']")).sendKeys("TEST");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//form//input[@type='password']")).sendKeys(pass);
		Thread.sleep(2000);
		
	
		driver.findElement(By.xpath("//form[@class='form']//button[@class='submit signInBtn' and @type='submit']")).click();
		
		System.out.println("Test Successful");
		Thread.sleep(5000);
		
		driver.quit();

	}

}
