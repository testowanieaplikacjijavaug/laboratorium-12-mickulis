package z2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import shared.DriverFactory;


public class EmailLoginPageTest
{
	WebDriver driver;
	EmailLoginPageFactory page;

	@BeforeEach
	public void setup()
	{
		driver = DriverFactory.get();
		page = PageFactory.initElements(driver, EmailLoginPageFactory.class);
	}

	@AfterEach
	public void teardown()
	{
		driver.quit();
	}

	@Test
	public void wpLogin_invalidData()
	{
		driver.navigate().to("https://profil.wp.pl/");
		page.login("invalid", "123456");
		Assertions.assertTrue(page.findError());
	}

	@Test
	public void onetLogin_invalidData()
	{
		driver.navigate().to("https://konto.onet.pl/login.html");
		page.login("invalid", "123445");
		Assertions.assertTrue(page.findError());
	}


}
