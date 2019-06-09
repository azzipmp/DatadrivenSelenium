package DataDriven;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class MercuryDataDriven {
	public WebDriver driver;
    public String baseUrl = "http://newtours.demoaut.com";
    public String testdatapath="D:\\Selenium - Java\\Scripts\\DatadrivenSelenium\\Registration.xlsx";
    
     @BeforeTest
   
    public void openApplication() throws InterruptedException
    {
    	//***********************************RUNNING IN FIREFOX *************************************************
		 	   

 		
    	 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
 	 	 driver = new ChromeDriver();
 		
 	//***********************************************************
 driver.get(baseUrl);
	  Thread.sleep(5000);
    }    

    // VERIFYING THE REGISTER LINK
     @Test(priority=0)
     public void SecondTestverifyRegisterLink() throws InterruptedException
    {
    	//regexpected = "Register: Mercury Tours";
    	  driver.findElement(By.linkText("REGISTER")).click();
      	   Thread.sleep(5000);
      	  String actual = driver.getTitle();
      	  String expected="Register: Mercury Tours";
      	 Assert.assertEquals(actual, expected);
   	Reporter.log("Testing the Register link test case sucessfully passed");
    }
 
    // VERIFYING THE USER REGISTRATION
 @Test(priority=1)
   public void SecondTestuserRegistration() throws InterruptedException, IOException 
    {
    	  File fi= new File(testdatapath);
    	  FileInputStream inputstream=new FileInputStream(fi);
    	  
    	  
    	  Workbook wb=new XSSFWorkbook(inputstream);
    	     Sheet sh = wb.getSheet("Sheet1");       
    	  int rowcount= sh.getLastRowNum()-sh.getFirstRowNum();
    	  System.out.println("no of rows:"+ rowcount);
    	  
    	  for (int record=1 ; record<=rowcount;record++)
    	  {
    	  
    	  Row row=sh.getRow(record);
    	 
    	  
    	  driver.findElement(By.name("firstName")).sendKeys(row.getCell(0).getStringCellValue());
	 	  	driver.findElement(By.name("lastName")).sendKeys(row.getCell(1).getStringCellValue());
	 	  	driver.findElement(By.name("phone")).sendKeys(row.getCell(2).getStringCellValue());
	 	  	driver.findElement(By.name("userName")).sendKeys(row.getCell(3).getStringCellValue());
	 	  	driver.findElement(By.name("address1")).sendKeys(row.getCell(4).getStringCellValue());
	 	  	driver.findElement(By.name("address2")).sendKeys(row.getCell(5).getStringCellValue());
	 	  	driver.findElement(By.name("city")).sendKeys(row.getCell(6).getStringCellValue());
	 	  	driver.findElement(By.name("state")).sendKeys(row.getCell(7).getStringCellValue());
	 	  	driver.findElement(By.name("postalCode")).sendKeys(row.getCell(8).toString());
	 	  	Select drpcountry = new Select(driver.findElement(By.name("country")));
	 	    drpcountry.selectByVisibleText(row.getCell(9).getStringCellValue());
	 	   driver.findElement(By.name("email")).sendKeys(row.getCell(10).getStringCellValue());
	 	   driver.findElement(By.name("password")).sendKeys(row.getCell(11).getStringCellValue());
	 	   driver.findElement(By.name("confirmPassword")).sendKeys(row.getCell(12).getStringCellValue());
	 	   driver.findElement(By.name("register")).click();
	 	   Thread.sleep(2000);
	 	   
	 	 String  sActualValue=driver.findElement(By.tagName("Body")).getText();
	 	System.out.println( "check the actual value:"+sActualValue); 
	 	
	 	 String userexpected = "Your user name is "+row.getCell(10).getStringCellValue(); 	 	   
		Assert.assertTrue(sActualValue.contains(userexpected));  
		 System.out.println( "Out put resuts for Test2" + sActualValue.contains(userexpected)  );
		
		 driver.findElement(By.linkText("REGISTER")).click();
    	  
    	  }
		 
	 	 driver.findElement(By.linkText("SIGN-OFF")).click();  
 

 // Validating Logic 2
	 	   
	 	//   String exp ="Your user name is Test@test.com";	 	   
	 	// String  sActualValue=driver.findElement(By.tagName("Body")).getText();	 	 
	 //	System.out.println( "check the actual value:"+sActualValue);
	 	// System.out.println( "check user registrion:"+sActualValue.contains(expected));
	 	//  Assert.assertTrue( sActualValue.contains(sExpected), "Checking the User Registration");
	 	 //  Assert.assertTrue(sActualValue.contains(exp));    	
    }     
    
    @AfterTest
    public void closeApplication()
    {
    	driver.close();
    	driver.quit();
    }
   	  
	
}