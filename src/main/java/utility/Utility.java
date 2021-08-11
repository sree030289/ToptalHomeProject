package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utility {


	static WebDriverWait wait; 
    static WebDriver driver; 
    static Properties prop = new Properties();
    public Scenario scenario;
    public Utility()
	{
		this.driver=DriverFactory.getDriver();
		this.scenario= DriverFactory.getInstance().getScenario();
	}

    public static void loadPropertyFile() {
    	
    	try {

			String path =  Utility.class.getClass().getResource("/config/configuration.properties").getFile();

			prop.load(new FileInputStream(path));
		} catch (IOException ex) {
            ex.printStackTrace();
        }
    }


	public static String getProperty(String key) {
		
		loadPropertyFile();
		return prop.getProperty(key);
		
	}

	public static void uploadFile(String path,WebElement fileUpload){
		if(driver instanceof RemoteWebDriver){

			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			LocalFileDetector detector = new LocalFileDetector();

			File localFile = detector.getLocalFile(path);

			fileUpload.sendKeys(localFile.getAbsolutePath());
		}
		else {
			fileUpload.sendKeys(path);
		}
	}

	public static void webDriverWait(WebElement we)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated((By) we));
	}

	public static void screenShot(String fileName) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This function will take screenshot
	 * @param webdriver
	 * @param fileName
	 * @throws Exception
	 */
	public  void takeSnapShot(WebDriver webdriver,String fileName ) throws Exception{


		byte[] screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", fileName);



	}



}
