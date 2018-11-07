package draftpanels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Font;

@SuppressWarnings("serial")
public class PanelAfterDraft extends JLayeredPane {

	private JLayeredPane panel = new JLayeredPane();

	public PanelAfterDraft(String[][] draftergebnis) {
		panel.setLayout(null);
		panel.setBounds(0, 0, 450, 350);
		panel.setVisible(true);

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(450, 320, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 450, 320);
		panel.setLayer(back, 0);
		panel.add(back);

		TextArea textArea = new TextArea();
		textArea.setBounds(10, 10, 430, 280);
		panel.setLayer(textArea, 1);
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
		setLayout(null);

		add(panel);
	}

	public PanelAfterDraft() {
		panel.setLayout(null);
		panel.setVisible(true);

		JLabel lblNewLabel = new JLabel("Du sollst hier nicht sein!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(98, 109, 254, 82);
		panel.setLayer(lblNewLabel, 0);
		add(lblNewLabel);

		panel.setBounds(0, 0, 409, 640);

		add(panel);
	}
}