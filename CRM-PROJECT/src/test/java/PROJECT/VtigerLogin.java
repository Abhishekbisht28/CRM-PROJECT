package PROJECT;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VtigerLogin {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open page
        driver.get("http://localhost:8888/index.php?action=Login&module=Users");

        // send username
        WebElement Uname = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div[2]/input"));
        Uname.sendKeys("admin");

        // send password
        WebElement Pwd = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div[4]/input"));
        Pwd.sendKeys("manager");

        // click login
        driver.findElement(By.id("submitButton")).click();

        //  Create a list of companies 
        List<String> companies = new ArrayList<String>();
        companies.add("Black Rock");
        companies.add("Infosys");
        companies.add("TCS");
        companies.add("Google");
        companies.add("Amazon");   

        for (String i : companies) {
            // go to Organizations
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Organizations"))).click();

            // click Create Organization
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Create Organization...']"))).click();

            // enter organization name
            WebElement name = driver.findElement(By.xpath("//input[@name='accountname']"));
            name.sendKeys(i);
            WebElement website = driver.findElement(By.xpath("//input[@name ='website']"));
            website.sendKeys("www." + i + ".com");
            
            WebElement type = driver.findElement(By.name("accounttype"));
            type.click();
            Select select = new Select(type);

            // Select by visible text
            select.selectByVisibleText("Analyst");
            // click Save 
            WebElement saveBtn = driver.findElement(By.xpath("//input[@title='Save [Alt+S]']"));
            saveBtn.click();

            //  Verify organization name
            WebElement orgName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mouseArea_Organization Name")));
            String addedOrgName = orgName.getText();
            WebElement TypeName = driver.findElement(By.id("dtlview_Type"));
            TypeName.getText();
            
            if (addedOrgName.contains(i) ) {
            	if(TypeName.getText().equals("Analyst")) {
                System.out.println("Organization '" + i + "' successfully added.");
            	}
            	else {
                    System.out.println("Organization '" + i + "' created but Type mismatch. " + TypeName);

            	}
            } else {
                System.out.println("Organization '" + i + "' not added.");
            }
        }

        //  Hover on logo and click Sign Out
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
