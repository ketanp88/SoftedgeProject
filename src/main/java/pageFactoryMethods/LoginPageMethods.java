package pageFactoryMethods;

import core.Log;
import methodBase.MethodBase;

public class LoginPageMethods extends MethodBase
{	
	
	
	public void LoginM(String uname, String pwd, String Action) throws Exception 
	{	
		if(isDisplayed("SignInBtn"))
		{
			if(uname!=null){
				type("Username", uname);
			}
			if(pwd!=null){
				type("Password", pwd);
			}
			if(Action!=null){
			click("SignInBtn");
			}
		}
		else{
			Log.error("Login Page is not displayed");
		}
	}
	
	
}
