/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEngine;

/**
 *
 * @author payal.verma
 */
public class TestData {
    // Array of email, email confirmation, password, password confirmation
    public static final String[] Emaillist = {"test",//invalid format
                          "@example.co12m",// invalid format
                          ".com",// invalid format
                          "much.“moreunusual”@example.com",// invalid format
                          "very.unusual.“@”.unusual.com@example.com",//invalid format
                           "very.“(),:;<>[]”.VERY.“very@\\ very”.unusual@strange.example.com",// invalid format
                           "?????@example.com", //invalid format
                           "email@111.222.333.44444",// invalid format
                           "“(),:;<>[]@example.com"//invalid format
                          };
    
    public static final String[] Passwordlist = { "test", //minimum length check
	                  "12345678",// invalid format only numbers
	                  "@@@@@@@@@@@@@@@@@@@@",// invalid format only special characters
	                  "test123thisistestthat passwordshiuldnotgobeyondonehundredandtwentyeightcharactersinpassworkfield",// maximum length validation
	                  "123@12334",// only letters and special characters
	                  "!!!!!!!!!!!!!!AB"// only special characters and alphabets
                          };
      public static final String[] EmailConfirmation = {
                             "email@test.com",
                           "test@email.com"//invalid format
                          };
      
      public static final String[] DBA = {
           "",//Blank field validation
	"123",// numeric value 
	 "testerer!!",
	 "testerer!!",//Invalid format : special characters
	"This is test for DBA that it should not extend more than 20 chaacters",//maximum length validation
	"MASTERCARD"// special Banned word check
      };
      
       public static final String[] Bsummary = {
      "",//Blank field validation
	 "1232323",//numeric only : invalid field validation
	"This is test case which test the validations of all fields of signup page.Please provide the description of the nature of your business such as list of services rendered or goods sold. Note that description has to be at least 50 characters long and is an important part in our assessment of your application."
      };
       

      public static final String[] Website = {
      "",//Blank field validation
	 "www.123",
         "www.!!!.com",
         "www .com",
      };
        public static final String[] Firstname = {
      "",//Blank field validation
	 "www.123",
         "test123",
         "123!!",
      };
        
       public static final String[] PhoneNumber = {
         "",//Blank field validation
	 "test",
         "123",
         "!!12345678",
         "12345678"
      };
      public static final String[] CompStreet = {
         "",//Blank field validation
	 "@12324241",
         "308 Merry Abbey, Coyote Wells, WV, 25604-3199, US, (304) 485-4875",
        "test street 121"
         
      };
      
         public static final String[] housenumber = {
         "",//Blank field validation
	 "test",
         "1@@2",
        "12"
         
      };
       public static final String[] Poastalcode = {
         "",//Blank field validation
	 "test",
         "1@@2",
         "12345",
         "123456"
         
      };
          public static final String[] sve = {
         "",//Blank field validation
	 "test",
         "1@@2",
         "2000"
     
         
      };
    

        
      public static final String[] datevalidation = {
         "",//Blank field validation
	 " 12",
         " 12-13-2016",
         "22-22-2016",
         "test",
         "123455 "
     
         
      };
     public static final String[] bankcode = {
         "",//Blank field validation
	 "!!!",
         "12+"
  
      };
       public static final String[] Accountnamevalidation = {
         "test!!",//Blank field validation
	 "test45",
         "123",
         "!!12345678",
         "12345678+++",
         "123456789012345678901234567890"
  
      };
       

	   public static final String[] Accountnumbervalidation = {
         "test!!",//Blank field validation
	 "test12",
         "123",
          "!!12345678",
          "12345678+++",
         "123456789012345678901234567890"
  
      };

	   public static final String[] CompanyName = {
        "",//Blank field validation
	 "www!!123",
         "test|||123",
          "test",
          "121221222222222221",

      };
       	   public static final String[] CompanyRegistration = {
        "",//Blank field validation
	 "123",
         "!!12345678",
          "test",
          "123456781233333333333333333333333332121",

      };
       
}
