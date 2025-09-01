package Product;

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class product2 {
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
		
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Products")).click();
		
		driver.findElement(By.cssSelector("img[title='Create Product...']")).click();
		
		String p = "Shirt"; 
		WebElement pName = driver.findElement(By.name("productname"));
		pName.sendKeys(p);
		WebElement Vname = driver.findElement(By.name("vendor_name"));
		Vname.sendKeys("Rastogi Vendors");
		WebElement pSheet = driver.findElement(By.name("productsheet"));
		pSheet.sendKeys("88");
		WebElement Manuf = driver.findElement(By.name("productsheet"));
		Manuf.sendKeys("MetBeat Corp");
		driver.findElement(By.cssSelector("input[name='button']")).click();
		
//		verification
		String verPName = driver.findElement(By.id("dtlview_Product Name")).getText();
		if (verPName.equals(p)) {
			System.out.println("Product "+p+ " created successfully!!!");
				}
		else  {
			System.out.println("Product " +p+" could not be created successfully!!!");
		}
		Actions act =new Actions(driver);
		WebElement sign = driver.findElement(By.cssSelector("img[style='padding: 0px;padding-left:5px']"));
		act.moveToElement(sign).build().perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		Thread.sleep(5000);
		driver.quit();
	}
	}