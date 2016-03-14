/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import static Config.ActionKeywords.driver;
import org.openqa.selenium.By;

/**
 *
 * @author payal.verma
 */
public class BankDetailsModule {
    //IBAN
     public static void set_paymentInformation(){
    driver.findElement(By.id("merchant_bank_account_holder_name")).sendKeys("Test Accountname"); 
    driver.findElement(By.id("merchant_bank_iban")).sendKeys("DE68210501700012345678");
    
    } 
    //ACCOUNTNUMBER
    
}
