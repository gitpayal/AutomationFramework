/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GroovyScripts


/*
@Author : Payal Verma
@Description : .This is test step 1 to open the merchant Signup page in Intrnet explorer
@GroovyTestStepName : "Launch SignUp Page"
*/
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
import Utility.NRICGenerator;
import Utility.PickNRICdata
import GroovyScripts.searchFile;
import executionengine.DriverScript;


public void m1groovy(WebDriver driver) {
int maxTries = 3;
int countexception =0;
int teststepid = 1;
int str = 1;
String desc;
try
{
   //WebDriver driver;
  
   workbook = new HSSFWorkbook();
   sheet = workbook.createSheet("Test Result");
   Map<Integer, Demo > testresultdata = new HashMap<Integer,Demo>();
   Date myDate = new Date();
   SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
   String strDate = sm.format(myDate);
   

    
    Row rowHeader1 = sheet.createRow((short) 0);
   
    rowHeader1.createCell(0).setCellValue("Test Step Id");
    Cell cell1 = rowHeader1.getCell((short)0);
    setHeaderCellStyle(sheet,cell1,workbook);
    
    rowHeader1.createCell(1).setCellValue("Test Description");
    Cell cell2 = rowHeader1.getCell((short)1);
    setHeaderCellStyle(sheet,cell2,workbook);
    
    rowHeader1.createCell(2).setCellValue("Expected Result");
    Cell cell3 = rowHeader1.getCell((short)2);
    setHeaderCellStyle(sheet,cell3,workbook);
    
    rowHeader1.createCell(3).setCellValue("Actual Result");
    Cell cell4 = rowHeader1.getCell((short)3);
    setHeaderCellStyle(sheet,cell4,workbook);

    rowHeader1.createCell(4).setCellValue("Error Details");
    Cell cell5 = rowHeader1.getCell((short)4);
    setHeaderCellStyle(sheet,cell5,workbook);
   
    Row rowHeader = sheet.createRow((short) 1);
    Cell cellHeader1 = rowHeader.createCell((short) 0);
    
    sheet.addMergedRegion(new CellRangeAddress(1,3,0,5));
    cellHeader1.setCellValue("Validate Disclaimer Acceptance - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
    setHeaderCellStyle(sheet,cellHeader1,workbook);

     Row rowHeader2 = sheet.createRow((short) 7);
     Cell cellHeader2 = rowHeader2.createCell((short) 0);
    
    sheet.addMergedRegion(new CellRangeAddress(7,9,0,5));
    cellHeader2.setCellValue("Validate User Account - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
    setHeaderCellStyle(sheet,cellHeader2,workbook);

    Row rowHeader3 = sheet.createRow((short) 16);
    Cell cellHeader3= rowHeader3.createCell((short) 0);
    
    sheet.addMergedRegion(new CellRangeAddress(16,18,0,5));
    cellHeader3.setCellValue("Validate Business Information - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
    setHeaderCellStyle(sheet,cellHeader3,workbook);
    
    Row rowHeader4 = sheet.createRow((short) 34);
    Cell cellHeader4 = rowHeader4.createCell((short) 0);
    sheet.addMergedRegion(new CellRangeAddress(34,36,0,5));
    cellHeader4.setCellValue("Validate Merchant Account Information - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
    setHeaderCellStyle(sheet,cellHeader4,workbook);
    
        
   
 
  // File pathfirefox = new File("C:\\Program Files\\Java\\firefox.exe");
   //FirefoxProfile profile = new FirefoxProfile();
   //driver = new FirefoxDriver(new FirefoxBinary(pathfirefox), profile); 
   //driver.get("http://signup-page.dev.up-next.com/");
   sleep(5000)

   

   if (driver.getTitle().equals("SignupPage"))
   {
   Demo d1 = new Demo(str,"Open SignupPge","User able to open signup page","Pass");
   testresultdata.put(d1.getkey(),d1);
   
   }
   else
   {
   	 Demo d1 = new Demo(str,"Open SignupPge","User not able to open signup page","Fail");
   	 testresultdata.put(d1.getkey(),d1);
   }
  
   sleep(5000)

   driver.findElement(By.name("commit")).click();
   Boolean b = driver.findElement(By.cssSelector("div[class = 'inputs'] span[class='error field_with_errors']")).isDisplayed()
   System.out.println(b);
   if (b)
   {
   	 String kk = driver.findElement(By.cssSelector("div[class = 'inputs'] span[class='error field_with_errors']")).getText();
   	 System.out.println("User accepts the disclaimer :" +kk);
   	 ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('disclaimer[accepts]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
      str = str+1;
     
      Demo d2 = new Demo(str,"Disclaimer CheckBox - SignupPge","User able to Accept Disclaimer checkbox","Pass");
      testresultdata.put(d2.getkey(),d2);
      sleep(1000)
      if(driver.findElement(By.name("commit")).click())
      {
      str = str+1;
      Demo d3= new Demo(str,"Disclaimer Next - Signup Page","User able to click disclaimer Next button","Pass");
      testresultdata.put(d3.getkey(),d3);
      sleep(1000)
      }
      else{
      str = str+1;
      Demo d3= new Demo(str,"Disclaimer Next - Signup Page","User able to click disclaimer Next button","Pass");
      testresultdata.put(d3.getkey(),d3);
      sleep(1000)
      	
      	}
   }
   else
   {
   	str = str+1;
     Demo d2 = new Demo(str,"Disclaimer CheckBox - SignupPge","Disclaimer Acceptance test failure","Fail");
     testresultdata.put(d2.getkey(),d2);
     sleep(1000)
      
   	System.out.println("Disclaimer Acceptance test failure");
   }
  
    driver.findElement(By.linkText("Next")).click()
   sleep(3000)

   if (driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed() || driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
   {
   	 str = str+1;
   	 Demo d4 = new Demo(str,"Email field - SignupPge","Email field must not be blank","Pass");
      testresultdata.put(d4.getkey(),d4);
      sleep(1000)
   	}
   else
   {
   	 str = str+1;
   	 Demo d4 = new Demo(str,"Email field - SignupPge","Email field must not be blank","fail");
      testresultdata.put(d4.getkey(),d4);
      sleep(1000)
      }
   //UserDetailValidation(driver);
   
    str = emailvalidation(driver, testresultdata,str);
   System.out.println("Value of str after email validation "+str);
   GenerateData genData = new GenerateData();
   String URL = genData.generateEmail(20);
   
    str = str+1;
   driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);
   Demo d5 = new Demo(str,"User Account - Signup Page","User is able to check email for corect format","Pass");
   testresultdata.put(d5.getkey(),d5);
   
   sleep(1000)
    str = str+1;
   driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);
   Demo d6 = new Demo(str,"User Account - Signup Page","User  is able to put matching email in email confirmation field","Pass");
  testresultdata.put(d6.getkey(),d6);
  sleep(1000)

  String Password = genData.generateRandomAlphaNumeric(15);
  System.out.println("Password Generated is : " +Password);
      str = str+1;
  driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
  Demo d7 = new Demo(str,"User Account - Signup Page","User  Password format is  correct","Pass");
  
  testresultdata.put(d7.getkey(),d7);
  sleep(1000)


   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
       str = str+1;
   Demo d8 = new Demo(str,"User Account - Signup Page","User  Password confirmation is correct","Pass");
   testresultdata.put(d8.getkey(),d8);
   sleep(1000)

   driver.findElement(By.linkText("Next")).click()
   sleep(1000)
  
while(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed() || driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
 {
  Password = genData.generateRandomAlphaNumeric(15);
  System.out.println("Password Generated is : " +Password);
   
   URL = genData.generateEmail(20);
   
   driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);
 
   
   sleep(1000)
   driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);
 
  sleep(1000)

  
  driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
 

   sleep(1000)
   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
   

   driver.findElement(By.linkText("Next")).click()
   sleep(1000)
 } 

   
str = selectentitytype(driver, str, testresultdata);

while(driver.findElement(By.cssSelector("div[class = 'input select required merchant_legal_form'] span[class='error']")).isDisplayed())
 {
 	str = selectentitytype(driver,str,testresultdata);
 } 
   
sleep(1000)
str = str+1;
Demo d9 = new Demo(str,"Entity Type Dropdown - All Options in Entity type dropdown is correct","Pass","Pass");
testresultdata.put(d9.getkey(),d9);

str = DBAValidation(driver,str,testresultdata);
//driver.findElement(By.id("merchant_company_dba")).sendKeys("Salesman");
str = str+1;
Demo d10 = new Demo(str,"DBA textbox- User is able to select correct DBA","Pass","Pass");
testresultdata.put(d10.getkey(),d10);

//str = str+1;
//Demo d10 = new Demo(str,"Business summary textbox- User is able to select correct business summary","Pass","Pass");
//testresultdata.put(d10.getkey(),d10);

str = categorySubcategory(driver,testresultdata,str);


sleep(1000)
while(driver.findElement(By.cssSelector("div[class = 'input select optional merchant_company_category'] span[class='error']")).isDisplayed()||driver.findElement(By.cssSelector("div[class = 'input select optional merchant_company_type'] span[class='error']")).isDisplayed())
 {
 	str = categorySubcategory(driver,testresultdata,str);
 } 
   
//SSICValidation(driver);
//driver.findElement(By.id("merchant_company_ssic")).sendKeys("47521");

str = str+1;
Demo d11 = new Demo(str,"Business Information - User is able to select correct SSIC","Pass","Pass");
testresultdata.put(d11.getkey(),d11);
sleep(1000)

str = merchantwebsitevalidation(driver,str,testresultdata);
//Start : Merchant Attribute check
Select selectMerchantPrefix = new Select(driver.findElement(By.id("merchant_contact_person_attributes_prefix")));

int iMerchantPrefix =selectMerchantPrefix.getOptions().size();
sleep(1000)

Assert.assertEquals(4, iMerchantPrefix);

sleep(1000)

selectMerchantPrefix.selectByValue("");
Assert.assertEquals("Please Select", selectMerchantPrefix.getFirstSelectedOption().getText()); 

sleep(1000)
selectMerchantPrefix.selectByValue("Mr");
Assert.assertEquals("Mr", selectMerchantPrefix.getFirstSelectedOption().getText()); 
sleep(1000)
selectMerchantPrefix.selectByValue("Ms"); 
Assert.assertEquals("Ms", selectMerchantPrefix.getFirstSelectedOption().getText()); 
sleep(1000)
selectMerchantPrefix.selectByValue("Mrs"); 
Assert.assertEquals("Mrs", selectMerchantPrefix.getFirstSelectedOption().getText()); 

//End : Merchant Attribute check

//Start : Merchant Gender check
Select selectMerchantGender = new Select(driver.findElement(By.id("merchant_contact_person_attributes_gender")));

int iMerchantGender =selectMerchantGender.getOptions().size();
sleep(1000)

Assert.assertEquals(3, iMerchantGender);

sleep(1000)

selectMerchantGender.selectByValue("");
Assert.assertEquals("Please Select", selectMerchantGender.getFirstSelectedOption().getText()); 

sleep(1000)
selectMerchantGender.selectByValue("Male");
Assert.assertEquals("Male", selectMerchantGender.getFirstSelectedOption().getText()); 
sleep(1000)
selectMerchantGender.selectByValue("Female"); 
Assert.assertEquals("Female", selectMerchantGender.getFirstSelectedOption().getText()); 
sleep(1000)

//End : Merchant Gender check





str = str+1;
Demo d12 = new Demo(str,"Business Information - User is able to select correct category","Pass","Pass");
testresultdata.put(d12.getkey(),d12);

str = str+1; System.out.println("str value is " +str)
Demo d13 = new Demo(str,"Business Information - User is able to select correct sub category","Pass","Pass");
testresultdata.put(d13.getkey(),d13);
sleep(1000)

str = merchantfirstlastnamevalidation(driver,testresultdata,str);

sleep(1000)
driver.findElement(By.linkText("Next")).click()
sleep(1000)

str = merchantsveamount(driver,testresultdata,str);

        

Set<String> keyset = testresultdata.keySet();
   System.out.println(testresultdata.keySet());
    int rownum = 4;
    int count = 1;
	
	  for (String key : keyset) {
        Row row = sheet.createRow(rownum++);
        Demo objArr = testresultdata.get(count);
        System.out.println("object array : " +objArr);
       
        int cellnum = 0;
        Cell Cellgetkey = row.createCell(cellnum++);
        Cellgetkey.setCellValue(objArr.getkey());
        setCellStyle(sheet,Cellgetkey,workbook);
        
        Cell cellgetAction = row.createCell(cellnum++);
        cellgetAction.setCellValue(objArr.getAction());
        setCellStyle(sheet,cellgetAction,workbook);
        
        Cell cellgetExpectedResult = row.createCell(cellnum++);
        cellgetExpectedResult.setCellValue(objArr.getExpectedResult());
        setCellStyle(sheet,cellgetExpectedResult,workbook);
        
        Cell cellgetActualResult = row.createCell(cellnum++);
        cellgetActualResult.setCellValue(objArr.getActualResult());
        setCellStyle(sheet,cellgetActualResult,workbook);
        count++
        if (count == 4 || count == 8|| count == 22)
        {
        	rownum = rownum+4;
        }
        
    }




Confirmemail(driver);

FileOutputStream out =new FileOutputStream(new File("TestResult.xls"));   
workbook.write(out);
out.close();
File fileObj = new File("TestResult.xls");
String dirPath = fileObj.getAbsolutePath();
System.out.println("Test Result for M1 sign up page written successfully " );
System.out.println("The path of M1 sign up page test result  is :" +dirPath);
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

public int  emailvalidation(org.openqa.selenium.WebDriver driver, Map<Integer, Demo > testresultdata,int str){

	String email1 = "test";//invalid format
	String email2 = "@example.co12m";// invalid format
	String email3 = ".com";// invalid format
	//String email4 = "_______@example.com";// invalid format
	String email5 = "much.“moreunusual”@example.com";// invalid format
	String email6 = "very.unusual.“@”.unusual.com@example.com";//invalid format
	String email7 = "very.“(),:;<>[]”.VERY.“very@\\ very”.unusual@strange.example.com";// invalid format
	String email8 = "?????@example.com"; //invalid format
	String email9 = "email@111.222.333.44444";// invalid format
	String email4 = "“(),:;<>[]@example.com";//invalid format

	String Password1 = "test"; //minimum length check
	String Password2 = "12345678";// invalid format only numbers
	String Password3 = "@@@@@@@@@@@@@@@@@@@@";// invalid format only special characters
	String Password4 = "test123thisistestthat passwordshiuldnotgobeyondonehundredandtwentyeightcharactersinpassworkfield";// maximum length validation
	String Password5 = "123@12334";// only letters and special characters
	String Password6 = "!!!!!!!!!!!!!!AB";// only special characters and alphabets
     String desc;

	String error = "";

	driver.findElement(By.linkText("Next")).click()
     sleep(1000)

//Start : Blank Email and password validation
      if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should not be blank User must put an email to start signup";
                         Demo d1 = new Demo(str,"User details - Blank field validation",desc,"Pass");
                         testresultdata.put(d1.getkey(),d1);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
        		         desc = "Email field should not be blank User must put an email to start signup"
                        Demo d1 = new Demo(str,"User details - Blank field validation failed",desc,"Pass");
                         testresultdata.put(d1.getkey(),d11);
                         sleep(1000)
                         
        	}

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Password field should not be blank User must put an password to start signup";
                         Demo d2 = new Demo(str,"User details - Blank field validation",desc,"Pass");
                         testresultdata.put(d2.getkey(),d2);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
        		desc ="Password field should not be blank User must put an password to start signup";
                        Demo d2 = new Demo(str,"User details - Blank field validation failed",desc,"Pass");
                        testresultdata.put(d2.getkey(),d2);
                        sleep(1000)
                         
        	}


 //End : Blank Email and password validation       	

 //Start : Invalid and password validation   
 sleep(1000);
         driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email1);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email1);

         	driver.findElement(By.linkText("Next")).click()
     sleep(1000)

       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be valida format. User must put valid email to start signup The value" +email2 +"is invalid format for signup email";
                         Demo d31 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d31.getkey(),d31);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d31 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d31.getkey(),d31);
               sleep(1000)
                         
        	}

        	    sleep(1000);


	
        
        driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email2);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email2);

         	driver.findElement(By.linkText("Next")).click()
     sleep(1000)

       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should not be blank User must put an email to start signup The value" +email2 +"is invalid format for signup email";
                         Demo d3 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d3.getkey(),d3);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d3 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d3.getkey(),d3);
               sleep(1000)
                         
        	}

        	    sleep(1000);


        driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email3);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email3);

     driver.findElement(By.linkText("Next")).click()
     sleep(1000)
     
       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be in correct format User must put an email to start signup The value" +email3 +"is invalid format for signup email";
                         Demo d4 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d4.getkey(),d4);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d4 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d4.getkey(),d4);
               sleep(1000)
                         
        	}

        	    sleep(1000);

        
        	      driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email4);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email4);
        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)

       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be valid format of email The email " +email4+ "is not valid format of email";
                         Demo d5 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d5.getkey(),d5);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
        	
               Demo d6 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d6.getkey(),d6);
               sleep(1000)
                         
        	}

        	    sleep(1000);


       
        driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email5);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email5);
        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)

       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be valid format of email The email " +email5+ "is not valid format of email";
                         Demo d7 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d7.getkey(),d7);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d8 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d8.getkey(),d8);
               sleep(1000)
                         
        	}

        	    sleep(1000);

 	     driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email6);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email6);

        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be valid format of email The email " +email6+ "is not valid format of email";
                         Demo d9 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d9.getkey(),d9);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d9 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Fail");
               testresultdata.put(d9.getkey(),d9);
               sleep(1000)
                         
        	}

        	    sleep(1000);

        	      driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email7);
                driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email7);

       	driver.findElement(By.linkText("Next")).click()
        sleep(1000)
       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                         desc = "Email field should be valid format of email The email " +email7+ "is not valid format of email";
                         Demo d10 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d10.getkey(),d10);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d11 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d11.getkey(),d11);
               sleep(1000)
                         
        	}

        	    sleep(1000);

        driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email8);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email8);

       	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                        desc = "Email field should be valid format of email The email " +email8+ "is not valid format of email";
                         Demo d12 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d12.getkey(),d12);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d13 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d13.getkey(),d13);
               sleep(1000)
                         
        	}

        	    sleep(1000);

        	      driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email9);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email9);
        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)

       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                         desc = "Email field should be valid format of email The email " +email9+ "is not valid format of email";
                         Demo d14 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         testresultdata.put(d14.getkey(),d14);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d14  = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d14.getkey(),d14);
               sleep(1000)
                         
        	}

        	    sleep(1000);


          driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email9);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email9);

     driver.findElement(By.linkText("Next")).click()
     sleep(1000)
       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be valid format of email The email " +email9+ "is not valid format of email";
                         Demo d15 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                         
                        
                         testresultdata.put(d15.getkey(),d15);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
               Demo d16 = new Demo(str,"User details - Invalid format email field validation failed","Email entered should be valid format.User must put an email in valid format to start sign up page","Pass");
               testresultdata.put(d16.getkey(),d16);
               sleep(1000)
                         
        	}

        	    sleep(1000);

        	    //Start : validation for email and email confrmation matching

               String emailmatch1 = "email@test.com";
               String emailmatch2 = "test@email.com";
                       driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),emailmatch1);
        driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),emailmatch2);

       	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
       if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email_confirmation'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Email field should be matching the email confirmation field. " +emailmatch1+ "AND" +emailmatch2+ "are not same";
                         Demo d17 = new Demo(str,"User details - Email and email confirmation should be matching",desc,"Pass");
                         
                        
                         testresultdata.put(d17.getkey(),d17);
                         sleep(1000)
                    	
                    }
        else
         {
        		str = str+1; System.out.println("str value is " +str)
             desc = "Email field is not matching email confirmation " +emailmatch1+ "AND" +emailmatch2+ "are not same";
                         Demo d17 = new Demo(str,"User details - Email and email confirmation should be matching",desc,"Fail");
                              testresultdata.put(d17.getkey(),d17);
               sleep(1000)
                         
        	}

        	    sleep(1000);
        	    
        	    // End : Validation for email and email confirmation matching 

        	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password1);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password1);

       	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                         Demo d18 = new Demo(str,"User details - Password field - minimum length check  ","Password field should be alphanumeric with minimum 8 characters","Pass");
                         testresultdata.put(d18.getkey(),d18);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d18 = new Demo(str,"User details - Password field - minimum length check ","Password field should be alphanumeric with minimum 8 characters","Fail");
                        testresultdata.put(d18.getkey(),d18);
                        sleep(1000)
                         
        	}

      sleep(1000);

      
        	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password2);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password2);
        
        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	Desc = "Password field should be both alphabets and numbers with minimum eight characters. Password : " +Password2+ "contains only numbers." 
                         Demo d19 = new Demo(str,"User details - Password field -Invalid format check",desc,"Pass");
                         testresultdata.put(d19.getkey(),d19);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d19 = new Demo(str,"User details - Password field -Invalid format check","Password field should be both alphabets and numbers with minimum eight characters","Fail");
                        testresultdata.put(d19.getkey(),d19);
                        sleep(1000)
                         
        	}

      sleep(1000);


      
     
        	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password3);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password3);

        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                      	Desc = "Password field should be both alphabets and numbers with minimum eight characters. Password : " +Password2+ "contains only special characters." 
                         Demo d20 = new Demo(str,"User details - Password field -Invalid format check",desc,"Pass");
                          testresultdata.put(d20.getkey(),d20);
                        sleep(1000)
                         
                       
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d20 = new Demo(str,"User details - Invalid format for password  field validation failed","Password field shouuld be alphanumeric","Fail");
                        testresultdata.put(d20.getkey(),d20);
                        sleep(1000)
                         
        	}

      sleep(1000);
      
        	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password4);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password4);

        	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Password field should have maximum length of 128 characters.Password : " +Password4+ " has more than 128 characters."
                         Demo d21 = new Demo(str,"User details - Password field -Maximum length check",desc,"Pass");
                         testresultdata.put(d21.getkey(),d21);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d21 = new Demo(str,"User details - Password field -Maximum length check failed","Password field should nothave more than 128 characters.","Fail");
                        testresultdata.put(d21.getkey(),d21);
                        sleep(1000)
                         
        	}

      sleep(1000);

        	        	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password5);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password5);

       	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                    	desc = "Password field should contain only alphabets and numbers." +Password5+ "has special characters."
                         Demo d22 = new Demo(str,"User details - Password field -Invalid format check",desc,"Pass");
                         testresultdata.put(d22.getkey(),d22);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d22 = new Demo(str,"User details - Password field -Invalid format check failed","Password field should not have special characters","Fail");
                        testresultdata.put(d22.getkey(),d22);
                        sleep(1000)
                         
        	}

      sleep(1000);

              	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password6);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password6);

       	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                         desc = "Password field should contain only alphabets and numbers." +Password5+ "has special characters."
                         Demo d23 = new Demo(str,"User details - Password field -Invalid format check",desc,"Pass");
                         testresultdata.put(d23.getkey(),d23);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d23 = new Demo(str,"User details - Password field -Invalid format check failed","Password field should not have special characters","Fail");
                        testresultdata.put(d23.getkey(),d23);
                        sleep(1000)
                         
        	}

      sleep(1000);

        sleep(1000);

              	    
        driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password6);
        driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password5);

       	driver.findElement(By.linkText("Next")).click()
     sleep(1000)
        

        if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password_confirmation'] span[class='error']")).isDisplayed())
                    {
                    	str = str+1; System.out.println("str value is " +str)
                         desc = "Password field should match Password confirmation  field" +Password5+ "AND" +Password6+ "are not matching."
                         Demo d24 = new Demo(str,"User details - Password field and Password confirmation Match check",desc,"Pass");
                         testresultdata.put(d24.getkey(),d24);
                         sleep(1000)
                    	
                    }
        else
              {
        		str = str+1; System.out.println("str value is " +str)
                        Demo d24 = new Demo(str,"User details - Password field and Password confirmation Match check","Password field should match Password confirmation field","Fail");
                        testresultdata.put(d24.getkey(),d24);
                        sleep(1000)
                         
        	}

      sleep(1000);
      return str;


      
 
  
 //End :  Invalid and password validation   

}
public int categorySubcategory(org.openqa.selenium.WebDriver driver,Map<Integer, Demo > testresultdata,int str ){
	
	//Object of the Dropdownlist
 WebElement drpDwnListcategory = driver.findElement(By.id("merchant_company_category"));
 //Using FindElements to create a List object
 //List <WebElement> weblist = driver.findElements(By.xpath(".//*[@id='drpdwnTopics']/option"));
 //Using Select Class to fetch the count
 Select objSelcategory = new Select(drpDwnListcategory);
 List <WebElement> weblistcategory = objSelcategory.getOptions();
 //Taking the count of items
 int iCntcategory = weblistcategory.size();
 System.out.println("count category : "+iCntcategory);
 //Using Random class to generate random values
 Random numcategory = new Random();
 int iSelectcategory = numcategory.nextInt(iCntcategory);
 System.out.println("select category : "+iSelectcategory );
 //Selecting value from DropDownList
 objSelcategory.selectByIndex(iSelectcategory);
 //Selected Value

sleep(1000);
str = str+1; System.out.println("str value is " +str)
                         desc = "User is able to select one of the categories";
                         Demo dcategory = new Demo(str,"User details - Category Dropdown User is ale to select category",desc,"Pass");
                         testresultdata.put(dcategory.getkey(),dcategory);
                         sleep(1000)
                         

 //Object of the Dropdownlist
 WebElement drpDwnListsubcategory = driver.findElement(By.id("merchant_company_type"));
 //Using FindElements to create a List object
 //List <WebElement> weblist = driver.findElements(By.xpath(".//*[@id='drpdwnTopics']/option"));
 //Using Select Class to fetch the count
 Select objSelsubcategory  = new Select(drpDwnListsubcategory);
 List <WebElement> weblistsubcategory  = objSelsubcategory .getOptions();
 //Taking the count of items
 int iCntsubcategory  = weblistsubcategory.size();
  System.out.println("count subcategory : "+iCntsubcategory);
 //Using Random class to generate random values
 Random numsubcategory  = new Random();
 int iSelectsubcategory  = numsubcategory.nextInt(iCntsubcategory);
  System.out.println("select subcategory : "+iSelectsubcategory );
 //Selecting value from DropDownList
 objSelsubcategory.selectByIndex(iSelectsubcategory);
 //Selected Value
str = str+1; System.out.println("str value is " +str)
                         desc = "User is able to select one of the subcategories";
                         Demo dsubcategory = new Demo(str,"User details - Category Dropdown User is ale to select subcategory",desc,"Pass");
                         testresultdata.put(dsubcategory.getkey(),dsubcategory);
                         
 return str;
sleep(1000);
	}  
public void Confirmemail(org.openqa.selenium.WebDriver driver){
	sleep(6000);
	String url = driver.getCurrentUrl();
	System.out.println("url after submitting the signup merchant request" +url);
	String s1=url+"/confirm";
	System.out.println("url for email confirm" +s1);
	sleep(2000);
	File pathfirefox = new File("C:\\Program Files\\Java\\firefox.exe");
   FirefoxProfile profile = new FirefoxProfile();
   driver = new FirefoxDriver(new FirefoxBinary(pathfirefox), profile); 
   driver.get(s1);
   sleep(2000);
   

   
   File pathfirefoxfinal = new File("C:\\Program Files\\Java\\firefox.exe");
   FirefoxProfile profilefinal = new FirefoxProfile();
   driverfinal = new FirefoxDriver(new FirefoxBinary(pathfirefoxfinal), profilefinal);
   String s2=url+"/documents_submitted?already_uploaded=true"; 
   System.out.println("final URL :" +s2);
     
   driver.get(s2);


	
	
	}   
    


public void setHeaderCellStyle(org.apache.poi.hssf.usermodel.HSSFSheet sheet,org.apache.poi.ss.usermodel.Cell cell,org.apache.poi.hssf.usermodel.HSSFWorkbook wb) {
    CellStyle s = null;
    s = sheet.getWorkbook().createCellStyle();
    cell.setCellStyle(s);
    
    HSSFFont font = wb.createFont();
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    s.setFont(font);
    s.setAlignment(CellStyle.ALIGN_GENERAL);
    s.setBorderBottom(CellStyle.BORDER_THIN);
    s.setBorderLeft(CellStyle.BORDER_THIN);
    s.setBorderRight(CellStyle.BORDER_THIN);
    s.setBorderTop(CellStyle.BORDER_THIN);
    s.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    s.setAlignment(CellStyle.ALIGN_LEFT);
    s.setFont(font);

}

public void setCellStyle(org.apache.poi.hssf.usermodel.HSSFSheet sheet,org.apache.poi.ss.usermodel.Cell cell,org.apache.poi.hssf.usermodel.HSSFWorkbook wb)
{
   CellStyle s = null;
   s = sheet.getWorkbook().createCellStyle();
   cell.setCellStyle(s);
   for (int i=0; i<10; i++){
   sheet.autoSizeColumn(i);
}
  
}

public void errorloginexcel(org.apache.poi.hssf.usermodel.HSSFSheet sheet ,String email, String password, String emailerror,String passworderror,int i)
{
  
  Cell cell;
  Row row = sheet.createRow(i);
   for (int j =0 ; j<4 ;j++){
    
    cell = row.createCell(j);
    if (j == 0)
    {
    cell.setCellValue(email);
    setCellStyle(sheet,cell,workbook);
    }
    else if (j == 1)
    {
    	 cell.setCellValue(password);
    	  setCellStyle(sheet,cell,workbook);
    	}
     else if (j == 2)
    {
    	 cell.setCellValue(emailerror);
    	  setCellStyle(sheet,cell,workbook);
    	}
    	 else if (j == 3)
      {
    	 cell.setCellValue(passworderror);
    	  setCellStyle(sheet,cell,workbook);
    	 }
    	 
    	 
  
  }
  i++;
}




	
   
	

public int DBAValidation(org.openqa.selenium.WebDriver driver ,int str,Map<Integer, Demo > testresultdata)
{
	
     GenerateData genData = new GenerateData();
	String DBA = "";//Blank field validation
	String DBA1 = "123";// numeric value 
	String DBA2 = "testerer!!";
	String DBA3 = "testerer!!";//Invalid format : special characters
	String DBA4 = "This is test for DBA that it should not extend more than 20 chaacters";//maximum length validation
	String DBA5 = "MASTERCARD";// special Banned word check
	String DBAerror;
	String BSerror;
	String BS = "";//Blank field validation
	String BS2 = "1232323";//numeric only : invalid field validation
	String BS1 = "This is test case which test the validations of all fields of signup page.Please provide the description of the nature of your business such as list of services rendered or goods sold. Note that description has to be at least 50 characters long and is an important part in our assessment of your application.";
	driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBA);
                sleep (1000);  
                driver.findElement(By.linkText("Next")).click()
                sleep(1000)
                if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
                {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    	str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not be blank";
                         Demo dba1 = new Demo(str,"User details - DBA field Blank Field validation",desc,"Pass");
                         testresultdata.put(dba1.getkey(),dba1);
                         System.out.println("DBA Field must not be blank : Test passed" +DBAerror)
                 }
                 else
                 {
                    	 System.out.println("DBA Field must not be blank : Test failed" +DBAerror)

                    	str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not be blank. this test is failed";
                         Demo dba1 = new Demo(str,"User details - DBA field Blank Field validation",desc,"Fail");
                         testresultdata.put(dba1.getkey(),dba1);
                         System.out.println("DBA Field must not be blank : Test passed" +DBAerror)

                         
                 }
                sleep (1000);  
                
	   driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBA1);
	      driver.findElement(By.linkText("Next")).click()
                sleep(1000)
	   if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
         {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBA Field must not be numeric : Test passed" +DBAerror)
                    str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not be only numeric. The DBA value :" +DBA1+ "is not correct";
                         Demo dba2 = new Demo(str,"User details - DBA fieldInvalid format validation",desc,"Pass");
                         testresultdata.put(dba2.getkey(),dba2);
                         System.out.println("DBA Field must be alphanumeric: Test passed" +DBAerror)
         }
         else
         {
          str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not be only numeric. The DBA value :" +DBA1+ "is not correct";
                         Demo dba2 = new Demo(str,"User details - DBA fieldInvalid format validation",desc,"Fail");
                         testresultdata.put(dba2.getkey(),dba2);
                 
         }
         sleep (1000);  
        driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBA3);
           driver.findElement(By.linkText("Next")).click()
                sleep(1000)
	   if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
        {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBA Field must not contain special characters : Test passed" +DBAerror)

                     str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not contain special characters. The DBA value :" +DBA3+ "is not correct";
                         Demo dba3 = new Demo(str,"User details - DBA field Invalid format validation",desc,"Pass");
                         testresultdata.put(dba3.getkey(),dba3);
                         System.out.println("DBA Field must be alphanumeric: Test passed" +DBAerror)
                    
         }
         else
         {
         System.out.println("DBA Field must not contain special characters : Test failed" +DBAerror)
          str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not contain special characters. The DBA value :" +DBA3+ "is not correct";
                         Demo dba3 = new Demo(str,"User details - DBA field Invalid format validation",desc,"Fail");
                         testresultdata.put(dba3.getkey(),dba3);
         }
         sleep (1000);  
        driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBA4);
           driver.findElement(By.linkText("Next")).click()
                sleep(1000)
	   if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
        {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBA Field must not exceed 20 charcters: Test passed" +DBAerror)
                     str = str+1; System.out.println("str value is " +str)
                         desc = "DBA Field must not exceed 20 charcters. The DBA value :" +DBA4+ "is more tha 20 characters";
                         Demo dba4 = new Demo(str,"User details - DBA field Maximum length validation",desc,"Pass");
                         testresultdata.put(dba4.getkey(),dba4);
                    
         }
         else
         {
         System.out.println("DBA Field must not exceed 20 characters: Test failed" +DBAerror)
              str = str+1; System.out.println("str value is " +str)
                         desc = "DBA Field must not exceed 20 charcters. The DBA value :" +DBA1+ "is more tha 20 characters";
                         Demo dba4 = new Demo(str,"User details - DBA field Maximum length validation",desc,"Fail");
                         testresultdata.put(dba4.getkey(),dba4);
         }
         sleep (1000);  
        driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBA5);
           driver.findElement(By.linkText("Next")).click()
                sleep(1000)
	   if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
        {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBA Field should contain alphabets: Test passed" +DBAerror)
                            str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not contain special Words. The DBA value :" +DBA5+ "is not allowed";
                         Demo dba5 = new Demo(str,"User details - DBA field Specail word validation",desc,"Pass");
                         testresultdata.put(dba5.getkey(),dba5);
                    
         }
         else
         {
         System.out.println("DBA Field contain alphabets: Test failed" +DBAerror)
          str = str+1; System.out.println("str value is " +str)
                         desc = "DBA field should not contain special Words. The DBA value :" +DBA5+ "is not allowed";
                         Demo dba5 = new Demo(str,"User details - DBA field Special word validation",desc,"Fail");
                         testresultdata.put(dba5.getkey(),dba5);
         }
         
       driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBA5);
       driver.findElement(By.linkText("Next")).click()
      
         
         String DBAfinal = "MONETST"+ genData.generateRandomString(5);
         driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBAfinal);
         driver.findElement(By.linkText("Next")).click()
         driver.findElement(By.id("merchant_business_summary")).sendKeys(Keys.chord(Keys.CONTROL, "a"),BS);
         driver.findElement(By.linkText("Next")).click()
         sleep (1000);  
         if(driver.findElement(By.cssSelector("div[class = 'input text required merchant_business_summary'] span[class='error']")).isDisplayed())
                     {
                     BSerror = driver.findElement(By.cssSelector("div[class = 'input text required merchant_business_summary'] span[class='error']")).getText();
                     System.out.println("Business summary Should not be blank :" +BSerror)
                     sleep(1000)

                       str = str+1; System.out.println("str value is " +str)
                         desc = "Business summary field should not blank";
                         Demo bs1 = new Demo(str,"User details - Business summary field Blank field validation",desc,"Pass");
                         testresultdata.put(bs1.getkey(),bs1);
                     }
                    else
                     {
                          str = str+1; System.out.println("str value is " +str)
                         desc = "Business summary field should not blank";
                         Demo bs1 = new Demo(str,"User details - Business summary field Blank field validation",desc,"Fail");
                         testresultdata.put(bs1.getkey(),bs1);

               	
               	}

	driver.findElement(By.id("merchant_business_summary")).sendKeys(Keys.chord(Keys.CONTROL, "a"),BS1);
	  driver.findElement(By.linkText("Next")).click()
         sleep (1000);
                     if(driver.findElement(By.cssSelector("div[class = 'input text required merchant_business_summary'] span[class='error']")).isDisplayed())
                     {
                        str = str+1; System.out.println("str value is " +str)
                         desc = "Business summary field should be 50 characters long.";
                         Demo bs1 = new Demo(str,"User details - Business summary field Maximum length field validation",desc,"Pass");
                         testresultdata.put(bs1.getkey(),bs1);
                     }
                    else
                     {
                         
                       
                        str = str+1; System.out.println("str value is " +str)
                         desc = "Business summary field should be 50 characters long.";
                         Demo bs1 = new Demo(str,"User details - Business summary field Maximum length field validation",desc,"Fail");
                         testresultdata.put(bs1.getkey(),bs1);

               	
               	}

               	
	driver.findElement(By.id("merchant_business_summary")).sendKeys(Keys.chord(Keys.CONTROL, "a"),BS2);
	  driver.findElement(By.linkText("Next")).click()
         sleep (1000);
                     if(driver.findElement(By.cssSelector("div[class = 'input text required merchant_business_summary'] span[class='error']")).isDisplayed())
                     {
                      BSerror = driver.findElement(By.cssSelector("div[class = 'input text required merchant_business_summary'] span[class='error']")).getText();
                    str = str+1; System.out.println("str value is " +str)
                         desc = "Business summary field should not be numeric value.";
                         Demo bs2 = new Demo(str,"User details - Business summary field Invalid format validation",desc,"Pass");
                         testresultdata.put(bs2.getkey(),bs2);
                     }
                    else
                     {
                         
                            
                           sleep(1000)
                                 str = str+1; System.out.println("str value is " +str)
                         desc = "Business summary field should not be numeric value.";
                         Demo bs2 = new Demo(str,"User details - Business summary field Invalid format validation",desc,"Fail");
                         testresultdata.put(bs2.getkey(),bs2);

               	
               	}

       driver.findElement(By.id("merchant_business_summary")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateStringWithspace(70," "));
         driver.findElement(By.linkText("Next")).click()
         sleep (1000); 
         return str;

	//WriteTestResultdata(testresultdatabi);
}

public void MerchantDBAValidation(org.openqa.selenium.WebDriver driver )
{

String DBA = "";
String DBAerror = "";
Map<Integer, Demo > testresultdatabi = new HashMap<Integer,Demo>(); 
    sheetBIErrorlog = workbook.createSheet("Business Information Error log")
    int BIrowcount = 0;
    Row BIrowErrorlog = sheetBIErrorlog.createRow(BIrowcount);
    BIrowcount++;
    BIrowErrorlog.createCell(0).setCellValue("DBA");
    Cell cellBIErrorlog1 = BIrowErrorlog.getCell((short)0);
    setHeaderCellStyle(sheetBIErrorlog,cellBIErrorlog1,workbook);

    BIrowErrorlog.createCell(1).setCellValue("DBA ERROR");
    Cell cellBIErrorlog2 = BIrowErrorlog.getCell((short)0);
    setHeaderCellStyle(sheetBIErrorlog,cellBIErrorlog2,workbook);
   
FileInputStream file = new FileInputStream( new File("H:\\Test data\\TestData.xlsx"));
XSSFWorkbook Testworkbook = new XSSFWorkbook(file);
XSSFSheet Testdatasheet = Testworkbook.getSheetAt(1);
   Iterator<Row> rowIterator = Testdatasheet.iterator();
   while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                
               switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    DBA = cell.getNumericCellValue();
                    driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getNumericCellValue());
                  
                    if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
                    {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBAerror on page :" +DBAerror)
                     errorloginexcel(sheetBIErrorlog, DBA, DBAerror, "","", BIrowcount);
                       
                   
                    }

                    
                    
                    break;
                case Cell.CELL_TYPE_STRING:
                    
                    DBA = cell.getStringCellValue();
                    driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getStringCellValue());
                 
                  
                    if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
                    {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBAerror on page :" +DBAerror)
                    errorloginexcel(sheetBIErrorlog, DBA, DBAerror, "","", BIrowcount);
                    }
                    break;
                }
               

            
               	 
             BIrowcount++;
            }
            System.out.println("");
            
        }
        file.close();

      
  
  
}
public void BusinessValidation(org.openqa.selenium.WebDriver driver )
{

String DBA = "";
String DBAerror = "";
    sheetBIErrorlog = workbook.createSheet("Business Information Error log")
    int BIrowcount = 0;
    Row BIrowErrorlog = sheetBIErrorlog.createRow(BIrowcount);
    BIrowcount++;
    BIrowErrorlog.createCell(0).setCellValue("DBA");
    Cell cellBIErrorlog1 = BIrowErrorlog.getCell((short)0);
    setHeaderCellStyle(sheetBIErrorlog,cellBIErrorlog1,workbook);

    BIrowErrorlog.createCell(1).setCellValue("DBA ERROR");
    Cell cellBIErrorlog2 = BIrowErrorlog.getCell((short)0);
    setHeaderCellStyle(sheetBIErrorlog,cellBIErrorlog2,workbook);
   
FileInputStream file = new FileInputStream( new File("H:\\Test data\\TestData.xlsx"));
XSSFWorkbook Testworkbook = new XSSFWorkbook(file);
XSSFSheet Testdatasheet = Testworkbook.getSheetAt(1);
   Iterator<Row> rowIterator = Testdatasheet.iterator();
   while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                
               switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    DBA = cell.getNumericCellValue();
                    driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getNumericCellValue());
                  
                    if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
                    {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBAerror on page :" +DBAerror)
                     errorloginexcel(sheetBIErrorlog, DBA, DBAerror, "","", BIrowcount);
                       
                   
                    }

                    
                    
                    break;
                case Cell.CELL_TYPE_STRING:
                    
                    DBA = cell.getStringCellValue();
                    driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getStringCellValue());
                 
                  
                    if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).isDisplayed())
                    {
                    DBAerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_dba'] span[class='error']")).getText();
                    System.out.println("DBAerror on page :" +DBAerror)
                    errorloginexcel(sheetBIErrorlog, DBA, DBAerror, "","", BIrowcount);
                    }
                    break;
                }
               

            
               	 
             BIrowcount++;
            }
            System.out.println("");
            
        }
        file.close();

      
  
  
}
public void UserDetailValidation(org.openqa.selenium.WebDriver  driver)
{
  sheetErrorlog = workbook.createSheet("User detail Error log")
  int rowcount = 0;
  Row rowErrorlog = sheetErrorlog.createRow(rowcount);
  rowcount++;
  rowErrorlog.createCell(0).setCellValue("Email :");
  Cell cellErrorlog1 = rowErrorlog.getCell((short)0);
  setHeaderCellStyle(sheetErrorlog,cellErrorlog1,workbook);

    
  rowErrorlog.createCell(1).setCellValue("Password Input");
  Cell cellErrorlog2 = rowErrorlog.getCell((short)1);
  setHeaderCellStyle(sheetErrorlog,cellErrorlog2,workbook);

    
  rowErrorlog.createCell(2).setCellValue("Email  Input Error message");
  Cell cellErrorlog3 = rowErrorlog.getCell((short)2);
  setHeaderCellStyle(sheetErrorlog,cellErrorlog3,workbook);
  
    
  rowErrorlog.createCell(3).setCellValue("Password Input Error message");
  Cell cellErrorlog4 = rowErrorlog.getCell((short)3);
  setHeaderCellStyle(sheetErrorlog,cellErrorlog4,workbook);
  

  String email = "" ;
  String password = " ";
  String emailerror = "";
  String passworderror = "";

  driver.findElement(By.linkText("Next")).click()
  sleep(1000)
   if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    emailError = driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).getText();
                    System.out.println("Blank Email Error on page : " +emailError)
                    errorloginexcel(sheetErrorlog , email , password , emailError,passworderror,  rowcount);
                   
                    }

   if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                     {
                      passworderror = driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).getText();
                      System.out.println("Blank Password error on page :" +passworderror)
                      errorloginexcel(sheetErrorlog , email , password , emailError,passworderror, rowcount);
                     }


                   

                  
   
   FileInputStream file = new FileInputStream( new File("H:\\Test data\\TestData.xlsx"));
   XSSFWorkbook Testworkbook = new XSSFWorkbook(file);
   XSSFSheet Testdatasheet = Testworkbook.getSheetAt(0);
   Iterator<Row> rowIterator = Testdatasheet.iterator();
   while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 0)
               {
               switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                   System.out.println(cell.getNumericCellValue());
                    email = cell.getNumericCellValue();
                    driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getNumericCellValue());
                    driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getNumericCellValue());
                    if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                    {
                    emailError = driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).getText();
                    System.out.println("Email error on page :" +emailError)
               
                   
                    }

                    
                    
                    break;
                case Cell.CELL_TYPE_STRING:
                     System.out.println(cell.getStringCellValue());
                     email = cell.getStringCellValue();
                     driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getStringCellValue());
                     driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getStringCellValue());
                     if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
                     {
                     emailError =driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).getText();
                     System.out.println("Email error on page :" +emailError)
                     
                     errorloginexcel(sheetErrorlog , email , password , emailError,passworderror,  rowcount);
                     }
                
                     sleep(1000)
                     break;
                }
               }

               else if (cell.getColumnIndex() == 1)
               {
               
               switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    password = cell.getNumericCellValue();
                    driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getNumericCellValue());
                    driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getNumericCellValue());
                    if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                    {
                     passworderror = driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).getText();
                    System.out.println("Password error on page :" +passworderror)
                    
                    }
            
      
                    break;
                case Cell.CELL_TYPE_STRING:
                     System.out.println(cell.getStringCellValue());
                     password = cell.getStringCellValue();
                     driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getStringCellValue());
                     driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),cell.getStringCellValue());
                     if(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed())
                     {
                      passworderror = driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).getText();
                      System.out.println("Password error on page :" +passworderror)
                      errorloginexcel(sheetErrorlog , email , password , emailError,passworderror, rowcount);
                     }
              
                     break;
                }
               	}

               	 
            rowcount++;   	 
            }
            System.out.println("");
            
        }
        file.close();
  
 
	}


public int merchantwebsitevalidation(org.openqa.selenium.WebDriver  driver,int str,Map<Integer, Demo > testresultdata)
{
     String website1 = "";
	String website2 = "www.123";
	String website3 = "www.!!!.com";
	String website4 = "www .com";
	String websiterror = "";
	
	GenerateData gendata = new GenerateData();
  
	
	driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),website1);
	sleep (1000);  
     driver.findElement(By.linkText("Next")).click()
     
                         
     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).isDisplayed())
     {
      websiterror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).getText();
      System.out.println("merchant_website error on page :" +websiterror)
       str = str+1; System.out.println("str value is " +str)
                         desc = "Website field should not blank";
                         Demo ws1 = new Demo(str,"User details - Website textbox Blank field validation",desc,"Pass");
                         testresultdata.put(ws1.getkey(),ws1);
                                  
                      
                     }
                    else
                     {
                         str = str+1; System.out.println("str value is " +str)
                         desc = "Website field should not blank";
                         Demo ws1 = new Demo(str,"User details - Website textbox Blank field validation",desc,"Fail");
                         testresultdata.put(ws1.getkey(),ws1);
                           
                     }
    driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),website2);
	sleep (1000);  
     driver.findElement(By.linkText("Next")).click()
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).isDisplayed())
                    {
                     websiterror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).getText();
                         str = str+1; System.out.println("str value is " +str)
                         desc = "Website textbox  should follow correct website format";
                         Demo ws2 = new Demo(str,"User details - Website textbox Invalid Format validation",desc,"Pass");
                         testresultdata.put(ws2.getkey(),ws2);
                                  
                      
                     }
                    else
                     {
                     	    str = str+1; System.out.println("str value is " +str)
                         desc = "Website textbox  should follow correct website format";
                         Demo ws2 = new Demo(str,"User details - Website textbox Invalid Format validation",desc,"Fail");
                         testresultdata.put(ws2.getkey(),ws2);
                          
                           
                     }
               	   driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),website3);
	sleep (1000);  
     driver.findElement(By.linkText("Next")).click()
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).isDisplayed())
                    {
                     websiterror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).getText();
                     System.out.println("merchant_website format is not correct  :" +websiterror)
                         str = str+1; System.out.println("str value is " +str)
                         desc = "Website textbox  should follow correct website format";
                         Demo ws3 = new Demo(str,"User details - Website textbox Invalid Format validation",desc,"Pass");
                         testresultdata.put(ws3.getkey(),ws3);
                                  
                      
                     }
                    else
                     {
                            str = str+1; System.out.println("str value is " +str)
                         desc = "Website textbox  should follow correct website format";
                         Demo ws3 = new Demo(str,"User details - Website textbox Invalid Format validation",desc,"Fail");
                         testresultdata.put(ws3.getkey(),ws3);  
                     }
                        driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),website4);
	sleep (1000);  
     driver.findElement(By.linkText("Next")).click()
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).isDisplayed())
                    {
                     websiterror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_website'] span[class='error']")).getText();
                     System.out.println("merchant_website error on page :" +websiterror)
                          str = str+1; System.out.println("str value is " +str)
                         desc = "Website textbox  should follow correct website format";
                         Demo ws4 = new Demo(str,"User details - Website textbox Invalid Format validation",desc,"Pass");
                         testresultdata.put(ws4.getkey(),ws4);            
                      
                     }
                    else
                     {
                             str = str+1; System.out.println("str value is " +str)
                         desc = "Website textbox  should follow correct website format";
                         Demo ws2 = new Demo(str,"User details - Website textbox Invalid Format validation",desc,"Fail");
                         testresultdata.put(ws4.getkey(),ws4);  
                     }
                      driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),gendata.generateUrl(20));

               	//WriteTestResultdata( testresultdatabi);
     
	return str;
}

public int merchantfirstlastnamevalidation(org.openqa.selenium.WebDriver  driver,Map<Integer, Demo > testresultdata,int str)
{
 System.out.println("Str calue merchantfirstlastnamevalidation  is" +str)
     String firstname1 = "";
	String firstname2 = "www.123";
	String firstname3 = "test123";
	String firstname4 = "123!!";
	String fserror = "";
	String phonenumber1 = "";
	String phonenumber2 = "test";
	String phonenumber3 = "123";
	String phonenumber4 = "!!12345678";
	String phonenumber5 = "12345678";
	
     String Compstreet1 = "";
     String Compstreet2 = "@12324241";
	String Compstreet3 = "308 Merry Abbey, Coyote Wells, WV, 25604-3199, US, (304) 485-4875";
	String Compstreet4 = "test street 121";

	String housenumber1 = "";
	String housenumber2 = "test";
	String housenumber3 = "1@@2";
	String housenumber4 = "12";

     String postalcode1 = "";
	String postalcode2 = "test";
	String postalcode3 = "1@@2";
	String postalcode4 = "12345";
	String postalcode5 = "123456";

	GenerateData genData = new GenerateData() ;
	String desc;
  
	
	driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname1);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first name error on page : test passed" +fserror)
                      str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should not be blank.";
                         Demo fs1 = new Demo(str,"User details - First Name textbox Blank Field validation",desc,"Pass");
                         testresultdata.put(fs1.getkey(),fs1);
                   
                     }
                    else
                     {
                          System.out.println("merchant_first name error on page : validation failed" +fserror)
                                 str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should not be blank.";
                         Demo fs1 = new Demo(str,"User details - First Name textbox Blank Field validation",desc,"Fail");
                         testresultdata.put(fs1.getkey(),fs1);
                          

               	
               	}
      driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname2);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first name error on page :" +fserror)
                                str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should consist of only letters. the value " +firstname2+ " is not correct.Please check the value";
                         Demo fs2 = new Demo(str,"User details - First Name textbox Invalid format validation",desc,"Pass");
                         testresultdata.put(fs2.getkey(),fs2);
                    
                   
                     }
                    else
                     {
                             System.out.println("merchant_first name error on page : validation failed" +fserror)
                                   str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should consist of only letters. the value " +firstname2+ " is not correct.Please check the value";
                         Demo fs2 = new Demo(str,"User details - First Name textbox Invalid format validation",desc,"Fail");
                         testresultdata.put(fs2.getkey(),fs2);
                          

               	
               	}
      driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname3);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first should not contain numeric character : Test passed" +fserror)
                                 str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should consist of only letters. the value " +firstname3+ " is not correct.Please check the value";
                         Demo fs3 = new Demo(str,"User details - First Name textbox Invalid format validation",desc,"Pass");
                         testresultdata.put(fs3.getkey(),fs3);
                   
                     }
                    else
                     {
                           System.out.println("merchant_first name error on page : validation failed" +fserror)
                                      str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should consist of only letters. the value " +firstname3+ " is not correct.Please check the value";
                         Demo fs3 = new Demo(str,"User details - First Name textbox Invalid format validation",desc,"Fail");
                         testresultdata.put(fs3.getkey(),fs3);

               	}

        driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname4);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first name testing failed :" +fserror)
                                str = str+1; System.out.println("str value is " +str)
                         desc = "First name textbox  should consist of only letters. the value " +firstname4+ " is not correct.Please check the value";
                         Demo fs4 = new Demo(str,"User details - First Name textbox Invalid format validation",desc,"Pass");
                         testresultdata.put(fs4.getkey(),fs4);
                    
                   
                     }
                    else
                     {
                            System.out.println("merchant_first name error on page : test passed" +fserror)
               	}
           String Firstname5 = "MONETST" + genData.generateRandomString(5);
           driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Firstname5);
            str = str+1; System.out.println("str value is " +str)
            Demo d13 = new Demo(str,"Business Information - First name is validated","Pass","Pass");
            testresultdata.put(d13.getkey(),d13);

           driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname1);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name error on page :" +fserror)
                      str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox  should not be blank.";
                         Demo ls1 = new Demo(str,"User details - last Name textbox Blank Field validation",desc,"Pass");
                         testresultdata.put(ls1.getkey(),ls1);
                   
                   
                     }
                    else
                     {
                          
                         System.out.println("merchant last name error on page : validation failed" +fserror)
                         str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox  should not be blank.";
                         Demo ls1 = new Demo(str,"User details - last Name textbox Blank Field validation",desc,"Fail");
                         testresultdata.put(ls1.getkey(),ls1);

               	
               	}
      driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname2);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name error on page :" +fserror)
                      str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox should be alphabetic.The value :" +firstname2 +" is not correct.Please check the value.";
                         Demo ls2 = new Demo(str,"User details - last Name textbox Invalid format validation",desc,"Pass");
                         testresultdata.put(ls2.getkey(),ls2);
                   
                     }
                    else
                     {
                          
                          
                 System.out.println("merchant last name error on page : validation failed" +fserror)
               	         str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox should be alphabetic.The value :" +firstname2 +" is not correct.Please check the value.";
                         Demo ls2 = new Demo(str,"User details - last Name textbox Invalid format validation",desc,"Fail");
                         testresultdata.put(ls2.getkey(),ls2);
               	}
      driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname3);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name should not contain numeric character : Test passed" +fserror)
                              str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox should be alphabetic.The value :" +firstname3 +" is not correct.Please check the value.";
                         Demo ls3 = new Demo(str,"User details - last Name textbox Invalid format validation",desc,"Pass");
                         testresultdata.put(ls3.getkey(),ls3);
                   
                     }
                    else
                     {
                         
                   System.out.println("merchant last name error on page : validation failed" +fserror)
                          str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox should be alphabetic.The value :" +firstname3 +" is not correct.Please check the value.";
                         Demo ls3 = new Demo(str,"User details - last Name textbox Invalid format validation",desc,"Fail");
                         testresultdata.put(ls3.getkey(),ls3);

               	}

        driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),firstname4);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).isDisplayed())
                     {
                    fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_contact_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name testing failed :" +fserror)
                                 str = str+1; System.out.println("str value is " +str)
                         desc = "Last name textbox should be alphabetic.The value :" +firstname3 +" is not correct.Please check the value.";
                         Demo ls4 = new Demo(str,"User details - last Name textbox Invalid format validation",desc,"Pass");
                         testresultdata.put(ls4.getkey(),ls4);
                    
                   
                     }
                    else
                     {
                      
                      System.out.println("merchant last name testing passed :")
                        str = str+1; System.out.println("str value is " +str)
                       desc = "Last name textbox should be alphabetic.The value :" +firstname3 +" is not correct.Please check the value.";
                         Demo ls4 = new Demo(str,"User details - last Name textbox Invalid format validation",desc,"Fail");
                         testresultdata.put(ls4.getkey(),ls4);

               	}
              
             // str = str+1; System.out.println("str value is " +str)
             // Demo d14 = new Demo(str,"Business Information - Last name is validated","Pass","Pass");
             // testresultdata.put(d14.getkey(),d14);
             String lastname = "MONETST" + genData.generateRandomString(10);
          driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),lastname);      	
          sleep(1000);
         driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber1);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not be blank:" +fserror)
                      desc = "merchant phone number should not be blank :" ;
                         str = str+1; System.out.println("str value is " +str)
                      Demo pn1 = new Demo(str,"User details - Merchant phone number textbox -Blank Field validation",desc,"Pass");
                      testresultdata.put(pn1.getkey(),pn1);
                   
                     }
                    else
                     {
                        desc = "merchant phone number should not be blank :" ;
                         str = str+1; System.out.println("str value is " +str)
                      Demo pn1 = new Demo(str,"User details - Merchant phone number textbox -Blank Field validation",desc,"Fail");
                      testresultdata.put(pn1.getkey(),pn1);

               	
               	}
          driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber2);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not be blank:" +fserror)
                         desc = "merchant phone number should should contain only numbers :The value enterred" +phonenumber2+  "  is not correct.";
                        str = str+1; System.out.println("str value is " +str)
                      Demo pn2 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pn2.getkey(),pn2);
                   
                   
                     }
                    else
                     {
                      
                    
                                      desc = "merchant phone number should should contain only numbers :The value enterred" +phonenumber2+  "  is not correct.";
  str = str+1; System.out.println("str value is " +str)
                      Demo pn2 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pn2.getkey(),pn2);

               	}
             driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber3);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not contain special characters:" +fserror)
                     desc = "merchant phone number should should contain only numbers.The value entered" +phonenumber3+  "  is not correct.";
  str = str+1; System.out.println("str value is " +str)
                      Demo pn3 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pn3.getkey(),pn3);
                   
                     }
                    else
                     {
                                   desc = "merchant phone number should should contain only numbers.The value entered" +phonenumber2+  "  is not correct.";
  str = str+1; System.out.println("str value is " +str)
                      Demo pn3 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pn3.getkey(),pn3);
                   

               	
               	}
              driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber4);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not contain special characters:" +fserror)
                             desc = "merchant phone number should should contain only numbers.The value entered" +phonenumber4+  "  is not correct.";
                               str = str+1; System.out.println("str value is " +str)

                      Demo pn4 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pn4.getkey(),pn4);
                   
                     }
                    else
                     {
                            System.out.println("merchant phone number should not contain special characters: Test failed for Phone number " +phonenumber4)
                                    desc = "merchant phone number should should contain only numbers.The value entered" +phonenumber4+  "  is not correct.";
                                      str = str+1; System.out.println("str value is " +str)

                      Demo pn4 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pn4.getkey(),pn4);

               	
               	}


               	driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber5);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number validation failed:" +fserror)
                                       desc = "merchant phone number should should contain only numbers.The value entered" +phonenumber5+  "  is not correct.";
                                         str = str+1; System.out.println("str value is " +str)

                      Demo pn5 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pn5.getkey(),pn5);
                   
                     }
                    else
                     {
                                                   desc = "merchant phone number should should contain only numbers.The value entered" +phonenumber5+  "  is not correct.";
                        str = str+1; System.out.println("str value is " +str)
                      Demo pn5 = new Demo(str,"User details - Merchant phone number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pn5.getkey(),pn5);

               	
               	}

                 sleep(1000)
                 str = str+1; System.out.println("str value is " +str)
                  Demo d15 = new Demo(str,"Business Information - merchant phone number validated successfully:","Pass","Pass");
                           testresultdata.put(d15.getkey(),d15);
                         

         driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomNumber(8));
         driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(5));      	
         sleep(1000);
               
              
     
               //((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[contact_email_matches_account_email]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true}");
       
               sleep(1000)

               //((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[contact_email_matches_account_email]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true}");
       
               

            
                 String document = "Same Contact e-Mail as Login";
                 sleep(1000);
                 new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(., \"" + document + "\")]"))).click();

                  driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Compstreet1);
                  if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_street'] span[class='error']")).isDisplayed())
                     {
                      fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_street'] span[class='error']")).getText();
                        str = str+1; System.out.println("str value is " +str)
                     System.out.println("merchant company street shoud not be blank :" +fserror)
                         desc = "merchant company street shoudd contain English alphabet letters, spaces, dashes, and numbers.It should not be empty. Please put value for it.";

                      Demo cs1 = new Demo(str,"User details - Merchant company street textbox - Blank Field validation",desc,"Pass");
                      testresultdata.put(cs1.getkey(),cs1);
                     
                     }
                    else
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                            desc = "merchant company street shoudd contain English alphabet letters, spaces, dashes, and numbers.It should not be empty. Please put value for it.";

                      Demo cs1 = new Demo(str,"User details - Merchant company street textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(cs1.getkey(),cs1);

               	
               	}

                  driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Compstreet2);
                  if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_street'] span[class='error']")).isDisplayed())
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                      fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_street'] span[class='error']")).getText();
                     System.out.println("merchant company street shoud not contain special characters :Test Passed" +fserror)
                     desc = "merchant company street shoud contain English alphabet letters, spaces, dashes, and numbers The value entered  " +Compstreet2+  "  is not correct.";

                      Demo cs1 = new Demo(str,"User details - Merchant company street textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(cs1.getkey(),cs1);
                     
                     }
                    else
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                     	    desc = "merchant company street shoud contain English alphabet letters, spaces, dashes, and numbers The value entered  " +Compstreet2+  "  is not correct.";

                      Demo cs1 = new Demo(str,"User details - Merchant company street textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(cs1.getkey(),cs1);
                        

               	}

                   driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Compstreet3);
                  if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_street'] span[class='error']")).isDisplayed())
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                     	 
                            fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_street'] span[class='error']")).getText();
                           System.out.println("merchant company street shoud not contain special characters :Test Passed" +fserror)
                               desc = "merchant company street shoud contain English alphabet letters, spaces, dashes, and numbers The value entered  " +Compstreet3+  "  is not correct.";

                      Demo cs2 = new Demo(str,"User details - Merchant company street textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(cs2.getkey(),cs2);
                     
                     
                     
                     }
                    else
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                     	            desc = "merchant company street shoud contain English alphabet letters, spaces, dashes, and numbers The value entered  " +Compstreet3+  "  is not correct.";

                      Demo cs2 = new Demo(str,"User details - Merchant company street textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(cs2.getkey(),cs2);
                          
                     
               	}

              // driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateStringWithAllobedSplChars(25," _,"));
               
               	

               	 driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomString(10));
              
                
                     
                driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),housenumber1);
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).getText();
                     System.out.println("merchant company house number error on page :" +fserror)

                      desc = "merchant company house number should not be empty.Please put correct house number";
                         str = str+1; System.out.println("str value is " +str)
                      Demo hn2 = new Demo(str,"User details - merchant company house number textbox -Blank Field validation",desc,"Pass");
                      testresultdata.put(hn2.getkey(),hn2);
                     
                     
                    
                     }
                    else
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                   
                                desc = "merchant company house number should not be empty.Please put correct house number";

                      Demo hn2 = new Demo(str,"User details - merchant company house number textbox -Blank Field validation",desc,"Fail");
                      testresultdata.put(hn2.getkey(),hn2);
                    
                         

               	
               	}

               	 driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),housenumber2);
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).getText();
                     System.out.println("Contact Address Unit number error on page :" +fserror)
                         desc = "Contact Address Unit number should be numeric integer .The value "+housenumber2+" is not correct.Please put correct house number";
                       str = str+1; System.out.println("str value is " +str)
                      Demo hn3 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(hn3.getkey(),hn3);
                    
                     }
                    else
                    {
                      str = str+1; System.out.println("str value is " +str)

                           desc = "Contact Address Unit number should be numeric integer .The value "+housenumber2+" is not correct.Please put correct house number";

                      Demo hn3 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(hn3.getkey(),hn3);
                         

               	
               	}

                   driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),housenumber3);
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).isDisplayed())
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).getText();
                     System.out.println("merchant company house number error on page :" +fserror)
                     desc = "Contact Address Unit number should be numeric integer .The value "+housenumber3+" is not correct.Please put correct house number";

                      Demo hn4 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(hn4.getkey(),hn4);
                         
                     }
                    else
                     {
                           str = str+1; System.out.println("str value is " +str)
                           
                                   desc = "Contact Address Unit number should be numeric integer .The value "+housenumber3+" is not correct.Please put correct house number";

                      Demo hn4 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(hn4.getkey(),hn4);

               	
               	}

               	 driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),housenumber4);
                     if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_house_number'] span[class='error']")).getText();
                     System.out.println("merchant company house number error on page :" +fserror)
                               desc = "Contact Address Unit number should be numeric integer .The value "+housenumber4+" is not correct.Please put correct house number";
                        str = str+1; System.out.println("str value is " +str)
                      Demo hn5= new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(hn5.getkey(),hn5);
                    
                     }
                    else
                     {
                                  desc = "Contact Address Unit number should be numeric integer .The value "+housenumber4+" is not correct.Please put correct house number";
                        str = str+1; System.out.println("str value is " +str)
                      Demo hn5= new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(hn5.getkey(),hn5);

               	}
               	str = str+1; System.out.println("str value is " +str)
                     Demo d17 = new Demo(str,"Business Information - merchant company house number validated successfuly ","Pass","Pass");
                     testresultdata.put(d17.getkey(),d17);
                    
                    driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), postalcode1);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).isDisplayed())
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).getText();
                     System.out.println("merchant company postal code can not be blank :" +fserror)
                               desc = "Contact Poastal code should be numeric integer .The value "+postalcode1+" is not correct.Please put correct house number";

                      Demo pc1 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pc1.getkey(),pc1);
                      
                     }
                    else
                     {
                     	        System.out.println("merchant company postal code can not be blank :" +fserror)
                               desc = "Contact Poastal code should be numeric integer .The value "+postalcode1+" is not correct.Please put correct house number";
                        str = str+1; System.out.println("str value is " +str)
                      Demo pc1 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pc1.getkey(),pc1);


               	}

               	 driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), postalcode2);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).isDisplayed())
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                     fserror =  driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).getText();
                     System.out.println("merchant company postal code should not be alphanumeric :" +fserror)
                                   desc = "Contact Poastal code should be numeric integer .The value "+postalcode2+" is not correct.Please put correct house number";

                      Demo pc2 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pc2.getkey(),pc2);
                      
                     }
                    else
                     {
                                         desc = "Contact Poastal code should be numeric integer .The value "+postalcode2+" is not correct.Please put correct house number";
                         str = str+1; System.out.println("str value is " +str)
                      Demo pc2 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pc2.getkey(),pc2);

               	}

               	driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), postalcode3);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).isDisplayed())
                     {
                     fserror= driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).getText();
                     System.out.println("merchant company postal code should not contain special characters :" +fserror)
                       str = str+1; System.out.println("str value is " +str)

                                            desc = "Contact Poastal code should be numeric integer .The value "+postalcode3+" is not correct.Please put correct house number";

                      Demo pc3 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pc3.getkey(),pc3);
                     }
                    else
                     {
                                           desc = "Contact Poastal code should be numeric integer .The value "+postalcode3+" is not correct.Please put correct house number";
                        str = str+1; System.out.println("str value is " +str)
                      Demo pc3 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pc3.getkey(),pc3);

               	}

                    driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), postalcode4);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).isDisplayed())
                     {
                                  fserror= driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).getText();
                     System.out.println("merchant company postal code should not contain special characters :" +fserror)
                       str = str+1; System.out.println("str value is " +str)

                                            desc = "Contact Poastal code should be numeric integer .The value "+postalcode4+" is not correct.Please put correct house number";

                      Demo pc4 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pc4.getkey(),pc4);
                      
                     }
                    else
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                          System.out.println("merchant company postal code should contain six digits :Test Failed " +fserror)
                                                 desc = "Contact Poastal code should be numeric integer .The value "+postalcode4+" is not correct.Please put correct house number";

                      Demo pc4 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pc4.getkey(),pc4);

               	}


                     driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), postalcode5);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_company_postal_code'] span[class='error']")).isDisplayed())
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                       System.out.println("merchant company postal code should contain six digits :Test Failed " +fserror)
                                                       desc = "Contact Poastal code should be numeric integer .The value "+postalcode5+" is not correct.Please put correct house number";

                      Demo pc5 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Pass");
                      testresultdata.put(pc5.getkey(),pc5);

                      
                     }
                    else
                     {
                     	  str = str+1; System.out.println("str value is " +str)
                                                  desc = "Contact Poastal code should be numeric integer .The value "+postalcode5+" is not correct.Please put correct house number";

                      Demo pc5 = new Demo(str,"User details - merchant company house number textbox -Invalid format validation",desc,"Fail");
                      testresultdata.put(pc5.getkey(),pc5);

               	}
               	str = str+1; System.out.println("str value is " +str)
               	 Demo d18= new Demo(str,"Business Information -merchant company postal code validated successfuly ","Pass","Pass");
                     testresultdata.put(d18.getkey(),d18);

          

             driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(6));

          //WriteTestResultdata( testresultdatabi);
              return str; 	

}

public int merchantsveamount(org.openqa.selenium.WebDriver  driver,Map<Integer, Demo > testresultdata,int str)
{
	String sve1 = "";
	String sve2 = "test";
	String sve3 = "1@@2";

	String sve5 = "2000";
	String sveerror;
	String desc;
	GenerateData genData = new GenerateData() ;
	//Map<Integer, Demo > testresultdata = new HashMap<Integer,Demo>();
	driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sve1);
	 sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

     if(driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).isDisplayed())
                     {
                     	
                     sveerror = driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).getText();
                     System.out.println("SVE amount is required :" +sveerror)
                     str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount must not be empy.Please put sve amount for the same."
               	 Demo sv1= new Demo(str,"Business Information - SVE amount merchant - Blank field validation ",desc,"Pass");
                     testresultdata.put(sv1.getkey(),sv1);
                      
                     }
                     else
                     {

                   
                     System.out.println("SVE amount is required : Test failed")
                         desc = "The SVE amount must not be empy.Please put sve amount for the same."
                          str = str+1; System.out.println("str value is " +str)
               	 Demo sv1= new Demo(str,"Business Information - SVE amount merchant - Blank field validation ",desc,"Fail");
                     testresultdata.put(sv1.getkey(),sv1);
                      
                     	}
                     
       sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

     driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sve2);
     if(driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).isDisplayed())
                     {
                     sveerror = driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).getText();
                     System.out.println("SVE amount format is incorrect :" +sveerror)
                       str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount must be integer.The value "+sve2+ " is not numeric. Please put sve amount for the same."
               	 Demo sv2= new Demo(str,"Business Information - SVE amount merchant - Invalid value validation ",desc,"Pass");
                     testresultdata.put(sv2.getkey(),sv2); 
                     }
                     else
                     {
                     
                     System.out.println("SVE amount format error: Test failed")
                     str = str+1; System.out.println("str value is " +str)
               	 Demo sv2= new Demo(str,"Business Information - SVE amount merchant - Invalid value validation ",desc,"Fail");
                     testresultdata.put(sv2.getkey(),sv2);
                     	}
                     
    sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)
          driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sve3);
     if(driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).isDisplayed())
                     {
                     sveerror = driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).getText();
                     System.out.println("SVE amount format error :" +sveerror)
                           str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount must be integer.The value "+sve2+ " is not numeric. Please put sve amount for the same."
               	 Demo sv3= new Demo(str,"Business Information - SVE amount merchant - Invalid value validation ",desc,"Pass");
                     testresultdata.put(sv3.getkey(),sv3); 
                     }
                     else
                     {
                     
                     System.out.println("SVE amount format error: Test failed")
                         str = str+1; System.out.println("str value is " +str)
                               desc = "The SVE amount must be integer.The value "+sve2+ " is not numeric. Please put sve amount for the same."
               	 Demo sv3= new Demo(str,"Business Information - SVE amount merchant - Invalid value validation ",desc,"Fail");
                     testresultdata.put(sv3.getkey(),sv3); 
                     	}
                     
       sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

         

     driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sve3);
     if(driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).isDisplayed())
                     {
                     sveerror = driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).getText();
                     System.out.println("SVE amount format error :" +sveerror)
                         str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount must be integer.The value "+sve3+ " is not numeric. Please put sve amount for the same."
               	 Demo sv4= new Demo(str,"Business Information - SVE amount merchant - Invalid value validation ",desc,"Pass");
                     testresultdata.put(sv4.getkey(),sv4); 
                      
                     }
                     else
                     {
                     
                     System.out.println("SVE amount format error: Test failed")
                              str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount must be integer.The value "+sve3+ " is not numeric. Please put sve amount for the same."
               	 Demo sv4= new Demo(str,"Business Information - SVE amount merchant - Invalid value validation ",desc,"Fail");
                     testresultdata.put(sv4.getkey(),sv4); 
                      
                     	}
                     
  sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

     
   
                     
     sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

      driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sve5);

     if(driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).isDisplayed())
                     {
                     sveerror = driver.findElement(By.cssSelector("div[class = 'input integer required merchant_sve_amount'] span[class='error']")).getText();
                     System.out.println("SVE amount format error :" +sveerror)

                                   str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount value "+sve5+ " is very large. Checkbox message must be selected."
               	 Demo sv5= new Demo(str,"Business Information - SVE amount merchant - checkbox message validation ",desc,"Pass");
                     testresultdata.put(sv5.getkey(),sv5);
                     }
                     else
                     {
                      System.out.println("SVE amount format error: Test failed")
                                                         str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount value "+sve5+ " is very large. Checkbox message must be selected."
               	 Demo sv5= new Demo(str,"Business Information - SVE amount merchant - checkbox message validation ",desc,"Fail");
                     testresultdata.put(sv5.getkey(),sv5);
                     }

          sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

       if(driver.findElement(By.cssSelector("span[class='validationMessage error']")).isDisplayed())
                     {
                     sveerror = driver.findElement(By.cssSelector("span[class='validationMessage error']")).getText();
                     System.out.println("SVE amount checkbox message :" +sveerror)

                                                     str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount value "+sve5+ " is very large. Checkbox message is visible"
               	 Demo sv6= new Demo(str,"Business Information - SVE amount merchant - checkbox message validation ",desc,"Pass");
                     testresultdata.put(sv6.getkey(),sv6);

                     
                      
                     }
                     else
                     {
                      System.out.println("SVE amount checkbox message: Test failed")
                                                                    str = str+1; System.out.println("str value is " +str)
                     desc = "The SVE amount value "+sve5+ " is very large. Checkbox message is visible"
               	 Demo sv6= new Demo(str,"Business Information - SVE amount merchant - checkbox message validation ",desc,"Pass");
                     testresultdata.put(sv6.getkey(),sv6);
                     }
            
     sleep(1000)

    
        
    String documentBusinessOwner1 = "I hereby confirm that I understand that my sales volume is above the established settlement cap and payment of some of the processed funds may be delayed.";
   
     new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(., \"" + documentBusinessOwner1 + "\")]"))).click();
    
     // new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(cssSelector("<tag name>[id*='Same as Contact Details']"))).click();
                   driver.findElement(By.linkText("Next")).click()
sleep(1000)
           str = str+1; System.out.println("str value is " +str)
              Demo d19= new Demo(str,"Business Information - SVE amount is validated ","SVE amount is digit and user is able to click on chekbox","Pass");
             testresultdata.put(d19.getkey(),d19);   

//Start : Companyname

sleep(1000)
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
str = companynameRegistrationValidation(driver,str,testresultdata);

sleep(1000) 
}
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
         {
     	  WebElement element = driver.findElement(By.id("merchant_representative_person_matches_contact"));
     	  JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
                sleep(1000) 
            driver.findElement(By.linkText("Next")).click()
    }

        str = str+1; System.out.println("str value is " +str)     
         Demo d20= new Demo(str,"Business Information - Company name and company registration number is validated ","user is able to choose company name and registration number successfully","Pass");
             testresultdata.put(d20.getkey(),d20);  
//End : Company registration number
//Start : Merchant Attribute check

 sleep(2000) 
System.out.println("merchant_business_person_attributes_prefix");

if (driver.findElement(By.id("merchant_business_person_attributes_prefix")).isDisplayed())
{
Select selectMerchantPrefix = new Select(driver.findElement(By.id("merchant_business_person_attributes_prefix")));

int iMerchantPrefix =selectMerchantPrefix.getOptions().size();
sleep(1000)

Assert.assertEquals(4, iMerchantPrefix);

sleep(1000)

//selectMerchantPrefix.selectByValue("");
//Assert.assertEquals("Please Select", selectMerchantPrefix.getFirstSelectedOption().getText()); 

sleep(1000)
selectMerchantPrefix.selectByValue("Mr");
Assert.assertEquals("Mr", selectMerchantPrefix.getFirstSelectedOption().getText()); 
sleep(1000)
selectMerchantPrefix.selectByValue("Ms"); 
Assert.assertEquals("Ms", selectMerchantPrefix.getFirstSelectedOption().getText()); 
sleep(1000)
selectMerchantPrefix.selectByValue("Mrs"); 
Assert.assertEquals("Mrs", selectMerchantPrefix.getFirstSelectedOption().getText()); 


         str = str+1; System.out.println("str value is " +str)     
         Demo dperfix= new Demo(str,"Business Information - Business Person Attributes Prefix dropdown - Validation ","user is able to select business person attribute from the dropdown successfully","Pass");
         testresultdata.put(dperfix.getkey(),dperfix);  

System.out.println("merchant_business_person_attributes_prefix test completed");
//End : Merchant Attribute check

//Start : Merchant Gender check
Select selectMerchantGender = new Select(driver.findElement(By.id("merchant_business_person_attributes_gender")));
System.out.println("merchant_business_person_attributes_gender test started");
int iMerchantGender =selectMerchantGender.getOptions().size();
sleep(1000)

Assert.assertEquals(3, iMerchantGender);

sleep(1000)
System.out.println("merchant_business_person_attributes_gender test started");
selectMerchantGender.selectByValue("");
Assert.assertEquals("Please Select", selectMerchantGender.getFirstSelectedOption().getText()); 

sleep(1000)
selectMerchantGender.selectByValue("Male");
Assert.assertEquals("Male", selectMerchantGender.getFirstSelectedOption().getText()); 
sleep(1000)
selectMerchantGender.selectByValue("Female"); 
Assert.assertEquals("Female", selectMerchantGender.getFirstSelectedOption().getText()); 
sleep(1000)

//End : Merchant Gender check

  str = str+1; System.out.println("str value is " +str)     
         Demo dgender= new Demo(str,"Business Information - Business Person Gender  dropdown - Validation ","user is able to select business person gender from the dropdown successfully","Pass");
         testresultdata.put(dgender.getkey(),dgender);   

String bpfirstname1 = "";
String bpfirstname2 = "www.123";
String bpfirstname3 = "test123";
String bpfirstname4 = "te";
String bpfserror = "";
String phonenumber1 = "";
	String phonenumber2 = "test";
	String phonenumber3 = "123";
	String phonenumber4 = "!!12345678";
	String phonenumber5 = "1234512222222222232323233333333333333333333333";

	


   Map<Integer, Demo > testresultdataow = new HashMap<Integer,Demo>();
   // GenerateData genData=new GenerateData();
  
	
	driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname1);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first name error on page :" +fserror)

                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person irst name should not be blank.";
                       Demo dfn1= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Pass");
                       testresultdata.put(dfn1.getkey(),dfn1);   
                   
                     }
                    else
                     {
                     	
                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person irst name should not be blank.";
                       Demo dfn1= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Fail");
                       testresultdata.put(dfn1.getkey(),dfn1); 
                
                          

               	
               	}
      driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname2);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first name error on page :" +fserror)
                    
                     
                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person first name should not contain number and special characters.The value" +bpfirstname2+ "is not correct value for first name field.Please check the value.";
                       Demo dfn2= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Pass");
                       testresultdata.put(dfn2.getkey(),dfn2); 
                     }
                    else
                     {
                        str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person first name should not contain number and special characters.The value" +bpfirstname2+ "is not correct value for first name field.Please check the value.";
                       Demo dfn2= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Fail");
                       testresultdata.put(dfn2.getkey(),dfn2); 
                            
                          

               	
               	}
      driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname3);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first should not contain numeric character : Test passed" +fserror)
                        str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person first name should not contain number and special characters.The value" +bpfirstname3+ "is not correct value for first name field.Please check the value.";
                       Demo dfn3= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Pass");
                       testresultdata.put(dfn3.getkey(),dfn3); 
                   
                     }
                    else
                     {
                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person first name should not contain number and special characters.The value" +bpfirstname3+ "is not correct value for first name field.Please check the value.";
                       Demo dfn3= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Fail");
                       testresultdata.put(dfn3.getkey(),dfn3); 
 

               	}

        driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname4);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_first_name'] span[class='error']")).getText();
                     System.out.println("merchant_first name testing failed :" +fserror)

                           str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person first name should not contain number and special characters.The value" +bpfirstname4+ "is not correct value for first name field.Please check the value.";
                       Demo dfn4= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Pass");
                       testresultdata.put(dfn4.getkey(),dfn4); 
                   
                     }
                    else
                     {
                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person first name should not contain number and special characters.The value" +bpfirstname4+ "is not correct value for first name field.Please check the value.";
                       Demo dfn4= new Demo(str,"Business Information - Business Person First name - Validation ",desc,"Pass");
                       testresultdata.put(dfn4.getkey(),dfn4); 
  

               	}
 driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));

         driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname1);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name error on page :" +fserror)

                      str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person Last name should not be blank.";
                       Demo dln1= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Pass");
                       testresultdata.put(dln1.getkey(),dln1);   
                   
                   
                     }
                    else
                     {
                        str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person irst name should not be blank.";
                       Demo dln1= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Fail");
                       testresultdata.put(dln1.getkey(),dln1);   
                            
                          

               	
               	}
      driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname2);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name error on page :" +fserror)
                    
                      str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person Last name should not contain number and special characters.The value" +bpfirstname2+ "is not correct value for first name field.Please check the value.";
                       Demo dln2= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Pass");
                       testresultdata.put(dln2.getkey(),dln2); 
                     }
                    else
                     {
                                      str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person Last name should not contain number and special characters.The value" +bpfirstname2+ "is not correct value for first name field.Please check the value.";
                       Demo dln2= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Fail");
                       testresultdata.put(dln2.getkey(),dln2); 
                          

               	
               	}
      driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname3);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name should not contain numeric character : Test passed" +fserror)
                       str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person Last name should not contain number and special characters.The value" +bpfirstname3+ "is not correct value for first name field.Please check the value.";
                       Demo dln3= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Pass");
                       testresultdata.put(dln3.getkey(),dln3); 
                   
                     }
                    else
                     {
                                 str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person Last name should not contain number and special characters.The value" +bpfirstname3+ "is not correct value for first name field.Please check the value.";
                       Demo dln3= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Fail");
                       testresultdata.put(dln3.getkey(),dln3); 
                   
  

               	}

        driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bpfirstname4);
                     if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).isDisplayed())
                     {
                    fserror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_last_name'] span[class='error']")).getText();
                     System.out.println("merchant last name testing failed :" +fserror)

                      str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person Last name should not contain number and special characters.The value" +bpfirstname4+ "is not correct value for first name field.Please check the value.";
                       Demo dln4= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Pass");
                       testresultdata.put(dln4.getkey(),dln4); 
                   
                     }
                    else
                     {
                
                      str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person Last name should not contain number and special characters.The value" +bpfirstname4+ "is not correct value for first name field.Please check the value.";
                       Demo dln4= new Demo(str,"Business Information - Business Person Last name - Validation ",desc,"Fail");
                       testresultdata.put(dln4.getkey(),dln4); 
  

               	}
     str = str+1; System.out.println("str value is " +str)
      Demo d18 = new Demo(str,"Business Information - Last name validation completed","Pass","Pass");
      testresultdataow.put(d18.getkey(),d18);
      driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));
            
   
    sleep(1000) 

driver.findElement(By.linkText("Next")).click()
sleep(1000)
           driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber1);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not be blank:" +fserror)

                          
                      str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should not be blank.Please provide valid phone number.";
                       Demo dpn1= new Demo(str,"Business Information - Business Person Phone number - Blank field Validation ",desc,"Pass");
                       testresultdata.put(dpn1.getkey(),dpn1); 
                     }
                    else
                     {
                          str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should not be blank.Plesae provide valid phone number.";
                       Demo dpn1= new Demo(str,"Business Information - Business Person Phon number - Blank field Validation ",desc,"Fail");
                       testresultdata.put(dpn1.getkey(),dpn1); 

               	
               	}
          driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber2);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not be blank:" +fserror)

                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person phone number should contain only numeric value. The value " +phonenumber2+ " is not valid vaue for phonenumber.";
                       Demo dpn2= new Demo(str,"Business Information - Business Person Phone number - Invalid format Validation ",desc,"Pass");
                       testresultdata.put(dpn2.getkey(),dpn2); 
                   
                     }
                    else
                     {
                       
                       str = str+1; System.out.println("str value is " +str)     
                       desc = "Business person phone number should contain only numeric value. The value " +phonenumber2+ " is not valid vaue for phonenumber.";
                       Demo dpn2= new Demo(str,"Business Information - Business Person Phone number -  Invalid format Validation ",desc,"Fail");
                       testresultdata.put(dpn2.getkey(),dpn2); 

               	
               	}
             driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber3);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not contain special characters:" +fserror)

                     
                       str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should contain only numeric value. The value " +phonenumber3+ " is not valid value for phonenumber.";
                       Demo dpn3 = new Demo(str,"Business Information - Business Person Phone number -  Invalid format Validation ",desc,"Pass");
                       testresultdata.put(dpn3.getkey(),dpn3); 
                   
                     }
                    else
                     {
                   str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should contain only numeric value. The value " +phonenumber3+ " is not valid vaue for phonenumber.";
                       Demo dpn3 = new Demo(str,"Business Information - Business Person Phone number -  Invalid format Validation ",desc,"Fail");
                       testresultdata.put(dpn3.getkey(),dpn3); 
                         

               	
               	}
              driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber4);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_contact_person_phone_number'] span[class='error']")).getText();
                     System.out.println("merchant phone number should not contain special characters:" +fserror)


                        str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should contain only numeric value. The value " +phonenumber4+ " is not valid value for phonenumber.";
                       Demo dpn4 = new Demo(str,"Business Information - Business Person Phone number -  Invalid format Validation ",desc,"Pass");
                       testresultdata.put(dpn4.getkey(),dpn4); 
                   
                     }
                    else
                     {
                   
                        str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should contain only numeric value. The value " +phonenumber4+ " is not valid value for phonenumber.";
                       Demo dpn4 = new Demo(str,"Business Information - Business Person Phone number -  Invalid format Validation ",desc,"Pass");
                       testresultdata.put(dpn4.getkey(),dpn4); 
                         

               	
               	}


               	driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),phonenumber5);
                     if(driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).isDisplayed())
                     {
                     fserror = driver.findElement(By.cssSelector("div[class = 'input tel required merchant_business_person_phone_number'] span[class='error']")).getText();
               
                     
                        str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should contain only numeric value. The value " +phonenumber5+ " is not valid value for phonenumber.";
                       Demo dpn5 = new Demo(str,"Business Information - Business Person Phone number - Maximum length Validation ",desc,"Pass");
                       testresultdata.put(dpn5.getkey(),dpn5); 
                     }
                    else
                     {
                            str = str+1; System.out.println("str value is " +str)     
                      desc = "Business person phone number should contain only numeric value. The value " +phonenumber5+ " is not valid value for phonenumber.";
                       Demo dpn5 = new Demo(str,"Business Information - Business Person Phone number - Maximum length Validation ",desc,"Fail");
                       testresultdata.put(dpn5.getkey(),dpn5); 
                         

               	
               	}

                 sleep(1000)
               
              driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomNumber(8));

   
    //String nrictestdata = nric_generator();
     nrictestdata = Utility.PickNRICdata();
System.out.println("nric value is " +nrictestdata) 
    driver.findElement(By.id("merchant_business_person_attributes_nric")).sendKeys(Keys.chord(Keys.CONTROL, "a"),nrictestdata);
    sleep(5000)
driver.findElement(By.linkText("Next")).click()
sleep(1000)

 while(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_nric'] span[class='error']")).isDisplayed())
 {
 	
 //	nrictestdata = nric_generator();
 nrictestdata = Utility.PickNRICdata(); 
 
 
    driver.findElement(By.id("merchant_business_person_attributes_nric")).sendKeys(Keys.chord(Keys.CONTROL, "a"),nrictestdata);
    sleep(5000)
driver.findElement(By.linkText("Next")).click()
sleep(1000)
 }


String[] datetest =  new String[6];


 System.out.println(datetest.length);
// Step 2: Array creation:

	 datetest[0] =" ";
	 datetest[1] =" 12";
	datetest[2] =" 12-13-2016";
	 datetest[3] ="22-22-2016";
	datetest[4] ="test";
 datetest[5] ="123455 ";

	for (i= 0;i<6;i++)
	{
	driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
     driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(datetest[i]);
     sleep(1000)
     driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
     driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(datetest[i]);
     sleep(1000)

	
	}
    driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
	driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys("19/08/1986");

	 driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
	 driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys("13/09/2015")

          
   
   


driver.findElement(By.linkText("Next")).click()
sleep(1000)
    


driver.findElement(By.linkText("Next")).click()
sleep(1000)
}
str = str+1; System.out.println("str value is " +str)
Demo d21= new Demo(str,"Business Information - Merchant Business Person attributes successfully validated ","user is able to choose merchant business person attribute successfully","Pass");
testresultdata.put(d21.getkey(),d21); 
System.out.println("Account details");
 str = accountnameValidation(driver,testresultdata,str);

driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateStringWithspace(10, " "));
sleep(2000)


 
//driver.findElement(By.id("merchant_bank_code")).sendKeys("7010 - THE ROYAL BANK OF SCOTLAND N.V.");
//sleep(1000)


//int Value = (new Random()).Next(1, 999);
//driver.FindElement(By.Id("merchant_bank_branch_code")).SendKeys(Keys.chord(Keys.CONTROL, "a"),Value.ToString()); 

//driver.findElement(By.id("merchant_bank_branch_code")).sendKeys("100");
//sleep(1000)
//driver.findElement(By.id("merchant_bank_branch_code_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Value.ToString());
while(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_bank_branch_code'] span[class='error']")).isDisplayed())
 {
str = merchantbankbranchcode(driver,testresultdata,str);
 }             	
sleep(1000)
    str = str+1; System.out.println("str value is " +str)
    Demo d24= new Demo(str,"Account details - Merchant bank branch code successfully validated ","user is able to choose correct bank cranch code","Pass");
       testresultdata.put(d24.getkey(),d24);    



str = accountnumberValidation(driver,testresultdata,str);

//Object of the Dropdownlist
 WebElement drpDwnList = driver.findElement(By.id("merchant_bank_code"));
 //Using FindElements to create a List object
 //List <WebElement> weblist = driver.findElements(By.xpath(".//*[@id='drpdwnTopics']/option"));
 //Using Select Class to fetch the count
 Select objSel = new Select(drpDwnList);
 List <WebElement> weblist = objSel.getOptions();
 //Taking the count of items
 int iCnt = weblist.size();
 //Using Random class to generate random values
 Random num = new Random();
 int iSelect = num.nextInt(iCnt);
 //Selecting value from DropDownList
 objSel.selectByIndex(iSelect);
 //Selected Value
 System.out.println(drpDwnList.getAttribute("value"));
 
driver.findElement(By.linkText("Next")).click()
sleep(1000)


Thread.sleep(1000);
//generateXPATH(element, "");
   
   
    // new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(\"" + documentBusinessOwner2 + "\")]"))).click();

     
//String terms = "I have read the Terms & Conditions of the Merchant Agreement of Wirecard Singapore and hereby confirm my agreement to them.";
//WebElement nameInputField = driver.findElement(By.id("name"));
WebElement element = driver.findElement(By.id("merchant_accepts_terms"));
JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("arguments[0].click();", element);
Thread.sleep(1000);

        
    
   
     //new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(., \"" + terms + "\")]"))).click();
//((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[accepts_terms]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
sleep(1000)

driver.findElement(By.id("recaptcha_response_field")).sendKeys("99M1");
str = str+1; System.out.println("str value is " +str)
   Demo d25= new Demo(str,"Terms and Conditions -  captcha field","user is able to correct the magic captcha field","Pass");
       testresultdata.put(d25.getkey(),d25);       
driver.findElement(By.xpath("//input[@value='Submit Application' and @type='submit']")).click();
       str = str+1; System.out.println("str value is " +str)
	  Demo d26= new Demo(str,"Terms and Conditions -  captcha field","user is able to submit m1 signup page","Pass");
       testresultdata.put(d26.getkey(),d26); 

	return str;
}
public int merchantbankbranchcode(org.openqa.selenium.WebDriver  driver,Map<Integer, Demo > testresultdata,int str)
{
	String desc;
	String bcode1 = "test";
	String bcode2 = "!!!";
	String bcode3 = "12+"; 
	driver.findElement(By.id("merchant_bank_branch_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bcode1);
    if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_bank_branch_code'] span[class='error']")).isDisplayed())
                     {
	   str = str+1; System.out.println("str value is " +str)
	   desc = "Branch code consist of three numeric digic . the value " +bcode1+ "is not numeric.Please check the value."
	  Demo dbc1= new Demo(str,"Bank Account details - Branch Code validation",desc,"Pass");
       testresultdata.put(dbc1.getkey(),dbc1); 
                     }

              else
              {
       str = str+1; System.out.println("str value is " +str)
       desc = "Branch code consist of three numeric digic . the value " +bcode1+ "is not numeric.Please check the value."
	  Demo dbc1= new Demo(str,"Bank Account details - Branch Code validation","Branch code must be numeric","Fail");
       testresultdata.put(dbc1.getkey(),dbc1); 
              	}
       
	sleep(1000);
     
		driver.findElement(By.id("merchant_bank_branch_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bcode2);
    if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_bank_branch_code'] span[class='error']")).isDisplayed())
                     {
	   str = str+1; System.out.println("str value is " +str)
	   desc = "Branch code consist of three numeric digic . the value " +bcode2+ "is not numeric.Please check the value."

	  Demo dbc2= new Demo(str,"Bank Account details  - Branch Code validation",desc,"Pass");
       testresultdata.put(dbc2.getkey(),dbc2); 
                     }

              else
              {
       str = str+1; System.out.println("str value is " +str)
	  Demo dbc2= new Demo(str,"Bank Account details - Branch Code validation","Branch code must be numeric","Fail");
       testresultdata.put(dbc2.getkey(),dbc2); 
              	}

       sleep(1000);

		driver.findElement(By.id("merchant_bank_branch_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"),bcode3);
    if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_bank_branch_code'] span[class='error']")).isDisplayed())
                     {
	   str = str+1; System.out.println("str value is " +str)
	  Demo dbc3= new Demo(str,"Bank Account details  - Branch Code validation","Branch code must be numeric","Pass");
       testresultdata.put(dbc3.getkey(),dbc3); 
                     }

              else
              {
       str = str+1; System.out.println("str value is " +str)
	  Demo dbc3= new Demo(str,"Bank Account details  - Branch Code validation","Branch code must be numeric","Fail");
       testresultdata.put(dbc3.getkey(),dbc3); 
              	}

        sleep(1000);

	
	
	Random BCValue = new Random();
 Integer d = BCValue.nextInt(999);
  System.out.println("random number :"+d);
 String randomnumberstr = Integer.toString(d);
 System.out.println("random number :"+randomnumberstr);

driver.findElement(By.id("merchant_bank_branch_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"),randomnumberstr); 
//driver.findElement(By.id("merchant_bank_account_number")).sendKeys("11117490");
sleep(1000)
//sleep(1000)
driver.findElement(By.id("merchant_bank_branch_code_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),randomnumberstr);

 sleep(1000);


    if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_bank_branch_code'] span[class='error']")).isDisplayed())
                     {
	   str = str+1; System.out.println("str value is " +str)
	    desc = "Branch code consist of three numeric digic .Please check the value."

	  Demo dbc4= new Demo(str,"Bank Account details  - Branch Code validation",desc,"Pass");
       testresultdata.put(dbc4.getkey(),dbc4); 
                     }

              else
              {
       str = str+1; System.out.println("str value is " +str)
       desc = "Branch code consist of three numeric digit . The value" +randomnumberstr+ "is correct."
	  Demo dbc4= new Demo(str,"Bank Account details  - Branch Code validation","Branch code must be numeric","Pass");
       testresultdata.put(dbc4.getkey(),dbc4); 
              	}

    
        

return str;
	
	}
public void Writeexception(String errormesssage)
{
  org.apache.poi.hssf.usermodel.HSSFWorkbook  workbookobj =  workbook.getWorkbook(new File(Test Result));

if(workbookobj.getSheet().getName().contentEquals("Exception log"))
  {

  int noofrows = workbookobj.getSheet().getName().contentEquals("Exception log").getrows(); 
  Row rowExlog = sheetErrorlog.createRow(noofrows);
  noofrows++;
  }
  else
  {
  sheetExceptinlog = workbook.createSheet("Exception log")
  int rowcount = 0;
  Row rowErrorlog = sheetErrorlog.createRow(rowcount);
  rowcount++;
  rowErrorlog.createCell(0).setCellValue("Exception :");
  Cell cellErrorlog1 = rowErrorlog.getCell((short)0);
  setHeaderCellStyle(sheetErrorlog,cellErrorlog1,workbook);
  }
  
	
}
public void validateDate(org.openqa.selenium.WebDriver  driver)
{
     String date[6] = new String[6];
	String date[0] =" ";
	String date[1] =" 12";
	String date[2] =" 12-13-2016";
	String date[3] ="22-22-2016";
	String date[4] ="test";
	String date[5] ="123455 ";

	for (i= 0 ; i<= 6 ; i++)
	{
	driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
        driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(date[i]);
        sleep(1000)
        driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
        driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(date[i]);
        sleep(1000)

	
	}
    driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
	driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys("19/08/1986");

	 driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
	 driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys("13/09/2015");
}
public class GenerateData {

 public String generateRandomString(int length){
  return RandomStringUtils.randomAlphabetic(length);
 }
 
 public String generateRandomNumber(int length){
  return RandomStringUtils.randomNumeric(length);
 }
 
 public String generateRandomAlphaNumeric(int length){
  return RandomStringUtils.randomAlphanumeric(length);
 }
 
 public String generateStringWithAllobedSplChars(int length,String allowdSplChrs){
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    "1234567890" +   //numbers
    allowdSplChrs;
  return RandomStringUtils.random(length, allowedChars);
 }

  public String generateStringWithspace(int length,String allowdSplChrs){
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    allowdSplChrs +
    "abcdefghijklmnopqrstuvwxyz" ;  //numbers
   
  return RandomStringUtils.random(length, allowedChars);
 }
 
 public String generateEmail(int length) {
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    "1234567890" +   //numbers
    "_-.";   //special characters
  String email="";
  String temp=RandomStringUtils.random(length,allowedChars);
  email=temp.substring(0,temp.length()-9)+"@test.org";
  return email;
 }
 
 public String generateUrl(int length) {
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    "1234567890";   //special characters
  String url="";
  String temp=RandomStringUtils.random(length,allowedChars);
  url="www" + "." +temp.substring(4,temp.length()-4)+ "." + "com";
  return url;
 }
}
public String nric_generator()
{
	 File pathfirefox = new File("C:\\Program Files\\Java\\firefox.exe");
   FirefoxProfile profile = new FirefoxProfile();
   driver = new FirefoxDriver(new FirefoxBinary(pathfirefox), profile); 
   driver.get("http://xeroy.net/nric-generator/");
   sleep(5000)
   
   WebElement element = driver.findElement(By.xpath("//input[@name='xeroyNRIC' and @class='Textfield']"));
   //JavascriptExecutor executor = (JavascriptExecutor)driver;
   //executor.executeScript("arguments[0].click();", element);
   Thread.sleep(1000);
   String nric = element.getAttribute("value");
   System.out.println("NRIC is generated : " +nric);
   return nric;

   
	
}

public int accountnameValidation(org.openqa.selenium.WebDriver  driver,Map<Integer, Demo > testresultdata,int str)
{
	String AC1 = "test!!";
        String AC2 = "test45";
	String AC3 = "123";
	String AC4 = "!!12345678";
	String AC5 = "12345678+++";
	String AC6 = "123456789012345678901234567890"
	String ACerror;


   // Map<Integer, Demo > testresultdataow = new HashMap<Integer,Demo>();
    if(driver.findElement(By.id("merchant_bank_account_holder_name")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC1);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).getText();
                     System.out.println("merchant bank account name format is not correct :" +ACerror)

                      str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC1+ "is not correct.Please check your value again.";
                     Demo dan1= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dan1.getkey(),dan1); 
                     }
                    else
                     {
                           System.out.println("merchant bank account name validation failed");
                               str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC1+ "is not correct.Please check your value again.";
                     Demo dan1= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dan1.getkey(),dan1); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
if(driver.findElement(By.id("merchant_bank_account_holder_name")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC2);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).getText();
                     System.out.println("merchant bank account name format is not correct :" +ACerror)
                             str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC2+ "is not correct.Please check your value again.";
                     Demo dan2= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dan2.getkey(),dan2); 
                     }
                    else
                     {
                           System.out.println("merchant bank account name validation failed");
                                    str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC2+ "is not correct.Please check your value again.";
                     Demo dan2= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dan2.getkey(),dan2); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)

if(driver.findElement(By.id("merchant_bank_account_holder_name")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC3);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).getText();
                     System.out.println("merchant bank account name format is not correct :" +ACerror)
                              str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC3+ "is not correct.Please check your value again.";
                     Demo dan3= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dan3.getkey(),dan3); 
                     }
                    else
                     {
                           System.out.println("merchant bank account name validation failed");
                                    str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC3+ "is not correct.Please check your value again.";
                     Demo dan2= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dan2.getkey(),dan2); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
if(driver.findElement(By.id("merchant_bank_account_holder_name")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC4);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).getText();
                     System.out.println("merchant bank account name format is not correct :" +ACerror)
                              str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dan3= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dan3.getkey(),dan3); 
                     }
                    else
                     {
                           System.out.println("merchant bank account name validation failed");
                                          str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC2+ "is not correct.Please check your value again.";
                     Demo dan3= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dan3.getkey(),dan3); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)

if(driver.findElement(By.id("merchant_bank_account_holder_name")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC5);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).getText();
                     System.out.println("merchant bank account name format is not correct :" +ACerror)
                                    str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC5+ "is not correct.Please check your value again.";
                     Demo dan4= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dan4.getkey(),dan4); 
                     }
                    else
                     {
                           System.out.println("merchant bank account name validation failed");
                                     str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC5+ "is not correct.Please check your value again.";
                     Demo dan4= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dan4.getkey(),dan4);
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
if(driver.findElement(By.id("merchant_bank_account_holder_name")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC6);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_holder_name'] span[class='error']")).getText();
                     System.out.println("merchant bank account name format is not correct :" +ACerror)

                               str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC6+ "is not correct.Please check your value again.";
                     Demo dan5= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dan5.getkey(),dan5);
                     }
                    else
                     {
                     System.out.println("merchant bank account name validation failed");
                     str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf name should consist of only digits. The value" +AC6+ "is not correct.Please check your value again.";
                     Demo dan5= new Demo(str,"Bank Account Details - Account Holder Name - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dan5.getkey(),dan5);
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
str = str+1;
       Demo d22= new Demo(str,"Account details - Merchant Account name successfully validated ","user is able to choose merchant Account name successfully","Pass");
       testresultdata.put(d22.getkey(),d22); 
return str;
}
public int accountnumberValidation(org.openqa.selenium.WebDriver  driver,Map<Integer, Demo > testresultdata,int str)
{
	String AC1 = "test";
     String AC2 = "test12";
	String AC3 = "123";
	String AC4 = "!!12345678";
	String AC5 = "12345678+++";
	String AC6 = "123456789012345678901234567890"
	String ACerror;
	String desc;



    
if(driver.findElement(By.id("merchant_bank_account_number")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC1);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).getText();
                     System.out.println("merchant bank account number format is not correct :" +ACerror)
                       str = str+1; System.out.println("str value is " +str)
                     desc = "Accounf number should consist of only digits. The value" +AC1+ "is not correct.Please check your value again.";
                     Demo dac1= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac1.getkey(),dac1); 
                     }
                    else
                     {
                           desc = "Accounf number should consist of only digits. The value" +AC1+ "is not correct.Please check your value again.";
                     Demo dac1= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dac1.getkey(),dac1); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)

if(driver.findElement(By.id("merchant_bank_account_number")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC2);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).getText();
                     System.out.println("merchant bank account number format is not correct :" +ACerror)
                      desc = "Accounf number should consist of only digits. The value" +AC2+ "is not correct.Please check your value again.";
                     Demo dac2= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac2.getkey(),dac2); 
                     }
                    else
                     {
                           System.out.println("merchant bank account number validation failed");
                               desc = "Accounf number should consist of only digits. The value" +AC2+ "is not correct.Please check your value again.";
                     Demo dac2= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac2.getkey(),dac2); 
                     }

               	
}


driver.findElement(By.linkText("Next")).click()
sleep(1000)

if(driver.findElement(By.id("merchant_bank_account_number")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC3);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).getText();
                     System.out.println("merchant bank account number format is not correct :" +ACerror)
                         desc = "Accounf number should consist of only digits. The value" +AC3+ "is not correct.Please check your value again.";
                     Demo dac3= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac3.getkey(),dac3); 
                     }
                    else
                     {
                           System.out.println("merchant bank account number validation failed");
                                desc = "Accounf number should consist of only digits. The value" +AC3+ "is not correct.Please check your value again.";
                     Demo dac3= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dac3.getkey(),dac3); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)

if(driver.findElement(By.id("merchant_bank_account_number")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC4);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).getText();
                     System.out.println("merchant bank account number format is not correct :" +ACerror)
                              desc = "Accounf number should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dac4= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac4.getkey(),dac4); 
                     }
                    else
                     {
                           System.out.println("merchant bank account number validation failed");
                                        desc = "Accounf number should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dac4= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dac4.getkey(),dac4); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)

if(driver.findElement(By.id("merchant_bank_account_number")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC5);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).getText();
                     System.out.println("merchant bank account number format is not correct :" +ACerror)
                     desc = "Accounf number should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dac5= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac5.getkey(),dac5);
                     }
                    else
                     {
                           System.out.println("merchant bank account number validation failed");
                             desc = "Accounf number should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dac5= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dac4.getkey(),dac5);
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
if(driver.findElement(By.id("merchant_bank_account_number")).isDisplayed())
{
	driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),AC6);
	if(driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).isDisplayed())
	
                     {
                     ACerror = driver.findElement(By.cssSelector("div[class = 'input string required merchant_bank_account_number'] span[class='error']")).getText();
                     System.out.println("merchant bank account number format is not correct :" +ACerror)
                       desc = "Accounf number should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dac6= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Pass");
                     testresultdata.put(dac6.getkey(),dac6);
                     }
                    else
                     {
                           System.out.println("merchant bank account number validation failed");
                             desc = "Accounf number should consist of only digits. The value" +AC4+ "is not correct.Please check your value again.";
                     Demo dac6= new Demo(str,"Bank Account Details - Account number - Invalid Format validation ",desc,"Fail");
                     testresultdata.put(dac6.getkey(),dac6);
                     }

               	
}
 

Random AC = new Random();
 Integer ACI = AC.nextInt(99999999);
  System.out.println("random number :"+ACI);
 String ACStr= Integer.toString(ACI);


driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),ACStr);
sleep(1000)
driver.findElement(By.id("merchant_bank_account_number_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),ACStr);
sleep(1000)
str = str+1; System.out.println("str value is " +str)
desc ="User is able to choose merchant account number.The value is " +ACStr+ "correct for account number."
  Demo d23= new Demo(str,"Banck Account details - Merchant Account number",desc,"Pass");
       testresultdata.put(d23.getkey(),d23); 

       return str;
}

public int companynameRegistrationValidation(org.openqa.selenium.WebDriver  driver,int str,Map<Integer, Demo > testresultdata)
{
String companyname1 = "";
String companyname2 = "www!!123";
String companyname3 = "test|||123";
String companyname4 = "test";
String companyname5 = "121221222222222221";
String cnfserror = "";
String companyregis1 = "";
String companyregis2 = "test";
String companyregis3 = "123";
String companyregis4 = "!!12345678";
String companyregis5 = "123456781233333333333333333333333332121";

	GenerateData genData = new GenerateData();

if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
	driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyname1);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).isDisplayed())
	
                     {
                     cnfserror= driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).getText();
                     System.out.println("merchant bank company name format is not correct :" +cnfserror)

                     str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should not be blank. Please provide valid company name";
                     Demo dcn1= new Demo(str,"Company details - Comapny name - Blank Field Validation ",desc,"Pass");
                     testresultdata.put(dcn1.getkey(),dcn1); 
                     
                     }
                    else
                     {
                           System.out.println("merchant company name validation failed");
                               str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should not be blank. Please provide valid company name";
                     Demo dcn1= new Demo(str,"Company details - Comapny name - Blank Field Validation ",desc,"Fail");
                     testresultdata.put(dcn1.getkey(),dcn1); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
	  

if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
	driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyname2);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).isDisplayed())
	
                     {
                     cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).getText();
                     System.out.println("merchant company name format is not correct :" +cnfserror)
                         str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed " +companyname2+ " is not valid.";
                     Demo dcn2= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Pass");
                     testresultdata.put(dcn2.getkey(),dcn2); 
                     }
                    else
                     {
                     str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed" +companyname2+ " is not valid.";
                     Demo dcn2= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Fail");
                     testresultdata.put(dcn2.getkey(),dcn2); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)

	  if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
	driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyname3);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).isDisplayed())
	
                     {
                     cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).getText();
                     System.out.println("merchant company name format is not correct :" +cnfserror)
                              str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed" +companyname3+ " is not valid.";
                     Demo dcn3= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Pass");
                     testresultdata.put(dcn3.getkey(),dcn3); 
                     }
                    else
                     {
                           System.out.println("merchant company name validation failed");
                                            str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed " +companyname3+ " is not valid.";
                     Demo dcn3= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Fail");
                     testresultdata.put(dcn3.getkey(),dcn3); 
                     }

               	
}



	  if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
	driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyname4);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).isDisplayed())
	
                     {
                     cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).getText();
                     System.out.println("merchant company name format is not correct :" +cnfserror)

                                      str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed " +companyname4+ " is not valid.";
                     Demo dcn4= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Pass");
                     testresultdata.put(dcn4.getkey(),dcn4); 
                     
                     }
                    else
                     {
                           System.out.println("merchant company name validation failed");

                           
                                      str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed" +companyname4+ " is not valid.";
                     Demo dcn4= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Fail");
                     testresultdata.put(dcn4.getkey(),dcn4); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)
	  
	  
	  if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
	driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyname5);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).isDisplayed())
	
                     {
                     cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name'] span[class='error']")).getText();
                     System.out.println("merchant company name format is not correct :" +cnfserror)
                                       str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed" +companyname5+ " is not valid.";
                     Demo dcn5= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Pass");
                     testresultdata.put(dcn5.getkey(),dcn5); 
                     
                     }
                    else
                     {
                           System.out.println("merchant company name validation failed");
                                                 str = str+1; System.out.println("str value is " +str)
                     desc = "Company name should contain only English alphabet letters, numerals, dots, quotes, and brackets to be allowed" +companyname5+ " is not valid.";
                     Demo dcn5= new Demo(str,"Company details - Comapny name - Invalid Format Validation ",desc,"Fail");
                     testresultdata.put(dcn5.getkey(),dcn5); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)	 

   if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
                     {
                      driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));
                    
                       
                     }
	  
	  
if(driver.findElement(By.cssSelector("div[class ='input string optional merchant_registration_number']")).isDisplayed())
{
	driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyregis1);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).isDisplayed())
	
                     {
                    cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).getText();
                     System.out.println("merchant registration number is not correct :" +cnfserror)

                         str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should not be blank" +companyregis1+ " is not valid.";
                     Demo dcr1= new Demo(str,"Company details - Comapny registration number  - Blank Field Validation ",desc,"Pass");
                     testresultdata.put(dcr1.getkey(),dcr1); 
                     }
                    else
                     {
                           System.out.println("merchant company registration number validation failed");
                             str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be blank" +companyregis1+ " is not valid.";
                     Demo dcr1= new Demo(str,"Company details - Comapny registration number  - Blank Field Validation ",desc,"Fail");
                     testresultdata.put(dcr1.getkey(),dcr1); 
                     }

               	
}

driver.findElement(By.linkText("Next")).click()
sleep(1000)	  
	  
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
{
	driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyregis2);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).isDisplayed())
	
                     {
                    cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).getText();
                     System.out.println("merchant registration number  is not correct :" +cnfserror)

                         str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be numeric" +companyregis2+ " is not valid company registration number.";
                     Demo dcr2= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Pass");
                     testresultdata.put(dcr2.getkey(),dcr2); 
                     }
                    else
                     {
                           System.out.println("merchant company registration number validation failed");

                              str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be numeric" +companyregis2+ " is not valid company registration number.";
                     Demo dcr2= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Fail");
                     testresultdata.put(dcr2.getkey(),dcr2); 
                     }

               	
}

	  

	  if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
{
	driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyregis3);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).isDisplayed())
	
                     {
                    cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).getText();
                     System.out.println("merchant registration number  is not correct :" +cnfserror)

                     
                         str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be numeric" +companyregis3+ " is not valid company registration number.";
                     Demo dcr3= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Pass");
                     testresultdata.put(dcr3.getkey(),dcr3); 
                     }
                    else
                     {
                           System.out.println("merchant company registration number validation failed");

                                    str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be numeric" +companyregis3+ " is not valid company registration number.";
                     Demo dcr3= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Fail");
                     testresultdata.put(dcr3.getkey(),dcr3); 
                     }

               	
}


	  
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
{
	driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyregis4);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).isDisplayed())
	
                     {
                    cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).getText();
                     System.out.println("merchant registration number  is not correct :" +cnfserror)

                              str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be numeric" +companyregis4+ " is not valid company registration number.";
                     Demo dcr4= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Pass");
                     testresultdata.put(dcr4.getkey(),dcr4); 
                     }
                    else
                     {
                           System.out.println("merchant company registration number validation failed");
                                    str = str+1; System.out.println("str value is " +str)
                     desc = "Company registration number should be numeric" +companyregis4+ " is not valid company registration number.";
                     Demo dcr4= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Fail");
                     testresultdata.put(dcr4.getkey(),dcr4); 
                     }

               	
}

	  
	  
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
{
	driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyregis5);
	if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).isDisplayed())
	
                    {
                    cnfserror = driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number'] span[class='error']")).getText();
                    System.out.println("merchant registration number   is not correct :" +cnfserror)

                                     str = str+1; System.out.println("str value is " +str)
                    desc = "Company registration number should be numeric" +companyregis5+ " is not valid company registration number.";
                    Demo dcr5= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Pass");
                     testresultdata.put(dcr5.getkey(),dcr5); 
                     
                    }
                    else
                    {
                          System.out.println("merchant company registration number validation failed");

                                   str = str+1; System.out.println("str value is " +str)
                    desc = "Company registration number should be numeric" +companyregis5+ " is not valid company registration number.";
                     Demo dcr5= new Demo(str,"Company details - Comapny registration number  - Invalid format Field Validation ",desc,"Fail");
                     testresultdata.put(dcr5.getkey(),dcr5); 
                    }

               	
}
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())

                     {
                      driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(6));

                     }


    return str;

}




public int selectentitytype(org.openqa.selenium.WebDriver  driver, int str,Map<Integer, Demo > testresultdata)
{
    Select select = new Select(driver.findElement(By.id("merchant_legal_form")));
    
   int i =select.getOptions().size();
   sleep(1000)

   Assert.assertEquals(6, i);

   sleep(1000)

   //select.selectByValue("");
  // Assert.assertEquals("Please Select", select.getFirstSelectedOption().getText()); 

sleep(1000)
select.selectByValue("sole_proprietor");
Assert.assertEquals("Sole Proprietor", select.getFirstSelectedOption().getText()); 
str = str+1;
Demo dentity1 = new Demo(str,"Entity Dropdown - Signup Page","Entity type has following options : Sole Proprietor .User would be able to select this option as entity type.","Pass");
      testresultdata.put(dentity1.getkey(),dentity1);
      sleep(1000)

      
sleep(1000)
select.selectByValue("private_limited"); 
Assert.assertEquals("Private Limited", select.getFirstSelectedOption().getText()); 
str = str+1;
Demo dentity2 = new Demo(str,"Entity Dropdown - Signup Page","Entity type has following options Private Limited .User would be able to select this option.","Pass");
      testresultdata.put(dentity2.getkey(),dentity2);
      sleep(1000)
      
sleep(1000)
select.selectByValue("partnership"); 
Assert.assertEquals("Partnership", select.getFirstSelectedOption().getText()); 
sleep(1000)
str = str+1;
Demo dentity3 = new Demo(str,"Entity Dropdown - Signup Page","Entity type has following options : partnership .User would be able to select this option.","Pass");
      testresultdata.put(dentity3.getkey(),dentity3);
      
select.selectByValue("limited_liability_partnership");
Assert.assertEquals("Limited Liability Partnership", select.getFirstSelectedOption().getText()); 
sleep(1000)
str = str+1;
Demo dentity4 = new Demo(str,"Entity Dropdown - Signup Page","Entity type has following options : Limited Liability Partnership .User would be able to select this option.","Pass");
testresultdata.put(dentity4.getkey(),dentity4);

      
select.selectByValue("individual_service_provider");
Assert.assertEquals("Not Incorporated / Registered", select.getFirstSelectedOption().getText()); 
sleep(1000)
str = str+1;
Demo dentity5 = new Demo(str,"Entity Dropdown - Signup Page","Entity type has following options :Not Incorporated / Registered .User would be able to select this option.","Pass");
testresultdata.put(dentity5.getkey(),dentity5);

      
List list = Arrays.asList("Please Select","Sole Proprietor", "Private Limited", "Partnership","Limited Liability Partnership","Not Incorporated / Registered");

List list1 = new ArrayList(); 

for(WebElement element:select.getOptions())
	{
	list1.add(element.getText());
	}

int j = list1.size(); 

sleep(10000)
System.out.println("value entered in J ="+j); 
for(int k = 1; k<j; k++) { System.out.println(list1.get(k)); }

Assert.assertEquals(list.toArray(), list1.toArray());
str = str+1;
Demo dentity6 = new Demo(str,"Entity Dropdown - Signup Page","Number of options at entity type dropdown is correct","Pass");
testresultdata.put(dentity6.getkey(),dentity6);


 System.out.println("str value is " +str)

      sleep(1000)

 WebElement drpDwnListentitytype = driver.findElement(By.id("merchant_legal_form"));
 //Using FindElements to create a List object

 //Using Select Class to fetch the count
 Select objSelentitytype  = new Select(drpDwnListentitytype);
 List <WebElement> weblistentitytype  = objSelentitytype.getOptions();
 //Taking the count of items
 int iCntentitytype  = weblistentitytype.size();
 System.out.println("count entity type : "+iCntentitytype);
 //Using Random class to generate random values
 Random numentitytype  = new Random();
 int iSelectentitytype  = numentitytype.nextInt(iCntentitytype);
 System.out.println("select entity type : "+iSelectentitytype  );
 //Selecting value from DropDownList
 objSelentitytype.selectByIndex(iSelectentitytype);
 //Selected Value


sleep(1000)
	return str;
	
	}


