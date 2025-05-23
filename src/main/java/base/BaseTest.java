package base;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.FileUtil;
import utils.PDFReportGenerator;

public class BaseTest {
    private WebDriver driver;

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");
    }

    public void addwait() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    
    
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public WebDriver getDriver() {
    	return driver;
    }
    
    // Reusable method to generate PDF report with timestamp and delete screenshots
    
    public void generateReportAndCleanup(Class<?> testClass) {
        try {
            String className = testClass.getSimpleName();
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss_a").format(new Date(0));
            String reportPath = "reports/pdf/" + className + "_" + timestamp + ".pdf";

            PDFReportGenerator.generatePDFReport(reportPath);
            FileUtil.deleteAllScreenshots("reports/screenshots/");

            System.out.println("Report generated at: " + reportPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
