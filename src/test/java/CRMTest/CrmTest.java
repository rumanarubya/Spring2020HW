package CRMTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CrmTest {

	WebDriver driver;

	
	public void loginBrowser() {

		// I have Set Chrome Driver as my default Driver
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");

		// Instantiate chromeDriver object
		driver = new ChromeDriver();

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// delete cookies and history
		driver.manage().deleteAllCookies();

		// maximize the browser
		driver.manage().window().maximize();

		// Go to site
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
	}


	public void init() throws InterruptedException {

	String logingTitle = driver.getTitle();
	Assert.assertEquals("Login - iBilling",logingTitle );
	System.out.println("Pass");
	
	
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("demo@techfios.com ");
	    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);

		// Store Element
		WebElement DASHBOARD_PAGETILE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(),' Dashboard ')]"));
                         
		
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOf(DASHBOARD_PAGETILE_ELEMENT));

	
	}
		
		
}

	
	

