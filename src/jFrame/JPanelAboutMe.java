package jFrame;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JPanelAboutMe extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577322959147655321L;
	private static final String version = "0.1.2";
	
	public JPanelAboutMe() {
		setLayout(null);
		
		JEditorPane jep = new JEditorPane("text/html", "");
		jep.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jep.setBackground(SystemColor.menu);
		jep.setText("<p align=\"center\" style=\"font-size:13.3;\">This software was developed by Matheus Preischadt and Lucas Preischadt under the MIT License. "
				+ "For more information, access <a href='https://opensource.org/licenses/MIT'>the open source initiative website</a>. "
				+ "All the DNA tables were extracted from Froste's DNA Digivolution Guide, which can be accessed "
				+ "<a href='http://www.gamefaqs.com/ps/437339-digimon-world-2/faqs/61067'>here</a>. All the "
				+ "digivolutions and DPs were extracted from Rena Chan's Digivolving Guide, which can also be accessed "
				+ "<a href='http://www.gamefaqs.com/ps/437339-digimon-world-2/faqs/11515'>here</a>. "
				+ "Skills table was extracted from MetalKid's guide, <a href='http://www.gamefaqs.com/ps/437339-digimon-world-2/faqs/64683'>here</a>. "
				+ "All the tables were adapted to be usable in "
				+ "the program, some data was modified. The data contained inside the tables <b>does not</b> fall under the MIT license, since each "
				+ "guide creator licensed it differently. Thank you for using it!</body></html>");
		jep.setBounds(10, 11, 430, 192);
		jep.setEditable(false);
		add(jep);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window window = SwingUtilities.getWindowAncestor(JPanelAboutMe.this);
				window.setVisible(false);
			}
		});
		btnClose.setBounds(173, 229, 89, 23);
		add(btnClose);
		
		JLabel lblVersion = new JLabel(version);
		lblVersion.setFont(new Font("Ubuntu Condensed", Font.PLAIN, 11));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setBounds(173, 210, 89, 14);
		add(lblVersion);
		
		jep.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent hle) {
				if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
					try {
                        Desktop.getDesktop().browse(new URI(hle.getURL().toString()));
	                } catch (URISyntaxException | IOException ex) {
	                        //It looks like there's a problem
	                }
				}
			}
		});
	}
}
