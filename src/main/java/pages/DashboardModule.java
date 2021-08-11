package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardModule {

	// Initialization: Page factory will initialize every web element with reference
	// to corresponding element on the actual web page based on locators

	public DashboardModule(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Declare elements on AdminModule page by @findBy annotation
	@FindBy(id = "menu_dashboard_index")
	private WebElement dashboardMenu;

	@FindBy(id = "Subscriber_link")
	private WebElement subscriber_link;
	
	@FindBy(id = "subscriber_name")
	private WebElement subscriber_name;

	@FindBy(id = "subscriber_email")
	private WebElement subscriber_email;

	@FindBy(id = "btnSubscribe")
	private WebElement subscribe_btn;

	@FindBy(xpath = "//*[@class=\"message success\"]")
	private WebElement success_message_label;


	// Utilization: Methods to perform action on the web page
	public void click_dashboardMenu_button() {
		dashboardMenu.click();
	}	

	public void click_subscriber_link() {
		subscriber_link.click();
	}
	public void click_subscriber_button() {
		subscribe_btn.click();
	}


	public void enter_username(String username) {
		subscriber_name.click();
		subscriber_name.sendKeys(username);
	}
	public void enter_email(String email) {
		subscriber_email.click();
		subscriber_email.clear();
		subscriber_email.sendKeys(email);
	}


	public void enter_username_email(String username, String email)
	{
			enter_username(username);

			enter_email(email);
	}

	public void verify_success_message()
	{
		Assert.assertTrue(success_message_label.getText().equalsIgnoreCase("Successfully Subscribed"));
	}
}
