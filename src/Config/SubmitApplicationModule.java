/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import static Config.ActionKeywords.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author payal.verma
 */
public class SubmitApplicationModule {
    public static void select_submitcheckbox(){
          ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[card_readers_order]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
           ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[accepts_terms]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");
             ((JavascriptExecutor) driver).executeScript(" var checkboxes =  document.getElementsByName('merchant[accepts_contract]');for(var i=0, n=checkboxes.length;i<n;i++) { checkboxes[i].checked = true }");   
             driver.findElement(By.xpath("//input[@name='commit' and @type='submit']")).click();
    }
    
    
}
