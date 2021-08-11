package stepDefination.rest;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import stepDefination.web.BranchOperations;
import utility.DriverFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class hooks {

    /**
     * @description: setUp() method to set up initial state for all test methods and tearDown() method perform cleanup after execution.
     * @return None
     * @author sreeramvennapusa
     * @version 1.0
     */

    public WebDriver driver;
    static Logger logger = Logger.getLogger(BranchOperations.class.getName());

    Scenario scenario;

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        logger.info("Instantiating before hooks for activaitng scenario");
//        driver =  DriverFactory.get_driver_instance();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        DriverFactory.getInstance().setScenario(scenario);
        this.scenario=scenario;
        scenario.log("Before test");

    }


    @After
    public void tearDown() {

       // driver.quit();

        scenario.log("After test");
    }
}
