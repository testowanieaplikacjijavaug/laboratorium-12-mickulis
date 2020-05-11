package z4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import shared.DriverFactory;

import static java.lang.Thread.sleep;

public class AddressBookTests
{
	WebDriver driver;
	AddressBookPage page;

	String login = "testing@somewhere.io";
	String password = "123456";
	String url = "http://a.testaddressbook.com/sign_in";

	@BeforeEach
	public void setup()
	{
		driver = DriverFactory.get();
		page = new AddressBookPage(driver);
		driver.navigate().to(url);
		page.login(login, password)
			.goToAddresses()
			.deleteAllRecords();	// clearing all records that could've persisted through any previous test
	}

	@AfterEach
	public void teardown()
	{
		page
			.goToAddresses()
			.deleteAllRecords();
		driver.quit();
	}

	@Test
	public void createNewAddress()
	{
		page.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm().goToAddresses();

		Assertions.assertEquals(1, page.getNumberOfAddresses());
	}

	@Test
	public void createMultipleAddresses()
	{
		page.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm().goToAddresses()
			.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm().goToAddresses()
			.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm().goToAddresses();

		Assertions.assertEquals(3, page.getNumberOfAddresses());
	}

	@Test
	public void createNewAddress_missingRequiredField()
	{
		page.newAddress()
			.fillRequiredFields("", "last", "address", "city", "10000")
			.confirm();

		Assertions.assertTrue(page.isErrorDisplayed());
	}

	@Test
	public void deleteAddress()
	{
		page.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm().goToAddresses()
			.deleteFirstAddress();

		Assertions.assertEquals(0, page.getNumberOfAddresses());
	}

	@Test
	public void editFirstName() throws InterruptedException
	{
		page.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm().goToAddresses()
			.editFirst()
			.fillFirstName("first 2")
			.confirm();
		Assertions.assertEquals("first 2", page.getFirstName());
	}

	@Test
	public void editFirstName_blank()
	{
		page.newAddress()
			.fillRequiredFields("first", "last", "address", "city", "10000")
			.confirm()
			.editFirst()
			.fillFirstName("")
			.confirm();

		Assertions.assertTrue(page.isErrorDisplayed());
	}


}
