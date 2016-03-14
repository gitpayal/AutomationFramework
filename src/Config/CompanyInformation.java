/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import static Config.ActionKeywords.driver;
import executionengine.DriverScript;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author payal.verma
 */
public class CompanyInformation {
    
    public static void select_merchantlegalform(){
		
        try {
            sleep(500);
            WebElement drpDwnListcategory = driver.findElement(By.id("merchant_legal_form"));
           
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
             if ("Lexware Signup".equals(DriverScript.SignupFORTest))
             {
                 sleep(500);
                 
                 ((JavascriptExecutor)driver).executeScript("document.getElementById('merchant_legal_form').selectedIndex = 5;");
               
               
             }
             //Selected Value

            else
             {
                 objSelcategory.selectByIndex(iSelectcategory);
             }

            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }
                
		}
        
    public static void select_branche(){
		
        try {
            sleep(500);
            WebElement drpDwnListcategory = driver.findElement(By.id("merchant_company_type"));
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
            if ("Lexware Signup".equals(DriverScript.SignupFORTest))
             {
                 sleep(500);
                 
                 ((JavascriptExecutor)driver).executeScript("document.getElementById('merchant_company_type').selectedIndex = 5;");
               
               
             }
            else
            {
            objSelcategory.selectByIndex(iSelectcategory);
            //Selected Value
            }
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }
                
		}
    
    public static void set_companyname(){
    driver.findElement(By.id("merchant_company_name")).sendKeys("testcompanyname"); 
    }
    public static void set_registrationNumber(){
     if ( driver.findElement(By.id("merchant_registration_number")).isDisplayed())
        {
    driver.findElement(By.id("merchant_registration_number")).sendKeys("54321"); 
    }
     else{
     }
    }
    public static void set_controlNumber(){
        if ( driver.findElement(By.id("merchant_control_number")).isDisplayed())
        {
    driver.findElement(By.id("merchant_control_number")).sendKeys("543211"); 
        }
        else
        {
           
        }
        }
    public static void set_companyYear(){
    driver.findElement(By.id("merchant_year_of_foundation")).sendKeys("1999"); 
    }
    public static void set_website(){
    driver.findElement(By.id("merchant_website")).sendKeys("www.merchantwebsite.com"); 
    }
    public static void set_address(){
        try {
            driver.findElement(By.id("merchant_company_street")).sendKeys("hohenzollernplatz");
            driver.findElement(By.id("merchant_company_house_number")).sendKeys("88");
            driver.findElement(By.id("merchant_company_postal_code")).sendKeys("88796");
            driver.findElement(By.id("merchant_company_city")).sendKeys("munich");
            driver.findElement(By.id("merchant_company_phone_number")).sendKeys("15214551606");
            driver.findElement(By.id("merchant_company_fax")).sendKeys("+4315214441606");
            sleep(1000);
              if (!"Lexware Signup".equals(DriverScript.SignupFORTest))
            {
                driver.findElement(By.linkText("Weiter")).click();
                driver.findElement(By.xpath("//a[@data-target='.step-3' and @class='goto']")).click();
            }
            
               
                 
            
            sleep(1000);
         
       
           
        } catch (InterruptedException ex) {
            Logger.getLogger(CompanyInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
