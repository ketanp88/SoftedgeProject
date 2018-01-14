package pageFactoryTestCases;

import org.testng.annotations.Test;

import core.Assertion;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import pageFactoryMethods.LoginPageMethods;
import testBase.TestBase;

import java.util.Map;
public class LoginPageTestCases extends TestBase
{
	
	@Test(groups={"Regression"})
	public void blankEmailIDLogin() throws Exception
	{
		testCaseId = 1;
		Map<String, String> testData = getTestdata(); 
		LoginPageMethods loginm = new LoginPageMethods();
		loginm.LoginM(testData.get("Username"),testData.get("Password"));
	}

	
}
	
	
	
	

