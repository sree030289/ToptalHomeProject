package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class DirectoryModule {

	// Initialization: Page factory will initialize every web element with reference
	// to corresponding element on the actual web page based on locators

	public DirectoryModule(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Declare elements on AdminModule page by @findBy annotation
	@FindBy(id = "menu_directory_viewDirectory")
	private WebElement directoryMenu;

	@FindBy(id = "searchDirectory_job_title")
	private WebElement searchDirectory_job_title;
	
	@FindBy(id = "searchBtn")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[@id=\"resultTable\"]/tbody/tr[2]/td[2]/ul/li[2]")
	private WebElement resultTableJobTitle;



	// Utilization: Methods to perform action on the web page
	public void click_directoryMenu_button() {
		directoryMenu.click();
	}	

	public void click_search_button() {
		searchBtn.click();
	}


	public void select_jobTitle(String jobTitle) {
		Select drop= new Select(searchDirectory_job_title);
		drop.selectByVisibleText(jobTitle);
	}



	public void verify_result_jobtitle_email(String jobtitle)
	{

		Assert.assertTrue(resultTableJobTitle.getText().equalsIgnoreCase(jobtitle));

	}

}
