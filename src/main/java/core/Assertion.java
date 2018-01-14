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
			Log.info("Assertion : PASSED. Expected: '"+ob1+"' Actual: '"+ob2+"'");
		} catch (Exception e) {
			Log.error("Assertion : FAILED Expected: '"+ob1+"' Actual: '"+ob2+"'");
			throw e;
		}
	}
	
	
	/**
	 * This method compares the WebElement with its text.
	 * @param property control of which we need to get text for assertion.
	 * @param value expected value of the control property.
	 * @throws Exception
	 */
	public static void assertLocatorvalue(Locator locator, String value) throws Exception
	{
		Action act = new Action();	
		 String text = act.getText(locator);
		try {
			Assert.assertEquals(text, value);
			Log.info("Assertion : PASSED. Expected: '"+text+"' Actual: '"+value+"'");
			
		} catch (Exception e) {
			Log.error("Assertion : FAILED. Expected: '"+text+"' Actual: '"+value+"'");
			throw e;
		}
	}
	
	
	/**
	 * This method asserts whether control is visible on the page or not. If locator is displayed test case is passed else failed.
	 * @param property control property of control for which assertion as applied. You can enter multiple locator with comma separated.
	 * @throws Exception
	 */
	public static void assertDisplyed(Locator locator) throws Exception
	{	

		Action act =  new Action();
		boolean bl=	act.isDisplayed(locator);
		try{
			Assert.assertEquals(true, bl);
			Log.info("Assertion: PASSED '"+ act.getControlDescription(locator) +"' is displayed.");
		}
		catch(Exception ex)
		{
			Log.info("Assertion: FAILED '"+ act.getControlDescription(locator) +"' is not visible.");
			throw ex;
			}
		
	  }
	
	

	/**
	 * This method verifies whether radio button/Check box is selected or not. 
	 * @param property Radio button/Check box control property
	 * @throws Exception
	 */
	public static void assertIsRadioButtonSelected(Locator locator) throws Exception
	{	

		Action act = new Action();	
		
		boolean bl=	act.isSelected(locator);
		try{
			Assert.assertEquals(true, bl);
			Log.info("Verification : PASSED '"+ act.getControlDescription(locator)+"' radio button is selected");
		}
		catch(Exception ex)
		{
			Log.error("Verification : FAILED '"+ act.getControlDescription(locator)+"' radio button is not selected");
			throw ex;
			}
	}
	  
	
	
	/**
	 * This method asserts for control not visibility on the page. If locator is not displayed test case is passed else failed.
	 * @param property control property of control for which assertion as applied. You can enter multiple locator with comma separated.
	 * @throws Exception
	 */
	public static void assertNotDisplyed(Locator locator) throws Exception
	{

		Action act = new Action();	
	
		boolean bl=	act.isDisplayed(locator);
		try{
			Assert.assertEquals(false, bl);
			Log.info("Assertion : PASSED '"+act.getControlDescription(locator)+"' is not displayed");
		}
		catch(Exception ex)
		{
			Log.info("Assertion : FAILED '"+act.getControlDescription(locator)+"' is displayed");
			throw ex;
		
	}
  }
	
}
