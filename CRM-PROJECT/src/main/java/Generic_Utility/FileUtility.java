package Generic_Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUtility{
	static WebDriver driver;
	public String getDataFromPropertiesFile(String key) throws IOException {
		// step 1 get the java representation object of the physical 
				FileInputStream fis = new FileInputStream("C:\\Users\\Abhishek Bisht\\git\\CRM-PROJECT\\CRM-PROJECT\\src\\test\\resources\\CommonData.properties");
				// step 2 load all the keys using load() of properties class
				Properties pObj= new Properties();
				pObj.load(fis);
				
				// step 3 get the value by passing the keys 
				String value = pObj.getProperty(key);
						return value;
						
	}
	
	public String getDataFromExcelFile(String sheetName , int rowNum, int cellNum) throws IOException {
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Abhishek Bisht\\git\\CRM-PROJECT\\CRM-PROJECT\\src\\test\\resources\\testScriptData.xlsx");

		Workbook book = WorkbookFactory.create(fis1);
		 Sheet sheet = book.getSheetAt(0);	
		 String value = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue()+(int)(Math.random()*9999);
		 return value;
	}
	public int getNumDataFromExcelFile(String sheetName , int rowNum, int cellNum) throws IOException {
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Abhishek Bisht\\git\\CRM-PROJECT\\CRM-PROJECT\\src\\test\\resources\\testScriptData.xlsx");

		Workbook book = WorkbookFactory.create(fis1);
		 Sheet sheet = book.getSheetAt(0);	
		 int value = (int) (sheet.getRow(rowNum).getCell(cellNum).getNumericCellValue()+(int)(Math.random()*9999));
		 return value;
	}
	
	// Synchronization methods 
	public static void impWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	public static void expWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));	
	}
	
	public static void main(String[] args) throws IOException {
		FileUtility futil = new FileUtility();

	    String url = futil.getDataFromPropertiesFile("url");
	    System.out.println("Property File Value: " + url);

	    String excelData = futil.getDataFromExcelFile("Sheet1", 0, 0);
	    System.out.println("Excel File Value: " + excelData);
	}
	
}