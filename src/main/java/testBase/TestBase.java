package testBase;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import core.BaseClass;

public class TestBase extends BaseClass{
	
	
	

	 @BeforeMethod(alwaysRun=true)
	 public void browserOpen() throws MalformedURLException 
	 {
		 browserInitialize();
	 }
	
	 
		
	@AfterMethod(alwaysRun=true)
	public void Close()
	{
		closeAllBrowser();
		
		
	}
			

}
