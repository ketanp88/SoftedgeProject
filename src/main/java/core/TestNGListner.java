package core;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListner implements ITestListener, IRetryAnalyzer
{ 	

	private  String startTime = null;
	private  String endTime = null; 
	
	public void onTestStart(ITestResult result) 
	{
		String testName = result.getName();
		Log.info("Test Case  '"+ result.getName()+"' is Started");	
		startTime = getTime();
		Log.info("############################# START ###########################");
		
	}

	public void onTestSuccess(ITestResult result) {
		Log.info("Test Case  '"+ result.getName()+"' is Passed");	
		Log.info("############################# END ###########################");
		Log.info("");
		endTime = getTime();
		try {
			GenerateReport.generateHtmlReport(BaseClass.testCaseId, result.getName(), "Pass", startTime, endTime);
			startTime = null;
			endTime = null;
			BaseClass.testCaseId=0;
		} catch (IOException e) {
			
		}
	}

	public void onTestFailure(ITestResult result) {
		Log.info("Test Case '"+ result.getName()+"' is Failed");	
		Log.info("############################# END ###########################");
		Log.info("");
		BaseClass objBase = new BaseClass();
		objBase.errorScreenShot(result.getName());
		endTime = getTime();
		try {
			GenerateReport.generateHtmlReport(BaseClass.testCaseId, result.getName(), "Fail", startTime, endTime);
			startTime = null;
			endTime = null;
			BaseClass.testCaseId=0;
		} catch (IOException e) {
			
		}
	}

	public void onTestSkipped(ITestResult result) {
		Log.info("Test Case '"+ result.getName()+"' is "+result.getStatus());	
		startTime = getTime();
		endTime = getTime();
		try {
			GenerateReport.generateHtmlReport(BaseClass.testCaseId, result.getName(), "Skipped", startTime, endTime);
			startTime = null;
			endTime = null;
			BaseClass.testCaseId=0;
		} catch (IOException e) {
			
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		Log.info("Started running Test suite : "+ context.getName());	
	}

	public void onFinish(ITestContext context) {
		Log.info(context.getName()+" suite execution is completed.");	
		Log.info("############################# Finish ###########################");
		Log.info("");
	}

	
	private String getTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String	time = dateFormat.format(date);
		return time;
	}

	@Override
	public boolean retry(ITestResult arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
