/**
 * ActionKeywords
 * The class defines all the actions taken by Automation framework.   
 * 
 *
 * @author Payal
 * @version 1.00, 01 Mar 2016
 */
package Config;

import DataEngine.Constants;
import GroovyScripts.M1SignupGroovyscript;
import executionengine.DriverScript;
import Utility.SendTestReport;
import static executionengine.DriverScript.Browsertotest;
import static executionengine.DriverScript.SelectAction;
import executionengine.M1SignupTestSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
 
public class ActionKeywords {
 
		public static WebDriver driver;
               
                
	public static void Open_Browser(){	
              /**
     *
     * Open Browser Method.
     * <br />
     * The method open browser using selenium web driver. There are three options. User can select one of these options. 
     *
     * @param message String variable to be printed
     * @see Open_browser
     * @deprecated
     * @since version 1.00
     */
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
                        /**
     *
     * Open Browser Method.
     * <br />
     * The method navigate required SIGHUP page using selenium web driver.
     *
     * @param message String variable to be printed
     * @see Open_browser
     * @deprecated
     * @since version 1.00
     */
		//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
                System.out.print(DriverScript.SignupFORTest);
                   switch(DriverScript.SignupFORTest )
                           {
                                case "M1 Signup Page":   driver.get(Constants.M1_URL);
                                  
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
                                case "M1 Signup Page":   
                                                           if ("Create Merchant".equals(SelectAction))
        {
           // M1SignupTestSuite m2 = new M1SignupTestSuite();
            //m2.LexwareSignUpTestSuite();
            m1SignupCreatemerchant m1 = new m1SignupCreatemerchant();
                                                         m1.createMerchant();
        }
        else
        {
            M1SignupGroovyscript m3 =new M1SignupGroovyscript();
            m3.m1groovy(driver);
        }
                                
                                                         break;
		                 
                                case "Lexware Signup" :
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
                                                                  
                                                                 
                                                                 CompanyInformation.set_website();
                                                                 CompanyInformation.set_address();
                                                                 
                                                                 CompanyInformation.select_merchantlegalform();
                                                                  CompanyInformation.select_branche();
                                                                 
                                                                 BankDetailsModule.set_paymentInformation();
                                                                 SubmitApplicationModule.select_submitcheckbox();
                                                                 // send_testReport();
                                                                 
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
        public static void send_testReport(){
            {
                SendTestReport st = new SendTestReport();
                st.sendemail();
            }
        }
 
	}