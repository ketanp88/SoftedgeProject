package core;

import org.openqa.selenium.By;

public class Locator {

	public By locator = null;
	public String controlDescription = "";
	
	public	Locator(By locator , String controlDescription)
	{
		this.locator= locator;
		this.controlDescription =controlDescription;

	}

}
