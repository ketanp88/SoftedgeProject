package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


public class BaseClass extends ReadExcel
{
	
	public WebDriver driver;
	public static Map<String,String> objectrepo = getObjectRepositoryFromExcelFile();
	public static int testCaseId;
	public static DesiredCapabilities cap;
	public String ipAddress = "localhost"; 
	
	public WebDriver getDriver()
	{
		return this.driver;
	}


	public void setDriver(WebDriver driver)
	{
		 this.driver=driver;
	}
/**
 * This method launch browser as per given browserName. It opens URL provided in config.property file.
 * @param browserName expected values are Chrome/Firefox/IE as String for opening browser.
 * @throws MalformedURLException 
 */
	public WebDriver browserInitialize(String browserName) throws MalformedURLException 
	{
		WebDriver driver = null; 
		
		Log.info("Opening "+browserName+" browser");
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", getProperty("ChromePath"));
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FireFox"))
		{
			//System.setProperty("webdriver.gecko.driver",getProperty("firefoxpath"));
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", getProperty("IEpath"));
			driver=new InternetExplorerDriver();
		}
		
		else if(browserName.equalsIgnoreCase("RemoteChrome"))
		{
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setJavascriptEnabled(true);
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://"+ipAddress+":4444/wd/hub"), cap);
		}
		
		else if(browserName.equalsIgnoreCase("RemoteIE"))
		{
			cap = DesiredCapabilities.internetExplorer();
			cap.setBrowserName("iexplore");
			cap.setJavascriptEnabled(true);
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://"+ipAddress+":4444/wd/hub"), cap);
		}
		
		else if(browserName.equalsIgnoreCase("Remotefirefox"))
		{
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setJavascriptEnabled(true);
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		}
		
		driver.get(getProperty("URL"));
		Log.info("Type URL "+getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	
	
	public void closeAllBrowser()
	{
		driver.quit();
	}
	
	/**
	 * This method returns property value of provided key from config.property file.
	 * @param PropertyName Key for which you need value from property file.
	 * @return value related to propertyName from config.property
	 */
	public static String getProperty(String propertyName) 
	{
		try {
		   		Properties prop = new Properties();
				FileInputStream propfile = new FileInputStream("./configuration/config.property");
				prop.load(propfile);
				String Property =  prop.getProperty(propertyName);
				propfile.close();
				return Property;
			}
		   	catch (FileNotFoundException e) 
		   	{
				e.printStackTrace();
			} 
		   	catch (IOException e) 
		   	{
		   		e.printStackTrace();
			}
		 return null;       
	}	
	
	
	/**
	 * This method returns By locator of given object Name.
	 * @param propertyName Object property name for which you need By element.
	 * @return By property of given element. returns null if object is not present.
	 */
	public By getLocator(String propertyName) 
	{		
		
			
		boolean getLocatortype=false;
		String Property = objectrepo.get(propertyName);
		if(Property!=null)
		{
			String[] propertydata = Property.split("##");
			By locator=null;
		if(propertydata[0].equalsIgnoreCase("id"))
		{
			getLocatortype=true;
			locator=By.id(propertydata[1]);
			return locator;
		}
		if(propertydata[0].equalsIgnoreCase("xpath"))
		{
			getLocatortype=true;
			locator=By.xpath(propertydata[1]);
			return locator;
		}
		if(propertydata[0].equalsIgnoreCase("ClassName"))
		{
			getLocatortype=true;
			locator=By.className(propertydata[1]);
			return locator;
		}
		if(propertydata[0].equalsIgnoreCase("LinkText"))
		{
			getLocatortype=true;
			locator=By.linkText(propertydata[1]);
			return locator;
		}
		if(propertydata[0].equalsIgnoreCase("CssSelector"))
		{
			getLocatortype=true;
			locator=By.cssSelector(propertydata[1]);
			return locator;
		}
		if(propertydata[0].equalsIgnoreCase("TagName"))
		{
			getLocatortype=true;
			locator=By.tagName(propertydata[1]);
			return locator;
		}	
		if(!getLocatortype)
		{
			Log.error("Locator type written for control "+propertyName+ "is Invalid.");
			System.out.println("Locator type written for control "+propertyName+ "is Invalid.");
		}
	}
		else
		{
			Log.error("Entered property name for control "+propertyName+ " is not present in the Object Repository excel File");
		}	
		return null;
	}
		
	/**
	 * This method returns WebElement for given By object.
	 * @param locator By object for which WebElement is to be generated.
	 * @return WebElement of given By locator. returns null if WebElement is not found on page or Locator is null.
	 */

	public WebElement getWebElement(By locator)
	{	
			try {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				WebElement we=  driver.findElement(locator);
				return we;
			} catch (Exception e) {
				Log.error("Element not found on Web Page");
			return null;
		}
	}
	
	
	/**
	 * This method returns the WebElement for given control property.
	 * @param controlproperty of the Element for which you need to create WebElement.
	 * @return returns WebElement for for given property.
	 * @throws Exception
	 */
	public WebElement getWebElement(String controlproperty) throws Exception
	{	
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(controlproperty)));
			WebElement we=  driver.findElement(getLocator(controlproperty));
			return we;
		} catch (Exception e) {
			Log.error("Element is not visible or NULL for control '"+getControlDescription(controlproperty));
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * This method gives you the control description for given control name.
	 * @param controlProperty property name for which you wants the control description.
	 * @return control description for given property name.
	 */
	public String getControlDescription(String controlProperty)
	{	
		String Property = objectrepo.get(controlProperty);
		if(Property!=null)
		{
			try {
				String[] propertydata = Property.split("##");
				String controldescriprion = propertydata[2];
				return controldescriprion;
			} catch (Exception e) {
				Log.error("Control description is not available for Web Control"+ controlProperty);
				return null;
			}
		}
		else{Log.error("property could not be found for control: "+controlProperty);}
		return null;
	}

	
	/**
	 * This method takes the screen shot of the browser instance when method is called.
	 * This method saves file in given test case ID folder under Screenshot folder.
	 */
	public  void takeScreenShot() 
	{
			
		String TCID = Integer.toString(testCaseId);	
		 File file = new File(".\\Screenshots\\TCID_"+TCID);
	        if (!file.exists()) 
	        {
	        	file.mkdirs();
	        }
	   int i=1;
	   File file2;
	   file2 = new File(".\\Screenshots\\TCID_"+TCID+"\\"+i+".jpg");
		
	while(file2.exists())
	{
		i++;
		file2 = new File(".\\Screenshots\\TCID_"+TCID+"\\"+i+".jpg");
	}
	   
		String ScreenShotpath = ".\\Screenshots\\TCID_"+TCID+"\\"+i+".jpg";
	    File ScrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    
	    try {
			Files.copy(ScrFile, new File(ScreenShotpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
}
	
	
	/**
	 * This method takes the screen shot of the browser instance when test case is failed.
	 * This method saves file in given Error folder under Screenshot folder.
	 */
	public  void errorScreenShot(String TCName)
	{
		 File file = new File(".\\Screenshots\\Error\\");
	        if (!file.exists()) 
	        {
	        	file.mkdirs();
	        }
		String ScreenShotpath = ".\\Screenshots\\Error\\"+TCName+".jpg";
	    File ScrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			Files.copy(ScrFile, new File(ScreenShotpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}

