package validationTestCases;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.Assertion;
import core.Locator;
import core.ReadExcel;
import pageFactoryMethods.LoginPageMethods;
import testBase.TestBase;

public class LoginPageValidations extends TestBase
{
	public static Map<String,Locator> objectRepository = ReadExcel.getObjectRepositoryFromExcel("Login");

	@DataProvider(name="Loginfn")
	public Object[][] LoginDataProvider() throws Exception
	{
		Object[][] obj = getDataProviderData("Login");
		return obj;
	}
	
	@Test(dataProvider="Loginfn")
	public void Login(String username, String password, String assertDisplayed) throws Exception
	{
		Locator lc = objectRepository.get("assertDisplayed");
		LoginPageMethods loginm = new LoginPageMethods();
		loginm.LoginM(username,password);
		takeScreenShot();
		Assertion.assertDisplyed(lc);
	}
	
}
