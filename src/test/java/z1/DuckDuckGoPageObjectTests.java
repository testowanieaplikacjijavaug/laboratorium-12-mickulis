package z1;

import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import shared.DriverFactory;

import java.util.concurrent.TimeUnit;


public class DuckDuckGoPageObjectTests
{
	WebDriver driver;
	DuckDuckGoPageObject page;



	@BeforeEach
	public void setup()
	{
		driver = DriverFactory.get();
		page = new DuckDuckGoPageObject(driver);
		driver.navigate().to("https://duckduckgo.com");
	}

	@AfterEach
	public void teardown()
	{
		driver.quit();
	}

	@Test
	public void searchForSomethingAndOpenFirstResult()
	{
		page.searchFor("duck").clickOnNthResult(1);
	}

	@Test
	public void searchForSomethingAndOpenThirdResult()
	{
		page.searchFor("duck").clickOnNthResult(3);
	}

	@Test
	public void searchForSomethingAndOpenNonExistingResult()
	{
		int numberOfResults = page.searchFor("duck").getNumberOfResults();
		Assertions.assertThrows(NotFoundException.class, () -> page.clickOnNthResult(numberOfResults+1));
	}

	@Test
	public void findSomethingThatDoesntExist()
	{
		Assertions.assertThrows(NotFoundException.class, () -> driver.findElement(By.linkText("asdfghjhgfdsasdfgh")));
	}
}
