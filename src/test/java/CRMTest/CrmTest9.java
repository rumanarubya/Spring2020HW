package CRMTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CrmTest9 {


	WebDriver driver;
	String browser = null;   //first it will be null later it will be chrome
	
	@BeforeTest
	public void readConfig() {
		
		Properties prop = new Properties();
		
		try {
			
		InputStream input = new FileInputStream(".\\src\\test\\java\\config\\config.properties");
		prop.load(input);
	    browser = prop.getProperty("browser");
	    System.out.println("We have used: "+browser);
		
		}
		catch(IOException e) {
		e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

	
		if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");

          driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			
			driver = new FirefoxDriver();
		}

	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("http://www.techfios.com/ibilling/?ng=admin/");
	}

@Test (priority = 2)
	public void login() throws InterruptedException {
	
	String logingTitle = driver.getTitle();
	Assert.assertEquals("Login - iBilling",logingTitle );
	System.out.println("Pass");
	
    //Element Library
	
	By USERNAME_FIELD_LOCATOR = By.id("username");
	By PASSOWRD_FIELD_LOCATOR = By.id("password");
	By SIGNIN_BUTTON_LOCATOR = By.name("login");
	By DASHBOARD_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'Dashboard')]");
	
	//Data
	
	String loginId = "demo@techfios.com";
	String passWord = "abc123";
	
	driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
	driver.findElement(PASSOWRD_FIELD_LOCATOR).sendKeys(passWord);
	driver.findElement(SIGNIN_BUTTON_LOCATOR).click();
	

	//  Assert.assertEquals("Dashboard", driver.findElement(DASHBOARD_BUTTON_LOCATOR) ,"Wrong Page!");  or to look more nice then follow down one
	
     String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON_LOCATOR).getText();
     Assert.assertEquals("Dashboard", dashboardValidationText  ,"Wrong Page!");  
     waitForElement(driver,5, DASHBOARD_BUTTON_LOCATOR);
			
	}

public void addDepositTest() {

}

 @Test (priority = 1)
 public void login1() throws InterruptedException {

 String logingTitle = driver.getTitle();
 Assert.assertEquals("Login - iBilling",logingTitle );
 System.out.println("Pass");

//Element Library

 By USERNAME_FIELD_LOCATOR = By.id("username");
 By PASSOWRD_FIELD_LOCATOR = By.id("password");
 By SIGNIN_BUTTON_LOCATOR = By.name("login");
 By DASHBOARD_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'Dashboard')]");
//Data

 String loginId = "demo@techfios.com";
 String passWord = "abc1234";   //failed intentionally

 driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
 driver.findElement(PASSOWRD_FIELD_LOCATOR).sendKeys(passWord);
 driver.findElement(SIGNIN_BUTTON_LOCATOR).click();


 //calling the wait
 waitForElement(driver,5, DASHBOARD_BUTTON_LOCATOR);

 }

 public void waitForElement(WebDriver driver, int timeInSecond, By locator) {
	
	WebDriverWait wait = new WebDriverWait(driver,2);
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	
}

 /*@AfterMethod

	public void teardown() {
		driver.close();
		driver.quit();
	}   */
	
}
