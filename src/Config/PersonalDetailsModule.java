/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import static Config.ActionKeywords.driver;
import executionengine.DriverScript;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author payal.verma
 */
public class PersonalDetailsModule {
      public static void select_checkbox(){
          ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[gender]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
    
    }
      public static void set_personalInformation(){
    driver.findElement(By.id("merchant_first_name")).sendKeys("test firstname"); 
    driver.findElement(By.id("merchant_last_name")).sendKeys("test lastname");
    driver.findElement(By.id("merchant_position")).sendKeys("testposition");
    } 
      public static void set_Address(){
          
        //  ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[private_data_matches_company]');for(var i=0, n=checkboxes.length;i<n;i++) { if ( document.getElementsByName(\"merchant[private_data_matches_company]\").innerHTML = \"Identisch mit Firmenadresse\") checkboxes[i].checked = true }");
          driver.findElement(By.id("merchant_private_street")).sendKeys("test street"); 
          driver.findElement(By.id("merchant_private_house_number")).sendKeys("12");
          driver.findElement(By.id("merchant_private_postal_code")).sendKeys("80697"); 
          driver.findElement(By.id("merchant_private_city")).sendKeys("Graz");
           if (!"Lexware Signup".equals(DriverScript.SignupFORTest))
            {
          driver.findElement(By.xpath("//a[@data-target='.step-4' and @class='goto']")).click();
            }
    }  
    
}
