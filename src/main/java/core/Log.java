package core;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Reporter;

public class Log {

	 private static Logger Log = Logger.getLogger(Log.class.getName());
	
		 
	 public static void info(String message) 
	 {
		 DOMConfigurator.configure("log4j.xml");
		Log.info(message);
		System.out.println(message);
		Reporter.log(message);
		}
	 
	 public static void warn(String message)
	 {
		 DOMConfigurator.configure("log4j.xml");
	    Log.warn(message);
	    System.out.println(message);
		Reporter.log(message);
	 	}
	 
	 public static void error(String message) 
	 {
		 DOMConfigurator.configure("log4j.xml");
	    Log.error(message);
	    System.out.println(message);
		Reporter.log(message);
		}
	 
	 public static void fatal(String message) 
	 {
		 DOMConfigurator.configure("log4j.xml");
	    Log.fatal(message);
	    System.out.println(message);
		Reporter.log(message);
		}
	 
	 public static void debug(String message) 
	 {
		 DOMConfigurator.configure("log4j.xml");
	    Log.debug(message);
	    System.out.println(message);
		Reporter.log(message);
		}

}
