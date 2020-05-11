package z3;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@ExtendWith(SeleniumExtension.class)
public class GooglePageObjectTests
{
	WebDriver driver;
	SearchPageObject page;

	public GooglePageObjectTests(ChromeDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeEach
	public void setup()
	{
		page = new SearchPageObject(driver);
		driver.navigate().to("https://google.com");
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
