/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.internet.MimeBodyPart;


/**
 *
 * @author payal.verma
 */
public class SendTestReport {
    public void sendemail()
{
     String to = "payal.verma@wirecard.com";//change accordingly  
      String from = "payal.verma@wirecard.com";
      String host = "localhost";//or IP address  
  
     //Get the session object  
      
      Properties properties = System.getProperties();  
       properties.setProperty("wirecard.lan.hub", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

          // Set Subject: header field
         message.setSubject("Testing Subject");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("This is message body");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
        // multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
        // messageBodyPart = new MimeBodyPart();
       // String filename =  ".\\TestResult.xlsx";
         //String filename = "/home/manisha/file.txt";
        // DataSource source = new FileDataSource(filename);
         //messageBodyPart.setDataHandler(new DataHandler(source));
        // messageBodyPart.setFileName(filename);
        // multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
        // message.setContent(multipart);
       // message.setContent(messageBodyPart);
 
Transport.send(message);

         System.out.println("Sent message successfully....");
      }
       catch (MessagingException mex) {
         mex.printStackTrace();
      }
      
      
    
}
}
