package z3;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchPageObject
{
	public SearchPageObject(WebDriver driver)
	{
		this.driver = driver;
	}

	protected WebDriver driver;

	private By searchTextField_Name = By.name("q");
	private By searchButton = By.id("search_button_homepage");
	private By searchResult = By.cssSelector(".result__a, .g");

	public SearchPageObject searchFor(String search)
	{
		WebElement textField = driver.findElement(searchTextField_Name);
		textField.sendKeys(search);
		textField.submit();
		return this;
	}

	public int getNumberOfResults()
	{
		return driver.findElements(searchResult).size();
	}

	public SearchPageObject clickOnNthResult(int n)
	{
		if(n < 1)
			throw new InvalidArgumentException("");

		List<WebElement> list = driver.findElements(searchResult);
		if(list.size() < n)
			throw new NotFoundException();

		list.get(n-1).click();
		return this;
	}

	public SearchPageObject navigateBack()
	{
		driver.navigate().back();
		return this;
	}
}
