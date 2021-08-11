package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;


public class Login {

	// Initialization: Page factory will initialize every web element with reference
	// to corresponding element on the actual web page based on locators
	public Login(WebDriver driver) {
		/**
		   * Constructor
		 */	
		PageFactory.initElements(driver, this);
	}

	// Declare elements on Login page by @findBy annotation
	@FindBy(id = "btnLogin")
	private WebElement login_lnkbtn;

	@FindBy(id = "txtUsername")
	private WebElement username_txt;

	@FindBy(id = "txtPassword")
	private WebElement password_txt;

	@FindBy(linkText = "Forgot your password?")
	private WebElement forgotPasswordlink;

	@FindBy(linkText = "Logout")
	private WebElement logoutlink;


	@FindBy(id = "welcome")
	private WebElement welcome_dropdown;


	// Utilization: Methods to perform action on the web page
	public void click_login_link() {

		login_lnkbtn.click();
	}	

	/**
	   * Enter text in username textbox.
	   * @param: uname Username
	   * Parameters:uname, must not be null
	 */	
	public void enter_username(String uname) {

		username_txt.sendKeys(uname);
	}
	
	/**
	   * Enter text in password textbox.
	   * @param: pwd Password
	   * Parameters:pwd, must not be null
	 */	
	public void enter_password(String pwd) {

		password_txt.sendKeys(pwd);
	}

	/*
	Perform logout
	 */
	public void click_on_logout() {

		welcome_dropdown.click();
		logoutlink.click();
	}
	
	//Login assertion
	public void validate_login() {
		if(!login_lnkbtn.isDisplayed()) {
			System.out.println("Login successful");
		}else {
			System.out.println("Login failed");
		}
	}
}
