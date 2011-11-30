package com.gogbuehi.android.cc2.utilities;

import com.gogbuehi.android.cc2.exceptions.BadLocationException;

import android.content.Context;
import android.text.Editable;
import android.widget.EditText;

/**
 * This class is meant to emulate some of the functionality available in 
 * Java Swing's JTextArea class
 * 
 * Note: This does not accurately emulate the JTextArea functionality. It is only
 * implemented to fulfill the needs of the code I ported from my old Java application.
 * 
 * @author goodwin
 *
 */
public class JEditText extends EditText {
	String mText;
	int [] mNewLinePositions;
	public JEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public JEditText(EditText editText) {
		super(editText.getContext());
		setLineOffsets();
	}
	public String getAllText() {
		return mText;
	}
	private void setLineOffsets() {
		mText = getText().toString();
		mNewLinePositions = new int[getLineCount()];
		int lastNewLineIndex = -1;
		for (int i = 0; i < mNewLinePositions.length; i++) {
			lastNewLineIndex = mText.indexOf(StringUtil.NEWLINE,lastNewLineIndex);
			mNewLinePositions[i] = lastNewLineIndex;
		}
	}
	
	public int getLineOfOffset(int caretPosition) throws BadLocationException {
		if (caretPosition >= mText.length()) {
			throw new BadLocationException();
		}
		for (int i = 0; i < mNewLinePositions.length; i++) {
			if (mNewLinePositions[i] > caretPosition)
				return i+1;
		}
		return getLineCount();
	}
	
	public int getCaretPosition() {
		int selectionStart = getSelectionStart();
		int selectionEnd = getSelectionEnd();
		if (selectionStart != selectionEnd)
			return -1; //We don't know where there Caret is!
		return selectionStart;
	}
	public int getLineStartOffset(int line) throws BadLocationException {
		if (line < 1 || line > getLineCount()) {
			throw new BadLocationException();
		}
		if (line == 1) {
			return 0;
		}
		return mNewLinePositions[line-1]+1;
	}
	
	public int getLineEndOffset(int line) throws BadLocationException {
		if (line < 1 || line > getLineCount()) {
			throw new BadLocationException();
		}
		if (line == getLineCount()) {
			return mText.length();
		}
		return mNewLinePositions[line-1];
	}
	
	public void replaceRange(String text,int lineStart,int lineEnd) {
		Editable e = getText();
		e.replace(lineStart, lineEnd, text);
		setLineOffsets();
	}
	
	public void insert(String caseBreak, int lineOffset) {
		//TODO: This was for Sotech. We don't need this anymore
	}
	//FIXME: Refactor to just use setSelection
	public void setCaretPosition(int position) {
		setSelection(position);
	}

}
