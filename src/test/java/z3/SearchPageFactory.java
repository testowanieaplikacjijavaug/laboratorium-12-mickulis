package z3;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPageFactory
{
	public SearchPageFactory(WebDriver driver)
	{
		this.driver = driver;
	}

	protected WebDriver driver;
	protected WebElement q;
	protected WebElement search_button_homepage;
	@FindAll({
		@FindBy(className = "result__a"),
		@FindBy(className = "g")
	})
	protected List<WebElement> resultList;

	public SearchPageFactory searchFor(String search)
	{
		q.sendKeys(search);
		q.submit();
		return this;
	}

	public int getNumberOfResults()
	{
		return resultList.size();
	}

	public SearchPageFactory clickOnNthResult(int n)
	{
		if(n < 1)
			throw new InvalidArgumentException("");

		if(resultList.size() < n)
			throw new NotFoundException();

		resultList.get(n-1).click();
		return this;
	}

	public SearchPageFactory navigateBack()
	{
		driver.navigate().back();
		return this;
	}
}
