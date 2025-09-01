package Documents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class doc {
	public static void main(String[] args) throws InterruptedException {
		// directly call method for Login
        WebDriver driver = new ChromeDriver(); 
        
     driver.findElement(By.linkText("Documents")).click();
     driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
     
    String k = "CRM2";
    WebElement tit= driver.findElement(By.name("notes_title"));
    tit.sendKeys(k);
     driver.findElement(By.cssSelector("input[value='T']")).click();
     driver.findElement(By.name("assigned_group_id")).sendKeys("Team Selling");
     driver.findElement(By.name("button")).click();
     
//     for Verification

    String title= driver.findElement(By.id("dtlview_Title")).getText();
    System.out.println("a :"+title);
    System.out.println("b :"+k);
    if (title.equals(k)){
    	System.out.println("Document  created successfully!!!");
    }else {
    System.out.println("Document Not Created Successfully!!!");
    }
//    Signout
     Actions act = new Actions(driver);
    
     WebElement sign = driver.findElement(By.cssSelector("img[style='padding: 0px;padding-left:5px']"));
     
     act.moveToElement(sign).build().perform();
     driver.findElement(By.linkText("Sign Out")).click();
     
       Thread.sleep(5000);
       driver.quit();
}			
	}