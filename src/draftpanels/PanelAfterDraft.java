package draftpanels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Image;
import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelAfterDraft extends JLayeredPane {

	/**
	 * Stellt das Panel
	 */
	private JLayeredPane panel = new JLayeredPane();
	/**
	 * Eine ArrayList aus TextArea's, die für jedes Team eins zur verfügung stellt
	 */
	private ArrayList<TextArea> listen = new ArrayList<>();
	/**
	 * Die Breite des Fensters
	 */
	private int panelwidth = 10;

	/**
	 * Initialisiert das Panel, den Hintergrund, und je Team ein TextArea mit den
	 * Pokemon
	 * 
	 * @param draftergebnis String[][] - die Ausgewählten, darzustellenden
	 *                      gedrafteten Pokemon
	 */
	public PanelAfterDraft(String[][] draftergebnis) {

		panel.setBounds(0, 0, 450, 400);
		panel.setLayout(null);
		panel.setVisible(true);

		JScrollPane scrollPane = new JScrollPane();
		panel.setLayer(scrollPane, 1);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 450, 400);
		scrollPane.setLayout(null);
		scrollPane.setVisible(true);
		scrollPane.setOpaque(false);
		panel.add(scrollPane);

		JButton btnAEndern = new JButton("Ändern");
		btnAEndern.addActionListener(e -> {
			DraftGui.getwindow().getPanelOrder().setOrder();
			DraftGui.getwindow().visDraft();
		});
		btnAEndern.setBounds(22, 10, 89, 25);
		scrollPane.add(btnAEndern);

		for (String k : DraftGui.getwindow().getPanelPlayer().player) {
			listen.add(new TextArea("\t" + k + "\n" + "\n", 15, 16, java.awt.TextArea.SCROLLBARS_VERTICAL_ONLY));
		}

		for (TextArea k : listen) {
			k.setBounds(panelwidth, 45, 128, 270);
			k.setEditable(false);
			scrollPane.add(k);
			panelwidth += 135;
		}

		for (int m = 0; m < draftergebnis.length; m++) {
			for (int k = 0; k < draftergebnis[0].length; k++) {
				if (draftergebnis[m][k] != null) {
					listen.get(m).append(draftergebnis[m][k] + "\n");
				}
			}
		}
		scrollPane.setBounds(0, 0, panelwidth + 30, 400);
		panel.setBounds(0, 0, panelwidth + 50, 450);

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(panelwidth + 30, 400, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, panelwidth + 30, 400);
		panel.add(back);

		add(panel);
	}
	/**
	 * @return die Breite des Panels {@link PanelAfterDraft}
	 */
	public int getwidth() {
		return panelwidth + 17;
	}
	/**
	 * leerer Konstruktor
	 */
	public PanelAfterDraft() {

	}
}