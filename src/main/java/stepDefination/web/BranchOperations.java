package stepDefination.web;

import java.util.logging.Logger;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;


import pages.*;
import utility.DriverFactory;
import utility.Utility;


public class BranchOperations extends Utility{
	
	public WebDriver driver;
	Login login;
	PIMmodule pimModule;
	AdminModule adminModule;
	DashboardModule dashboardModule;
	DirectoryModule directoryModule;
	String id="";
	
	static Logger logger = Logger.getLogger(BranchOperations.class.getName());

	public BranchOperations()
	{
		driver = DriverFactory.getDriver();
		login = new Login(driver);
		pimModule = new PIMmodule(driver);
		adminModule = new AdminModule(driver);
		dashboardModule = new DashboardModule(driver);
		directoryModule = new DirectoryModule(driver);

	}

    @Given("^User login into Application with \"([^\"]*)\" creds$")
    public void userLoginIntoApplicationWithCreds(String arg0){

		scenario.log("Login to the application");

		if(arg0.equalsIgnoreCase("Admin")) {
			login.enter_username(Utility.getProperty("adminUsername"));
			scenario.log("Enter User Name: "+Utility.getProperty("adminUsername"));
			scenario.log("Enter Password: XXXXX" );
			login.enter_password(Utility.getProperty("adminpwd"));
		}
		else
		{
			login.enter_username(Utility.getProperty("empUsername"));
			scenario.log("Enter User Name: "+Utility.getProperty("empUsername"));

			scenario.log("Enter Password: XXXX");
			login.enter_password(Utility.getProperty("emppwd"));
		}
		scenario.log("Click on Login button:");
		login.click_login_link();

    }

	@Given("^User navigates to Orange HRM Application$")
	public void userNavigatesToOrangeHRMApplication() throws Exception {
		driver.get(Utility.getProperty("url"));
		takeSnapShot(driver,"loginScreen");

	}

	@And("^he navigates to PIM section and clicks on Add user button$")
	public void heNavigatesToPIMSectionAndClicksOnAddUserButton() throws Exception {

		scenario.log("Navigate to PIM Module");
		pimModule.click_Menu_pim_Link();

		takeSnapShot(driver,"PIM Module");
		scenario.log("Click on Add Employee button");
		pimModule.click_AddEmployee_Btn();
		takeSnapShot(driver,"Add Employee");

	}

	@And("^he provides \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" details and clicks on Save button$")
	public void heProvidesDetailsAndClicksOnSaveButton(String arg0, String arg1, String arg2) throws Exception {
		// Write code here that turns the phrase above into concrete actions

		scenario.log("Enter all the details required for adding employee");
		id=pimModule.enter_EmployeeDetails(arg0,arg1,arg2);

		scenario.log("Click on Save button");
		pimModule.click_Save_Btn();
		takeSnapShot(driver,"add_employee_details");

	}


	@Then("^he logout of the application$")
	public void heLogoutOfTheApplication() throws Exception {
		login.click_on_logout();
		takeSnapShot(driver,"Logged out");
	}

	@Then("^navigate to Employee List and Search for the Employee with \"([^\"]*)\",\"([^\"]*)\"is added successfully$")
	public void navigateToEmployeeListAndSearchForTheEmployeeWithIsAddedSuccessfully(String arg0, String arg1) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		scenario.log("Validate if the employee is created successfully by comparing the emp id");
		pimModule.validate_Employee_Creation(arg0, arg1);

//		scenario.log("Search Employee in the employee list with employee id and\n" +
//				"    validate in the table result below");
//		//pimModule.search_employee(arg0);
		takeSnapShot(driver,"search_employee");

	}

    @When("^he navigates to Admin section and search for \"([^\"]*)\"$")
    public void heNavigatesToAdminSectionAndSearchFor(String arg0) throws Exception {

		adminModule.click_adminMenu_button();
		takeSnapShot(driver,"Admin Menu");
		adminModule.enter_username(arg0);

		adminModule.click_search_button();

    }

	@Then("^he is able to find the \"([^\"]*)\" in the result table section$")
	public void heIsAbleToFindTheInTheResultTableSection(String arg0) throws Exception {

		takeSnapShot(driver,"Search Result");
		adminModule.verify_username_ResultTable(arg0);

	}

	@When("^he navigates to Dashboard section$")
	public void heNavigatesToDashboardSection() throws Exception {
		dashboardModule.click_dashboardMenu_button();
		takeSnapShot(driver,"dashboardMenu");
		
	}



	@Then("^Verify subscribe is not displayed$")
	public void verifySubscribeIsNotDisplayed() throws Exception {

		takeSnapShot(driver,"success message");
		dashboardModule.verify_success_message();
	}

	@And("^he clicks on subscribe marketplace link and provides \"([^\"]*)\" and \"([^\"]*)\"$")
	public void heClicksOnSubcribeMarketplaceLinkAndProvidesAnd(String arg0, String arg1) throws Exception {
		dashboardModule.click_subscriber_link();
		takeSnapShot(driver,"subscriber");

		dashboardModule.enter_username_email(arg0,arg1);
		takeSnapShot(driver,"username and email");

		dashboardModule.click_subscriber_button();

	}

	@When("^he navigates to Admin section$")
	public void heNavigatesToAdminSection() {
		adminModule.click_adminMenu_button();
	}


	@And("^he hovers on configuration and selects email configuration menu item$")
	public void heHoversOnConfigurationAndSelectsEmailConfigurationMenuItem() throws Exception {
		adminModule.hover_configuration_menu(driver);
		takeSnapShot(driver,"hover_configuration_menu");

		adminModule.click_view_email_Configuration_button();

		takeSnapShot(driver,"view Email_configuration_menu");

	}

	@Then("^he subscribes to first two notification types$")
	public void heSubscribesToFirstTwoNotificationTypes() throws Exception {

		adminModule.click_edit_button();
		takeSnapShot(driver,"Edit view");

		adminModule.select_checkbox1_button();
		adminModule.select_checkbox2_button();
		adminModule.click_save_button();

		takeSnapShot(driver,"Save view");


	}

	@When("^he navigates to Directory section$")
	public void heNavigatesToDirectorySection() throws Exception {

		directoryModule.click_directoryMenu_button();
		takeSnapShot(driver,"directory view");
	}


	@And("^he provides \"([^\"]*)\" parameter for search item$")
	public void heProvidesParameterForSearchItem(String arg0) throws Exception {
		directoryModule.select_jobTitle(arg0);
		takeSnapShot(driver,"select jobtitle");
		directoryModule.click_search_button();
	}

	@Then("^verify the top result match \"([^\"]*)\" selected in the search$")
	public void verifyTheTopResultMatchSelectedInTheSearch(String arg0) throws Exception {
		takeSnapShot(driver,"result");
		directoryModule.verify_result_jobtitle_email(arg0);
	}
}
