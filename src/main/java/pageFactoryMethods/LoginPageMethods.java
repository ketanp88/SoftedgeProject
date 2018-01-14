package pageFactoryMethods;
import java.util.Map;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.Alert;
import org.openqa.selenium.security.Credentials;

import core.Locator;
import core.Log;
import core.ReadExcel;
import methodBase.MethodBase;

public class LoginPageMethods extends MethodBase
{	
	
	public static Map<String,Locator> objectRepository = ReadExcel.getObjectRepositoryFromExcel("Login");
	public Locator btnSignInBtn  = objectRepository.get("btnSignInBtn");
	public Locator txtbxUsername = objectRepository.get("txtbxUsername");
	public Locator txtbxPassword = objectRepository.get("txtbxPassword");
	

	public void LoginM(String uname, String pwd) throws Exception 
	{		
	if(isDisplayed(btnSignInBtn))
		{
			if(uname!=null){
				type(txtbxUsername, uname);
			}
			if(pwd!=null){
				type(txtbxPassword, pwd);
			}
			click(btnSignInBtn);
			
		}
		else{
			Log.error("Login Page is not displayed");
		}
		
		
	}
	
	
	public void getsessionID()
	{
		
		driver.switchTo().frame(0);
		String title = driver.getTitle();
		
		
	}
	
}
