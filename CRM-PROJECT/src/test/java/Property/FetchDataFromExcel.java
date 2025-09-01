package Property;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcel{
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Abhishek Bisht\\eclipse-workspace\\CRM-PROJECT\\src\\test\\resources\\testScriptData.xlsx");
	
		Workbook book = WorkbookFactory.create(fis);
		 Sheet sheet = book.getSheetAt(0);	
		 
		 int length = sheet.getLastRowNum(); 
				 for(int i = 1; i<=length;i++) {
					 Cell d = sheet.getRow(i).getCell(0);
					System.out.println(d); 
				 }
		 book.close();	
	}
	
}
// edit helps to send data from eclipse to github
