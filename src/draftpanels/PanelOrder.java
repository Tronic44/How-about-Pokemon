package draftpanels;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import client.Manage;

@SuppressWarnings("serial")
public class PanelOrder extends JPanel {

	private JPanel panel = new JPanel();
	private int order = 0;

	public PanelOrder() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblorder = new JLabel("Wie soll gedraftet werden?");
		lblorder.setFont(new Font(Manage.FONT, Font.PLAIN, 15));
		lblorder.setBounds(110, 26, 183, 27);
		panel.add(lblorder);

		JCheckBox chckbxRandom = new JCheckBox("Random");
		JCheckBox chckbxManuell = new JCheckBox("Manuell");
		chckbxManuell.addActionListener(e -> {
			if (chckbxManuell.isSelected()) {
				order = 1;
			}
			chckbxRandom.setSelected(false);
		});
		chckbxManuell.setBounds(153, 69, 97, 23);
		panel.add(chckbxManuell);

		JTextPane txtpnJederzeitZwischenTeanms = new JTextPane();
		txtpnJederzeitZwischenTeanms.setText("Jederzeit zwischen Teams wechseln, beliebig viele Pokemon auswählen");
		txtpnJederzeitZwischenTeanms.setBounds(74, 108, 254, 39);
		panel.add(txtpnJederzeitZwischenTeanms);

		chckbxRandom.addActionListener(e -> {
			if (chckbxRandom.isSelected()) {
				order = 2;
				randomiseTeams();
			}
			chckbxManuell.setSelected(false);
		});
		chckbxRandom.setBounds(153, 163, 97, 23);
		panel.add(chckbxRandom);

		JTextPane txtpnZuflligeReihenfolgeImmer = new JTextPane();
		txtpnZuflligeReihenfolgeImmer.setText("Zufällige Reihenfolge, immer ein Pokemon\r\n");
		txtpnZuflligeReihenfolgeImmer.setBounds(74, 202, 254, 39);
		panel.add(txtpnZuflligeReihenfolgeImmer);

		add(panel);
	}

	private void randomiseTeams() {
		String[] teamfolge;
		String[] spieler = Gui.getwindow().getPanelPlayer().player
				.toArray(new String[Gui.getwindow().getPanelPlayer().player.size()]);
		teamfolge = new String[spieler.length * 2];
		int[] random = new int[spieler.length];
		for (int i = 0; i < random.length; i++) {
			int rd = (int) (Math.random() * spieler.length);
			for (int j = 0; j < i; j++) {
				if (rd == random[j]) {
					rd = -1;
					break;
				}
			}
			if (rd >= 0) {
				random[i] = rd;
			} else {
				i -= 1;
			}
		}
		for (int k = 0; k < teamfolge.length; k++) {
			if (k < spieler.length) {
				teamfolge[k] = spieler[random[k]];
			} else {
				teamfolge[k] = teamfolge[teamfolge.length - k - 1];
			}
		}
	}

	protected int getOrder() {
		return order;
	}
}