package pages.flightreservations;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.AbstractPage;

public class FlightSelectionPage extends AbstractPage {

	@FindBy(name = "departure-flight")
	private List<WebElement> departureFlightOptions;
	
	@FindBy(name = "arrival-flight")
	private List<WebElement> arrivalFlightOptions;
	
	@FindBy(xpath= "//button[@id='confirm-flights']")
	private WebElement confirmButton;
	
	public FlightSelectionPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public boolean isAt() {
		
		this.wait.until(ExpectedConditions.visibilityOf(confirmButton));
		return this.confirmButton.isDisplayed();
	}
	
	public void selectFlight() {
		
		int random = ThreadLocalRandom.current().nextInt(0, departureFlightOptions.size());
		this.departureFlightOptions.get(random).click();
		this.arrivalFlightOptions.get(random).click();
	}
	
	public void confirmFlight() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.confirmButton);
		Thread.sleep(500);
		this.confirmButton.click();
	}

}
