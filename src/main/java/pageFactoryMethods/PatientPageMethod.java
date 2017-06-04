package pageFactoryMethods;

import core.Log;
import methodBase.MethodBase;


public class PatientPageMethod extends MethodBase {
	
	public void AddPatient(String FN, String LN, String DOB, String Gender, String Maritialstatus,String Action) throws Exception
	{
		if(isDisplayed("Subject"))
		{
		  if(FN!=null){
			type("FirstName", FN);
		}
		if(LN!=null){
			type("LastName", LN);
		}
		if(DOB!=null){
			type("DateOfBirth", DOB);
		}
		if(Gender!=null){
			selectFromDropdown("Gender",Gender);
		}

		if(Maritialstatus!=null){
			selectFromDropdown("MaritialStatus", Maritialstatus);
		}
		if(Action!=null){
			click("Save");
		}
		}
		else{
			Log.error("Subject Page is not displayed");
		}
	}
		
	
	}


	