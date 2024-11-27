package pages.flightreservations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.AbstractPage;
public class RegistrationConfirmationPage extends AbstractPage {

	@FindBy(linkText = "Go To Flights Search")
	private WebElement goToFlightsSearchButton;
	
	@FindBy(css = "#registration-confirmation-section p b")
	private WebElement firstNameElement;
	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);		
	}
	
	public void goToFlightsSearch() {
		if (this.isAt()) {
			this.goToFlightsSearchButton.click();
		}
	}
	
	public String getFirstName() {
		return this.firstNameElement.getText();
	}
	
	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(goToFlightsSearchButton));
		return this.goToFlightsSearchButton.isDisplayed();
	}
}
