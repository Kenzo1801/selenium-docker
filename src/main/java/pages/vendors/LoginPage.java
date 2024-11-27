package pages.vendors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.AbstractPage;

public class LoginPage extends AbstractPage {

	@FindBy(id = "username")
	private WebElement userNameInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "login")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void login(String username, String password) {
		this.userNameInput.sendKeys(username);
		this.passwordInput.sendKeys(password);
		this.loginButton.click();
	}
	
	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(loginButton));
		return this.loginButton.isDisplayed();
	}

}
