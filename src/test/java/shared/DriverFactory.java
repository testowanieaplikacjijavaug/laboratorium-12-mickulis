package shared;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory
{
	public static WebDriver get()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("log-level=3");

		return new ChromeDriver(options);
	}
}
