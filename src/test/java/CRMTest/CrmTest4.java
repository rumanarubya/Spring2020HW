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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CrmTest4 {

	WebDriver driver;
	String browser = null;   //first it will be null later it will be chrome
	
	@BeforeTest
	public void readConfig() {
		
		Properties prop = new Properties();
		
		try {
			
		InputStream input = new FileInputStream(".\\src\\test\\java\\config\\config.properties");
		prop.load(input);
	    browser = prop.getProperty("browser");
		
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

@Test
	public void login() throws InterruptedException {
	
	String logingTitle = driver.getTitle();
	Assert.assertEquals("Login - iBilling",logingTitle );
	System.out.println("Pass");
	
    //Element Library
	
	By USERNAME_FIELD_LOCATOR = By.id("username");
	By PASSOWRD_FIELD_LOCATOR = By.id("password");
	By SIGNIN_BUTTON_LOCATOR = By.name("login");
	
	//Data
	
	String loginId = "demo@techfios.com";
	String passWord = "abc123";
	
	driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
	driver.findElement(PASSOWRD_FIELD_LOCATOR).sendKeys(passWord);
	driver.findElement(SIGNIN_BUTTON_LOCATOR).click();
	Thread.sleep(2000);

	
	}


@AfterMethod

	public void teardown() {
		driver.close();
		driver.quit();
	}
	
}
