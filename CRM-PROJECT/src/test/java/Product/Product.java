package Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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

public class Product {
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
								}        driver.manage().window().maximize();
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

        // click on products 
        driver.findElement(By.linkText("Products")).click();

        // click on add button
        driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

        // send data on product name
        WebElement name = driver.findElement(By.name("productname"));
        name.sendKeys("Software Testing Course");

        //  Fetch product name 
        String Name = driver.findElement(By.name("productname")).getAttribute("value");

        // send data on start date
        WebElement Sdate = driver.findElement(By.id("jscal_field_sales_start_date"));
        Sdate.sendKeys("2004/12/20");

        // select product category
        WebElement category = driver.findElement(By.name("productcategory"));
        Select sel = new Select(category);
        sel.selectByVisibleText("Software");

        // send data on end date 
        WebElement Edate = driver.findElement(By.id("jscal_field_sales_end_date"));
        Edate.sendKeys("2024/06/27");

        // select vendor name (new window opens)
        WebElement ven = driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']"));
        ven.click();

        String mainWindow = driver.getWindowHandle();
        Set<String> Windows = driver.getWindowHandles();

        // Switch to child window
        for (String handle : Windows) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                driver.manage().window().maximize();

                // select vendor Patricia
                driver.findElement(By.linkText("Patricia")).click();

                // switch back to main window
                driver.switchTo().window(mainWindow);
                break;
            }
        }

        // select GL Account
        WebElement GL = driver.findElement(By.name("glacct"));
        Select sel1 = new Select(GL);
        sel1.selectByValue("301-Sales-Hardware");

        // click on save button 
        driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();

        // fetch product name for verification
        String Pname = driver.findElement(By.id("dtlview_Product Name")).getText();

        // check product name is correct or not
        if(Name.equals(Pname)) {
            System.out.println(" Product successfully created: " + Pname);
        } else {
            System.out.println(" Product not created. Expected: " + Name + " | Found: " + Pname);
        }
        
        // Hover on logo and click Sign Out
        Actions act = new Actions(driver);
        WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        act.moveToElement(logo).perform();
        Thread.sleep(3000);

        WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
        signOut.click();

        Thread.sleep(2000);
        driver.quit();
    }
}
