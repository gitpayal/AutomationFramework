/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GroovyScripts



//import com.eviware.soapui.model.testsuite.TestRunner.Status; 
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.Label;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil
import org.apache.poi.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.commons.lang3.RandomStringUtils;
import Utility.Demo;
//import org.json.JSONArray;
public void lexwaregroovy() {
int maxTries = 3;
int countexception =0;
int teststepid = 1;
int str = 1;
String desc;
try {
   WebDriver driver;
  
   workbook = new HSSFWorkbook();
   sheet = workbook.createSheet("Test Result");
   Map<Integer, Demo > testresultdata = new HashMap<Integer,Demo>();

   Date myDate = new Date();
   SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
   String strDate = sm.format(myDate);
   GenerateData genData = new GenerateData();



   File pathfirefox = new File("C:\\Program Files\\Java\\firefox.exe");
   FirefoxProfile profile = new FirefoxProfile();
   driver = new FirefoxDriver(new FirefoxBinary(pathfirefox), profile); 
   driver.get("http://acq-signup.staging.up-next.com/merchants/new");
   sleep(5000)

   Properties titleProp = new Properties()
   
   titleProp.setProperty('title', driver.getTitle())
   System.out.println(titleProp.getProperty('title'))
   
   assertEquals( driver.getTitle(),"Lexware pay" );

  if (driver.getTitle().equals("Lexware pay"))
   {
   Demo d1 = new Demo(str,"Open Lexware SignupPge","User able to open signup page","Pass");
   testresultdata.put(d1.getkey(),d1);
   System.out.println("title is validated")
   
   }
   else
   {
   	 Demo d1 = new Demo(str,"Open SignupPge","User not able to open signup page","Fail");
   	 testresultdata.put(d1.getkey(),d1);
   	  System.out.println("title is incorrect")
   }


  driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"test@test.com");
  titleProp.setProperty('merchant_email',"test@test.com" )
  System.out.println(titleProp.getProperty('merchant_email'))

  driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),titleProp.getProperty('merchant_email'));

  String externalid = genData.generateRandomString(10);
  driver.findElement(By.id("merchant_external_id")).sendKeys(Keys.chord(Keys.CONTROL, "a"),externalid);

  driver.findElement(By.id("merchant_external_id_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),externalid);

  String password = genData.generateRandomAlphaNumeric(10);
  
   driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),password);   

   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),password); 

    sleep(3000);

      driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"test"); 

       sleep(3000);
        String randomnumeric = genData.generateRandomNumber(6);

      driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),randomnumeric);

     sleep(3000);

      driver.findElement(By.id("merchant_control_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),randomnumeric);

           sleep(3000);
            String URL = genData.generateURL(10);

      driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);

            sleep(3000);
           String ran =  genData.generateRandomAlphaNumeric(10);
            String street = "street" + ran;

      driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"),street);

               sleep(3000);

      driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"111");
    sleep(3000);

      driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"54321");

      sleep(3000);

      driver.findElement(By.id("merchant_company_city")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"test city");

       sleep(3000);
      String phonenumber =  genData.generateRandomAlphaNumber(10);
      driver.findElement(By.id("merchant_company_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber);

      
       sleep(3000);

      driver.findElement(By.id("merchant_company_fax")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"1792569569");
   
}
catch(ElementNotVisibleException e)
{
	System.out.println("Exception is "+e);
	//Writeexception(e.toString());
	 Alert alert = driver.switchTo().alert();
      alert.accept(); 
	if (++countexception == maxTries) throw e;    
}

 

}



