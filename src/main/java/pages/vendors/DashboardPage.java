package pages.vendors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.AbstractPage;

public class DashboardPage extends AbstractPage{
	
	private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

	@FindBy(css = ".h3.mb-0.text-gray-800")
	private WebElement dashboardElement;
	
	@FindBy(css = "input[type='search']")
	private WebElement searchField;
	
	@FindBy(id = "monthly-earning")
	private WebElement monthlyEarnings;
	
	@FindBy(id = "annual-earning")
	private WebElement annualEarnings;
	
	@FindBy(id = "profit-margin")
	private WebElement profitMargin;
	
	@FindBy(id = "available-inventory")
	private WebElement availableInventory;
	
	@FindBy(id = "dataTable_info")
	private WebElement filterElement;
	
	@FindBy(css = "img.img-profile")
	private WebElement userProfileElement;
	
	@FindBy(css = "a[data-target='#logoutModal']")
	private WebElement logoutElement;
	
	@FindBy(linkText = "Logout")
	private WebElement modalLogoutButton;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getMonthlyEarning() {
		
		return this.monthlyEarnings.getText();		
	}
	
	public String getAnnualEarning() {
		
		return this.annualEarnings.getText();
	}
	
	public String getMargin() {
		
		return this.profitMargin.getText();	
	}
	
	public int getInventory() {
		
		return Integer.parseInt(this.availableInventory.getText());
	}
	
	public void searchOrderedHistoryBy(String searchText) {
		this.searchField.sendKeys(searchText);
	}
	
	public int getResearchCount() {
		String filterText = this.filterElement.getText();
		int count = Integer.parseInt(filterText.split("of")[1].split("entries")[0].trim());
		log.info("Result count is {}", count);
		return count;		
	}
	
	public void logout() {
		this.userProfileElement.click();
		this.wait.until(ExpectedConditions.visibilityOf(logoutElement));
		this.logoutElement.click();
		this.modalLogoutButton.click();
		
	}
	
	

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(dashboardElement));
		return this.dashboardElement.isDisplayed();
	}
	
	

}
