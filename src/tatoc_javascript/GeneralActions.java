package tatoc_javascript;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GeneralActions {
	private WebDriver driver;
	
	public WebDriver getDriver() {
		String browserName = Utility.getConfigValue("browser");
		if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("ie")){
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
	
	public void getURL(WebDriver driver){
		driver.get(Utility.getConfigValue("url"));
	}
}
