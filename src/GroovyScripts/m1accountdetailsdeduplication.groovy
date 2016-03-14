/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GroovyScripts

def name='payal.verma'

println "Hello $name!"

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
//mport utility.GenerateData;
import GroovyScripts.GenerateData;


int maxTries = 3;
int countexception =0;

 WebDriver driver;
  
   
    
   File pathfirefox = new File("C:\\Program Files\\Java\\firefox.exe");
   FirefoxProfile profile = new FirefoxProfile();
   driver = new FirefoxDriver(new FirefoxBinary(pathfirefox), profile); 
   driver.get("http://signup-page.dev.up-next.com/");
   sleep(5000)



   driver.findElement(By.name("commit")).click();
   Boolean b = driver.findElement(By.cssSelector("div[class = 'inputs'] span[class='error field_with_errors']")).isDisplayed()
   System.out.println(b);
   if (b)
   {
   	 String kk = driver.findElement(By.cssSelector("div[class = 'inputs'] span[class='error field_with_errors']")).getText();
   	 System.out.println("User accepts the disclaimer :" +kk);
   	 ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('disclaimer[accepts]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
     
      driver.findElement(By.name("commit")).click();
    
   }
   else
   {
   	System.out.println("Disclaimer Acceptance test failure");
   }
   
   
   GenerateData genData = new GenerateData();
   String URL = genData.generateEmail(20);
   
   driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);
  
   sleep(1000)
   driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),URL);
  
  sleep(1000)

  String Password = genData.generateRandomAlphaNumeric(15);
  System.out.println("Password Generated is : " +Password);
  driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
  
  sleep(1000)


   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
  
   sleep(1000)

   driver.findElement(By.linkText("Next")).click()
   sleep(1000)
  
while(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed() || driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
 {
 	Password = genData.generateRandomAlphaNumeric(15);
  System.out.println("Password Generated is : " +Password);
  driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
 

 sleep(1000)
   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
   

   driver.findElement(By.linkText("Next")).click()
   sleep(1000)
 } 
   
sleep(1000)  
selectentitytype(driver);

while(driver.findElement(By.cssSelector("div[class = 'input select required merchant_legal_form'] span[class='error']")).isDisplayed())
 {
 	selectentitytype(driver);
 } 
   
sleep(1000)




driver.findElement(By.id("merchant_company_dba")).sendKeys("Salesman");




driver.findElement(By.id("merchant_company_ssic")).sendKeys("47521");




merchantwebsitevalidation(driver);
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






merchantfirstlastnamevalidation(driver);

sleep(1000)
driver.findElement(By.linkText("Next")).click()
sleep(1000)



merchantsveamount(driver);
//driver.findElement(By.id("merchant_sve_amount")).sendKeys("100");

//sleep(1000)
//driver.findElement(By.linkText("Next")).click()
//sleep(1000)
	
public void categorySubcategory(org.openqa.selenium.WebDriver driver){
	
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

public void merchantwebsitevalidation(org.openqa.selenium.WebDriver  driver)
{
 
	
	GenerateData gendata = new GenerateData();

                      driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),gendata.generateUrl(20));

                      driver.findElement(By.id("merchant_business_summary")).sendKeys(Keys.chord(Keys.CONTROL, "a"),gendata.generateStringWithspace(70," "));
         driver.findElement(By.linkText("Next")).click()
         sleep (1000); 
    

               	
     
	
}

public void merchantfirstlastnamevalidation(org.openqa.selenium.WebDriver  driver)
{

	GenerateData genData = new GenerateData() ;
     String Firstname5 = "MONETST" + genData.generateRandomString(5);
    driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Firstname5);
             String lastname = "MONETST" + genData.generateRandomString(10);
          driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),lastname);      	
          sleep(1000);

                 sleep(1000)
                

         driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomNumber(8));
         driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(5));      	
         sleep(1000);

                 String document = "Same Contact e-Mail as Login";
                 sleep(1000);
                 new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(., \"" + document + "\")]"))).click();

               driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateStringWithAllobedSplChars(25," _,"));

                driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));
                  
               	 driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomString(5));

             driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(6));

         

}

public void merchantsveamount(org.openqa.selenium.WebDriver  driver)
{
	String sve1 = "";

	GenerateData genData = new GenerateData() ;
	
	driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "100");
	 sleep(1000)
     driver.findElement(By.linkText("Next")).click()
     sleep(1000)

     sleep(1000)  

//Start : Companyname
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
companynameRegistrationValidation(driver);

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


//End : Company registration number
//Start : Merchant Attribute check
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
 driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));

 driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));
            
   
    sleep(1000) 

driver.findElement(By.linkText("Next")).click()
sleep(1000)
          
 //String nrictestdata = nric_generator();

    driver.findElement(By.id("merchant_business_person_attributes_nric")).sendKeys("S2889373F");
    sleep(1000)
    driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
    driver.findElement(By.id("merchant_business_person_attributes_dob")).sendKeys("19/08/1986");
    sleep(1000)
driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"");
driver.findElement(By.id("merchant_business_person_attributes_nric_issue_date")).sendKeys("13/09/2015");
sleep(1000)          
  	
              driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomNumber(8));

driver.findElement(By.linkText("Next")).click()
sleep(1000)
}
   
System.out.println("Account details");
driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys("Foo Bar");
sleep(1000)

driver.findElement(By.id("merchant_bank_code")).sendKeys("7010 - THE ROYAL BANK OF SCOTLAND N.V.");
sleep(1000)

driver.findElement(By.id("merchant_bank_branch_code")).sendKeys("100");
sleep(1000)
driver.findElement(By.id("merchant_bank_branch_code_confirmation")).sendKeys("100");
sleep(1000)

driver.findElement(By.id("merchant_bank_account_number")).sendKeys("11117490");
sleep(1000)

driver.findElement(By.id("merchant_bank_account_number_confirmation")).sendKeys("11117490");
sleep(1000)

driver.findElement(By.linkText("Next")).click()
sleep(1000)

Thread.sleep(1000);

WebElement element = driver.findElement(By.id("merchant_accepts_terms"));
JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("arguments[0].click();", element);
Thread.sleep(1000);
driver.findElement(By.id("recaptcha_response_field")).sendKeys("99M1");
driver.findElement(By.xpath("//input[@value='Submit Application' and @type='submit']")).click();
sleep(2000);


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

public void accountnumberValidation(org.openqa.selenium.WebDriver  driver)
{
Random AC = new Random();
 Integer ACI = AC.nextInt(99999999);
  System.out.println("random number :"+ACI);
 String ACStr= Integer.toString(ACI);


driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),ACStr);
sleep(1000)
driver.findElement(By.id("merchant_bank_account_number_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),ACStr);
sleep(1000)


}
public void selectentitytype(org.openqa.selenium.WebDriver  driver)
{
    Select select = new Select(driver.findElement(By.id("merchant_legal_form")));
    
   int i =select.getOptions().size();
   sleep(1000)

   Assert.assertEquals(6, i);

   sleep(1000)




select.selectByValue("sole_proprietor");
Assert.assertEquals("Sole Proprietor", select.getFirstSelectedOption().getText()); 
sleep(1000)
select.selectByValue("private_limited"); 
Assert.assertEquals("Private Limited", select.getFirstSelectedOption().getText()); 
sleep(1000)
select.selectByValue("partnership"); 
Assert.assertEquals("Partnership", select.getFirstSelectedOption().getText()); 
sleep(1000)
select.selectByValue("limited_liability_partnership");
Assert.assertEquals("Limited Liability Partnership", select.getFirstSelectedOption().getText()); 
sleep(1000)
select.selectByValue("individual_service_provider");
Assert.assertEquals("Not Incorporated / Registered", select.getFirstSelectedOption().getText()); 
sleep(1000)
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
	
	
	}


public void companynameRegistrationValidation(org.openqa.selenium.WebDriver  driver)
{


GenerateData genData = new GenerateData();
 if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
                     {
                      driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomString(6));

                     }
	  
	  
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
                     {
                      driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(6));

                     }

     

}


