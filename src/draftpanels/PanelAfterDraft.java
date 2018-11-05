package draftpanels;

import javax.swing.JPanel;
import java.awt.TextArea;

@SuppressWarnings("serial")
public class PanelAfterDraft extends JPanel {

	private JPanel panel = new JPanel();

	public PanelAfterDraft(String[][] draftergebnis) {
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		TextArea textArea = new TextArea();
		textArea.setBounds(10, 10, 430, 280);
		textArea.setEditable(false);
		add(textArea);

		for (String k : DraftGui.getwindow().getPanelPlayer().player) {
			textArea.append("\t" + k + "\t"+"\t");
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

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
	}
}