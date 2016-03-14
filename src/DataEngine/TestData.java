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
    
}
