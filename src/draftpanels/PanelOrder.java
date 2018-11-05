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
	private int[] teamfolge;

	public PanelOrder() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblorder = new JLabel("Wie soll gedraftet werden?");
		lblorder.setFont(new Font(Manage.FONT, Font.PLAIN, 15));
		lblorder.setBounds(110, 26, 183, 27);
		panel.add(lblorder);

		JCheckBox chckbxRandom = new JCheckBox("Random");		
		chckbxRandom.setEnabled(true);
		
		JCheckBox chckbxManuell = new JCheckBox("Manuell");
		chckbxManuell.addActionListener(e -> {
			if (chckbxManuell.isSelected()) {
				order = 1;
			}
			chckbxRandom.setSelected(false);
		});
		chckbxManuell.setBounds(153, 69, 97, 23);
		panel.add(chckbxManuell);
		
		chckbxRandom.addActionListener(e -> {
			if (chckbxRandom.isSelected()) {
				order = 2;
				randomiseTeams();
			}
			chckbxManuell.setSelected(false);
		});
		chckbxRandom.setBounds(153, 163, 97, 23);
		panel.add(chckbxRandom);

		JTextPane txtpnJederzeitZwischenTeanms = new JTextPane();
		txtpnJederzeitZwischenTeanms.setText("Jederzeit zwischen Teams wechseln, beliebig viele Pokemon auswählen");
		txtpnJederzeitZwischenTeanms.setBounds(74, 108, 254, 39);
		txtpnJederzeitZwischenTeanms.setEditable(false);
		panel.add(txtpnJederzeitZwischenTeanms);

		JTextPane txtpnZuflligeReihenfolgeImmer = new JTextPane();
		txtpnZuflligeReihenfolgeImmer.setText("Zufällige Reihenfolge, immer ein Pokemon\r\n");
		txtpnZuflligeReihenfolgeImmer.setBounds(74, 202, 254, 39);
		txtpnZuflligeReihenfolgeImmer.setEditable(false);
		panel.add(txtpnZuflligeReihenfolgeImmer);

		add(panel);
	}

	protected void randomiseTeams() {
		String[] spieler = DraftGui.getwindow().getPanelPlayer().player.toArray(new String[0]);
		int[] random = new int[spieler.length * 2];
		for (int i = 0; i < spieler.length; i++) {
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
		for (int k = spieler.length; k < random.length; k++) {
			random[k] = random[random.length - k - 1];
		}
		teamfolge = random;
	}

	protected int getOrder() {
		randomiseTeams();
		return order;
	}
	
	protected int[] getTeamfolge() {
		return teamfolge;
	}
}