package ElemntLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class storeElement {

	WebDriver driver;

	@BeforeTest
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

	@Test
	public void loginSiteDashboard() throws InterruptedException {

		// Go to site
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");

		// Enter username
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("demo@techfios.com ");
		// slow down java for 3 secs
		Thread.sleep(2000);

		// Enter password
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("abc123");
		Thread.sleep(2000);

		// Click login button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);

		// Store Element
		WebElement DASHBOARD_PAGETILE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(),' Dashboard ')]"));
	//  var datatype    variable                           variable value
		
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOf(DASHBOARD_PAGETILE_ELEMENT));

	
		
		
		// anothr way of Store Elelment by 'BY' class
		/*
		 * By DASHBOARD_PAGETILE_Locator =
		 * By.xpath("//h2[contains(text(),' Dashboard ')]"); WebDriverWait wait = new
		 * WebDriverWait(driver,3);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(
		 * DASHBOARD_PAGETILE_Locator));
		 */

		// Another Expected condition
		// wait.until(ExpectedConditions.elementToBeClickable(DASHBOARD_PAGETILE_ELEMENT));

	}
}

	

