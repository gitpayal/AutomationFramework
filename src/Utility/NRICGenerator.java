/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import static Config.ActionKeywords.driver;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author payal.verma
 */
public class NRICGenerator {
    public String nric_generator() throws InterruptedException
{
	 File pathfirefox = new File("C:\\Program Files\\Java\\firefox.exe");
   FirefoxProfile profile = new FirefoxProfile();
   driver = new FirefoxDriver(new FirefoxBinary(pathfirefox), profile); 
   driver.get("http://xeroy.net/nric-generator/");
   Thread.sleep(5000);
   
   WebElement element = driver.findElement(By.xpath("//input[@name='xeroyNRIC' and @class='Textfield']"));
        try {
            //JavascriptExecutor executor = (JavascriptExecutor)driver;
            //executor.executeScript("arguments[0].click();", element);
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NRICGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
   String nric = element.getAttribute("value");
   System.out.print("NRIC is generated : " +nric);
   return nric;

   
	
}
    
    
}
