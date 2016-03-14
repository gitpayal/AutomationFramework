/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import DataEngine.Constants;
import executionengine.DriverScript;
import static executionengine.DriverScript.Browsertotest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
 
public class ActionKeywords {
 
		public static WebDriver driver;
               
                
	public static void Open_Browser(){	
            if (    null != Browsertotest)
                    switch (Browsertotest) {
                        case "Firefox":
                            driver=new FirefoxDriver();
                           // DesiredCapabilities capability = DesiredCapabilities.firefox();  
                            System.out.print("Firefox  browser opens");
                            break;
                        case "Internet explorer":
                            driver = new InternetExplorerDriver();
                            System.out.print("Internet explorer opens");
                            break;
                        case "Chrome":
                            driver = new ChromeDriver();
                            System.out.print("Chrome driver opens");
                            break;
                        default:
                            break;
                    }
            }
 
	public static void Navigate_Signup(){	
		//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
                System.out.print(DriverScript.SignupFORTest);
                   switch(DriverScript.SignupFORTest )
                           {
                                case "M1 Signup Page":   driver.get(Constants.M1_URL);
                                    //driver = new RemoteWebDriver(new URL(Constants.M1_URL), capability);
                                                         System.out.print("M1 signup page opens");
                                                         
                                                         break;
		                 
                                case "Lexware Signup" : driver.get(Constants.Lexware_URL);
                                                         System.out.print("Lexware signup page opens");
                                                         break;
                                                        
                  
                                case "Accept Pay" : driver.get(Constants.AcceptPay_URL);
                                                        System.out.print("Acceptpay signup page opens");
                                                         break;
                                case "Accept Pay CEE Austria" : driver.get(Constants.CEEAcceptPay_URL);
                                                        System.out.print("CEE signup page opens");
                                                         break;
                           }
		}
 
	public static void Email_Check() throws InterruptedException{
             switch(DriverScript.SignupFORTest )
                           {
                                case "M1 Signup Page":   m1SignupCreatemerchant m1 = new m1SignupCreatemerchant();
                                m1.createMerchant();
                                                         break;
		                 
                                case "Lexware Signup" : //driver.get(Constants.Lexware_URL);
                                                         //driver.findElement(By.xpath("//input[@value='Vertrag abschließen und zahlungspflichtig bestellen' and @type='submit']")).click();
                                                        // System.out.print("Lexware signup page opens");
                                                        LoginModule.set_Email();
                                                        
                                                                
                                                                 LoginModule.set_Password();
                                                                 LoginModule.set_PasswordConfirmation();
                                                                 LoginModule.set_username();
           
                                                                 
                                                                 PersonalDetailsModule.select_checkbox();
                                                                 PersonalDetailsModule.set_personalInformation();
                                                                 PersonalDetailsModule.set_Address();
                                                                 
                                                                 CompanyInformation.set_companyname();
                                                                 CompanyInformation.set_controlNumber();
                                                                 CompanyInformation.set_registrationNumber();
                                                                  
                                                                 //CompanyInformation.set_companyYear();
                                                                 CompanyInformation.set_website();
                                                                 CompanyInformation.set_address();
                                                                 
                                                                 CompanyInformation.select_merchantlegalform();
                                                                  CompanyInformation.select_branche();
                                                                 
                                                                 BankDetailsModule.set_paymentInformation();
                                                                 SubmitApplicationModule.select_submitcheckbox();
                                                                 
                                                                 
                                                         break;
                                                        
                  
                                case "Accept Pay" : driver.get(Constants.AcceptPay_URL);
                                                    LoginModule.set_Email();
                                                                 LoginModule.Set_EmailConfirmation();
                                                                 LoginModule.set_Password();
                                                                 LoginModule.set_PasswordConfirmation();
                                                                 //LoginModule.set_username();
                                                                 
                                                                 CompanyInformation.select_merchantlegalform();
                                                               //  CompanyInformation.select_branche();
                                                                 CompanyInformation.set_companyname();
                                                                 CompanyInformation.set_controlNumber();
                                                                 CompanyInformation.set_registrationNumber();
                                                                 CompanyInformation.set_companyYear();
                                                                 CompanyInformation.set_website();
                                                                 CompanyInformation.set_address();
                                                                 
                                                                 PersonalDetailsModule.select_checkbox();
                                                                 PersonalDetailsModule.set_personalInformation();
                                                                 PersonalDetailsModule.set_Address();
                                                                 
                                                                 BankDetailsModule.set_paymentInformation();
                                                                 SubmitApplicationModule.select_submitcheckbox();
                                                       
                                                         break;
                                case "Accept Pay CEE Austria" : driver.get(Constants.CEEAcceptPay_URL);
                                                                 LoginModule.set_Email();
                                                                 LoginModule.Set_EmailConfirmation();
                                                                 LoginModule.set_Password();
                                                                 LoginModule.set_PasswordConfirmation();
                                                                 LoginModule.set_username();
                                                                 
                                                                 CompanyInformation.select_merchantlegalform();
                                                                 CompanyInformation.select_branche();
                                                                 CompanyInformation.set_companyname();
                                                                 CompanyInformation.set_controlNumber();
                                                                 CompanyInformation.set_registrationNumber();
                                                                 CompanyInformation.set_companyYear();
                                                                 CompanyInformation.set_website();
                                                                 CompanyInformation.set_address();
                                                                 
                                                                 PersonalDetailsModule.select_checkbox();
                                                                 PersonalDetailsModule.set_personalInformation();
                                                                 PersonalDetailsModule.set_Address();
                                                                 
                                                                 BankDetailsModule.set_paymentInformation();
                                                                 SubmitApplicationModule.select_submitcheckbox();
                                                                 
                                                         break;
                           }
		
	}
 

 
	public static void close_Browser(){
			driver.quit();
		}
 
	}