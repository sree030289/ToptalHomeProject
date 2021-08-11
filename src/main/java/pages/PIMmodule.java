package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Utility;

public class PIMmodule {
    // Initialization: Page factory will initialize every web element with reference
    // to corresponding element on the actual web page based on locators
    public PIMmodule(WebDriver driver) {
        /**
         * Constructor
         */
        PageFactory.initElements(driver, this);
    }

    // Declare elements on PIM module page by @findBy annotation
    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement menu_pim;

    @FindBy(id = "menu_pim_addEmployee")
    private WebElement addEmployee_btn;

    @FindBy(id = "firstName")
    private WebElement firstname_txt;

    @FindBy(id = "lastName")
    private WebElement lastName_txt;

    @FindBy(id = "employeeId")
    private WebElement employeeId_txt;

    @FindBy(id = "photofile")
    private WebElement fileupload;

    @FindBy(id = "btnSave")
    private WebElement save_btn;

    @FindBy(xpath = "//div[@id='profile-pic']/h1")
    private WebElement profilePicText;

    @FindBy(id = "personal_txtEmployeeId")
    private WebElement personal_txtEmployeeId_txt;

    @FindBy(id = "menu_pim_viewEmployeeList")
    private WebElement viewEmployeeList_menu;


    @FindBy(id = "empsearch_employee_name_empName")
    private WebElement empsearch_txt;


    @FindBy(id = "searchBtn")
    private WebElement search_btn;


    @FindBy(xpath = "//*[@id=\"resultTable\"]/tbody/tr/td[3]/a")
    private WebElement tableResultFname;

    String empid="";

    // Utilization: Methods to perform action on the web page

    /*
    CLick on the menu PM link
     */
    public void click_Menu_pim_Link() {

        menu_pim.click();
    }

    /*
    Click on the Add Employee button inside PIM module
     */
    public void click_AddEmployee_Btn() {

        addEmployee_btn.click();
    }

    /*
   Click on the Save  button
    */
    public void click_Save_Btn() {

        save_btn.click();
    }

    /*
   Click on the Employee List inside PIM module
    */
    public void click_EmployeeList_Btn() {

        viewEmployeeList_menu.click();
    }


    /*
    Enter all the details required for Employee creation like
    first name, lastname and profile pic path
     */
    public String enter_EmployeeDetails(String fname, String lname, String filepath){


        firstname_txt.sendKeys(fname);
        lastName_txt.sendKeys(lname);

        String path =  Utility.class.getClass().getResource("/toptalImg.jpeg").getPath();

        System.out.println(path);
        Utility.uploadFile(path,fileupload);

        //fileupload.sendKeys(path);

        empid=getEmpid();
        return empid;

    }
    /*
        get Empid before
    */
    public String getEmpid()
    {
        return employeeId_txt.getText();
    }


    /*
    Validate if the employee is created successfully
    by comparing the emp id
     */
    public void validate_Employee_Creation(String fname,String lname)
    {
       // Assert.assertTrue(personal_txtEmployeeId_txt.getText().equalsIgnoreCase(empid));

        Assert.assertTrue(profilePicText.getText().equalsIgnoreCase(fname +" "+lname));
    }

    /*
    Search Employee in the employee list with employee id and
    validate in the table result below
     */
    public void search_employee(String fname) throws InterruptedException {
        click_EmployeeList_Btn();

        empsearch_txt.click();
        empsearch_txt.sendKeys(fname);

        search_btn.click();


        Assert.assertTrue(tableResultFname.getText().equalsIgnoreCase(fname));

    }


}
