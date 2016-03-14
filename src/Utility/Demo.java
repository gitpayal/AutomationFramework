/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author payal.verma
 */
 public class Demo{
    private int key;
    private String Action;
    private String ExpectedResult;
    private String ActualResult;
   

    public Demo(int key,String Action, String ExpectedResult, String ActualResult){
        this.key=key;
        
        this.Action = Action;
        this.ExpectedResult = ExpectedResult;
        this.ActualResult = ActualResult;
       
    }
    public String getAction() {
        return Action;
    }
  
    public String getExpectedResult() {
        return ExpectedResult;
    }
    public String getActualResult() {
        return ActualResult;
    }
    public int getkey() {
        return key;
    }
    @Override
    public String toString() {
        return "Demo [key=" + key + ", Action=" + Action + ", ExpectedResult=" + ExpectedResult + ", ActualResult=" +ActualResult + "]";
    }

}  
