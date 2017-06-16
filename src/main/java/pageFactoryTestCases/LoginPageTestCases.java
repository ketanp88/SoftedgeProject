package pageFactoryTestCases;

import org.testng.annotations.Test;

import core.Assertion;

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
		System.out.println(getDriver().getTitle());
		testCaseId = 2;
		Map<String, String> testData = getTestdata(); 
		LoginPageMethods loginm = new LoginPageMethods();
		loginm.LoginM(testData.get("Username"),testData.get("Password"),"Login");
		takeScreenShot();
		Assertion.assertLocatorvalue("emailidblankerror", "Please specify your Email ID");
		
	}

	
	//@Test(groups = {"Regression","Formal","Smoke"})
	public void blankPasswordLogin() throws Exception
	{	
		testCaseId = 3;
		Map<String, String> testData = getTestdata(); 
		LoginPageMethods loginm = new LoginPageMethods();
		loginm.LoginM(testData.get("Username"), testData.get("Password"),"Login");
		takeScreenShot();
		Assertion.assertLocatorvalue("passwordblankerror", "Please enter your Password");
	}
	
	
	//@Test(groups = {"Regression","Formal","Smoke"})
	public void invalidLoginCredentials() throws Exception
	{
		testCaseId = 4;
		LoginPageMethods loginm = new LoginPageMethods();
		Map<String, String> testData = getTestdata(); 
		loginm.LoginM(testData.get("Username"), testData.get("Password"),"Login");	
		takeScreenShot();
		Assertion.assertLocatorvalue("logincommonerror", "Invalid Details. Please check the EmailID-Password combination.");
	}
	
	
	//@Test(groups = {"Regression","Formal"})
	public void validLogin() throws Exception
	{
		testCaseId = 1;
		Map<String, String> testData = getTestdata(); 
		LoginPageMethods loginm = new LoginPageMethods();
		loginm.LoginM(testData.get("Username"), testData.get("Password"),"Login");
		takeScreenShot();
		Assertion.assertDisplyed("myNaukariHomelabel");
		
	}
	
	
}
	
	
	
	

