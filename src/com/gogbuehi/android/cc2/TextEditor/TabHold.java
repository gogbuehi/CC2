package com.gogbuehi.android.cc2.TextEditor;

import android.widget.EditText;

import com.gogbuehi.android.cc2.exceptions.BadLocationException;
import com.gogbuehi.android.cc2.utilities.JEditText;
import com.gogbuehi.android.cc2.utilities.StringUtil;
import com.gogbuehi.android.cc2.utilities.TestInfo;

//import javax.swing.JTextArea;
//import javax.swing.text.BadLocationException;


/**
 * Created Tuesday, November 29, 2011
 * @author Goodwin Ogbuehi
 *
 */
public class TabHold {

    static final int TABWIDTH = 8;
	JEditText jTA; //Naming is legacy from CC1 Java application; Used to be a JTextArea
	/** Get the text of the line where the cursor is at */
    public String getLine() {
        /*
         int line,lineStart,lineEnd;
        String ret;
        try {
            line = jTA.getLineOfOffset(jTA.getCaretPosition());
            lineStart = jTA.getLineStartOffset(line);
            lineEnd = jTA.getLineEndOffset(line);
            //lineEnd += checkForNewline(jTA.getText().charAt(lineEnd-1));
            ret = jTA.getText().substring(lineStart,lineEnd);
            TestInfo.testWriteLn("getLine(): Success");
        }
        catch (BadLocationException e) {
            TestInfo.testWriteLn("Error: getLine(): Bad Location Exception");
            return "";
        }
        return ret;
         */
        int line;
        try {
        	
            line = jTA.getLineOfOffset(jTA.getCaretPosition());
            TestInfo.testWriteLn("Line: " + line);
            return getLine(line);
        }
        catch (BadLocationException e) {
            TestInfo.testWriteLn("Error: getLine(): Bad Location Exception");
            return "";
        }
    }
    
    /** Get the text of the line specified */
    public String getLine(int line) {
        int lineStart,lineEnd;
        String ret;
        try {
            lineStart = jTA.getLineStartOffset(line);
            lineEnd = jTA.getLineEndOffset(line);
            //lineEnd += checkForNewline(jTA.getText().charAt(lineEnd));
            if (lineStart == lineEnd) {
                ret = "";
            }
            else if(String.valueOf(jTA.getText().charAt(lineEnd-1)).equals(StringUtil.NEWLINE)) {
                ret = jTA.getAllText().substring(lineStart,lineEnd-1);
            }
            else {
                ret = jTA.getAllText().substring(lineStart,lineEnd);
            }
            //TestInfo.testWriteLn("getLine(): Success");
            //TestInfo.testWriteLn(ret);
            //TestInfo.testWriteLn("Start: " + lineStart);
            //TestInfo.testWriteLn("End: " + lineEnd);
        }
        catch (BadLocationException e) {
            TestInfo.testWriteLn("Error: getLine(int): Bad Location Exception");
            return "";
        }
        TestInfo.testWriteLn("Text: " + ret);
        return ret;
    }
    
    /** Get the text of the line specified */
    public static String getLine(JEditText newJTA, int line) {
    	JEditText jTA = newJTA;
        int lineStart,lineEnd;
        String ret;
        try {
            lineStart = jTA.getLineStartOffset(line);
            lineEnd = jTA.getLineEndOffset(line);
            //lineEnd += checkForNewline(jTA.getText().charAt(lineEnd));
            if (lineStart == lineEnd) {
                ret = "";
            }
            else if(String.valueOf(jTA.getText().charAt(lineEnd-1)).equals(StringUtil.NEWLINE)) {
                ret = jTA.getAllText().substring(lineStart,lineEnd-1);
            }
            else {
                ret = jTA.getAllText().substring(lineStart,lineEnd);
            }
            //TestInfo.testWriteLn("getLine(): Success");
            //TestInfo.testWriteLn(ret);
            //TestInfo.testWriteLn("Start: " + lineStart);
            //TestInfo.testWriteLn("End: " + lineEnd);
        }
        catch (BadLocationException e) {
            TestInfo.testWriteLn("Error: getLine(int): Bad Location Exception");
            return "";
        }
        return ret;
    }
    
    private static int checkForNewline(char c) {
        if (String.valueOf(c).equals(StringUtil.NEWLINE))
            return -1;
        return 0;
    }
    
    public void setLine(String text, int line) {
        int lineStart,lineEnd;
        try {
            lineStart = jTA.getLineStartOffset(line);
            lineEnd = jTA.getLineEndOffset(line);
            if (lineStart == lineEnd) {
                //ret = "";
                //Do nothing
                jTA.replaceRange(text,lineStart,lineEnd);
            }
            else if(String.valueOf(jTA.getText().charAt(lineEnd-1)).equals(StringUtil.NEWLINE)) {
                //ret = jTA.getText().substring(lineStart,lineEnd-1);
                jTA.replaceRange(text,lineStart,lineEnd-1);
            }
            else {
                //ret = jTA.getText().substring(lineStart,lineEnd);
                jTA.replaceRange(text,lineStart,lineEnd);
            }
            
            
            //jTA.replaceRange(text+newline,lineStart,lineEnd);
        }
        catch (BadLocationException e) {
            //return "";
        }
    }
    public void setLine(String text) {
        int line;
        try {
            line = jTA.getLineOfOffset(jTA.getCaretPosition());
            setLine(text,line);
        }
        catch (BadLocationException e) {
            //return "";
        }
    }
    
    
    
    
    
    /** Get the whitespace that is the prefix of the given line of text */
    public String getIndent(int line) {
        return StringUtil.getIndent(getLine(line));
    }
    
    /** Set the whitespace that is the prefix of the line the cursor is at */
    public void setIndent(String whiteSpace) {
        int line = getRow();
        setIndent(whiteSpace,line);
        /*String strLine = getLine();
        String strWhite = getIndent(strLine);
        strLine = strLine.substring(strWhite.length());
        strLine = whiteSpace + strLine;
        setLine(strLine);
         */
    }
    
    /** Set the whitespace that is the prefix of the line the cursor is at */
    public void setIndent(String whiteSpace, int line) {
        //int line = getRow();
        String strLine = getLine(line);
        String strWhite = StringUtil.getIndent(strLine);
        //TestInfo.testWriteLn("Indent for line " + String.valueOf(line) + " |~" + strWhite);
        //TestInfo.testWriteLn("Indent from line " + String.valueOf(line-1) + " |~" + whiteSpace);
        strLine = strLine.substring(strWhite.length());
        //TestInfo.testWriteLn("Text for line " + String.valueOf(line) + " |~" + strLine);
        strLine = whiteSpace + strLine;
        setLine(strLine,line);
    }
    
    
    /** Gets the row that the cursor is at */
    public int getRow() {
        try {
            return jTA.getLineOfOffset(jTA.getCaretPosition());
        }
        catch (BadLocationException e) {
            return -1;
        }
    }
    
    /** Gets the row that the cursor is at */
    public int getCol() {
        int pos,row,rowOffset;
        try {
            pos = jTA.getCaretPosition();
            row = getRow();
            rowOffset = jTA.getLineStartOffset(row);
            return (pos-rowOffset);
        }
        catch (BadLocationException e) {
            return -1;
        }
    }
    
    public int getCalculatedCol() {
        int pos,row,rowOffset,calcCol;
        String text = jTA.getAllText();
        try {
            calcCol = 0;
            pos = jTA.getCaretPosition();
            row = getRow();
            rowOffset = jTA.getLineStartOffset(row);
            //calcCol = pos - rowOffset;
            /*
            for (col = 0; col < thisLine.length(); col++) {
                if (ATAB.equals(String.valueOf(thisLine.charAt(col)))) {
                    colCount = colCount + (tabWidth - colCount%tabWidth) + 1;
                }
                else
                    colCount++;
            }
             */
            for (int i = 0; i < (pos - rowOffset); i++) {
                if (String.valueOf(text.charAt(rowOffset + i)).equals(StringUtil.ATAB))
                    calcCol += (TABWIDTH - (calcCol%TABWIDTH));
                else
                    calcCol ++;
            }
            return calcCol;
        }
        catch (BadLocationException e) {
            return -1;
        }
    }
    
    /** Set the JTextArea associated with this TabHold */
    public void setJTA(JEditText newJTA) {
        jTA = newJTA;
    }
    
    /** Get the JTextArea associated with this TabHold */
    public JEditText getJTA() {
        return jTA;
    }
    
    /** Check for Tab Hold from previous line and apply it to the current line */
    public void processNewline() {
        //TestInfo.testWriteLn("----Testing Newline----");
        int line = getRow();
        if (line > 0) {
            //TestInfo.testWriteLn("Processing line " + line);
            String strIndent = getIndent(line-1);
            strIndent = StringUtil.formatIndent(strIndent);
            //TestInfo.testWriteLn("Indent:");
            //TestInfo.testWriteLn(strIndent);
            setIndent(strIndent,line);
            try {
                jTA.setCaretPosition(jTA.getLineStartOffset(line) + strIndent.length());
            }
            catch (BadLocationException e) {
                //Do nothing
            }
            if ((line-1) > 0) {
                String caseBreak = StringUtil.ATAB + "'****************************";
                if (getLine(line-2).equals(caseBreak)) {
                    //Do nothing
                }
                else {
                    if (getLine(line-1).length() >= 5) {
                        if (getLine(line-1).substring(0,5).toLowerCase().equals(StringUtil.ATAB + "case")) {
                            processCase();
                        }
                    }
                }
            }
            else {
                if (getLine(line-1).length() >= 5) {
                    if (getLine(line-1).substring(0,5).toLowerCase().equals(StringUtil.ATAB + "case")) {
                        processCase();
                    }
                }
            }
            /*
             try {
                jTA.setCaretPosition(jTA.getLineStartOffset(line)+strIndent.length());
            }
            catch (BadLocationException e) {
                //Do nothing
            }
             */
             
        }
        /*
        if (row > -1) {
            thisLine = getLine(text,row);
            pTabs = countSomething(thisLine);
            if (thisLine.length() >= 5) {
                if (thisLine.substring(0,5).toLowerCase().equals(caseText)) {
                    if (row > 0) {
                        if (prevLineCheck(getLine(text,row-1))) {
                            lineText.setText("Process Case");
                            processCase();
                        }
                        else
                            lineText.setText("Don't Process Case");
                    }
                    else {
                        lineText.setText("Process Case");
                        processCase();
                    }
                }
            }
         */
        //jTA.append("|+|" + String.valueOf(line) + "|+|");
    }
    public void processCase() {
        //Used in conjuction with processNewLine()
        String caseBreak = StringUtil.ATAB + "'****************************" + StringUtil.NEWLINE;
        int currentLine = getRow();
        try {
            int lineOffset = jTA.getLineStartOffset(getRow()-1);
            jTA.insert(caseBreak,lineOffset);
            //jTA.setCaretPosition(lineOffset + caseBreak.length());
            jTA.setCaretPosition(jTA.getLineStartOffset(currentLine+1) + 1);
            //pos += caseBreak.length();
        }
        catch(BadLocationException e) {
            //Do nothing;
        }
    }
	
}
