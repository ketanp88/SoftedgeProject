package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action extends BaseClass{

	
	
	public By getSelector(Locator locator)
	{
		By by = locator.locator;
		return by;
	}
	
	public String getControlDescription(Locator locator)
	{
		String controlDescription = locator.controlDescription;
		return controlDescription;
		
	}
	
	
	/**
	 * This method clicks on the element of which user has provided control property.
	 * @param controlproperty control on which we want to click.
	 * @throws Exception 
	 */
	public void click(Locator locator) throws Exception 
	{
		WebElement webControl =getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				webControl=wait.until(ExpectedConditions.elementToBeClickable(webControl));
				webControl.click();
				Log.info("Clicked on '"+getControlDescription(locator)+"'");
			} catch (Exception e) {
				Log.error("'"+getControlDescription(locator)+"' Control is not visible to click");
				throw e;
			 }
		}
		else{
			takeScreenShot();
			Log.error("'"+getControlDescription(locator)+"' Control is not visible.");
		}
	}
	
	
	/**
	 * This method type into the text box of given control property and input as text in text box.
	 * @param controlproperty control in which you need to enter data.
	 * @param inputtext data which you want to enter in text box.
	 * @throws Exception
	 */
	public void type(Locator locator, String inputText) throws Exception 
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				webControl.sendKeys(inputText);
				Log.info("Type '"+inputText+"' in text box '"+getControlDescription(locator)+"'");
				
			} catch (Exception e) 
			{
				Log.error(getControlDescription(locator)+" Control is not visible to type");
				throw e;
			}
		}
		else{
				Log.error(getControlDescription(locator)+" Web Control is null.");
			}
	}
	
	
	
	/**
	 * This method read text from label/error message/button and returns the same.
	 * @param controlproperty control of which user need to get text.
	 * @return text as String of the given control.
	 */
	public String getText(Locator locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				String str =webControl.getText().trim();
				return str;
				
			} catch (Exception e) {
				Log.error("'"+getControlDescription(locator)+"' Control is not visible to read text");
			}
		}
	else{
			Log.error("'"+getControlDescription(locator)+"' Web Control is not visible.");
		}
		return null;
	}
	
	
	
	/**
	 * This method gets value from text box and returns the same. 
	 * @param controlproperty control property of text box off which you want to fetch data.
	 * @return value as String inside text box.
	 * @throws Exception
	 */
	public String readTextboxValue(Locator locator) throws Exception
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				String str = webControl.getAttribute("value").trim();
				return str;
			} catch (Exception e) {
				Log.error("'"+getControlDescription(locator)+"' textbox is not visible to read text from it.");
				throw e;
			}
		}
	else{
			Log.error(getControlDescription(locator)+" Web Control is null.");
		}
		return null;
	}
	
	
	/**
	 * This method selects value from drop down. This method is applicable for drop down with select tag.
	 * @param dropdownPropertyName control property of drop down.
	 * @param selection Value value to be selected.
	 * @throws Exception
	 */
	public void selectFromDropdownByValue(Locator locator, String selectionValue) throws Exception
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				Select se = new Select(webControl);
				se.selectByValue(selectionValue);
				Log.error("Selected '"+selectionValue+"' from dropdown "+getControlDescription(locator));
			} catch (Exception e) {
				takeScreenShot();
				Log.error(getControlDescription(locator)+" Control is not visible to type");
				throw e;
			}
		}
		else{
				Log.debug(getControlDescription(locator)+" Web Control is null.");
			}
	}
	
	
	
	/**
	 * This method selects value from drop down. This method is applicable for drop down with select tag.
	 * @param dropdownPropertyName control property of drop down.
	 * @param selection Value value to be selected.
	 * @throws Exception
	 */
	public void selectFromDropdownByVisibleText(Locator locator, String selectionValue) throws Exception
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(webControl));
				Select se = new Select(webControl);
				se.selectByVisibleText(selectionValue);
				Log.info("Selected '"+selectionValue+"' from dropdown "+getControlDescription(locator));
			} catch (Exception e) {
				takeScreenShot();
				Log.error("'"+getControlDescription(locator)+"' Control is not visible to type");
				throw e;
			}
		}
		else{
			Log.error("'"+getControlDescription(locator)+"' Web Control is null.");
				
			}
	}
	
	
	/**
	 * This method returns whether element is displayed on the page or not in boolean format.
	 * @param controlproperty control property for which you want to verify displaying or not.
	 * @return true if visible. false if not visible. 
	 */
	public boolean isDisplayed(Locator locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		{
			try {
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.visibilityOf(webControl));
					return webControl.isDisplayed();
				} catch (Exception e) {
					return false;
				}
		}
	else{
		Log.error("'"+getControlDescription(locator)+"' Web Control is not visible.");
		
		}
		return false;
	}

	

	/**
	 * This method returns whether check box is selected or not in boolean.
	 * @param controlproperty control property for which you want to verify selected or not.
	 * @return true if selected. false if not selected. 
	 */
	public boolean isSelected(Locator locator)
	{
		WebElement webControl =  getWebElement(locator);
		if(webControl!=null)
		 {
			try {
					WebDriverWait wait = new WebDriverWait(driver,20);
				boolean isSelected=	wait.until(ExpectedConditions.elementToBeSelected(webControl));
					return isSelected;
				} catch (Exception e) {
					return false;
				}
		 }
	else{
		Log.error("'"+getControlDescription(locator)+"' Web Control is null.");
		}
		return false;
	}

	
	/**
	 * This method returns whether button is enabled or not in boolean format.
	 * @param controlproperty control property for which you want to verify enabled or not.
	 * @return true if enabled. false if not enabled. 
	 */
	public boolean isEnabled(Locator locator)
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
			System.out.println("'"+getControlDescription(locator)+"' Web Control is not visible.");
			Log.info(getControlDescription(locator)+" Web Control is not visible.");
		}
		return false;
	}
	
	

	
	/**
	 * This method moves cursor on the given control.
	 * @param propertyName control property of WebElement on which you want to move cursor.
	 * @throws Exception
	 */
	public void mouseHover(Locator locator) throws Exception
	{
		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(locator)).perform();
		Log.info("Moved cursor on '"+locator.controlDescription+"'");
	}
	
	
	/**
	 * This method moves cursor on the given control.
	 * @param propertyName control property of WebElement on which you want to move cursor.
	 * @throws Exception
	 */
	public void mouseHoverAndClickSubItem(Locator mainMenu, Locator subMenu) throws Exception
	{
		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(mainMenu)).click(getWebElement(subMenu)).perform();
		Log.info("Moved cursor on '"+mainMenu.controlDescription+"' and clicked on '"+subMenu.controlDescription+"'");
	}
	
	
	
	/**
	 * This method select the check box value as per given boolean
	 * @param locator check box locator
	 * @param valueToSet value to be set if true it check the check box if false it de select it.
	 */
	public void selectcheckbox(Locator locator, boolean valueToSet)
	{
		boolean status = isSelected(locator);
		
		if(!(status==valueToSet))
		{
			getWebElement(locator).click();
			Log.info("Check box "+locator.controlDescription+" is selected as "+valueToSet);
		}	
		
	}
	
	
}
