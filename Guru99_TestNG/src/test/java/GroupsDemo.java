import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;
//userid : mngr263431; password : jyqAvys
public class GroupsDemo {
	WebDriver driver;
	final String browserProperty = "webdriver.gecko.driver";
	final String browserDriverPath = System.getProperty("user.dir")+"/src/test/resources/geckodriver.exe";
	final String URLPath = "http://www.demo.guru99.com/V4/";
	final String bankHeader = "//h2";
	final String userNamePath = "input[name='uid']";
	final String userName = "mngr263431";
	final String password = "jyqAvys";
	final String passwordPath = "input[name='password']";
	final String submitBtnPath = "input[name='submit']";
	final String managerIDText = "//td[contains(text(),'Manger Id')]";
	
	@BeforeSuite
	public void initiate() {
		System.setProperty(browserProperty,browserDriverPath );
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
	}
	@Test(groups = {"mandatory","useful"} )
	public void tc01LaunchURL()
	{
		driver.navigate().to(URLPath);
	}
	@Test
	public void tc02VerifyLaunchPage()
	{
		Assert.assertEquals(driver.findElement(By.xpath(bankHeader)).getText(), "Guru99 Bank");
	}
	@Test
	public void tc03EnterCredentials()
	{
		driver.findElement(By.cssSelector(userNamePath)).sendKeys(userName);
		driver.findElement(By.cssSelector(passwordPath)).sendKeys(password);
		driver.findElement(By.cssSelector(submitBtnPath)).click();
	}
	@Test
	public void tc04VerifyLoggedInPage()
	{
		Assert.assertTrue(driver.findElement(By.xpath(managerIDText)).isDisplayed(), "Manager ID is not displayed");
		System.out.println("Manager ID is displayed");
	}
	@Test
	public void tc05VerifyHyperlinks()
	{
		SoftAssert s  = new SoftAssert();
		List<WebElement> anchorTags = driver.findElements(By.xpath("//ul[@class='menusubnav']//a"));
		for(WebElement a:anchorTags)
			s.assertFalse(a.isDisplayed(),a.getAttribute("href").toString());
		s.assertAll();
	}
}
