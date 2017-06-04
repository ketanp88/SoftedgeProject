package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateReport {
	


public static int HeaderCnt=0;
public static int HeaderCntIndex = 0,HeaderCntDetail = 0;

public static String runTime = getDateTime();

 public static void generateHtmlReport(int TCID,String TestCaseName,String Status,String StartTime,String EndTime) throws IOException
 {
	 File file1 = new File(".\\HtmlReports");
     if (!file1.exists()) 
     {
     	file1.mkdirs();
     }
	 
	 	//String Path = ".//HtmlReports//Automation Result_"+runTime+".html";
	 
		File file = new File(".//HtmlReports//Automation Result.html");

		// if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("<html>");
		bw.write("<table width='1000' border='1'>");

         if (HeaderCntIndex == 0)
         {
        	 bw.write("<style type='text/css'>");
        	 bw.write("<!--");
             bw.write(".style1 {");
             bw.write("font-size: x-large;");
             bw.write("font-weight: bold;}");
             bw.write("-->");
             bw.write("</style>");
             bw.write("<p>&nbsp;</p>");
             bw.write("<div align='center'><strong><font size='6'>Automation Test Result</font> </strong></div>");
             bw.write("<p>&nbsp;</p>");
             bw.write("<tr>");
             bw.write("<td width='60'  bgcolor='#33CAFF' font color='black'><div align='center'>TCID</td>");
             bw.write("<td width='200' bgcolor='#33CAFF' font color='black'><div align='center'>TestCase Name </td>");
             bw.write("<td width='100' bgcolor='#33CAFF' font color='black'><div align='center'>Status</td>");
             bw.write("<td width='100' bgcolor='#33CAFF' font color='black'><div align='center'>Start Time </td>");
             bw.write("<td width='100' bgcolor='#33CAFF' font color='black'><div align='center'>End Time </td>");
             bw.write("<td width='100' bgcolor='#33CAFF' font color='black'><div align='center'>Screenshot Path </td>");
             bw.write("</tr>");
             HeaderCntIndex++;
         }

        bw.write("<tr>");
        bw.write("<td width='100'><div align='center'>" + TCID + "</div></td>");
        bw.write("<td width='200'><div align='center'>" + TestCaseName + "</div></td>");

        if(Status.equals("Pass"))
        {
            bw.write("<td width='100'><div align='center'><font color='Green'>" + Status + "</font></div></td>");
        }
        else if(Status.equals("Fail"))
        {
            bw.write("<td width='100'><div align='center'><font color='Red'>" + Status + "</font></div></td>");
        }else if(Status.equals("Skipped"))
        {
        	 bw.write("<td width='100'><div align='center'><font color='Blue'>" + Status + "</font></div></td>");
        }
        
        bw.write("<td width='100'><div align='center'>" + StartTime + "</div></td>");
        bw.write("<td width='100'><div align='center'>" + EndTime + "</div></td>");
        String currentDir = System.getProperty("user.dir");
        String TestCaseID = Integer.toString(BaseClass.testCaseId);
        bw.write("<td width='60'><div align='center'><a href='"+currentDir+"//Screenshots//TCID_"+TestCaseID+"'>"+"Screenshot"+"</a></div></td>");
        bw.write("</tr>");
        bw.write("</table>");
        bw.write("</body>");
        bw.write("</html>");
        bw.close();
 	}

 
 public static String getDateTime()
 {
	 DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
	 Date date = new Date();
	 String dat = dateFormat.format(date); 
	 return dat;
 }
 
 
}
