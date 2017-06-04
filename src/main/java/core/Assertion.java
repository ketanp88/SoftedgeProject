package core;

import org.testng.Assert;

public class Assertion 
{
	/**
	 * This method compares to objects (String/int/boolean). If both objects are equal then test case is passed else failed.
	 * @param ob1 Expected value.
	 * @param ob2 Actual value
	 * @throws Exception
	 */
	public static void assertEquals(Object ob1, Object ob2) throws Exception
	{
		try {
			Assert.assertEquals(ob1, ob2);
			Log.info("Assertion Pass. Expected: "+ob1+" Actual: "+ob2);
		} catch (Exception e) {
			Log.error("Assertion Failure. Expected: "+ob1+" Actual: "+ob2);
			throw e;
		}
	}
	
	
	/**
	 * This method compares the WebElement with its text.
	 * @param property control of which we need to get text for assertion.
	 * @param value expected value of the control property.
	 * @throws Exception
	 */
	public static void assertLocatorvalue(String property, String value) throws Exception
	{
		Action act = new Action();	
		 String text = act.readText(property);
		try {
			Assert.assertEquals(text, value);
			Log.info("Assertion Pass. Expected: "+text+" Actual: "+value);
		} catch (Exception e) {
			Log.error("Assertion Failure. Expected: "+ text+" Actual: "+value);
			throw e;
		}
	}
	
	
	/**
	 * This method asserts whether control is visible on the page or not. If locator is displayed test case is passed else failed.
	 * @param property control property of control for which assertion as applied. You can enter multiple locator with comma separated.
	 * @throws Exception
	 */
	public static void assertDisplyed(String property) throws Exception
	{	
		if(property!=null){
		Action act = new Action();	
		String[] str = property.split(","); 
		for(String prop : str){
		boolean bl=	act.isDisplayed(prop.trim());
		try{
			Assert.assertEquals(true, bl);
			Log.info("Assertion Passed. Expected: "+true+" Actual: "+bl);
			}
		catch(Exception ex){
			Log.error("Assertion Failure. Expected: "+true+" Actual: "+bl);
			Log.error(act.getControlDescription(property)+" is not visible");
			throw ex;
			}
		}
	  }
	}
	
	
	
	/**
	 * This method verifies whether radio button/Check box is selected or not. 
	 * @param property Radio button/Check box control property
	 * @throws Exception
	 */
	public static void assertIsRadioButtonSelected(String property) throws Exception
	{	
		if(property!=null){
		Action act = new Action();	
		String[] str = property.split(","); 
		for(String prop : str){
		boolean bl=	act.isSelected(prop.trim());
		try{
			Assert.assertEquals(true, bl);
			Log.info("Assertion Passed. Expected: "+true+" Actual: "+bl);
			}
		catch(Exception ex){
			Log.error("Assertion Failure. Expected: "+true+" Actual: "+bl);
			Log.error(act.getControlDescription(property)+" is not visible");
			throw ex;
			}
		}
	  }
	}
	
	
	
	/**
	 * This method asserts for control not visibility on the page. If locator is not displayed test case is passed else failed.
	 * @param property control property of control for which assertion as applied. You can enter multiple locator with comma separated.
	 * @throws Exception
	 */
	public static void assertNotDisplyed(String property) throws Exception
	{
		if(property!=null){
		Action act = new Action();	
		String[] str = property.split(","); 
		for(String prop : str){
		boolean bl=	act.isDisplayed(prop.trim());
		try{
			Assert.assertEquals(false, bl);
			Log.info("Assertion Pass. Expected: "+false+" Actual: "+bl);
		}
		catch(Exception ex)
		{
			Log.error("Assertion Failure. Expected: "+false+" Actual: "+bl);
			Log.error(act.getControlDescription(property)+" is visible");
			throw ex;
		}
	   }
	}
  }
	
	
	
	
}
