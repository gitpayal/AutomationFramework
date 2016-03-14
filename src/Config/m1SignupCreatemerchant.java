/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import static Config.ActionKeywords.driver;
import Utility.Demo;
import Utility.GenerateData;
import static executionengine.DriverScript.merchantEmail;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 *
 * @author payal.verma
 */
public class m1SignupCreatemerchant {
    public void createMerchant() throws InterruptedException
    {
        
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/*
@Author : Payal Verma
@Description : .This is test step 1 to open the merchant Signup page in Intrnet explorer
@GroovyTestStepName : "Launch SignUp Page"
*/
//import com.eviware.soapui.model.testsuite.TestRunner.Status; 






   

 

  
   	 ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('disclaimer[accepts]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
      
      driver.findElement(By.name("commit")).click();
   
    

 
  
    driver.findElement(By.linkText("Next")).click();
 


   GenerateData genData = new GenerateData();
  // String URL = genData.generateEmail(20);
  
   
   
   driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),merchantEmail);

   
  
   driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),merchantEmail);


  String Password = genData.generateRandomAlphaNumeric(15);
  System.out.println("Password Generated is : " +Password);
   
  driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
  
  



   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);

   driver.findElement(By.linkText("Next")).click();
   Thread.sleep(1000);
  
while(driver.findElement(By.cssSelector("div[class = 'input password required merchant_password'] span[class='error']")).isDisplayed() || driver.findElement(By.cssSelector("div[class = 'input email required merchant_email'] span[class='error']")).isDisplayed())
 {
  Password = genData.generateRandomAlphaNumeric(15);
  System.out.println("Password Generated is : " +Password);
   
  
   
   driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),merchantEmail);
 
   
   Thread.sleep(1000);
   driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),merchantEmail);
 
 Thread.sleep(1000);

  
  driver.findElement(By.id("merchant_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
 

    Thread.sleep(1000);
   driver.findElement(By.id("merchant_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Password);
   

   driver.findElement(By.linkText("Next")).click();
    Thread.sleep(1000);
 } 

   
selectentitytype(driver);

while(driver.findElement(By.cssSelector("div[class = 'input select required merchant_legal_form'] span[class='error']")).isDisplayed())
 {
 	selectentitytype(driver);
 } 
   
 Thread.sleep(1000);


DBAValidation(driver);




categorySubcategory(driver);


 Thread.sleep(1000);
while(driver.findElement(By.cssSelector("div[class = 'input select optional merchant_company_category'] span[class='error']")).isDisplayed()||driver.findElement(By.cssSelector("div[class = 'input select optional merchant_company_type'] span[class='error']")).isDisplayed())
 {
 	categorySubcategory(driver);
 } 
   




 merchantwebsitevalidation(driver);
//Start : Merchant Attribute check
Select selectMerchantPrefix = new Select(driver.findElement(By.id("merchant_contact_person_attributes_prefix")));

int iMerchantPrefix =selectMerchantPrefix.getOptions().size();
 Thread.sleep(1000);

Assert.assertEquals(4, iMerchantPrefix);

 Thread.sleep(1000);

selectMerchantPrefix.selectByValue("");
Assert.assertEquals("Please Select", selectMerchantPrefix.getFirstSelectedOption().getText()); 

 Thread.sleep(1000);
selectMerchantPrefix.selectByValue("Mr");
Assert.assertEquals("Mr", selectMerchantPrefix.getFirstSelectedOption().getText()); 
 Thread.sleep(1000);
selectMerchantPrefix.selectByValue("Ms"); 
Assert.assertEquals("Ms", selectMerchantPrefix.getFirstSelectedOption().getText()); 
 Thread.sleep(1000);
selectMerchantPrefix.selectByValue("Mrs"); 
Assert.assertEquals("Mrs", selectMerchantPrefix.getFirstSelectedOption().getText()); 

//End : Merchant Attribute check

//Start : Merchant Gender check
Select selectMerchantGender = new Select(driver.findElement(By.id("merchant_contact_person_attributes_gender")));

int iMerchantGender =selectMerchantGender.getOptions().size();
 Thread.sleep(1000);

Assert.assertEquals(3, iMerchantGender);

 Thread.sleep(1000);

selectMerchantGender.selectByValue("");
Assert.assertEquals("Please Select", selectMerchantGender.getFirstSelectedOption().getText()); 

 Thread.sleep(1000);
selectMerchantGender.selectByValue("Male");
Assert.assertEquals("Male", selectMerchantGender.getFirstSelectedOption().getText()); 
 Thread.sleep(1000);
selectMerchantGender.selectByValue("Female"); 
Assert.assertEquals("Female", selectMerchantGender.getFirstSelectedOption().getText()); 
 Thread.sleep(1000);

//End : Merchant Gender check







 Thread.sleep(1000);

merchantfirstlastnamevalidation(driver);

 Thread.sleep(1000);
driver.findElement(By.linkText("Next")).click();
 Thread.sleep(1000);

merchantsveamount(driver);

        


	
	  





    }
public void categorySubcategory(org.openqa.selenium.WebDriver driver) throws InterruptedException
{
	
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

Thread.sleep(1000);

                         

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
  }   
    







public void DBAValidation(org.openqa.selenium.WebDriver driver) throws InterruptedException
{
	
     GenerateData genData = new GenerateData();
	  
        
        
       
         
         String DBAfinal = "MONETST"+ genData.generateRandomString(5);
         driver.findElement(By.id("merchant_company_dba")).sendKeys(Keys.chord(Keys.CONTROL, "a"),DBAfinal);
         driver.findElement(By.linkText("Next")).click();
         String BS = "Business Summary :" + genData.generateRandomString(50);
         driver.findElement(By.id("merchant_business_summary")).sendKeys(Keys.chord(Keys.CONTROL, "a"),BS);
         driver.findElement(By.linkText("Next")).click();
         Thread.sleep(1000);  
        
	

               	
	

       

	
}






public void merchantwebsitevalidation(org.openqa.selenium.WebDriver  driver)
{
 
	
	GenerateData gendata = new GenerateData();
        driver.findElement(By.id("merchant_website")).sendKeys(Keys.chord(Keys.CONTROL, "a"),gendata.generateUrl(20));

      
}

public void merchantfirstlastnamevalidation(org.openqa.selenium.WebDriver  driver) throws InterruptedException
{

	GenerateData genData = new GenerateData() ;
	String desc;
  
	
	
             String lastname = "MONETST" + genData.generateRandomString(10);
          driver.findElement(By.id("merchant_contact_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),lastname);      	
          Thread.sleep(1000);
        
         driver.findElement(By.id("merchant_contact_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomNumber(8));
         driver.findElement(By.id("merchant_contact_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(5));      	
         sleep(1000);
               
              
     
               
       
               Thread.sleep(1000);

               
        //((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[contact_email_matches_account_email]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
               

            
                String document = "Same Contact e-Mail as Login";
                 sleep(1000);
                new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(., \"" + document + "\")]"))).click();

                
                  
driver.findElement(By.id("merchant_contact_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"), merchantEmail);

driver.findElement(By.id("merchant_contact_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"), merchantEmail);
              
                
                     
                 driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(4));
                 

               	 driver.findElement(By.id("merchant_company_street")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomString(10));
              
                
                     
                 driver.findElement(By.id("merchant_company_house_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(4));
               

             driver.findElement(By.id("merchant_company_postal_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(6));

      	

}

public void merchantsveamount(WebDriver driver) throws InterruptedException
{
	

	String sve = "2000";
	
	GenerateData genData = new GenerateData() ;
	//Map<Integer, Demo > testresultdata = new HashMap<Integer,Demo>();
	driver.findElement(By.id("merchant_sve_amount")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sve);
	 sleep(1000);
     driver.findElement(By.linkText("Next")).click();
     sleep(1000);

    
 
     driver.findElement(By.linkText("Next")).click();
  

     
    
        
    String documentBusinessOwner1 = "I hereby confirm that I understand that my sales volume is above the established settlement cap and payment of some of the processed funds may be delayed.";
   
     new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(., \"" + documentBusinessOwner1 + "\")]"))).click();
    
     // new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(cssSelector("<tag name>[id*='Same as Contact Details']"))).click();
                   driver.findElement(By.linkText("Next")).click();
sleep(1000);
          


if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_company_name']")).isDisplayed())
{
companynameRegistrationValidation(driver);

sleep(1000) ;
}
if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())
         {
     	  WebElement element = driver.findElement(By.id("merchant_representative_person_matches_contact"));
     	  JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
                sleep(1000);
            driver.findElement(By.linkText("Next")).click();
    }

     

 sleep(2000); 
System.out.println("merchant_business_person_attributes_prefix");

if (driver.findElement(By.id("merchant_business_person_attributes_prefix")).isDisplayed())
{
Select selectMerchantPrefix = new Select(driver.findElement(By.id("merchant_business_person_attributes_prefix")));

int iMerchantPrefix =selectMerchantPrefix.getOptions().size();
 sleep(2000); 

Assert.assertEquals(4, iMerchantPrefix);

 sleep(2000); 



 sleep(2000); 
selectMerchantPrefix.selectByValue("Mr");
Assert.assertEquals("Mr", selectMerchantPrefix.getFirstSelectedOption().getText()); 
 sleep(2000); 
selectMerchantPrefix.selectByValue("Ms"); 
Assert.assertEquals("Ms", selectMerchantPrefix.getFirstSelectedOption().getText()); 
 sleep(2000); 
selectMerchantPrefix.selectByValue("Mrs"); 
Assert.assertEquals("Mrs", selectMerchantPrefix.getFirstSelectedOption().getText()); 


         

System.out.println("merchant_business_person_attributes_prefix test completed");
//End : Merchant Attribute check

//Start : Merchant Gender check
Select selectMerchantGender = new Select(driver.findElement(By.id("merchant_business_person_attributes_gender")));
System.out.println("merchant_business_person_attributes_gender test started");
int iMerchantGender =selectMerchantGender.getOptions().size();
 sleep(2000); 

Assert.assertEquals(3, iMerchantGender);

 sleep(2000); 
System.out.println("merchant_business_person_attributes_gender test started");
selectMerchantGender.selectByValue("");
Assert.assertEquals("Please Select", selectMerchantGender.getFirstSelectedOption().getText()); 

 sleep(2000); 
selectMerchantGender.selectByValue("Male");
Assert.assertEquals("Male", selectMerchantGender.getFirstSelectedOption().getText()); 
 sleep(2000); 
selectMerchantGender.selectByValue("Female"); 
Assert.assertEquals("Female", selectMerchantGender.getFirstSelectedOption().getText()); 
 sleep(2000); 


                   
 driver.findElement(By.id("merchant_business_person_attributes_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));


      driver.findElement(By.id("merchant_business_person_attributes_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomString(10));
            
   
    Thread.sleep(1000); 

driver.findElement(By.linkText("Next")).click();
Thread.sleep(1000);

              driver.findElement(By.id("merchant_business_person_attributes_phone_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateRandomNumber(8));

   
    String nrictestdata = "S9515508A";
     //nrictestdata = Utility.PickNRICdata();
System.out.println("nric value is " +nrictestdata) ;
    driver.findElement(By.id("merchant_business_person_attributes_nric")).sendKeys(Keys.chord(Keys.CONTROL, "a"),nrictestdata);
    sleep(5000);
driver.findElement(By.linkText("Next")).click();
sleep(1000);

 while(driver.findElement(By.cssSelector("div[class = 'input string required merchant_business_person_nric'] span[class='error']")).isDisplayed())
 {
 	

 
 
    driver.findElement(By.id("merchant_business_person_attributes_nric")).sendKeys(Keys.chord(Keys.CONTROL, "a"),nrictestdata);
    sleep(5000);
driver.findElement(By.linkText("Next")).click();
sleep(1000);
 }


}

driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),genData.generateStringWithspace(10, " "));
Thread.sleep(2000);

accountnumberValidation(driver);
 merchantbankbranchcode(driver);

while(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_bank_branch_code'] span[class='error']")).isDisplayed())
 {
merchantbankbranchcode(driver);
 }             	
 






 WebElement drpDwnList = driver.findElement(By.id("merchant_bank_code"));

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
 
driver.findElement(By.linkText("Next")).click();
Thread.sleep(1000);


Thread.sleep(1000);

WebElement element = driver.findElement(By.id("merchant_accepts_terms"));
JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("arguments[0].click();", element);


        
    
   
   


driver.findElement(By.id("recaptcha_response_field")).sendKeys("99M1");

        
driver.findElement(By.xpath("//input[@value='Submit Application' and @type='submit']")).click();
      



}

public void merchantbankbranchcode(org.openqa.selenium.WebDriver  driver) throws InterruptedException
{

	
	Random BCValue = new Random();
 Integer d = BCValue.nextInt(999);
  System.out.println("random number :"+d);
 String randomnumberstr = Integer.toString(d);
 System.out.println("random number :"+randomnumberstr);

driver.findElement(By.id("merchant_bank_branch_code")).sendKeys(Keys.chord(Keys.CONTROL, "a"),randomnumberstr); 
//driver.findElement(By.id("merchant_bank_account_number")).sendKeys("11117490");
Thread.sleep(1000);
//sleep(1000)
driver.findElement(By.id("merchant_bank_branch_code_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),randomnumberstr);

Thread.sleep(1000);


 

    
        

	
}


     



public void accountnumberValidation(org.openqa.selenium.WebDriver  driver)
{
	

Random AC = new Random();
 Integer ACI = AC.nextInt(99999999);
  System.out.println("random number :"+ACI);
 String ACStr= Integer.toString(ACI);


driver.findElement(By.id("merchant_bank_account_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"),ACStr);
driver.findElement(By.id("merchant_bank_account_number_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),ACStr);

}

public void companynameRegistrationValidation(org.openqa.selenium.WebDriver  driver)
{


	GenerateData genData = new GenerateData();


if(driver.findElement(By.cssSelector("div[class = 'input string optional merchant_registration_number']")).isDisplayed())

                     {
                         String companyname = "company name" + genData.generateRandomString(5);
                         driver.findElement(By.id("merchant_company_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"),companyname );
                         driver.findElement(By.id("merchant_registration_number")).sendKeys(Keys.chord(Keys.CONTROL, "a"), genData.generateRandomNumber(6));

                     }


    

}




public void selectentitytype(WebDriver driver) throws InterruptedException
{
    Select select = new Select(driver.findElement(By.id("merchant_legal_form")));
    
   int i =select.getOptions().size();


   Assert.assertEquals(6, i);

 
select.selectByValue("sole_proprietor");
Assert.assertEquals("Sole Proprietor", select.getFirstSelectedOption().getText()); 

select.selectByValue("private_limited"); 
Assert.assertEquals("Private Limited", select.getFirstSelectedOption().getText()); 

      

select.selectByValue("partnership"); 
Assert.assertEquals("Partnership", select.getFirstSelectedOption().getText()); 

      
select.selectByValue("limited_liability_partnership");
Assert.assertEquals("Limited Liability Partnership", select.getFirstSelectedOption().getText()); 



      
select.selectByValue("individual_service_provider");
Assert.assertEquals("Not Incorporated / Registered", select.getFirstSelectedOption().getText()); 


      
List list = Arrays.asList("Please Select","Sole Proprietor", "Private Limited", "Partnership","Limited Liability Partnership","Not Incorporated / Registered");

List list1 = new ArrayList(); 

for(WebElement element:select.getOptions())
	{
	list1.add(element.getText());
	}

int j = list1.size(); 

Thread.sleep(10000);
System.out.println("value entered in J ="+j); 
for(int k = 1; k<j; k++) { System.out.println(list1.get(k)); }

Assert.assertEquals(list.toArray(), list1.toArray());




      Thread.sleep(1000);

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




	



    
}

}