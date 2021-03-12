package View_Utilidades;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TeclasPermitidas extends PlainDocument{

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
		super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
	}

	public void replace(int offset, String str, AttributeSet attr) throws BadLocationException{
		super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
	}
	
	
	
	
}
