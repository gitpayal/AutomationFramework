/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import org.apache.commons.lang3.RandomStringUtils;
/**
 *
 * @author payal.verma
 */
public class GenerateData {
     public String generateRandomString(int length){
  return RandomStringUtils.randomAlphabetic(length);
 }
 
 public String generateRandomNumber(int length){
  return RandomStringUtils.randomNumeric(length);
 }
 
 public String generateRandomAlphaNumeric(int length){
  return RandomStringUtils.randomAlphanumeric(length);
 }
 
 public String generateStringWithAllobedSplChars(int length,String allowdSplChrs){
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    "1234567890" +   //numbers
    allowdSplChrs;
  return RandomStringUtils.random(length, allowedChars);
 }

  public String generateStringWithspace(int length,String allowdSplChrs){
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    allowdSplChrs +
    "abcdefghijklmnopqrstuvwxyz" ;  //numbers
   
  return RandomStringUtils.random(length, allowedChars);
 }
 
 public String generateEmail(int length) {
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    "1234567890" +   //numbers
    "_-.";   //special characters
  String email="";
  String temp=RandomStringUtils.random(length,allowedChars);
  email=temp.substring(0,temp.length()-9)+"@test.org";
  return email;
 }
 
 public String generateUrl(int length) {
  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    "1234567890";   //special characters
  String url="";
  String temp=RandomStringUtils.random(length,allowedChars);
  url="www" + "." +temp.substring(4,temp.length()-4)+ "." + "com";
  return url;
 }
}
