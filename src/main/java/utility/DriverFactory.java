package utility;


import io.cucumber.java.Scenario;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
	
	 static WebDriver driver = null;

	private Scenario scenario;

	public  Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
	private static DriverFactory instance;

	public static DriverFactory getInstance() {
		if(instance==null) {
			instance = new DriverFactory();
		}
		return instance;
	}




	/**
	* Create a driver instance or returns existing driver
	* @author  sreeramvennapusa
	* @version 1.0
	* @since   2018-12-26
	*/
	public  static WebDriver get_driver_instance() throws MalformedURLException {

			System.out.println("Creating driver");
		    //getOperatingSystemSystemUtils();


			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox"); // Bypass OS security model
			options.addArguments("--headless");


		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
		desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		desiredCapabilities.setCapability("name", "myTestName");
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,options);



			String remoteUrl = "http://172.17.0.3:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(remoteUrl), options);

			//driver = new ChromeDriver(options);

			return driver;
	}

	public static WebDriver getDriver()
	{
		return  driver;
	}

	public static void getOperatingSystemSystemUtils() {
		String os = SystemUtils.OS_NAME.toUpperCase();
		// System.out.println("Using SystemUtils: " + os);
			if(os.contains("MAC")) {
				System.setProperty("webdriver.chrome.driver", "driver/chromedriver_mac");
			}
			else if(os.contains("WINDOWS"))
			{
				System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
			}
			else
			{
				System.setProperty("webdriver.chrome.driver", "driver/chromedriver_linux");
			}
	}

}
