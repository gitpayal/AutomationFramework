/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author payal.verma
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PickNRICdata {

   
 public Map<String, Integer> nric_PickNRICdata()  throws FileNotFoundException
{
       Map<String,Integer> map = new HashMap<String,Integer>();
     for(int i = 0; i < 1000; ++i)
     {
        String s = choose(new File("H:\\ExecutionEngine\\src\\DataEngine\\nric.txt"));
        if(!map.containsKey(s))
           map.put(s, 0);
        map.put(s, map.get(s) + 1);
     }

     return map;
    
}
  public static String choose(File f) throws FileNotFoundException
  {
     String result = null;
     Random rand = new Random();
     int n = 0;
     for(Scanner sc = new Scanner(f); sc.hasNext(); )
     {
        ++n;
        String line = sc.nextLine();
        if(rand.nextInt(n) == 0)
           result = line;         
     }

     return result;      
  }
  
}
