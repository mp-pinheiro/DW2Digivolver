import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class JTextPaneOutput extends JTextPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3815312397037701915L;
	private JPanelDisplay context;
	
	public JTextPaneOutput(JPanelDisplay context) {
		this.context = context;
	}

	public void print(String string, Color colour){
		setEditable(true);
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, colour);
		int len = this.getDocument().getLength();
		setCaretPosition(len);
		setCharacterAttributes(aset, false);
		replaceSelection(string);
		setEditable(false);
	}
	
	public void print(String string){
		setEditable(true);
		Color colour = Color.BLACK;
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, colour);
		int len = this.getDocument().getLength();
		setCaretPosition(len);
		setCharacterAttributes(aset, false);
		replaceSelection(string);
		setEditable(false);
	}
	
	public void println(String string){
		print(string+"\n");
	}
	
	public void println(String string, Color colour){
		print(string+"\n", colour);
	}
	
	public void println(){
		print("\n");
	}
	
	public void clear(){
		if(context.txtClear) setText("");
	}
}
