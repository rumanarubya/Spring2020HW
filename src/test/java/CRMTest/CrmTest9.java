package CRMTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CrmTest9 {

	WebDriver driver;
	String browser = null; // first it will be null later it will be chrome

	@BeforeTest
	public void readConfig() {

		Properties prop = new Properties();

		try {

			InputStream input = new FileInputStream(".\\src\\test\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("We have used: " + browser);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");

			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");

			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
	}

	//@Test(priority = 1)
	public void login() throws InterruptedException {

		String logingTitle = driver.getTitle();
		Assert.assertEquals("Login - iBilling", logingTitle);
		System.out.println("Pass");

		// Element Library

		By USERNAME_FIELD_LOCATOR = By.id("username");
		By PASSOWRD_FIELD_LOCATOR = By.id("password");
		By SIGNIN_BUTTON_LOCATOR = By.name("login");
		By DASHBOARD_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'Dashboard')]");

		// Data

		String loginId = "demo@techfios.com";
		String passWord = "abc123";

		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
		driver.findElement(PASSOWRD_FIELD_LOCATOR).sendKeys(passWord);
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();

		// Assert.assertEquals("Dashboard", driver.findElement(DASHBOARD_BUTTON_LOCATOR)
		// ,"Wrong Page!"); or to look more nice then follow down one

		waitForElement(driver, 5, DASHBOARD_BUTTON_LOCATOR);

		String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON_LOCATOR).getText();
		Assert.assertEquals("Dashboard", dashboardValidationText, "Wrong Page!");

	}

	@Test(priority = 2)
	public void AddCustomerTest() {

		// Element library

		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
		By CUSTOMERS_BUTTON = By.xpath("//span[contains(text(), 'Customers')]");
		By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(), 'Add Customer')]");
		By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text(),'Add Contact')]");
		By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
		By COMPANY_NAME_FIELD = By.xpath("//Select[@id='cid']");
		By EMAIL_FIELD = By.xpath("//input[@id='email']");
		By PHONE_FIELD = By.xpath("//input[@id='phone']");
		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_REGION_FIELD = By.xpath("//input[@id='state']");
		By ZIP_FIELD = By.xpath("//input[@id='zip']");
		By COUNTRY_DROP_DOWN_FIELD = By.xpath("//Select[@id='country']");
		By SAVE_BUTTON = By.xpath("//button[@id='submit']");
		By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(text(),'List Contacts')]");

		// Login Data

		String loginID = "demo@techfios.com";
		String password = "abc123";

		// Test Data

		String fullname = "Rumana Rubya";

		String companyName = "Techfios";
		String emailAddress = "rush@gmail.com";
		String phoneNumber = "46346364";
		String addressField = "710 Hardwood";
		String cityField = "Mckiney";
		String zipFiled = "75069";
		String countryField = "Bangladesh";
		// String

		// perform Login IN

		driver.findElement(USER_NAME_FIELD).sendKeys(loginID);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();

		// Validation Dashboard page
		waitForElement(driver, 3, DASHBOARD_BUTTON);
		String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON).getText();
		Assert.assertEquals("Dashboard", dashboardValidationText, "Wrong Page!!!");

		driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADD_CUSTOMER_BUTTON).click();
		waitForElement(driver, 3, ADD_CUSTOMER_BUTTON);

		// Generate Random number

		Random rnd = new Random();
		int randomNum = rnd.nextInt(999);

		// Fill out add customer form

		driver.findElement(FULL_NAME_FIELD).sendKeys(fullname + randomNum);
		
		waitForElement(driver, 3, COMPANY_NAME_FIELD);
        Select compName = new Select(driver.findElement(COMPANY_NAME_FIELD));
		compName.selectByVisibleText(companyName);

		driver.findElement(EMAIL_FIELD).sendKeys(phoneNumber + emailAddress);
		driver.findElement(PHONE_FIELD).sendKeys(phoneNumber + randomNum);
		driver.findElement(ADDRESS_FIELD).sendKeys(addressField);
		driver.findElement(CITY_FIELD).sendKeys(cityField);
		driver.findElement(ZIP_FIELD).sendKeys(zipFiled);
		
		waitForElement(driver, 5, COUNTRY_DROP_DOWN_FIELD);
		Select countryName = new Select(driver.findElement(COUNTRY_DROP_DOWN_FIELD));
		countryName.selectByVisibleText(countryField);
		
		waitForElement(driver, 3, SAVE_BUTTON);
		driver.findElement(SAVE_BUTTON).click();

	}

	public void waitForElement(WebDriver driver, int timeInSecond, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

@AfterTest

	public void teardown() {
		driver.close();
		driver.quit();
	}

}
