package Documents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Documents {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, IOException {
    	// step 1 get the java representation object of the physical 
    			FileInputStream fis = new FileInputStream("\\Users\\Abhishek Bisht\\eclipse-workspace\\CRM-PROJECT\\src\\test\\resources\\properties\\commonData.properties");
    			// step 2 load all the keys using load() of properties class
    			Properties pObj= new Properties();
    			pObj.load(fis);
    			
    			// step 3 get the value by passing the keys 
    			String BROWSER = pObj.getProperty("bro");
    					String URL = pObj.getProperty("url");
    							String USERNAME = pObj.getProperty("un");
    									String PASSWORD = pObj.getProperty("pwd");
    									
    													
    									if (BROWSER.equals("chrome")) {
    										driver = new ChromeDriver();
    									}else if(BROWSER.equals("edge")){
    										driver = new EdgeDriver();
    									}else if(BROWSER.equals("firefox")) {
    										driver = new FirefoxDriver();
    									}else {
    										driver = new ChromeDriver();
    									}    	        
    			driver.manage().window().maximize();
    	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	        // Open page
    	        driver.get(URL);

    	        // send username
    	        WebElement Uname = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div[2]/input"));
    	        Uname.sendKeys(USERNAME);

    	        // send password
    	        WebElement Pwd = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div[4]/input"));
    	        Pwd.sendKeys(PASSWORD);

        // click login
        driver.findElement(By.id("submitButton")).click();

        // click on Documents 
        driver.findElement(By.linkText("Documents")).click();

        // click on add button
        driver.findElement(By.xpath("//img[@alt='Create Document...']")).click();

        // send data on Document name
        WebElement title = driver.findElement(By.name("notes_title"));
        title.sendKeys("Software Testing Course");

        // select Download Type 
        WebElement type = driver.findElement(By.name("filelocationtype"));
        Select sel1 = new Select(type);
        sel1.selectByVisibleText("External ");
        
        // upload file 
        WebElement upload = driver.findElement(By.name("filename"));
        upload.sendKeys("AbhishekBishtResume");
        
        
        // click on save button 
        driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
        
        //  Fetch Document name 
        String Name = driver.findElement(By.id("dtlview_Title")).getAttribute("value");

        // fetch Document name for verification
        String Pname = driver.findElement(By.id("dtlview_Title")).getText();

        // check Document name is correct or not
        if(Pname.equals(Name)) {
            System.out.println(" Document uploaded created: " + Name);
        } else {
            System.out.println(" Document not Uploaded. Expected: " + Pname + " | Found: " + Name);
        }

        // Hover on logo and click Sign Out
        Actions act = new Actions(driver);
        WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        act.moveToElement(logo).perform();
        Thread.sleep(3000);

       driver.findElement(By.linkText("Sign Out")).click();

        Thread.sleep(2000);
        driver.quit();
    }
}
