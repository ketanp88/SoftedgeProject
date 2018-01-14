package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

public class ReadExcel {
	
	private static Workbook testDataWorkbook = getWorkBook("TestDataPath");
	private static Workbook dataProviderWorkbook = getWorkBook("DataProviderPath");
	private static Workbook objectRepositoryWorkbook = getWorkBook("ObjectRepositoryExcelPath");
	
	
	public static Workbook getWorkBook(String configPath)
	{
		String excelPath  = BaseClass.getProperty(configPath);
        FileInputStream inputStream = null;
		try{
			inputStream = new FileInputStream(excelPath);
			} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("File is not present at location :"+excelPath);
			Log.error("File is not present at location :"+excelPath);
			}	        
		
		Workbook workbook = null;
		try {
			if(excelPath.endsWith("xls"))
			{
				workbook = new HSSFWorkbook(inputStream);
			}
			if(excelPath.endsWith("xlsx"))
			{
				workbook = new XSSFWorkbook(inputStream);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook; 
	}
	
	
	public static String getvalueofCell(Cell cell)
	{
		String value=null;	
		 try {
			switch (cell.getCellType()) {
			 case Cell.CELL_TYPE_STRING:
				 value=cell.getRichStringCellValue().toString();
			     break;
			 case Cell.CELL_TYPE_BOOLEAN:
				 cell.getRichStringCellValue().toString();
			     break;
			 case Cell.CELL_TYPE_NUMERIC:
			     Double db = cell.getNumericCellValue();
			     value= Integer.toString(db.intValue()).trim();
			     break;
			 }
		} catch (Exception e) {
			value=null;
		}
		return value;
	 }
	
	
	
	public static  Map<String, Locator> getObjectRepositoryFromExcel(String sheetName)
	{

		Map<String, Locator> objRepo =new LinkedHashMap<String, Locator>();

				String logicalname = null;
				Sheet sh = objectRepositoryWorkbook.getSheet(sheetName);
				int rowcount = sh.getLastRowNum();

				for (int i = 1; i <= rowcount; i++) 
				{
					logicalname = getvalueofCell(sh.getRow(i).getCell(0));
					if (logicalname != null) {

						String locatortype = getvalueofCell(sh.getRow(i).getCell(1));
						String locatorvalue = getvalueofCell(sh.getRow(i).getCell(2));
						String locatordescription = getvalueofCell(sh.getRow(i).getCell(3));
						
						By locator = BaseClass.getLocator(locatortype, locatorvalue);
						
						objRepo.put(logicalname, new Locator(locator, locatordescription));
						
					} else 
					{
						break;
					}
				}
		
	   return objRepo; 
	}
	
	
	
	public Map<String,String> getTestdata()
	{
		Map<String,String> testdata = new LinkedHashMap<String,String>();
		String title = null;
		String	value =null;
		int columnCount = 0;
		int	noOfSheets = testDataWorkbook.getNumberOfSheets();
		
		for(int k=0; k<noOfSheets;k++)
		{
			Sheet sh = testDataWorkbook.getSheetAt(k);
			int rowcount = sh.getLastRowNum();
			String expTestCaseID = Integer.toString(BaseClass.testCaseId);
			boolean gotData =false;
		for(int i=1; i<=rowcount; i++)
       {	
    	   columnCount = sh.getRow(0).getPhysicalNumberOfCells();
    	   String acttestcaseId = getvalueofCell(sh.getRow(i).getCell(0)); 		
    	   
    	   if(acttestcaseId==null || gotData==true)
		   {
			   break;
		   }
    
		   if(expTestCaseID.equalsIgnoreCase(acttestcaseId))
		   {
			   for(int j=1; j<columnCount;j++)
			   {
				   title = getvalueofCell(sh.getRow(0).getCell(j)); 
				   value = getvalueofCell(sh.getRow(i).getCell(j));	  
				   testdata.put(title, value);   
			   }
			   gotData = true;
			   break;
    	   }
       }
	}
       return testdata; 
 }


public Object[][] getDataProviderData(String sheetName) throws Exception
{
	String[][] arrayExcelData = null;   
    Sheet sh = dataProviderWorkbook.getSheet(sheetName);

    int columnCount = sh.getRow(0).getPhysicalNumberOfCells();
    int rowcount = sh.getLastRowNum();
    
    arrayExcelData = new String[rowcount][columnCount];
    
   for(int i=1; i<=rowcount; i++)
   {
	   for(int j=0; j<columnCount;j++)
	   {
		   arrayExcelData[i-1][j] = getvalueofCell(sh.getRow(i).getCell(j));
	   }
   }	
   return arrayExcelData;
}

}
