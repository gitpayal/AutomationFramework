/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;


import static Config.ActionKeywords.driver;
import Utility.Demo;
import Utility.GenerateData;
import executionengine.DriverScript;
import static executionengine.DriverScript.merchantEmail;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginModule {
    public static int str;
    public static String desc;
    Map<Integer, Demo > testresultdata = new HashMap<Integer,Demo>();
    
    public static void set_Email(){
       // GenerateData genData = new GenerateData();
       // String email = genData.generateEmail(15);
         driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),merchantEmail); 
         driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),merchantEmail); 
             // for (String item : TestData.Emaillist ) {
                //  try {
                 //     driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),item);
                      //driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),item);
                   //   Thread.sleep(1000);
                     // if(driver.findElement(By.cssSelector("div[class = 'input email required merchant_email field_with_errors field_with_hint']")).isDisplayed())
                      //{
                        //  try {
                          //    str = str+1;
                            //  System.out.println("str value is "+str);
                             
                              //desc = "Email field should be valida format. User must put valid email to start signup The value" +item +"is invalid format for signup email";
                             // Demo d31 = new Demo(str,"User details - Invalid format email field validation",desc,"Pass");
                              //Map<Integer, Demo > testresultdata = new HashMap<>();
                              //testresultdata.put(d31.getkey(),d31);
                              //Thread.sleep(1000);
                          //} catch (InterruptedException ex) {
                            //  Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                          //}
                          
                      //}
                      //else
                      //{
                        //  try {
                          //    str = str+1;
                            //  System.out.print("str value is " +str);
                             // System.out.print("div is not visible");
                             // desc = "Email entered should be valid format.User must put an email in valid format to start sign up page";
                              //Demo d31 = new Demo(str,"User details - Invalid format email field validation",desc,"Fail");
                              //Map<Integer, Demo > testresultdata = new HashMap<>();
                              //testresultdata.put(d31.getkey(),d31);
                              //Thread.sleep(1000);
                          //} catch (InterruptedException ex) {
                           //   Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                          //}
                //      } 
               //catch (InterruptedException ex) {
             ////         Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                //  }
               //     }
                
		}
    
    public static void Set_EmailConfirmation(){
               // String emailmatch1 = "test123@gmail.com";
              //  String  emailmatch2 = "test123@gmail.com";
              //  driver.findElement(By.id("merchant_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"),emailmatch1); 
                //driver.findElement(By.id("merchant_email_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"),emailmatch2); 
                
                
      
                         
        	}

        	  
        	    
		
    
     public static void set_Password(){
		driver.findElement(By.id("merchant_password")).sendKeys("Wirecard54321"); 
             
		}
        public static void set_PasswordConfirmation(){
        try {
            driver.findElement(By.id("merchant_password_confirmation")).sendKeys("Wirecard54321");
            Thread.sleep(500);
            if (!"Lexware Signup".equals(DriverScript.SignupFORTest))
            {
                driver.findElement(By.linkText("Weiter")).click();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }
                
		}
         public static void set_username(){
                GenerateData genData = new GenerateData();
                String username = genData.generateRandomString(3) + "Username";
		driver.findElement(By.id("merchant_external_id")).sendKeys(username); 
                if ("Accept Pay CEE Austria".equals(DriverScript.SignupFORTest))
                {
                    
                } else {
                    driver.findElement(By.id("merchant_external_id_confirmation")).sendKeys(username);
        }
                        
		}
        
    }
     
        
       
    

