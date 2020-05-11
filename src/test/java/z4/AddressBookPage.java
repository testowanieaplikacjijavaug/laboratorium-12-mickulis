package z4;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class AddressBookPage
{
	public AddressBookPage(WebDriver driver)
	{
		this.driver = driver;
	}

	private AddressBookSelectors selectors = new AddressBookSelectors();
	protected WebDriver driver;


	public AddressBookPage deleteAllRecords()
	{
		while(driver.findElements(selectors.delete).size() > 0)
		{
			deleteFirstAddress();
			driver.navigate().refresh();
		}
		return this;
	}

	public AddressBookPage deleteFirstAddress()
	{
		WebElement row = driver.findElement(selectors.addressRow);
		findAndClick(selectors.delete);
		driver.switchTo().alert().accept();
		new WebDriverWait(driver, 10).until(ExpectedConditions.stalenessOf(row));
		goToAddresses();
		return this;
	}

	public AddressBookPage login(String login, String password)
	{
		driver.findElement(selectors.email).sendKeys(login);
		driver.findElement(selectors.password).sendKeys(password);
		driver.findElement(selectors.password).submit();
		return this;
	}

	public AddressBookPage goToAddresses()
	{
		findAndClick(selectors.goToAddresses);
		return this;
	}

	public AddressBookPage newAddress()
	{
		goToAddresses();
		findAndClick(selectors.newAddress);
		return this;
	}

	public AddressBookPage fillRequiredFields(String firstName, String lastName, String address1, String city, String zip)
	{
		driver.findElement(selectors.firstNameTextField).sendKeys(firstName);
		driver.findElement(selectors.lastNameTextField).sendKeys(lastName);
		driver.findElement(selectors.address1TextField).sendKeys(address1);
		driver.findElement(selectors.cityTextField).sendKeys(city);
		driver.findElement(selectors.zipTextField).sendKeys(zip);
		return this;
	}

	public AddressBookPage confirm()
	{
		driver.findElement(selectors.confirm).click();
		return this;
	}

	public boolean isErrorDisplayed()
	{
		try
		{
			driver.findElement(selectors.error);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public int getNumberOfAddresses()
	{
		try
		{
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(selectors.addressRow));
		} catch (Exception e){}
		return driver.findElements(selectors.addressRow).size();
	}

	public AddressBookPage editFirst()
	{
		goToAddresses();
		findAndClick(selectors.edit);
		return this;
	}

	public AddressBookPage fillFirstName(String firstName)
	{
		driver.findElement(selectors.firstNameTextField).clear();
		driver.findElement(selectors.firstNameTextField).sendKeys(firstName);
		return this;
	}

	public AddressBookPage showFirst()
	{
		goToAddresses();
		findAndClick(selectors.edit);
		return this;
	}

	public String getFirstName()
	{
		return driver.findElement(selectors.firstNameGet).getText();
	}

	private void findAndClick(By by)
	{
		findAndClick(by, 5);
	}

	private void findAndClick(By by, int repeats)
	{
		try
		{
			driver.findElement(by).click();
		}
		catch(Exception e)
		{
			if(repeats < 1)
				throw e;
			findAndClick(by, repeats - 1);
		}
	}
}
