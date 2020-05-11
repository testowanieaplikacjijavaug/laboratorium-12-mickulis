package z4;

import org.openqa.selenium.By;

public class AddressBookSelectors
{
	final By delete = By.cssSelector(".container [data-method=\"delete\"]");
	final By email = By.cssSelector("#session_email");
	final By password = By.cssSelector("#session_password");
	final By goToAddresses = By.cssSelector("[data-test=\"addresses\"]");
	final By newAddress = By.cssSelector("[data-test=\"create\"]");
	final By confirm = By.cssSelector("[name=\"commit\"]");
	final By error = By.cssSelector("#error_explanation");
	final By addressRow = By.cssSelector("tbody tr");

	final By show = By.xpath("(//td/a)[1]");
	final By edit = By.xpath("(//td/a)[2]");

	final By firstNameTextField = By.cssSelector("#address_first_name");
	final By lastNameTextField = By.cssSelector("#address_last_name");
	final By address1TextField = By.cssSelector("#address_street_address");
	final By cityTextField = By.cssSelector("#address_city");
	final By zipTextField = By.cssSelector("#address_zip_code");

	final By firstNameGet = By.cssSelector("[data-test=\"first_name\"]");

}
