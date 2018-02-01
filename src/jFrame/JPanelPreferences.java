package jFrame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;

public class JPanelPreferences extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577322959147655321L;

	/**
	 * Create the panel.
	 */
	public JPanelPreferences(JPanelDisplay context) {
		setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window window = SwingUtilities.getWindowAncestor(JPanelPreferences.this);
				window.setVisible(false);
			}
		});
		btnClose.setBounds(73, 37, 89, 23);
		add(btnClose);
		
		JCheckBox chckbxAlwaysClearOutput = new JCheckBox("Always clear output.");
		chckbxAlwaysClearOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				context.txtClear = chckbxAlwaysClearOutput.isSelected();
			}
		});
		
		chckbxAlwaysClearOutput.setSelected(context.txtClear);
		
		chckbxAlwaysClearOutput.setBounds(6, 7, 201, 23);
		add(chckbxAlwaysClearOutput);
	}
}
