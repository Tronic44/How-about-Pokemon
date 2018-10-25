package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class PanelOrder extends JPanel {

	private JPanel panel;
	protected Boolean order;
	private String[] teamfolge;

	public PanelOrder() {
		JLabel lblorder = new JLabel("Wie soll gedraftet werden?");
		lblorder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblorder.setBounds(110, 26, 183, 27);
		panel.add(lblorder);

		JCheckBox chckbxRandom = new JCheckBox("Random");
		JCheckBox chckbxManuell = new JCheckBox("Manuell");
		chckbxManuell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxManuell.isSelected()) {
					order = true;
				} else {
					order = null;
				}
				chckbxRandom.setSelected(false);
			}
		});
		chckbxManuell.setBounds(153, 69, 97, 23);
		panel.add(chckbxManuell);

		JTextPane txtpnJederzeitZwischenTeanms = new JTextPane();
		txtpnJederzeitZwischenTeanms.setText("Jederzeit zwischen Teanms wechseln, beliebig viele Pokemon auswählen");
		txtpnJederzeitZwischenTeanms.setBounds(74, 108, 254, 39);
		panel.add(txtpnJederzeitZwischenTeanms);

		chckbxRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxRandom.isSelected()) {
					order = false;
					randomiseteam();
				} else {
					order = null;
				}
				chckbxManuell.setSelected(false);
			}
		});
		chckbxRandom.setBounds(153, 163, 97, 23);
		panel.add(chckbxRandom);

		JTextPane txtpnZuflligeReihenfolgeImmer = new JTextPane();
		txtpnZuflligeReihenfolgeImmer.setText("Zufällige Reihenfolge, immer ein Pokemon\r\n");
		txtpnZuflligeReihenfolgeImmer.setBounds(74, 202, 254, 39);
		panel.add(txtpnZuflligeReihenfolgeImmer);

		add(panel);

	}
	protected void randomiseteam() {
		String[] Spieler = Gui.getwindow().getPanel_player().Spieler;
		teamfolge = new String[Spieler.length * 2];
		int[] random = new int[Spieler.length];
		for (int i = 0; i < random.length; i++) {
			int rd = (int) (Math.random() * Spieler.length);
			for (int j = 0; j < i; j++) {
				if (rd == random[j]) {
					rd = -1;
					break;
				}
			}
			if (rd >= 0) {
				random[i] = rd;
			} else {
				i--;
			}
		}
		for (int k = 0; k < teamfolge.length; k++) {
			if (k < Spieler.length) {
				teamfolge[k] = Spieler[random[k]];
			} else {
				teamfolge[k] = teamfolge[teamfolge.length - k - 1];
			}
		}
	}
}
