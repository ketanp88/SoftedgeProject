package methodBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Action;
import core.Log;

public class MethodBase extends Action{
	
	
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
				Log.info("Alert message is accepted");
			}
			else if(action.equalsIgnoreCase("Dismiss"))
			{
				alert.dismiss();
				Log.info("Alert message is dismissed");
			}
		}
		else{
			Log.error("Alert is not displayed");
		}
		return alertText;
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
			strList.add(we.getText());
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
	public boolean verifyListContainsData(List list,String value) throws Exception
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
	public boolean verifyListContainsData(String controlProperty,String value) throws Exception
	{
		List<String> li = WebElementListToStringList(findGroup(controlProperty));
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
	public boolean verifyListContainsAllData(List list,String value) throws Exception
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
	public boolean verifyListContainsAllData(String controlProperty,String value) throws Exception
	{
		List<String> li = WebElementListToStringList(findGroup(controlProperty));
		 
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
	
	
	/**
	 * This Method gives selected option from the drop down items.
	 * @param dropdownPropertyName Control property of the drop down item.
	 * @throws Exception 
	 */
	public String getSelectedItemFromDropdown(String dropdownPropertyName) throws Exception
	{
		String SelectedItem = null;
		WebElement webControl =  getWebElement(getLocator(dropdownPropertyName));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				Select se = new Select(webControl);
				SelectedItem = se.getFirstSelectedOption().toString();
				Log.info("Selected item is '"+SelectedItem+"' in "+getControlDescription(dropdownPropertyName));
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(dropdownPropertyName)+" Control is not visible to type");
				throw e;
			}
		}
		else{
			Log.debug(getControlDescription(dropdownPropertyName)+" Web Control is null.");
		}
		return SelectedItem;
	}
	

}
