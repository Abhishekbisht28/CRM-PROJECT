package Property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property{
	public static void main(String[] args) throws IOException {
		// step 1 get the java representation object of the physical 
		FileInputStream fis = new FileInputStream("C:\\Users\\Abhishek Bisht\\eclipse-workspace\\CRM-PROJECT\\src\\test\\resources\\testScriptData.xlsx");
		// step 2 load all the keys using load() of properties class
		Properties pObj= new Properties();
		pObj.load(fis);
//		
		// step 3 get the value by passing the keys 
		String BROWSER = pObj.getProperty("bro");
				String URL = pObj.getProperty("url");
						String USERNAME = pObj.getProperty("un");
								String PASSWORD = pObj.getProperty("pwd");
		
								System.out.println("BROWSER");
								System.out.println("URL");
								System.out.println("USERNAME");
								System.out.println("PASSWORD");
		
	}
	
}