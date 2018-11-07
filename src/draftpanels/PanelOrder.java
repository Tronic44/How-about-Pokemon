package draftpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import client.Manage;

@SuppressWarnings("serial")
public class PanelOrder extends JPanel {

	private JLayeredPane panel = new JLayeredPane();
	private int order = 0;
	private int[] teamfolge;

	public PanelOrder() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(409, 640, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 409, 640);
		panel.add(back);

		JLabel lblorder = new JLabel("Wie soll gedraftet werden?");
		lblorder.setFont(new Font(Manage.FONT, Font.PLAIN, 15));
		lblorder.setBounds(110, 26, 183, 27);
		panel.setLayer(lblorder, 1);
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
		chckbxManuell.setOpaque(false);
		panel.setLayer(chckbxManuell, 1);
		panel.add(chckbxManuell);

		chckbxRandom.addActionListener(e -> {
			if (chckbxRandom.isSelected()) {
				order = 2;
				randomiseTeams();
			}
			chckbxManuell.setSelected(false);
		});
		chckbxRandom.setBounds(153, 163, 97, 23);
		chckbxRandom.setOpaque(false);
		panel.setLayer(chckbxRandom, 1);
		panel.add(chckbxRandom);

		JTextPane txtpnJederzeitZwischenTeanms = new JTextPane();
		txtpnJederzeitZwischenTeanms.setText("Jederzeit zwischen Teams wechseln, beliebig viele Pokemon auswählen");
		txtpnJederzeitZwischenTeanms.setBounds(74, 108, 254, 39);
		panel.setLayer(txtpnJederzeitZwischenTeanms, 1);
		txtpnJederzeitZwischenTeanms.setOpaque(false);
		txtpnJederzeitZwischenTeanms.setBorder(BorderFactory.createLineBorder(Color.black));
		txtpnJederzeitZwischenTeanms.setEditable(false);
		panel.add(txtpnJederzeitZwischenTeanms);

		JTextPane txtpnZuflligeReihenfolgeImmer = new JTextPane();
		txtpnZuflligeReihenfolgeImmer.setText("Zufällige Reihenfolge, immer ein Pokemon\r\n");
		txtpnZuflligeReihenfolgeImmer.setBounds(74, 202, 254, 39);
		txtpnZuflligeReihenfolgeImmer.setOpaque(false);
		txtpnZuflligeReihenfolgeImmer.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayer(txtpnZuflligeReihenfolgeImmer, 1);
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