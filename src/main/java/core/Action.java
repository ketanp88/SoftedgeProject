package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action extends BaseClass{

	
	/**
	 * This method clicks on the element of which user has provided control property.
	 * @param controlproperty control on which we want to click.
	 * @throws Exception 
	 */
	public void click(String controlproperty) throws Exception 
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.elementToBeClickable(webControl));
				webControl.click();
				Log.info("Clicked on "+getControlDescription(controlproperty));
			} catch (Exception e) {
				Log.error(getControlDescription(controlproperty)+" Control is not visible to click");
				throw e;
		 }
	}
		else{
			takeScreenShot();
			Log.error(getControlDescription(controlproperty)+" Control is not visible.");
		}
	}
	
	
	/**
	 * This method clicks on the element of which user has provided locator property.
	 * @param controlproperty control on which we want to click.
	 * @throws Exception 
	 */
	public void click(By locator) throws Exception 
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.elementToBeClickable(webControl));
				webControl.click();
				
			} catch (Exception e) {
				throw e;
		 }
	}
		else{
			takeScreenShot();
			Log.error("Control is not visible.");
		}
	}
	
	
	/**
	 * This method type into the text box of given control property and input as text in text box.
	 * @param controlproperty control in which you need to enter data.
	 * @param inputtext data which you want to enter in text box.
	 * @throws Exception
	 */
	public void type(String controlproperty, String inputtext) throws Exception 
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				webControl.sendKeys(inputtext);
				Log.info("Type '"+inputtext+"' in "+" text box "+getControlDescription(controlproperty));
				takeScreenShot();
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(controlproperty)+" Control is not visible to type");
				throw e;
			}
		}
	
		else{Log.error(getControlDescription(controlproperty)+" Web Control is null.");}
	}
	
	
	
	
	/**
	 * This method type into the text box of given control property and input as text in text box.
	 * @param controlproperty control in which you need to enter data.
	 * @param inputtext data which you want to enter in text box.
	 * @throws Exception
	 */
	public void type(By locator, String inputtext) throws Exception 
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				webControl.sendKeys(inputtext);
				Log.info("Type '"+inputtext+"' in "+" text box.");
				takeScreenShot();
			} catch (Exception e) {
				takeScreenShot();
				Log.error("Control is not visible to type");
				throw e;
			}
		}
	
		else{Log.error("Web Control is null.");}
	}
	
	
	/**
	 * This method read text from label/error message/button and returns the same.
	 * @param controlproperty control of which user need to get text.
	 * @return text as String of the given control.
	 */
	public String readText(String controlproperty)
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				String str =webControl.getText().trim();
				Log.info("Read text from "+ getControlDescription(controlproperty)+ "Text= "+str);
				return str;
				
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(controlproperty)+" Control is not visible to read text");
			}
		}
	else{
			Log.error(getControlDescription(controlproperty)+" Web Control is not visible.");
		}
		return null;
	}
	
	
	
	/**
	 * This method read text from label/error message/button and returns the same.
	 * @param controlproperty control of which user need to get text.
	 * @return text as String of the given control.
	 */
	public String readText(By locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				String str =webControl.getText().trim();
				Log.info("Read text is : "+str);
				return str;
				
			} catch (Exception e) {
				takeScreenShot();
				Log.error("Control is not visible to read text");
			}
		}
	else{
			Log.error("Web Control is not visible.");
		}
		return null;
	}
	
	
	
	/**
	 * This method gets value from text box and returns the same. 
	 * @param controlproperty control property of text box off which you want to fetch data.
	 * @return value as String inside text box.
	 * @throws Exception
	 */
	public String readvalue(String controlproperty) throws Exception
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				String str = webControl.getAttribute("value").trim();
				Log.info("Read value from "+ getControlDescription(controlproperty)+ "textbox. Value= "+str);
				return str;
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(controlproperty)+" textbox is not visible to read text from it.");
				throw e;
			}
		}
	else{Log.error(getControlDescription(controlproperty)+" Web Control is null.");}
		return null;
	}
	
	
	
	/**
	 * This method gets value from text box and returns the same. 
	 * @param controlproperty control property of text box off which you want to fetch data.
	 * @return value as String inside text box.
	 * @throws Exception
	 */
	public String readvalue(By locator) throws Exception
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				String str = webControl.getAttribute("value").trim();
				Log.info("Read value from textbox. Value= "+str);
				return str;
			} catch (Exception e) {
				takeScreenShot();
				Log.error("textbox is not visible to read text from it.");
				throw e;
			}
		}
	else{Log.error("Web Control is null.");}
		return null;
	}
	
	
	/**
	 * This method selects value from drop down. This method is applicable for drop down with select tag.
	 * @param dropdownPropertyName control property of drop down.
	 * @param selection Value value to be selected.
	 * @throws Exception
	 */
	public void selectFromDropdown(String dropdownPropertyName, String selectionValue) throws Exception
	{
		WebElement webControl =  getWebElement(getLocator(dropdownPropertyName));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				Select se = new Select(webControl);
				se.selectByValue(selectionValue);
				Log.info("Selected '"+selectionValue+"' from dropdown "+getControlDescription(dropdownPropertyName));
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(dropdownPropertyName)+" Control is not visible to type");
				throw e;
			}
		}
		else{Log.debug(getControlDescription(dropdownPropertyName)+" Web Control is null.");}
	}
	
	
	
	
		
	/**
	 * This method returns List of drop down items in String format.
	 * @param dropdownPropertyName control property of the drop down.
	 * @return List of String having drop down items.
	 * @throws Exception
	 */
	public List<String> getDropdownItems(String dropdownPropertyName) throws Exception
	{
		List<String> strList = new ArrayList<String>();
		WebElement webControl =  getWebElement(getLocator(dropdownPropertyName));
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				Select se = new Select(webControl);
				List<WebElement> drpdown = se.getOptions();
				for(WebElement we: drpdown)
				{
					strList.add(we.getText());	
				}
				
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(dropdownPropertyName)+" Control is not visible to type");
				throw e;
			}
		}
		else{
			Log.debug(getControlDescription(dropdownPropertyName)+" Web Control is null.");
		}
		
		return strList;
	}
	
	
	
	/**
	 * This method returns list of WebElement for given control property path.
	 * @param propertyName control property for which you wants findElements.
	 * @return
	 */
	public List<WebElement> findGroup(String propertyName)
	{
		List<WebElement>li =driver.findElements(getLocator(propertyName));
		return li;	
	}
	
	
	/**
	 * This method returns whether element is displayed on the page or not in boolean format.
	 * @param controlproperty control property for which you want to verify displaying or not.
	 * @return true if visible. false if not visible. 
	 */
	public boolean isDisplayed(String controlproperty)
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		{
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isDisplayed();
				} catch (Exception e) {
					return false;
				}
		}
	else{
			Log.info(getControlDescription(controlproperty)+" Web Control is not visible.");
		}
		return false;
	}

	
	
	/**
	 * This method returns whether element is displayed on the page or not in boolean format.
	 * @param controlproperty control property for which you want to verify displaying or not.
	 * @return true if visible. false if not visible. 
	 */
	public boolean isDisplayed(By locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isDisplayed();
				} catch (Exception e) {
					return false;
				}
		}
	else{
			Log.info("Web Control is not visible.");
		}
		return false;
	}

	


	/**
	 * This method returns whether check box is selected or not in boolean.
	 * @param controlproperty control property for which you want to verify selected or not.
	 * @return true if selected. false if not selected. 
	 */
	public boolean isSelected(String controlproperty)
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		 {
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isSelected();
				} catch (Exception e) {
					return false;
				}
		 }
	else{
			Log.info(getControlDescription(controlproperty)+" Web Control is not visible.");
		}
		return false;
	}

	
	/**
	 * This method returns whether check box is selected or not in boolean.
	 * @param controlproperty control property for which you want to verify selected or not.
	 * @return true if selected. false if not selected. 
	 */
	public boolean isSelected(By locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		 {
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isSelected();
				} catch (Exception e) {
					return false;
				}
		 }
	else{
			Log.info("Web Control is not visible.");
		}
		return false;
	}

	
	
	
	/**
	 * This method returns whether button is enabled or not in boolean format.
	 * @param controlproperty control property for which you want to verify enabled or not.
	 * @return true if enabled. false if not enabled. 
	 */
	public boolean isEnabled(String controlproperty)
	{
		WebElement webControl =  getWebElement(getLocator(controlproperty));
		if(webControl!=null)
		 {
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isEnabled();
				} catch (Exception e) {
					return false;
				}
		 }
	else{
			Log.info(getControlDescription(controlproperty)+" Web Control is not visible.");
		}
		return false;
	}
	
	
	/**
	 * This method returns whether button is enabled or not in boolean format.
	 * @param controlproperty control property for which you want to verify enabled or not.
	 * @return true if enabled. false if not enabled. 
	 */
	public boolean isEnabled(By locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		 {
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isEnabled();
				} catch (Exception e) {
					return false;
				}
		 }
		else{
			Log.info("Web Control is not visible.");
		}
		return false;
	}
	
	
	/**
	 * This method verifies whether alert is present or not on the page.
	 * @return true if alert is present. false if alert is not present.
	 */
	public boolean isAlertDisplayed()
	{
		try{ 
			driver.switchTo().alert();
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	
	
	
	/**
	 * This method moves cursor on the given control.
	 * @param propertyName control property of WebElement on which you want to move cursor.
	 * @throws Exception
	 */
	public void mouseHover(String propertyName) throws Exception
	{
		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(propertyName)).perform();	
	}
	
	
	
	/**
	 * This method moves cursor on the given control.
	 * @param propertyName control property of WebElement on which you want to move cursor.
	 * @throws Exception
	 */
	public void mouseHover(By locator) throws Exception
	{
		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(locator)).perform();	
	}
	
	
	
}
