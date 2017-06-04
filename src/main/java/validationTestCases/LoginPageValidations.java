package validationTestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.Assertion;
import pageFactoryMethods.LoginPageMethods;
import testBase.TestBase;

public class LoginPageValidations extends TestBase
{
	
	@DataProvider(name="Loginfn")
	public Object[][] LoginDataProvider() throws Exception
	{
		return getDataProviderData("Login");
	}
	
	@Test(dataProvider="Loginfn")
	public void Login(String username, String password, String assertDisplayed) throws Exception
	{
		LoginPageMethods loginm = new LoginPageMethods();
		loginm.LoginM(username,password,"Login");
		takeScreenShot();
		Assertion.assertDisplyed(assertDisplayed);
	}
	
}
