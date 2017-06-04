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
			boolean gotdata =  false;
     
		for(int i=1; i<=rowcount; i++)
       {	
    	   columnCount = sh.getRow(0).getPhysicalNumberOfCells();
    	   if(!gotdata){
    	   for(int j=1; j<columnCount;j++)
    	   {
    		   String acttestcaseId = getvalueofCell(sh.getRow(i).getCell(0)); 		
    		   if(acttestcaseId==null)
    		   {
    			   break;
    		   }
    		   if(expTestCaseID.equalsIgnoreCase(acttestcaseId))
    		   {
    				title = getvalueofCell(sh.getRow(0).getCell(j)); 
					value = getvalueofCell(sh.getRow(i).getCell(j));	  
    			    testdata.put(title, value);
    			    gotdata = true;
    		   }else {break;};
    	   }
    	  }else{break;}
       }
	}
       return testdata; 
 }


	
public static String getvalueofCell(Cell cell)
{
	String value=null;	
	 try {
		switch (cell.getCellType()) {
		 case Cell.CELL_TYPE_STRING:
			 value=cell.getStringCellValue().trim();
		     break;
		 case Cell.CELL_TYPE_BOOLEAN:
		     System.out.print(cell.getBooleanCellValue());
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



public static  Map<String,String> getObjectRepositoryFromExcelFile()
{
	Map<String,String> testdata = new LinkedHashMap<String,String>();
	String logicalname = null;
	String	value =null;
 
	int noOfSheets = objectRepositoryWorkbook.getNumberOfSheets();
	for(int k=0; k<noOfSheets;k++)
	{
		Sheet sh = objectRepositoryWorkbook.getSheetAt(k);
		int rowcount = sh.getLastRowNum();
  
    	for(int i=1; i<=rowcount; i++)
    	{
			        logicalname = getvalueofCell(sh.getRow(i).getCell(0)); 
			        if(logicalname!=null)
			        {
			        	
			String	locatortype = getvalueofCell(sh.getRow(i).getCell(1));
			String	locatorvalue = getvalueofCell(sh.getRow(i).getCell(2));
			String	locatordescription = getvalueofCell(sh.getRow(i).getCell(3));
			
					value=locatortype+"##"+locatorvalue+"##"+locatordescription;
			    	testdata.put(logicalname, value);
			    	value=null;
			    }
			        else{break;}
    	}
	}
   return testdata; 
}

/**
 * This method reads the objects from property file and returns Map.
 * @return Map of Objects
 */
public static Map<String, String> getObjectRepositoryFromPropertyFile()
{
	Map<String, String> objects = new HashMap<String, String>();
	try {
		BaseClass bs = new BaseClass();
		FileInputStream propfile = new FileInputStream(bs.getProperty("objectrepositoryPropertyFilepath"));
		Properties properties = new Properties();
		properties.load(propfile);
		propfile.close();
		Enumeration<Object> enuKeys = properties.keys();
		while (enuKeys.hasMoreElements()) 
		{
			String key = (String) enuKeys.nextElement();
			String value = properties.getProperty(key);
			objects.put(key, value);
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return objects;
}
	


}
