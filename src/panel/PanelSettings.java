package panel;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.PopupMenuEvent;
import client.Data;
import client.Manage;
import client.PopupListener;

@SuppressWarnings("serial")
public class PanelSettings extends JPanel {

	private JPanel panel = new JPanel();
	private JLabel lblTier1;
	private JLabel lblTier2;
	private JLabel lblTier3;
	private JLabel lblTier4;
	private JLabel lblTier5;
	private JLabel lblTier6;
	protected JLabel lblbest;
	protected JCheckBox cBS;
	protected JCheckBox cBA;
	protected JCheckBox cBB;
	protected JCheckBox cBC;
	protected JCheckBox cBD;
	protected JCheckBox cBE;
	private JTextField tF1;
	private JTextField tF2;
	private JTextField tF3;
	private JTextField tF4;
	private JTextField tF5;
	private JTextField tF6;
	private JComboBox<String> comboBoxS;
	private JComboBox<String> comboBoxA;
	private JComboBox<String> comboBoxB;
	private JComboBox<String> comboBoxC;
	private JComboBox<String> comboBoxD;
	private JComboBox<String> comboBoxE;
	private JLabel lblTierS;
	private JLabel lblTierA;
	private JLabel lblTierB;
	private JLabel lblTierC;
	private JLabel lblTierD;
	private JLabel lblTierE;
	private boolean[] change = new boolean[6];
	protected int[] countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelSettings() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		lblTier1 = new JLabel("S:");
		lblTier1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier1.setBounds(32, 83, 56, 17);
		panel.add(lblTier1);

		lblTier2 = new JLabel("A:");
		lblTier2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier2.setBounds(32, 123, 56, 17);
		panel.add(lblTier2);

		lblTier3 = new JLabel("B:");
		lblTier3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier3.setBounds(32, 164, 56, 17);
		panel.add(lblTier3);

		lblTier4 = new JLabel("C:");
		lblTier4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier4.setBounds(32, 204, 56, 17);
		panel.add(lblTier4);

		lblTier5 = new JLabel("D:");
		lblTier5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier5.setBounds(32, 247, 56, 17);
		panel.add(lblTier5);

		lblbest = new JLabel("");
		lblbest.setBounds(168, 346, 206, 14);
		panel.add(lblbest);

		lblTier6 = new JLabel("E:");
		lblTier6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier6.setBounds(32, 290, 56, 17);
		panel.add(lblTier6);

		JTextPane txtpnAchtungnderungenHier = new JTextPane();
		txtpnAchtungnderungenHier.setEditable(false);
		txtpnAchtungnderungenHier.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnAchtungnderungenHier.setText(
				"Achtung: Änderungen hier führen zu einer\r\nAnpassung der aktuell eingegenbenen Tierlist. \r\nWenn du das nicht möchtest, speichere sie vorher!");
		txtpnAchtungnderungenHier.setBounds(40, 11, 323, 51);
		panel.add(txtpnAchtungnderungenHier);

		JButton btnsafetier = new JButton("Bestätige");
		btnsafetier.addActionListener(e -> {
			if (!cBS.isSelected() && !cBA.isSelected() && !cBB.isSelected() && !cBC.isSelected() && !cBD.isSelected()
					&& !cBE.isSelected()) {
				Manage.msgbox("Kein Tier? So geht das aber nicht!", Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			if ((cBS.isSelected() && tF1.getText().trim().length() < 1)
					|| (cBA.isSelected() && tF2.getText().trim().length() < 1)
					|| (cBB.isSelected() && tF3.getText().trim().length() < 1)
					|| (cBC.isSelected() && tF4.getText().trim().length() < 1)
					|| (cBD.isSelected() && tF5.getText().trim().length() < 1)
					|| (cBE.isSelected() && tF6.getText().trim().length() < 1)) {
				Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?",
						Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			if ((cBS.isSelected() && comboBoxS.getSelectedIndex() < 0)
					|| (cBA.isSelected() && comboBoxA.getSelectedIndex() < 0)
					|| (cBB.isSelected() && comboBoxB.getSelectedIndex() < 0)
					|| (cBC.isSelected() && comboBoxC.getSelectedIndex() < 0)
					|| (cBD.isSelected() && comboBoxD.getSelectedIndex() < 0)
					|| (cBE.isSelected() && comboBoxE.getSelectedIndex() < 0)) {
				Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
						+ "Wo gibt es denn sowas?", Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			String[] tiernamenlist = new String[] { tF1.getText().trim(), tF2.getText().trim(), tF3.getText().trim(),
					tF4.getText().trim(), tF5.getText().trim(), tF6.getText().trim() };
			for (int k = 0; k < tiernamenlist.length; k++) {
				for (int j = 0; j < tiernamenlist.length; j++) {
					if (tiernamenlist[k].equals("") || tiernamenlist[j].equals("") || j == k) {
						continue;
					}
					if (tiernamenlist[k].equals(tiernamenlist[j])) {
						Manage.msgbox("Den selben Namen für zwei Tier's? Das ist doch nur verwirrend!",
								Gui.getwindow().getFrmPokemonDraft());
						return;
					}
				}
			}
			Gui.getwindow().getPanelDraft().finishdraft = false;
			if (Data.tierlistclone == null) {
				Data.tierlistclone = Data.getTierlist().toCharArray().clone();
			}
			int count = 0;
			if (cBS.isSelected()) {
				Gui.getwindow().getPanelTierlist().radioButtonS.setEnabled(true);
				Gui.getwindow().getPanelTierlist().radioButtonS.setText(tF1.getText());
				Gui.getwindow().getPanelTierlist().settiernamen(0, tF1.getText());
			} else {
				Gui.getwindow().getPanelTierlist().radioButtonS.setEnabled(false);
				Gui.getwindow().getPanelTierlist().radioButtonS.setText("");
				Gui.getwindow().getPanelTierlist().settiernamen(0, null);
				count++;
			}
			if (cBA.isSelected()) {
				Gui.getwindow().getPanelTierlist().radioButtonA.setEnabled(true);
				Gui.getwindow().getPanelTierlist().radioButtonA.setText(tF2.getText());
				Gui.getwindow().getPanelTierlist().settiernamen(1, tF2.getText());
			} else {
				Gui.getwindow().getPanelTierlist().radioButtonA.setEnabled(false);
				Gui.getwindow().getPanelTierlist().radioButtonA.setText("");
				Gui.getwindow().getPanelTierlist().settiernamen(1, null);
				count++;
			}
			if (cBB.isSelected()) {
				Gui.getwindow().getPanelTierlist().radioButtonB.setEnabled(true);
				Gui.getwindow().getPanelTierlist().radioButtonB.setText(tF3.getText());
				Gui.getwindow().getPanelTierlist().settiernamen(2, tF3.getText());
			} else {
				Gui.getwindow().getPanelTierlist().radioButtonB.setEnabled(false);
				Gui.getwindow().getPanelTierlist().radioButtonB.setText("");
				Gui.getwindow().getPanelTierlist().settiernamen(2, null);
				count++;
			}
			if (cBC.isSelected()) {
				Gui.getwindow().getPanelTierlist().radioButtonC.setEnabled(true);
				Gui.getwindow().getPanelTierlist().radioButtonC.setText(tF4.getText());
				Gui.getwindow().getPanelTierlist().settiernamen(3, tF4.getText());
			} else {
				Gui.getwindow().getPanelTierlist().radioButtonC.setEnabled(false);
				Gui.getwindow().getPanelTierlist().radioButtonC.setText("");
				Gui.getwindow().getPanelTierlist().settiernamen(3, null);
				count++;
			}
			if (cBD.isSelected()) {
				Gui.getwindow().getPanelTierlist().radioButtonD.setEnabled(true);
				Gui.getwindow().getPanelTierlist().radioButtonD.setText(tF5.getText());
				Gui.getwindow().getPanelTierlist().settiernamen(4, tF5.getText());
			} else {
				Gui.getwindow().getPanelTierlist().radioButtonD.setEnabled(false);
				Gui.getwindow().getPanelTierlist().radioButtonD.setText("");
				Gui.getwindow().getPanelTierlist().settiernamen(4, null);
				count++;
			}
			if (cBE.isSelected()) {
				Gui.getwindow().getPanelTierlist().radioButtonE.setEnabled(true);
				Gui.getwindow().getPanelTierlist().radioButtonE.setText(tF6.getText());
				Gui.getwindow().getPanelTierlist().settiernamen(5, tF6.getText());
			} else {
				Gui.getwindow().getPanelTierlist().radioButtonE.setEnabled(false);
				Gui.getwindow().getPanelTierlist().radioButtonE.setText("");
				Gui.getwindow().getPanelTierlist().settiernamen(5, null);
				count++;
			}
			String gebannt = "wird gebannt";
			String hochgestuft = "wird hochgestuft";
			for (int i = 0; i < count; i++) {
				for (int k = 0; k < Data.getTierlist().length(); k++) {
					switch (Data.getTierlist(k)) {
					case 'S':
						if (lblTierS.getText().equals(gebannt)) {
							Data.editTierlist(k, 'X');
						}
						break;
					case 'A':
						if (lblTierA.getText().equals(gebannt)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierA.getText().equals(hochgestuft)) {
							Data.editTierlist(k, 'S');
						}
						break;
					case 'B':
						if (lblTierB.getText().equals(gebannt)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierB.getText().equals(hochgestuft)) {
							Data.editTierlist(k, 'A');
						}
						break;
					case 'C':
						if (lblTierC.getText().equals(gebannt)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierC.getText().equals(hochgestuft)) {
							Data.editTierlist(k, 'B');
						}
						break;
					case 'D':
						if (lblTierD.getText().equals(gebannt)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierD.getText().equals(hochgestuft)) {
							Data.editTierlist(k, 'C');
						}
						break;
					case 'E':
						if (lblTierE.getText().equals(hochgestuft)) {
							Data.editTierlist(k, 'D');
						}
						break;
					default:
						break;
					}
				}
			}
			lblbest.setText("Änderungen wurden übernommen!");
		});
		btnsafetier.setBounds(69, 337, 89, 23);
		panel.add(btnsafetier);

		tF2 = new JTextField();
		tF2.addCaretListener(e -> lblbest.setText(""));
		tF2.setColumns(10);
		tF2.setBounds(98, 123, 86, 20);
		panel.add(tF2);

		tF3 = new JTextField();
		tF3.addCaretListener(e -> lblbest.setText(""));
		tF3.setColumns(10);
		tF3.setBounds(98, 164, 86, 20);
		panel.add(tF3);

		tF4 = new JTextField();
		tF4.addCaretListener(e -> lblbest.setText(""));
		tF4.setColumns(10);
		tF4.setBounds(98, 204, 86, 20);
		panel.add(tF4);

		tF5 = new JTextField();
		tF5.addCaretListener(e -> lblbest.setText(""));
		tF5.setColumns(10);
		tF5.setBounds(98, 247, 86, 20);
		panel.add(tF5);

		tF1 = new JTextField();
		tF1.addCaretListener(e -> lblbest.setText(""));
		tF1.setColumns(10);
		tF1.setBounds(98, 83, 86, 20);
		panel.add(tF1);

		tF6 = new JTextField();
		tF6.addCaretListener(e -> lblbest.setText(""));
		tF6.setColumns(10);
		tF6.setBounds(98, 290, 86, 20);
		panel.add(tF6);

		tF1.setEditable(false);
		tF2.setEditable(false);
		tF3.setEditable(false);
		tF4.setEditable(false);
		tF5.setEditable(false);
		tF6.setEditable(false);

		lblTierS = new JLabel("");
		lblTierS.setBounds(276, 86, 117, 14);
		panel.add(lblTierS);

		lblTierA = new JLabel("");
		lblTierA.setBounds(276, 126, 117, 14);
		panel.add(lblTierA);

		lblTierB = new JLabel("");
		lblTierB.setBounds(276, 167, 117, 14);
		panel.add(lblTierB);

		lblTierC = new JLabel("");
		lblTierC.setBounds(276, 207, 117, 14);
		panel.add(lblTierC);

		lblTierD = new JLabel("");
		lblTierD.setBounds(276, 250, 117, 14);
		panel.add(lblTierD);

		lblTierE = new JLabel("");
		lblTierE.setBounds(276, 293, 117, 14);
		panel.add(lblTierE);

		comboBoxS = new JComboBox(Manage.getaarray(15));
		comboBoxS.setEnabled(false);
		comboBoxS.setSelectedIndex(-1);
		comboBoxS.setBounds(194, 83, 61, 20);
		panel.add(comboBoxS);

		comboBoxA = new JComboBox(Manage.getaarray(15));
		comboBoxA.setEnabled(false);
		comboBoxA.setSelectedIndex(-1);
		comboBoxA.setBounds(194, 123, 61, 20);
		panel.add(comboBoxA);

		comboBoxB = new JComboBox(Manage.getaarray(15));
		comboBoxB.setEnabled(false);
		comboBoxB.setSelectedIndex(-1);
		comboBoxB.setBounds(194, 164, 61, 20);
		panel.add(comboBoxB);

		comboBoxC = new JComboBox(Manage.getaarray(15));
		comboBoxC.setEnabled(false);
		comboBoxC.setSelectedIndex(-1);
		comboBoxC.setBounds(194, 204, 61, 20);
		panel.add(comboBoxC);

		comboBoxD = new JComboBox(Manage.getaarray(15));
		comboBoxD.setEnabled(false);
		comboBoxD.setSelectedIndex(-1);
		comboBoxD.setBounds(194, 247, 61, 20);
		panel.add(comboBoxD);

		comboBoxE = new JComboBox(Manage.getaarray(15));
		comboBoxE.setEnabled(false);
		comboBoxE.setSelectedIndex(-1);
		comboBoxE.setBounds(194, 290, 61, 20);
		panel.add(comboBoxE);

		comboBoxS.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxS.getSelectedIndex() + 1;
				changepokeanzahl(0, auswahl);
			}
		});
		comboBoxA.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxA.getSelectedIndex() + 1;
				changepokeanzahl(1, auswahl);
			}
		});
		comboBoxB.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxB.getSelectedIndex() + 1;
				changepokeanzahl(2, auswahl);
			}
		});
		comboBoxC.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxC.getSelectedIndex() + 1;
				changepokeanzahl(3, auswahl);
			}
		});
		comboBoxD.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxD.getSelectedIndex() + 1;
				changepokeanzahl(4, auswahl);
			}
		});
		comboBoxE.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxE.getSelectedIndex() + 1;
				changepokeanzahl(5, auswahl);
			}
		});

		cBS = new JCheckBox("");
		cBS.addActionListener(e -> {
			if (cBS.isSelected()) {
				tF1.setEditable(true);
				tF1.setText("S");
				comboBoxS.setEnabled(true);
			} else {
				tF1.setEditable(false);
				tF1.setText("");
				comboBoxS.setEnabled(false);
				comboBoxS.setSelectedIndex(-1);
				changepokeanzahl(0, 0);
			}
			changesetting();
		});
		cBS.setBounds(67, 83, 21, 23);
		panel.add(cBS);

		cBA = new JCheckBox("");
		cBA.addActionListener(e -> {
			if (cBA.isSelected()) {
				tF2.setEditable(true);
				tF2.setText("A");
				comboBoxA.setEnabled(true);
			} else {
				tF2.setEditable(false);
				tF2.setText("");
				comboBoxA.setEnabled(false);
				comboBoxA.setSelectedIndex(-1);
				changepokeanzahl(1, 0);
			}
			changesetting();
		});
		cBA.setBounds(67, 122, 21, 23);
		panel.add(cBA);

		cBB = new JCheckBox("");
		cBB.addActionListener(e -> {
			if (cBB.isSelected()) {
				tF3.setEditable(true);
				tF3.setText("B");
				comboBoxB.setEnabled(true);
			} else {
				tF3.setEditable(false);
				tF3.setText("");
				comboBoxB.setEnabled(false);
				comboBoxB.setSelectedIndex(-1);
				changepokeanzahl(2, 0);
			}
			changesetting();
		});
		cBB.setBounds(67, 163, 21, 23);
		panel.add(cBB);

		cBC = new JCheckBox("");
		cBC.addActionListener(e -> {
			if (cBC.isSelected()) {
				tF4.setEditable(true);
				tF4.setText("C");
				comboBoxC.setEnabled(true);
			} else {
				tF4.setEditable(false);
				tF4.setText("");
				comboBoxC.setEnabled(false);
				comboBoxC.setSelectedIndex(-1);
				changepokeanzahl(3, 0);
			}
			changesetting();
		});
		cBC.setBounds(67, 203, 21, 23);
		panel.add(cBC);

		cBD = new JCheckBox("");
		cBD.addActionListener(e -> {
			if (cBD.isSelected()) {
				tF5.setEditable(true);
				tF5.setText("D");
				comboBoxD.setEnabled(true);
			} else {
				tF5.setEditable(false);
				tF5.setText("");
				comboBoxD.setEnabled(false);
				comboBoxD.setSelectedIndex(-1);
				changepokeanzahl(4, 0);
			}
			changesetting();
		});
		cBD.setBounds(67, 246, 21, 23);
		panel.add(cBD);

		cBE = new JCheckBox("");
		cBE.addActionListener(e -> {
			if (cBE.isSelected()) {
				tF6.setEditable(true);
				tF6.setText("E");
				comboBoxE.setEnabled(true);
			} else {
				tF6.setEditable(false);
				tF6.setText("");
				comboBoxE.setEnabled(false);
				comboBoxE.setSelectedIndex(-1);
				changepokeanzahl(5, 0);
			}
			changesetting();
		});
		cBE.setBounds(67, 289, 21, 23);
		panel.add(cBE);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 383, 393, 2);
		panel.add(separator);

		JTextPane txtpnResetetDieOben = new JTextPane();
		txtpnResetetDieOben.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnResetetDieOben.setText(
				"Resetet die oben gemachten Einstellungen auf den Urspungswert.\r\nKann gemachte Änderungen an der Tierlist nicht rückgängig machen!");
		txtpnResetetDieOben.setEditable(false);
		txtpnResetetDieOben.setBounds(35, 396, 148, 144);
		panel.add(txtpnResetetDieOben);

		JTextPane txtpnStelltDieEinstellungen = new JTextPane();
		txtpnStelltDieEinstellungen.setText(
				"Stellt die Einstellungen oben auf Standart zurück und setzt die Tierlist auf dem Wert, bevor du das erste mal hier was geändert hast!");
		txtpnStelltDieEinstellungen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnStelltDieEinstellungen.setEditable(false);
		txtpnStelltDieEinstellungen.setBounds(218, 396, 148, 144);
		panel.add(txtpnStelltDieEinstellungen);

		JButton btnresettier = new JButton("RESET");
		btnresettier.addActionListener(e -> {
			resetsettings();
			Manage.msgboxerf("Erfolgreich Resetet!", Gui.getwindow().getFrmPokemonDraft());
		});
		btnresettier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnresettier.setBounds(65, 542, 89, 23);
		panel.add(btnresettier);

		JButton btnrestoretier = new JButton("RESTORE");
		btnrestoretier.addActionListener(e -> {
			try {
				Data.setTierlist(Data.tierlistclone.clone());
				Data.tierlistclone = null;
				resetsettings();
				Manage.msgboxerf("Erfolgreich wiederhergestellt!", Gui.getwindow().getFrmPokemonDraft());
			} catch (Exception f) {
				Manage.msgbox("Nichts zum wiederherstellen gefunden!", Gui.getwindow().getFrmPokemonDraft());
			}
		});
		btnrestoretier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnrestoretier.setBounds(248, 542, 89, 23);
		panel.add(btnrestoretier);

		add(panel);
	}

	private void changesetting() {
		lblbest.setText("");
		if (!cBS.isSelected() && !cBA.isSelected() && !cBB.isSelected() && !cBC.isSelected() && !cBD.isSelected()
				&& !cBE.isSelected()) {
			lblTierS.setText(" ");
			lblTierA.setText(" ");
			lblTierB.setText(" ");
			lblTierC.setText(" ");
			lblTierD.setText(" ");
			lblTierE.setText(" ");
			return;
		}
		if (cBS.isSelected()) {
			change[0] = true;
		}
		if (cBA.isSelected()) {
			change[1] = true;
		}
		if (cBB.isSelected()) {
			change[2] = true;
		}
		if (cBC.isSelected()) {
			change[3] = true;
		}
		if (cBD.isSelected()) {
			change[4] = true;
		}
		if (cBE.isSelected()) {
			change[5] = true;
		}

		if (cBS.isSelected()) {
			lblTierS.setText("");
		} else {
			change[0] = false;
			if (!up(0)) {
				lblTierS.setText("wird gebannt");
			}
		}
		if (cBA.isSelected()) {
			lblTierA.setText("");
		} else {
			change[1] = false;
			if (!up(1)) {
				lblTierA.setText("wird gebannt");
			}
			if (!down(1) || (up(1) && down(1))) {
				lblTierA.setText("wird hochgestuft");
			}
		}
		if (cBB.isSelected()) {
			lblTierB.setText("");
		} else {
			change[2] = false;
			if (!up(2)) {
				lblTierB.setText("wird gebannt");
			}
			if (!down(2) || (up(2) && down(2))) {
				lblTierB.setText("wird hochgestuft");
			}
		}
		if (cBC.isSelected()) {
			lblTierC.setText("");
		} else {
			change[3] = false;
			if (!up(3)) {
				lblTierC.setText("wird gebannt");
			}
			if (!down(3) || (up(3) && down(3))) {
				lblTierC.setText("wird hochgestuft");
			}

		}
		if (cBD.isSelected()) {
			lblTierD.setText("");
		} else {
			change[4] = false;
			if (!up(4)) {
				lblTierD.setText("wird gebannt");
			}
			if (!down(4) || (up(4) && down(4))) {
				lblTierD.setText("wird hochgestuft");
			}
		}
		if (cBE.isSelected()) {
			lblTierE.setText("");
		} else {
			change[5] = false;
			if (!down(5)) {
				lblTierE.setText("wird hochgestuft");
			}
		}
	}

	private boolean up(int a) {
		try {
			if (change[a - 1]) {
				return true;
			} else {
				return up(a - 1);
			}
		} catch (Exception e) {
			return false;
		}
	}

	private boolean down(int a) {
		try {
			if (change[a + 1]) {
				return true;
			} else {
				return down(a + 1);
			}
		} catch (Exception e) {
			return false;
		}
	}

	private void resetsettings() {
		cBS.setSelected(false);
		cBA.setSelected(false);
		cBB.setSelected(false);
		cBC.setSelected(false);
		cBD.setSelected(false);
		cBE.setSelected(false);
		changesetting();
		Gui.getwindow().getPanelTierlist().radioButtonS.setEnabled(true);
		Gui.getwindow().getPanelTierlist().radioButtonA.setEnabled(true);
		Gui.getwindow().getPanelTierlist().radioButtonB.setEnabled(true);
		Gui.getwindow().getPanelTierlist().radioButtonC.setEnabled(true);
		Gui.getwindow().getPanelTierlist().radioButtonD.setEnabled(true);
		Gui.getwindow().getPanelTierlist().radioButtonE.setEnabled(true);
		Gui.getwindow().getPanelTierlist().radioButtonS.setText("S");
		Gui.getwindow().getPanelTierlist().radioButtonA.setText("A");
		Gui.getwindow().getPanelTierlist().radioButtonB.setText("B");
		Gui.getwindow().getPanelTierlist().radioButtonC.setText("C");
		Gui.getwindow().getPanelTierlist().radioButtonD.setText("D");
		Gui.getwindow().getPanelTierlist().radioButtonE.setText("E");
		tF1.setText("");
		tF2.setText("");
		tF3.setText("");
		tF4.setText("");
		tF5.setText("");
		tF6.setText("");
		tF1.setEditable(false);
		tF2.setEditable(false);
		tF3.setEditable(false);
		tF4.setEditable(false);
		tF5.setEditable(false);
		tF6.setEditable(false);
		comboBoxS.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxA.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxB.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxC.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxD.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxE.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxS.setSelectedIndex(-1);
		comboBoxA.setSelectedIndex(-1);
		comboBoxB.setSelectedIndex(-1);
		comboBoxC.setSelectedIndex(-1);
		comboBoxD.setSelectedIndex(-1);
		comboBoxE.setSelectedIndex(-1);
		comboBoxS.setEnabled(false);
		comboBoxA.setEnabled(false);
		comboBoxB.setEnabled(false);
		comboBoxC.setEnabled(false);
		comboBoxD.setEnabled(false);
		comboBoxE.setEnabled(false);
		countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };
	}

	private void changepokeanzahl(int a, int auswahl) {
		lblbest.setText("");
		if (auswahl == -1) {
			auswahl = 0;
		}
		int count = 15 - auswahl + countauswahl[a];
		for (int k : countauswahl) {
			count -= k;
		}
		countauswahl[a] = auswahl;
		try {
			comboBoxS.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[0] + count)));
			comboBoxA.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[1] + count)));
			comboBoxB.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[2] + count)));
			comboBoxC.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[3] + count)));
			comboBoxD.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[4] + count)));
			comboBoxE.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[5] + count)));

			comboBoxS.setSelectedIndex(countauswahl[0] - 1);
			comboBoxA.setSelectedIndex(countauswahl[1] - 1);
			comboBoxB.setSelectedIndex(countauswahl[2] - 1);
			comboBoxC.setSelectedIndex(countauswahl[3] - 1);
			comboBoxD.setSelectedIndex(countauswahl[4] - 1);
			comboBoxE.setSelectedIndex(countauswahl[5] - 1);
		} catch (Exception g) {
			Manage.msgbox("Huch da ist was bei der Anzahl der Pokemon schief gelaufen" + "\n"
					+ "Keine Sorge das war nicht deine Schuld aber leider können deine Einstellungen nicht übernommen werden",
					Gui.getwindow().getFrmPokemonDraft());
			try {
				comboBoxS.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
				comboBoxA.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
				comboBoxB.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
				comboBoxC.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
				comboBoxD.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
				comboBoxE.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
			} catch (Exception e) {
			}
		}
	}

	protected void setsettings(Object[] list) {
		resetsettings();
		try {
			for (int k = 0; k < list.length; k++) {
				switch (k) {
				case 1:
					cBS.setSelected(list[k].equals(true));
					break;
				case 2:
					cBS.setSelected(list[k].equals(true));
					break;
				case 3:
					cBS.setSelected(list[k].equals(true));
					break;
				case 4:
					cBS.setSelected(list[k].equals(true));
					break;
				case 5:
					cBS.setSelected(list[k].equals(true));
					break;
				case 6:
					cBS.setSelected(list[k].equals(true));
					break;
				case 7:
					tF1.setText(list[k].toString());
					break;
				case 8:
					tF2.setText(list[k].toString());
					break;
				case 9:
					tF3.setText(list[k].toString());
					break;
				case 10:
					tF4.setText(list[k].toString());
					break;
				case 11:
					tF5.setText(list[k].toString());
					break;
				case 12:
					tF6.setText(list[k].toString());
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			Manage.msgbox("Settings konnten nicht geladen werden", Gui.getwindow().getFrmPokemonDraft());
			resetsettings();
		}
		changesetting();
	}

	public Object[] getsettings() {
		return new Object[] { Gui.getwindow().getPanelSettings().cBS.isSelected(),
				Gui.getwindow().getPanelSettings().cBA.isSelected(),
				Gui.getwindow().getPanelSettings().cBB.isSelected(),
				Gui.getwindow().getPanelSettings().cBC.isSelected(),
				Gui.getwindow().getPanelSettings().cBD.isSelected(),
				Gui.getwindow().getPanelSettings().cBE.isSelected(), Gui.getwindow().getPanelSettings().tF1.getText(),
				Gui.getwindow().getPanelSettings().tF2.getText(), Gui.getwindow().getPanelSettings().tF3.getText(),
				Gui.getwindow().getPanelSettings().tF4.getText(), Gui.getwindow().getPanelSettings().tF5.getText(),
				Gui.getwindow().getPanelSettings().tF6.getText() };
	}
}