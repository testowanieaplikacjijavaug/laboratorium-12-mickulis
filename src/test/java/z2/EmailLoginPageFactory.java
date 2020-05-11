package z2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailLoginPageFactory
{
	public EmailLoginPageFactory(WebDriver driver)
	{
		this.driver = driver;
	}

	private WebDriver driver;
	private WebElement login;
	private WebElement password;

	@FindAll({
		@FindBy(className = "error"),
		@FindBy(id = "login-error-message")
	})
	protected List<WebElement> error;

	public EmailLoginPageFactory login(String username, String pass)
	{
		login.sendKeys(username);
		password.sendKeys(pass);
		password.submit();
		return this;
	}

	public boolean findError()
	{
		return error.size() > 0;
	}
}
