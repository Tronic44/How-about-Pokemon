package draftpanels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.TextArea;

@SuppressWarnings("serial")
public class PanelAfterDraft extends JPanel {

	private JLayeredPane panel = new JLayeredPane();

	public PanelAfterDraft(String[][] draftergebnis) {
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		addbackground();

		TextArea textArea = new TextArea();
		textArea.setBounds(10, 10, 430, 280);
		textArea.setEditable(false);
		panel.add(textArea);

		for (String k : DraftGui.getwindow().getPanelPlayer().player) {
			textArea.append("\t" + k + "\t" + "\t");
		}
		textArea.append("\n");
		for (int m = 0; m < draftergebnis[0].length; m++) {
			for (int k = 0; k < draftergebnis.length; k++) {
				if (draftergebnis[k][m] != null) {
					textArea.append("\t" + draftergebnis[k][m] + "\t");
				}
			}
			textArea.append("\n");
		}
	}

	public PanelAfterDraft() {
		setLayout(null);
		addbackground();
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
	}
	private synchronized void addbackground() {
		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(409, 640, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 409, 640);
		panel.add(back);
	}
}