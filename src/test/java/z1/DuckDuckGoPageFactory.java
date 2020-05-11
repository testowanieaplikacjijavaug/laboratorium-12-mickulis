package z1;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DuckDuckGoPageFactory
{
	public DuckDuckGoPageFactory(WebDriver driver)
	{
		this.driver = driver;
	}

	protected WebDriver driver;
	protected WebElement search_form_input_homepage;
	protected WebElement search_button_homepage;
	@FindAll({
		@FindBy(className = "result__a")
	})
	protected List<WebElement> resultList;

	public DuckDuckGoPageFactory searchFor(String search)
	{
		driver.navigate().to("https://duckduckgo.com");
		search_form_input_homepage.sendKeys(search);
		search_button_homepage.click();
		return this;
	}

	public int getNumberOfResults()
	{
		return resultList.size();
	}

	public DuckDuckGoPageFactory clickOnNthResult(int n)
	{
		if(n < 1)
			throw new InvalidArgumentException("");

		if(resultList.size() < n)
			throw new NotFoundException();

		resultList.get(n-1).click();
		return this;
	}

	public DuckDuckGoPageFactory navigateBack()
	{
		driver.navigate().back();
		return this;
	}
}
