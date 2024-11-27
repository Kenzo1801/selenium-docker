package tests.flightreservations;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.flightreservations.FlightConfirmationPage;
import pages.flightreservations.FlightSearchPage;
import pages.flightreservations.FlightSelectionPage;
import pages.flightreservations.RegistrationConfirmationPage;
import pages.flightreservations.RegistrationPage;
import tests.AbstractTest;
import tests.flightreservations.model.FlightReservationData;
import tests.util.Config;
import tests.util.Constants;
import tests.util.JsonUtil;

public class FlightReservationTest extends AbstractTest {

	private FlightReservationData testData;
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setParameter(String testDataPath) {

		this.testData = JsonUtil.getTestData(testDataPath, FlightReservationData.class);
	}
	
	@Test
	public void userRegistrationTest() {
		
		RegistrationPage registrationPage = new pages.flightreservations.RegistrationPage(driver);
		registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registrationPage.isAt());
		
		registrationPage.enterUserDetails(this.testData.firstName(), this.testData.lastName());
		registrationPage.enterUserCredentials(this.testData.email(), this.testData.password());
		registrationPage.enterUserAddress(this.testData.street(), this.testData.city(), this.testData.zip());
		registrationPage.register();
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationPageTest() {
		RegistrationConfirmationPage registerConfirmation = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registerConfirmation.isAt());
		Assert.assertEquals(registerConfirmation.getFirstName(), this.testData.firstName());
		registerConfirmation.goToFlightsSearch();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationPageTest")
	public void flightsSearchTest() {
		FlightSearchPage flightsSearchPage = new FlightSearchPage(driver);
		Assert.assertTrue(flightsSearchPage.isAt());
		flightsSearchPage.selectPassengers(this.testData.passengersCount());
		flightsSearchPage.searchForflights();
	}
	
	@Test(dependsOnMethods = "flightsSearchTest")
	public void flightSelectionTest() throws InterruptedException {
		FlightSelectionPage flightSelection = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelection.isAt());
		flightSelection.selectFlight();
		flightSelection.confirmFlight();
	}
	
	@Test(dependsOnMethods = "flightSelectionTest")
	public void reservationConfirmationTest() {
		FlightConfirmationPage flightConfirmation = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmation.isAt());
		Assert.assertEquals(flightConfirmation.getPrice(), this.testData.expectedPrice());
	}
	
	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}
