package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;


public class BaseClass extends ReadExcel
{
	public static WebDriver driver;
	public static int testCaseId;
	public static DesiredCapabilities cap;
	public String ipAddress = "localhost";
	
/**
 * This method launch browser as per given browserName. It opens URL provided in config.property file.
 * @param browserName expected values are Chrome/Firefox/IE as String for opening browser.
 * @throws MalformedURLException 
 */
	public WebDriver browserInitialize() throws MalformedURLException 
	{
		String browserName = getProperty("BrowserName");
		Log.info("Opening '"+browserName+"' browser");
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", getProperty("ChromePath"));
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FireFox"))
		{
			System.setProperty("webdriver.gecko.driver",getProperty("firefoxpath"));
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", getProperty("IEpath"));
			driver=new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("HTMLUnit"))
		{
            driver = new HtmlUnitDriver();
		}
		
		driver.get(getProperty("URL"));
		Log.info("Type URL '"+getProperty("URL")+"' in browser window");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	public static By getLocator(String locatorType, String locatorValue) 
	{			
		boolean getLocatortype=false;
		By locator = null;
		
		if(locatorType.equalsIgnoreCase("xpath"))
		{
			getLocatortype=true;
			locator=By.xpath(locatorValue);
			return locator;
		}	
		else if(locatorType.equalsIgnoreCase("id"))
		{
			getLocatortype=true;
			locator=By.id(locatorValue);
			return locator;
		}
		else if(locatorType.equalsIgnoreCase("name"))
		{
			getLocatortype=true;
			locator=By.name(locatorValue);
			return locator;
		}
		else if(locatorType.equalsIgnoreCase("linktext"))
		{
			getLocatortype=true;
			locator=By.linkText(locatorValue);
			return locator;
		}
		else if(locatorType.equalsIgnoreCase("tagname"))
		{
			getLocatortype=true;
			locator=By.tagName(locatorValue);
			return locator;
		}
		else if(locatorType.equalsIgnoreCase("partiallinktext"))
		{
			getLocatortype=true;
			locator=By.partialLinkText(locatorValue);
			return locator;
		}
		else if(locatorType.equalsIgnoreCase("cssselector"))
		{
			getLocatortype=true;
			locator=By.cssSelector(locatorValue);
			return locator;
		}
		if(!getLocatortype)
		{
			Log.error("Locator type written for control is Invalid.");
		}
			
		return null;
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
				FileInputStream propfile = new FileInputStream("./Application Config/config.property");
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
	

	public WebElement getWebElement(Locator locator)
	{	
			try {
				By by = locator.locator;
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(by));
				return we;
			} catch (Exception e) 
			{	
				Log.error("Element not found on Web Page");
				return null;
		}
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
	public void errorScreenShot(String TCName)
	{
		String TCID = Integer.toString(testCaseId);	
		 File file = new File(".\\Screenshots\\TCID_"+TCID+"\\Error");
	        if (!file.exists()) 
	        {
	        	file.mkdirs();
	        }
	   int i=1;
	   File file2;
	   file2 = new File(".\\Screenshots\\TCID_"+TCID+"\\Error\\"+i+".jpg");
		
	while(file2.exists())
	{
		i++;
		file2 = new File(".\\Screenshots\\TCID_"+TCID+"\\Error\\"+i+".jpg");
	}
	   
		String ScreenShotpath = ".\\Screenshots\\TCID_"+TCID+"\\Error\\"+i+".jpg";
	    File ScrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    
	    try {
			Files.copy(ScrFile, new File(ScreenShotpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

