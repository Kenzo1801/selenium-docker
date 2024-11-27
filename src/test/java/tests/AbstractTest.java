package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.google.common.util.concurrent.Uninterruptibles;

import io.github.bonigarcia.wdm.WebDriverManager;
import tests.util.Config;
import tests.util.Constants;

@Listeners({listeners.TestListener.class})
public class AbstractTest {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
	protected WebDriver driver;
	
	@BeforeSuite
	public void setupConfig() {
		Config.initialized();
	}
	
	@BeforeTest
	public void setDriver(ITestContext ctx) throws MalformedURLException {

		this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))? getRemoteDriver() : getLocalDriver();
		ctx.setAttribute(Constants.DRIVER, this.driver);
		//if (Boolean.getBoolean("selenium.grid.enabled"))
//		{
//			this.driver = getRemoteDriver();
//		}
//		else {
//			this.driver = getLocalDriver();
//		}
	}
	
	private WebDriver getRemoteDriver() throws MalformedURLException {
		
		Capabilities capabilities = new ChromeOptions();
		if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			capabilities = new FirefoxOptions();
		}
		
		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		log.info("grid url: {}", url);
		return new RemoteWebDriver(new URL(url), capabilities);
	}
	
	private WebDriver getLocalDriver() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ulric\\Documents\\chromedriver\\chromedriver.exe"); 
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-features=Autofill,AutofillServerCommunication");
		options.addArguments("--disable-password-manager");
		options.addArguments("--disable-save-password");
		
        // Disable the "Save Password" and "Autofill" popups by modifying preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_enabled", false);  // Disable password manager
        prefs.put("autofill.addresses_enabled", false);         // Disable Autofill for addresses
        prefs.put("autofill.credit_card_enabled", false);       // Optionally disable Autofill for credit cards
        prefs.put("autofill.profile_enabled", false);           // Disable autofill profile
        options.setExperimentalOption("prefs", prefs);
        
		return new ChromeDriver(options);
	}
	
	@AfterTest
	public void quitDriver() {
		
		driver.quit();
	}
//	
//	@AfterMethod
//	public void sleep() {
//		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//	}
}
