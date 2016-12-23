package junit.framework.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import junit.framework.testdata.TestData;

public class TestCase {
	
public static WebDriver setup(WebDriver driver, String testCase) throws Exception {
		
	if("firefox".equalsIgnoreCase(TestData.getstringcellvalue("login", testCase, "Browser"))) {
		driver = new FirefoxDriver();
	}
	else if("chrome".equalsIgnoreCase(TestData.getstringcellvalue("login", testCase, "Browser")))	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if("IE".equalsIgnoreCase(TestData.getstringcellvalue("login", testCase, "Browser")))	{
		System.setProperty("webdriver.ie.driver","drivers/IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		driver = new InternetExplorerDriver(caps);
	}
	return driver;
}
public static void getURL(WebDriver driver,String testCase) throws Exception {
	driver.get(TestData.getstringcellvalue("login", testCase, "URL"));
	System.out.println("Your are in "+ driver.getTitle() + " Page");
}

public static void login(WebDriver driver,String testCase) throws Exception {
driver.findElement(By.id(TestData.getstringcellvalue("login", testCase, "UserNameIdentifier"))).sendKeys(TestData.getstringcellvalue("login", testCase, "UserName"));
driver.findElement(By.id(TestData.getstringcellvalue("login", testCase, "PasswordIdentifier"))).sendKeys(TestData.getstringcellvalue("login", testCase, "Password"));
driver.findElement(By.id(TestData.getstringcellvalue("login", testCase, "LoginButtonIdentifier"))).click();
}

public static void teardown(WebDriver driver) {
	driver.quit();
	}
}
