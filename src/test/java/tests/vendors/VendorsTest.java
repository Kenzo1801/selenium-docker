package tests.vendors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.vendors.DashboardPage;
import pages.vendors.LoginPage;
import tests.AbstractTest;
import tests.util.Config;
import tests.util.Constants;
import tests.util.JsonUtil;
import tests.vendors.model.VendorTestData;

public class VendorsTest extends AbstractTest {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private VendorTestData testData;
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setUp(String testDataPath) {
		this.loginPage = new LoginPage(driver);
		this.dashboardPage = new DashboardPage(driver);
		this.testData = JsonUtil.getTestData(testDataPath, VendorTestData.class);
	}
	
	@Test
	public void loginTest() {
		loginPage.goTo(Config.get(Constants.VENDOR_URL));
		Assert.assertTrue(loginPage.isAt());
		loginPage.login(this.testData.username(), this.testData.password());
	}
	
	@Test(dependsOnMethods="loginTest")
	public void dashboardTest() {
		
		Assert.assertTrue(dashboardPage.isAt());
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), this.testData.monthlyEarning());
		Assert.assertEquals(dashboardPage.getAnnualEarning(), this.testData.annualEarning());
		Assert.assertEquals(dashboardPage.getMargin(), this.testData.profitMargin());
		Assert.assertEquals(dashboardPage.getInventory(), this.testData.availableInventory());
		
	}
	
	@Test(dependsOnMethods="dashboardTest")
	public void searchTest() {
		
		dashboardPage.searchOrderedHistoryBy(this.testData.searchKeyword());
		Assert.assertEquals(dashboardPage.getResearchCount(), this.testData.searchResultsCount());
	}
	
	@Test(dependsOnMethods={"dashboardTest", "searchTest"})
	public void logoutTest() {
		
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}
	
}
