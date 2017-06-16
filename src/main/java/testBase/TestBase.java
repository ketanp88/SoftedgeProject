package testBase;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import core.BaseClass;

public class TestBase extends BaseClass{
	
	WebDriver driver;
	
	 @Parameters({"browser"})
	 @BeforeMethod(alwaysRun=true)
	 public void browserOpen(String browser) throws MalformedURLException 
	 {
		 driver=browserInitialize(browser);
	 }
	
	 
		
	@AfterMethod(alwaysRun=true)
	public void Close()
	{
		closeAllBrowser();
		
		
	}
			

}
