package executionengine;


import DataEngine.Constants;
import GroovyScripts.M1SignupGroovyscript;
import GroovyScripts.m1EmailDeduplication;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.JFrame;



 
public class DriverScript {
	
 public static String SignupFORTest;	
 public static  String merchantEmail;
  public static  String Browsertotest;
  public static String SelectAction;
		
	
 
    public static void main(String[] args) throws Exception {
   
    	//Declaring the path of the Excel file with the name of the Excel file
       
        String choice = ask("M1 Signup Page", "Lexware Signup", "Accept Pay", "Accept Pay CEE Austria");
        System.out.print(Constants.AcceptPay_URL);
        switch(choice)
         {
             case "M1 Signup Page":System.out.println("You choose " + choice);
                                   SignupFORTest = choice;
                                   //callm1signup();
                                    callM1SignupTestSuite();
                                   
                     break;
             case "Lexware Signup" : System.out.println("You choose " + choice);
                                     SignupFORTest = choice;
                                     callLexwareSignUpTestSuite();
                                     
                                    
                     break;
             case "Accept Pay" : System.out.println("You choose " + choice);
             SignupFORTest = choice;
                                 callAcceptpayTestsuite();
                                 
                                 
                                 break;
             case "Accept Pay CEE Austria" : System.out.println("You choose " + choice);
              SignupFORTest = choice;
                                 
                                 CEEaustriaAcceptPayTestSuite();
                                
                                 break;
         }
    }
     public static String ask(final String... values) {

        String result = null;
        
         

        if (EventQueue.isDispatchThread()) {

            JPanel panel = new JPanel();
              panel.setPreferredSize(new Dimension(250,280));
              panel.setVisible(true);
             //panel.add(new JLabel(new ImageIcon("H:\\ExecutionEngine\\src\\images\\Wirecard_logo.png"))); 
       panel.add(new JLabel(new ImageIcon(".\\src\\images\\Wirecard_logo.png"))); 
   



         
           
            panel.add(new JLabel("Please select a Signup Page to test : "));
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (String value : values) {
                model.addElement(value);
            }
            JComboBox comboBox = new JComboBox(model);
            panel.add(comboBox);
            
             panel.add(new JLabel("             Select Action :                            "));
             DefaultComboBoxModel modelchoose = new DefaultComboBoxModel();
            
            modelchoose.addElement("Create Merchant");
            modelchoose.addElement("Automate regression testing");
            
            JComboBox comboBoxchoose = new JComboBox(modelchoose);
            panel.add(comboBoxchoose);
            
             panel.add(new JLabel("              Select Browser :                           "));
             
             DefaultComboBoxModel modelbrowser = new DefaultComboBoxModel();
            
            modelbrowser.addElement("Firefox");
            modelbrowser.addElement("Internet explorer");
            modelbrowser.addElement("Chrome");
            
            
            JComboBox CBbrowser = new JComboBox(modelbrowser);
            panel.add(CBbrowser);
            
             panel.add(new JLabel(" Email id of Merchant :  "));
            
             JTextField JTemail = new JTextField(20);
             
             JTemail.setEditable(true);
              panel.add(JTemail);
             
             
             
             
            int iResult = JOptionPane.showConfirmDialog(null, panel, "Signup Page Automation test Framework", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (iResult) {
                case JOptionPane.OK_OPTION:
                    result = (String) comboBox.getSelectedItem();
                    merchantEmail = JTemail.getText();
                    Browsertotest = (String) CBbrowser.getSelectedItem();
                    SelectAction = (String) comboBoxchoose.getSelectedItem();
                    break;
            }

        } else {

            Response response = new Response(values);
            try {
                SwingUtilities.invokeAndWait(response);
                result = response.getResponse();
            } catch (InterruptedException | InvocationTargetException ex) {
            }

        }

        return result;

    }
     
   public static String askdeduplication(final String... values) {

        String result = null;

       

            JPanel panel = new JPanel();
            panel.add(new JLabel("Do you want to do deduplication test "));
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (String value : values) {
                model.addElement(value);
            }
            JComboBox comboBox = new JComboBox(model);
            panel.add(comboBox);

            int iResult = JOptionPane.showConfirmDialog(null, panel, "Signup Page Automation test Framework", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (iResult) {
                case JOptionPane.OK_OPTION:
                    result = (String) comboBox.getSelectedItem();
                    break;
            }

        

        return result;

    }
    
    
   public static void deduplication(){
        String choice = askdeduplication("Yes","No");
        
        switch(choice)
         {
             case "Yes":System.out.println("You choose " + choice);
                        System.out.println("De duplication test for signup page will start soon........");
                        callM1Signupdeduplicationtest();
                                   
                     break;
             case "No" : 
                                     break;
        
         }
    }
    private static void callLexwareSignUpTestSuite() {
        
     
        
        LexwareSignUpTestSuite lx = new LexwareSignUpTestSuite();
        lx.LexwareSignUpTestSuite();
       // lexwareGroovyscript lxg = new lexwareGroovyscript();
      //  lxg.lexwaregroovy();
        //LexwareSignUpTestSuite lx = new LexwareSignUpTestSuite();
        //lx.LexwareSignUpTestSuite();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void callm1signup(){
        try {
        M1SignupGroovyscript m1 =new M1SignupGroovyscript();
        m1.m1groovy();
        }
        catch(Exception ex)
        { 
            System.out.printf("Exception is " +ex);
        }
        
        
    }
    private static void callM1SignupTestSuite() {
        
      
        if ("Create Merchant".equals(SelectAction))
        {
            M1SignupTestSuite m1 = new M1SignupTestSuite();
            m1.LexwareSignUpTestSuite();
        }
        else
        {
            M1SignupGroovyscript m1 =new M1SignupGroovyscript();
            m1.m1groovy();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private static void callAcceptpayTestsuite() {
        AcceptpayTestsuite Accpay = new AcceptpayTestsuite();
        Accpay.LexwareSignUpTestSuite();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CEEaustriaAcceptPayTestSuite() {
        CEEaustriaAcceptPayTestSuite cee = new CEEaustriaAcceptPayTestSuite();
        cee.LexwareSignUpTestSuite();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void callM1Signupdeduplicationtest() {
        
        m1EmailDeduplication m1emailtest = new m1EmailDeduplication();
        m1emailtest.m1emaildeduplication();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    public static class Response implements Runnable {

        private String[] values;
        private String response;

        public Response(String... values) {
            this.values = values;
        }

        @Override
        public void run() {
            response = ask(values);
        }

        public String getResponse() {
            return response;
        }
    }


    
    
 }