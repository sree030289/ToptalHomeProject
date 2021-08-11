package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AdminModule {
	
	// Initialization: Page factory will initialize every web element with reference
	// to corresponding element on the actual web page based on locators
	
	public AdminModule(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Declare elements on AdminModule page by @findBy annotation
	@FindBy(id = "menu_admin_viewAdminModule")
	private WebElement adminMenu;

	@FindBy(id = "searchSystemUser_userName")
	private WebElement userName_txt;
	
	@FindBy(id = "searchBtn")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[@id=\"resultTable\"]/tbody/tr/td[2]/a")
	private WebElement resultLink;

	@FindBy(id = "menu_admin_Configuration")
	private WebElement adminConfigurationMenu;

	@FindBy(id = "menu_admin_viewEmailNotification")
	private WebElement menu_admin_viewEmailNotification;

	@FindBy(id = "btnEdit")
	private WebElement edit_btn;

	@FindBy(id = "ohrmList_chkSelectRecord_1")
	private WebElement checkbox1;

	@FindBy(id = "ohrmList_chkSelectRecord_2")
	private WebElement checkbox2;

	@FindBy(id = "btnSave")
	private WebElement save_btn;




	// Utilization: Methods to perform action on the web page
	public void click_adminMenu_button() {
		adminMenu.click();
	}	

	public void click_search_button() {
		searchBtn.click();
	}

	public void click_edit_button() {
		edit_btn.click();
	}

	public void click_save_button() {
		save_btn.click();
	}


	public void select_checkbox1_button() {
		checkbox1.click();
	}

	public void select_checkbox2_button() {
		checkbox2.click();
	}




	public void hover_configuration_menu(WebDriver driver)
	{
		//Creating object of an Actions class
		Actions action = new Actions(driver);

		//Performing the mouse hover action on the target element.
		action.moveToElement(adminConfigurationMenu).perform();

	}
	public void click_view_email_Configuration_button() {
		menu_admin_viewEmailNotification.click();
	}

	public void enter_username(String username) {
		userName_txt.click();
		userName_txt.sendKeys(username);
	}

	public void verify_username_ResultTable(String username)
	{
		Assert.assertTrue(resultLink.getText().equalsIgnoreCase(username));
	}
}
