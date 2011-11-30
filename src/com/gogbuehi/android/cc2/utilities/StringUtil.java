package com.gogbuehi.android.cc2.utilities;

/**
* Imported from source code of CC
* Import date: Tuesday, November 29, 2011
* The only changes made were:
*  - Changing TestInfo class out to use Android's logging system 
*  - Removed unused variables
* @author goodwin.ogbuehi
*/
public class StringUtil {
   public static final String NEWLINE = "\n";
   public static final String ATAB = String.valueOf('\u0009');
   public static final String ASPACE = " ";
   /** Creates a new instance of StringUtil */
   public StringUtil() {
   }
   
   public static String getLine(String text, int line) {
       int tempPos,tempStart,tempEnd;
       for (int pos = 0; pos <= text.length();) {
           //Get the Start Offset
           tempStart = pos;
           
           //Get the End Offset
           tempPos = text.indexOf("\n",pos);
           if (tempPos == -1) {
               //No more lines
               //End Offset is the end of the string
               tempEnd = text.length();
               pos = tempEnd + 1;
           }
           else {
               //There is one more line
               tempEnd = tempPos;
               pos = tempEnd + 1;
           }
           //TODO: Add Android logging
           /*
           TestInfo.testWriteLn("-----------------");
           TestInfo.testWrite("Row: " + row);
           TestInfo.testWrite(" - Start: " + tempStart);
           TestInfo.testWriteLn(" - End: " + tempEnd);
           */
           //Write line
           if (tempStart == tempEnd) {
           }
           else {
           }
           //TODO: TestInfo.testWriteLn(strLine);
       }
       return "";
   }
   
   /** Formats the indent so that it uses as many tabs as possible */
   public static String formatIndent(String whiteSpace) {
       int tabs,spaces,newTabs;
       char chTemp;
       String strTemp,ret;
       
       tabs = 0;
       spaces = 0;
       newTabs = 0;
       
       ret = "";
       
       for (int i = 0; i < whiteSpace.length(); i++) {
           chTemp = whiteSpace.charAt(i);
           strTemp = String.valueOf(chTemp);
           if (strTemp.equals(ASPACE))
               spaces++;
           else if (strTemp.equals(ATAB))
               tabs++;
       }
       if (spaces > 7) {
           newTabs = spaces / 8;
           spaces %= 8;
       }
       //Generate new indent
       for (int i = 0; i < tabs + newTabs; i++)
           ret += ATAB;
       for (int i = 0; i < spaces; i++)
           ret += ASPACE;
       
       return ret;
   }
   
   /** Get the whitespace that is the prefix of the given text */
   public static String getIndent(String text) {
       String ret = "";
       char chTemp;
       String strTemp;
       boolean boolA,boolB;
       for (int i = 0; i < text.length(); i++) {
           boolA = false;
           boolB = false;
           chTemp = text.charAt(i);
           strTemp = String.valueOf(chTemp);
           boolA = strTemp.equals(ASPACE);
           boolB = strTemp.equals(ATAB);
           if (boolA || boolB)
               ret += strTemp;
           else
               return ret;
       }
       //TODO: TestInfo.testWriteLn("Indent: " + ret);
       return ret;
   }
   
   public static boolean isWhitespace(String text) {
       //String ret = "";
       boolean ret = true;
       char chTemp;
       String strTemp;
       boolean boolA,boolB,boolC;
       for (int i = 0; i < text.length(); i++) {
           boolA = false;
           boolB = false;
           chTemp = text.charAt(i);
           strTemp = String.valueOf(chTemp);
           boolA = strTemp.equals(ASPACE);
           boolB = strTemp.equals(ATAB);
           boolC = strTemp.equals("\n");
           if (boolA || boolB || boolC)
               ;
           else
               return false;
       }
       //TestInfo.testWriteLn("Indent: " + ret);
       return ret;
   }
   public static String makeHex(int val) {
       String ret = "";
       String [] hexChars = new String [16];
       for (int i = 0; i < 10; i++) {
           hexChars[i] = String.valueOf(i);
       }
       hexChars[10] = "A";
       hexChars[11] = "B";
       hexChars[12] = "C";
       hexChars[13] = "D";
       hexChars[14] = "E";
       hexChars[15] = "F";
       int main,remain;
       while (val > 0) {
           main = val / 16;
           remain = val % 16;
           ret = hexChars[remain] + ret;
           val = main;
       }
       return ret;
   }
   
   public static int makeInt(String hex) {
       int ret = 0;
       String [] hexChars = new String [16];
       for (int i = 0; i < 10; i++) {
           hexChars[i] = String.valueOf(i);
       }
       hexChars[10] = "A";
       hexChars[11] = "B";
       hexChars[12] = "C";
       hexChars[13] = "D";
       hexChars[14] = "E";
       hexChars[15] = "F";
       int j;
       int k = 1;
       int l = hex.length() - 1;
       int m = hex.length() - 1;
       for (int i = 0; i < hex.length(); i++) {
           l = m - i;
           for (j = 0; j < hexChars.length; j++) {
               if (String.valueOf(hex.charAt(l)).equals(hexChars[j])) {
                   break;
               }
           }
           ret += k * j;
           k *= 16;
       }
       return ret;
   }
   /**
    * This method may no longer be necessary in Android
    * Anyhow, this needs some work to be more efficient
    * @param input
    * @return
    */
   public static String processUnicodeToASCII(String input) {
       //String input = entrArea.getText();
       String output = "";
       char [] cArray = input.toCharArray();
       int temp,temp1,temp3;
       char cTemp;
       int intVal = 256;
       String strTemp;
       for (int i = 0; i < cArray.length; i++) {
           strTemp = "";
           temp = Integer.valueOf(cArray[i]);
           temp1 = 0;
           temp3 = 0;
           if (temp >= intVal) {
               temp1 = temp / intVal;
               if (temp1 > intVal) {
                   temp3 = temp1 /intVal;
                   temp1 = temp1 % intVal;
                   if (temp3 > intVal) {
                       temp3 = temp3 % intVal;
                   }
               }
               
               /*
               if (temp1 >= 0)
                   //output += String.valueOf(cTemp1);
                   output += makeHex(temp1);
               if (temp2 >= 0)
                   //output += String.valueOf(cTemp2);
                   output += makeHex(temp2);
                */
               strTemp += makeHex(temp);
               //output += strTemp + "\n";
               //strGB = getGB(strTemp);
               //output += strGB + "\n";
               //temp1 = makeInt(strGB.substring(0,2));
               //temp2 = makeInt(strGB.substring(2));
               temp3 = makeInt(strTemp);
               //if (temp1 >= 0)
                   ;//output += String.valueOf(cTemp1);
                   //output += makeHex(temp1);
               //if (temp2 >= 0)
                   ;//output += String.valueOf(cTemp2);
               output += "&#" + String.valueOf(temp3) + ";";
                   //output += makeHex(temp2);
           }
           else {
               cTemp = (char) temp;
               output += String.valueOf(cTemp);
               //output += makeHex(temp) + "\n";
           }
       }
        // */
       //output = Convert.toBase64String(Convert.fromBase64String(input));
       //entrArea.setText(output);
       return output;
   }
}
