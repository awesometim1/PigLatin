
/*
Tim Lee
PD 7
9/6/2017


PIG LATINO
leading consonant to end and append "ay"
1st letter=vowel? append "way"
No vowel?= INVALID 
Loop until quit wanted 
*/
//import java util for Scanner and io for File

import java.util.*;
import java.io.*;
public class PigLatin{

   //Private variables. Char[] used for storing char values of vowels 
   //vFirst used for storing char value of first
   //capital used for capitalization and word formatting
   
   private static char[] vowels;
   private static String original;
   private static boolean capital;
   private static boolean vFound;
   private static String piggy;
   
   
   public static void main(String[]args)throws Exception{
   
     mainMenu();
      
      
   }
   
   //PigLatinizing Method
   
   public static String pigLatinize(String s){
   
   //String to store final output string that has been piglatinized
      
      String pigified = "";
   
   
   //Loop for special cases, and regular piglatinizing purposes (Nested for Loop)
      String special = "";
      outloop:
      for (int i = 0; i < s.length(); i ++){
         for (int a = 0; a < vowels.length; a++){
            
            //Special Case Q&U        
                
            if (i!=0 && Character.toLowerCase(s.charAt(i)) == ("u").charAt(0) && Character.toLowerCase(s.charAt(i-1)) == ("q").charAt(0) ){
               special = s.toLowerCase();
               pigified = s.substring(special.indexOf("u") + 1) + special.substring(0, special.indexOf("u") + 1) + "ay";
              // System.out.println(capFormat(pigified));
               vFound = true;
               break outloop;
            }
            
            //Special Case Y
            
            else if (i == 0 && Character.toLowerCase(s.charAt(0)) == "y".charAt(0)){
               pigified = s.substring(1) + s.substring(0, 1).toLowerCase() + "ay";
              // System.out.println(capFormat(pigified));
               vFound = true;
               break outloop;
            }
            
            //If Not Special Case
            
            else if (s.charAt(0) == vowels[a]){
               pigified = s.toLowerCase() + "way";
              // System.out.println(capFormat(pigified));
               vFound = true;
               break outloop;
            }
               
               //Regular PigLatinizing Process
               
            else if (s.charAt(i) == vowels[a]){
               pigified = s.substring(i) + s.substring(0, 1).toLowerCase() + s.substring(1, i) + "ay";
              // System.out.println(capFormat(pigified));
               vFound = true;
               break outloop;
            }
            
            
         }            
      }
      return pigified;
   }
   
   
   
   
   
   //Format the Capital Letters
   public static String capFormat(String s){
      String ret = "";
      String fLetter = s.substring(0,1);
      if (capital == true){
         return fLetter.toUpperCase() + s.substring(1);      
      }
      else{
         return s;
      }
        
   }
   //reverse a String
   public static String reverse(String input){
      char[] in = input.toCharArray();
      int begin = 0;
      int end = in.length-1;
      char temp;
      while(end>begin){
         temp = in[begin];
         in[begin]=in[end];
         in[end] = temp;
         end--;
         begin++;
      }
      return new String(in);
   }
   
   public static void fileRW()throws Exception{
      Scanner file = new Scanner(System.in);
      String fileName = file.next();
   
      Scanner inFile = new Scanner(new File(fileName));
   
   
      while(inFile.hasNext()){
         try{
            PrintWriter writer = new PrintWriter(fileName + "Output.txt", "UTF-8");
            writer.println(mainPiggy(inFile.next()));
            writer.close();
         } catch (IOException e) {
          e.printStackTrace();
         }   
      
      
      }
   }
   public static void mainPiggy(String input){
      boolean run = true;
   
      while (run){
      //Variable Declarations
      
      //Char Array of Vowels
      
         String vowel = "aeiouyAEIOUY";
         vowels = vowel.toCharArray();
      
      //Were there any vowels?
      
         vFound = false;
      
         char leftright = "r".charAt(0);
            
      //Scanner to receive user input
      
         Scanner in = new Scanner(System.in);
         System.out.println("What to PigLatinize?");
         String input = in.next();
         original = input;
      
      //Punctuation Engine v1.0
         String punc = "";
         if (!Character.isLetter(original.charAt(0))){
            for (int i = 0; i < original.length(); i ++){
               if (Character.isLetter(original.charAt(i)))
                  break;
               else {
                  punc += original.charAt(i);
                  leftright = "l".charAt(0);
               }
            }
            input = input.substring(punc.length());
            punc += ",";
         }
         if (!Character.isLetter(original.charAt(original.length() - 1))){
            for (int i = original.length() - 1; i > 0; i --){
               if (Character.isLetter(original.charAt(i)))
                  break;
               else {
                  punc += original.charAt(i);
                  leftright = "r".charAt(0);
               }
            }
            input = input.substring(0, input.length() - (punc.length() - punc.indexOf(",") - 1 ));
         }
         String[] hi = punc.split(",");
      
      
      //Look for first letter if capitalized
         original = input;
         if (Character.isUpperCase(input.charAt(0)))
            capital = true;
         else
            capital = false;
         
      
      //PigLATINIZER
         String pig = pigLatinize(input);
      
         
      //If No Vowel Is Found
      
         if (vFound == false)
            System.out.println("****INVALID****");
         else
            pig = capFormat(pig);
      
      //OUTPUT
         if (hi.length > 1)
            piggy = (hi[0] + pig + reverse(hi[1]));
         else if (hi[0]!= null && leftright == "l".charAt(0))
            piggy = (hi[0] + pig);
         else if (hi[0]!= null && leftright == "r".charAt(0)){
            piggy = (pig + reverse(hi[0]));
         }
         else
            System.out.println(pig);
      
         Scanner iLoveAdvancedCompSciAB = new Scanner(System.in);
         System.out.println("Another One? Y/y = Yes N/n = No");
         String hardestLabSoFar = in.next();
      
         if (hardestLabSoFar.toLowerCase().equals("n")){
            run = false;
            System.out.println("Quitting...");
         }
      
      }
   
   
   
   }
   
   public static void mainMenu(){
      Scanner main = new Scanner(System.in);
      System.out.println("F/f for File reading and W/w for Word Translation R/r for Reverse");
      char choice = main.next().charAt(0);
      
      if (Character.toLowerCase(choice) == 'f')
      fileRW();
      else if (Character.toLowerCase(choice) == 'w')
      mainPiggy();
      else if (Character.toLowerCase(choice) == 'r')
      mainPiggy();
   
   
   }
}