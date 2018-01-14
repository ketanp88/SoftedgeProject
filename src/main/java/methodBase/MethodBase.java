package methodBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Action;
import core.Locator;
import core.Log;

public class MethodBase extends Action{
	
	/**
	 * This method verifies whether alert is present or not on the page.
	 * @return true if alert is present. false if alert is not present.
	 */
	public boolean isAlertDisplayed()
	{
		try{ 
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert();
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * This method handles alert. This method returns the alert text and perform action according to user input.
	 * @param action to be performed on alert. Expected input Accept/Dismiss
	 * @return text present on alert.
	 */
	public String handleAlert(String action)
	{
		String alertText = null;
		if(isAlertDisplayed())
		{
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		
			Log.info("Alert is displayed with message "+alertText);
			
			if(action.equalsIgnoreCase("Accept"))
			{
				alert.accept();
				System.out.println("Alert message is accepted");
				Log.info("Alert message is accepted");
			}
			else if(action.equalsIgnoreCase("Dismiss"))
			{
				alert.dismiss();
				System.out.println("Alert message is dismissed");
				Log.info("Alert message is dismissed");
			}
		}
		else{
			Log.error("Alert is not displayed");
		}
		return alertText;
	}
	
	/**
	 * This Method gives selected option from the drop down items.
	 * @param dropdownPropertyName Control property of the drop down item.
	 * @throws Exception 
	 */
	public String getSelectedItemFromDropdown(Locator locator) throws Exception
	{
		String SelectedItem = null;
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				Select se = new Select(webControl);
				SelectedItem = se.getFirstSelectedOption().getText().trim();
				Log.info("Selected item is '"+SelectedItem+"' in "+getControlDescription(locator));
			} catch (Exception e) {
				takeScreenShot();
				Log.error("'"+getControlDescription(locator)+"' Control is not visible to type");
				throw e;
			}
		}
		else{
			Log.debug(getControlDescription(locator)+" Web Control is null.");
		}
		return SelectedItem;
	}
	
	
/**
 * This method returns List of drop down items in String format.
 * @param dropdownPropertyName control property of the drop down.
 * @return List of String having drop down items.
 * @throws Exception
 */
public List<String> getDropdownItems(Locator locator) throws Exception
{
	List<String> strList = new ArrayList<String>();
	WebElement webControl =  getWebElement(locator);
	if(webControl!=null)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOf(webControl));
			Select se = new Select(webControl);
			List<WebElement> drpdown = se.getOptions();
			for(WebElement we: drpdown)
			{
				strList.add(we.getText().trim());	
			}
			
		} catch (Exception e) {
			Log.error("'"+getControlDescription(locator)+"' Control is not visible to type");
			throw e;
		}
	}
	else{
		Log.debug("'"+getControlDescription(locator)+"' Web Control is null.");
	}
	
	return strList;
}



/**
 * This method returns list of WebElement for given control property path.
 * @param propertyName control property for which you wants findElements.
 * @return
 */
public List<WebElement> findGroup(Locator locator)
{
	List<WebElement>li =driver.findElements(locator.locator);
	return li;	
}

	
	/**
	 * This method converts WebElement List to String List.
	 * @param li List of WebElements which needs to be converted.
	 * @return List of String using get text of WebElements
	 */
	public List<String> WebElementListToStringList(List<WebElement> li)
	{
		List<String> strList = new ArrayList<String>();
		for(WebElement we : li)
		{
			strList.add(we.getText().trim());
		}
		return strList;
	}
	
	
	
	/**
	 * This method verifies the List of Data for particular data whether it is available or not.
	 * If List contains particular data then it will return true else false.
	 * @param list List of data which need to verify whether value is present.s
	 * @param value value which need to verify  whether it is present or not.
	 * @return true if present false if not.
	 * @throws Exception
	 */
	public boolean verifyListContainsData(List<?> list,String value) throws Exception
	{
		if(list.contains(value))
		{
			Log.info("List contains data "+value);
			return true;
		}else{
			Log.error("List does not contains data "+value);
			return false;
		}
	}
	
	
	
	/**
	 * This method verifies the List of Data for particular data whether it is available or not.
	 * If List contains particular data then it will return true else false.
	 * @param controlProperty control for which we need the List of WebElement which is converted to List of String for verifying data contains.
	 * @param value value which need to verify  whether it is present or not.
	 * @return true if present false if not.
	 * @throws Exception
	 */
	public boolean verifyListContainsData(Locator locator,String value) throws Exception
	{
		List<String> li = WebElementListToStringList(findGroup(locator));
		if(li.contains(value))
		{
			Log.info("List contains data "+value);
			return true;
		}else{
			Log.error("List does not contains data : "+value);
			Log.error("Expected value: "+value);
			Log.error("Actual List: "+li);
			return false;
		}
	}
	
	
	
	/**
	 * This method verifies whether List contains all data with same value as given value.
	 * @param list  List of Objects which need in which we need to verify data.
	 * @param Value value which should be present in all list
	 * @return true if value is present else false.
	 * @throws Exception
	 */
	public boolean verifyListContainsAllData(List<?> list,String value) throws Exception
	{
		for(Object str: list)
		{
			if(!str.equals(value))
			{
				Log.error("List does not contains all data with value :"+value);
				Log.error("Expected value: "+value);
				Log.error("Actual List: "+list);
				return false;
			}
		}
		Log.info("List contains all data with value :"+value);
	    return true;
		
	}
	
	
	
	/**
	 * This method verifies whether List contains all data with same value as given value.
	 * @param controlProperty control property of the WebElement to fetch Data and convert it into String List.
	 * @param value value which should be present in all list
	 * @return true if value is present else false.
	 * @throws Exception
	 */
	public boolean verifyListContainsAllData(Locator locator,String value) throws Exception
	{
		List<String> li = WebElementListToStringList(findGroup(locator));
		 
		for(String str: li)
		{
			if(!str.equalsIgnoreCase(value))
			{
				Log.error("List does not contains all data with value :"+value);
				Log.error("Expected value: "+value);
				Log.error("Actual List: "+li);
				return false;
			}
		}
		Log.info("List contains all data with value :"+value);
		return true;
		
	}
	
	
	
	/**
	 * This method compares two List for exact Match. (Order, value and size).
	 * @param list1 First List for comparison.
 	 * @param list2 Second List for comparison.
	 * @return returns true if exactly match else false.
	 */
	public boolean compareTwoListForExactMatch(List<?> list1, List<?> list2)
	{
		if(list1.equals(list2))
		{
			Log.info("Two List are exactly equal.");
			Log.info("List 1 Data: "+list1);
			Log.info("List 2 Data: "+list2);
			return true;
		}
		else{
			Log.error("Two List are not exactly equal");
			Log.error("List 1 Data: "+list1);
			Log.error("List 2 Data: "+list2);
			return false;
		}
	}
	
	
	
	/**
	 * This method verifies whether List is sorted or not.
	 * @param list List of which we need to verify sorting.
	 * @return true if list is sorted else false.
	 * @throws Exception 
	 */
	public boolean verifyfListIsSorted(List list) throws Exception
	{
		List<?> list1 = list;
		Collections.sort(list);
		 
		if(list1.equals(list))
		{
			Log.info("List if properly sorted");
			return true;
		}
		else{
			Log.error("List if not sorted properly");
			Log.error("Expected List : "+list);
			Log.error("Actual List : "+list1);
			return false;
		}
	}
	
	
	
	public boolean waitForIsDisplayed(Locator locator ,int TimeOutInSec)
	{
		int counter = 0;
		while(counter<TimeOutInSec)
		{
			try{
				WebDriverWait wait = new WebDriverWait(driver, TimeOutInSec);
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator.locator));
				WebElement we = driver.findElement(locator.locator);
				return we.isDisplayed();
				}catch(Exception ex){}
			counter++;
		}
		return false;
	}
	
	public boolean waitForNotDisplayed(Locator locator ,int TimeOutInSec)
	{
		int counter = 0;
		while(counter<TimeOutInSec)
		{
			try{
				WebDriverWait wait = new WebDriverWait(driver, TimeOutInSec);
				wait.until(ExpectedConditions.invisibilityOf(getWebElement(locator)));
				WebElement we = driver.findElement(locator.locator);
				return we.isDisplayed();
				}catch(Exception ex){}
			counter++;
		}
		return false;
	}
	
	
	public boolean waitForIsClickable(Locator locator ,int TimeOutInSec)
	{
		boolean isEnabled = false;
		int counter = 0;
		while(counter<TimeOutInSec)
			{
			try{
				WebDriverWait wait = new WebDriverWait(driver, TimeOutInSec);
				WebElement	webElement= wait.until(ExpectedConditions.elementToBeClickable(locator.locator));
				isEnabled=webElement.isEnabled();
				return isEnabled;
				}
			catch(Exception ex){
			}
				sleep(1);
		}
		return false;
	}
	
	
	public long getRandomNumber(int length) 
	{
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}

	
	
	public String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) 
        {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

	
	public boolean waitForAlertPresent(int timeout)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());	
		try{
			driver.switchTo().alert();
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	
	public Set<String> getWindowHandles()
	{
		return driver.getWindowHandles();
	}
	
	
	public String getWindowHandle()
	{
		return getWindowHandle();
	}
	
	public void switchToWindow(String window)
	{

		driver.switchTo().window(window);
		
	}
	
	public boolean waitForWindowGetClosed(int windowSize)
	{
		int counter =0;
		int windowList = getWindowHandles().size();
		
		while(counter<10 && windowList<windowSize)
		{
			sleep(1);
		}	
		return true;
	}
	
	
	public void sleep(int timeinSeconds)
	{
		try{
			Thread.sleep(timeinSeconds*1000);
		}
		catch(Exception ex){
			
		}
	}
	

	
}
