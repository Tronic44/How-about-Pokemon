package panel;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JLabel lblTierS;
	private JLabel lblTierA;
	private JLabel lblTierB;
	private JLabel lblTierC;
	private JLabel lblTierD;
	private JLabel lblTierE;
	private JLabel lblissettingsbestaetigung;
	private JCheckBox checkBoxS;
	private JCheckBox checkBoxA;
	private JCheckBox checkBoxB;
	private JCheckBox checkBoxC;
	private JCheckBox checkBoxD;
	private JCheckBox checkBoxE;
	private JTextField tFS;
	private JTextField tFA;
	private JTextField tFB;
	private JTextField tFC;
	private JTextField tFD;
	private JTextField tFE;
	private JComboBox<String> comboBoxS;
	private JComboBox<String> comboBoxA;
	private JComboBox<String> comboBoxB;
	private JComboBox<String> comboBoxC;
	private JComboBox<String> comboBoxD;
	private JComboBox<String> comboBoxE;
	private JLabel lblTierStatusS;
	private JLabel lblTierStatusA;
	private JLabel lblTierStatusB;
	private JLabel lblTierStatusC;
	private JLabel lblTierStatusD;
	private JLabel lblTierStatusE;
	private boolean[] change = new boolean[6];
	private int[] countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };
	private static final String GEBANNT = "wird GEBANNT";
	private static final String HOCHGESTUFT = "wird HOCHGESTUFT";
	private static final Font TEXTFONT = new Font(Manage.FONT, Font.PLAIN, 15);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelSettings() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		lblTierS = new JLabel("S:");
		lblTierS.setFont(TEXTFONT);
		lblTierS.setBounds(32, 83, 35, 17);
		panel.add(lblTierS);

		lblTierA = new JLabel("A:");
		lblTierA.setFont(TEXTFONT);
		lblTierA.setBounds(32, 123, 35, 17);
		panel.add(lblTierA);

		lblTierB = new JLabel("B:");
		lblTierB.setFont(TEXTFONT);
		lblTierB.setBounds(32, 164, 35, 17);
		panel.add(lblTierB);

		lblTierC = new JLabel("C:");
		lblTierC.setFont(TEXTFONT);
		lblTierC.setBounds(32, 204, 35, 17);
		panel.add(lblTierC);

		lblTierD = new JLabel("D:");
		lblTierD.setFont(TEXTFONT);
		lblTierD.setBounds(32, 247, 35, 17);
		panel.add(lblTierD);

		lblissettingsbestaetigung = new JLabel("");
		lblissettingsbestaetigung.setBounds(168, 346, 206, 14);
		panel.add(lblissettingsbestaetigung);

		lblTierE = new JLabel("E:");
		lblTierE.setFont(TEXTFONT);
		lblTierE.setBounds(32, 290, 35, 17);
		panel.add(lblTierE);

		JTextPane txtpnAchtungnderungenHier = new JTextPane();
		txtpnAchtungnderungenHier.setEditable(false);
		txtpnAchtungnderungenHier.setFont(new Font(Manage.FONT, Font.BOLD, 12));
		txtpnAchtungnderungenHier.setText(
				"Achtung: Änderungen hier führen zu einer\r\nAnpassung der aktuell eingegenbenen Tierlist. \r\nWenn du das nicht möchtest, speichere sie vorher!");
		txtpnAchtungnderungenHier.setBounds(40, 11, 323, 51);
		panel.add(txtpnAchtungnderungenHier);

		JButton btnsafetier = new JButton("Bestätige");
		btnsafetier.addActionListener(e -> {
			if (!checkBoxS.isSelected() && !checkBoxA.isSelected() && !checkBoxB.isSelected() && !checkBoxC.isSelected()
					&& !checkBoxD.isSelected() && !checkBoxE.isSelected()) {
				Manage.msgboxError("Kein Tier? So geht das aber nicht!", Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			if ((checkBoxS.isSelected() && tFS.getText().trim().length() < 1)
					|| (checkBoxA.isSelected() && tFA.getText().trim().length() < 1)
					|| (checkBoxB.isSelected() && tFB.getText().trim().length() < 1)
					|| (checkBoxC.isSelected() && tFC.getText().trim().length() < 1)
					|| (checkBoxD.isSelected() && tFD.getText().trim().length() < 1)
					|| (checkBoxE.isSelected() && tFE.getText().trim().length() < 1)) {
				Manage.msgboxError("Der Tiername ist etwas zu kruz, findest du nicht?",
						Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			if ((checkBoxS.isSelected() && comboBoxS.getSelectedIndex() < 0)
					|| (checkBoxA.isSelected() && comboBoxA.getSelectedIndex() < 0)
					|| (checkBoxB.isSelected() && comboBoxB.getSelectedIndex() < 0)
					|| (checkBoxC.isSelected() && comboBoxC.getSelectedIndex() < 0)
					|| (checkBoxD.isSelected() && comboBoxD.getSelectedIndex() < 0)
					|| (checkBoxE.isSelected() && comboBoxE.getSelectedIndex() < 0)) {
				Manage.msgboxError("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
						+ "Wo gibt es denn sowas?", Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			String[] tiernamenlist = new String[] { tFS.getText().trim(), tFA.getText().trim(), tFB.getText().trim(),
					tFC.getText().trim(), tFD.getText().trim(), tFE.getText().trim() };
			for (int k = 0; k < tiernamenlist.length; k++) {
				for (int j = 0; j < tiernamenlist.length; j++) {
					if (tiernamenlist[k].equals("") || tiernamenlist[j].equals("") || j == k) {
						continue;
					}
					if (tiernamenlist[k].equals(tiernamenlist[j])) {
						Manage.msgboxError("Den selben Namen für zwei Tier's? Das ist doch nur verwirrend!",
								Gui.getwindow().getFrmPokemonDraft());
						return;
					}
				}
			}
			if (lblissettingsbestaetigung.getText().equals("Änderungen wurden übernommen!")) {
				return;
			} else if (Gui.getwindow().isFinishdraft()) {
				Object[] options = { "Fortfahren", "Abbrechen" };
				if (JOptionPane.showOptionDialog(Gui.getwindow().getFrmPokemonDraft(),
						"Achtung: Die Änderungen die Du gemacht hast restarten den Draft",
						"Willst du wiklich Fortfahren", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1])==1) {
					return;
				}else {
					Gui.getwindow().setFinishdraft(false);
				}
			}
			Data.cloneTierlist();
			int count = Gui.getwindow().getPanelTierlist()
					.setRadioButton(
							new boolean[] { checkBoxS.isSelected(), checkBoxA.isSelected(), checkBoxB.isSelected(),
									checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected() },
							tiernamenlist);

			for (int i = 0; i < count; i++) {
				for (int k = 0; k < Data.getTierlist().length; k++) {
					switch (Data.getTierlist(k)) {
					case 'S':
						if (lblTierStatusS.getText().equals(GEBANNT)) {
							Data.editTierlist(k, 'X');
						}
						break;
					case 'A':
						if (lblTierStatusA.getText().equals(GEBANNT)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierStatusA.getText().equals(HOCHGESTUFT)) {
							Data.editTierlist(k, 'S');
						}
						break;
					case 'B':
						if (lblTierStatusB.getText().equals(GEBANNT)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierStatusB.getText().equals(HOCHGESTUFT)) {
							Data.editTierlist(k, 'A');
						}
						break;
					case 'C':
						if (lblTierStatusC.getText().equals(GEBANNT)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierStatusC.getText().equals(HOCHGESTUFT)) {
							Data.editTierlist(k, 'B');
						}
						break;
					case 'D':
						if (lblTierStatusD.getText().equals(GEBANNT)) {
							Data.editTierlist(k, 'X');
						}
						if (lblTierStatusD.getText().equals(HOCHGESTUFT)) {
							Data.editTierlist(k, 'C');
						}
						break;
					case 'E':
						if (lblTierStatusE.getText().equals(HOCHGESTUFT)) {
							Data.editTierlist(k, 'D');
						}
						break;
					default:
						break;
					}
				}
			}
			lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
		});
		btnsafetier.setBounds(42, 342, 116, 23);
		panel.add(btnsafetier);

		tFS = new JTextField();
		tFS.addCaretListener(e -> {
			String text = tFS.getText().trim();
			try {
				if (text.equals("")) {
					lblissettingsbestaetigung.setText("");
				} else {
					Gui.getwindow().getPanelDraft().updateTiernamen(0, text);
					if (Gui.getwindow().isFinishdraft()) {
						lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
					}
				}
			} catch (Exception f) {
				lblissettingsbestaetigung.setText("");
			}
		});
		tFS.setColumns(10);
		tFS.setBounds(98, 83, 86, 20);
		panel.add(tFS);

		tFA = new JTextField();
		tFA.addCaretListener(e -> {
			String text = tFA.getText().trim();
			try {
				if (text.equals("")) {
					lblissettingsbestaetigung.setText("");
				} else {
					Gui.getwindow().getPanelDraft().updateTiernamen(1, text);
					if (Gui.getwindow().isFinishdraft()) {
						lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
					}
				}
			} catch (Exception f) {
				lblissettingsbestaetigung.setText("");
			}
		});
		tFA.setColumns(10);
		tFA.setBounds(98, 123, 86, 20);
		panel.add(tFA);

		tFB = new JTextField();
		tFB.addCaretListener(e -> {
			String text = tFB.getText().trim();
			try {
				if (text.equals("")) {
					lblissettingsbestaetigung.setText("");
				} else {
					Gui.getwindow().getPanelDraft().updateTiernamen(2, text);
					if (Gui.getwindow().isFinishdraft()) {
						lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
					}
				}
			} catch (Exception f) {
				lblissettingsbestaetigung.setText("");
			}
		});
		tFB.setColumns(10);
		tFB.setBounds(98, 164, 86, 20);
		panel.add(tFB);

		tFC = new JTextField();
		tFC.addCaretListener(e -> {
			String text = tFC.getText().trim();
			try {
				if (text.equals("")) {
					lblissettingsbestaetigung.setText("");
				} else {
					Gui.getwindow().getPanelDraft().updateTiernamen(3, text);
					if (Gui.getwindow().isFinishdraft()) {
						lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
					}
				}
			} catch (Exception f) {
				lblissettingsbestaetigung.setText("");
			}
		});
		tFC.setColumns(10);
		tFC.setBounds(98, 204, 86, 20);
		panel.add(tFC);

		tFD = new JTextField();
		tFD.addCaretListener(e -> {
			String text = tFD.getText().trim();
			try {
				if (text.equals("")) {
					lblissettingsbestaetigung.setText("");
				} else {
					Gui.getwindow().getPanelDraft().updateTiernamen(4, text);
					if (Gui.getwindow().isFinishdraft()) {
						lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
					}
				}
			} catch (Exception f) {
				lblissettingsbestaetigung.setText("");
			}
		});
		tFD.setColumns(10);
		tFD.setBounds(98, 247, 86, 20);
		panel.add(tFD);

		tFE = new JTextField();
		tFE.addCaretListener(e -> {
			String text = tFE.getText().trim();
			try {
				if (text.equals("")) {
					lblissettingsbestaetigung.setText("");
				} else {
					Gui.getwindow().getPanelDraft().updateTiernamen(5, text);
					if (Gui.getwindow().isFinishdraft()) {
						lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
					}
				}
			} catch (Exception f) {
				lblissettingsbestaetigung.setText("");
			}
		});
		tFE.setColumns(10);
		tFE.setBounds(98, 290, 86, 20);
		panel.add(tFE);

		tFS.setEditable(false);
		tFA.setEditable(false);
		tFB.setEditable(false);
		tFC.setEditable(false);
		tFD.setEditable(false);
		tFE.setEditable(false);

		lblTierStatusS = new JLabel("");
		lblTierStatusS.setBounds(276, 86, 117, 14);
		panel.add(lblTierStatusS);

		lblTierStatusA = new JLabel("");
		lblTierStatusA.setBounds(276, 126, 117, 14);
		panel.add(lblTierStatusA);

		lblTierStatusB = new JLabel("");
		lblTierStatusB.setBounds(276, 167, 117, 14);
		panel.add(lblTierStatusB);

		lblTierStatusC = new JLabel("");
		lblTierStatusC.setBounds(276, 207, 117, 14);
		panel.add(lblTierStatusC);

		lblTierStatusD = new JLabel("");
		lblTierStatusD.setBounds(276, 250, 117, 14);
		panel.add(lblTierStatusD);

		lblTierStatusE = new JLabel("");
		lblTierStatusE.setBounds(276, 293, 117, 14);
		panel.add(lblTierStatusE);

		comboBoxS = new JComboBox(getanArray(15));
		comboBoxS.setEnabled(false);
		comboBoxS.setSelectedIndex(-1);
		comboBoxS.setBounds(194, 83, 61, 20);
		panel.add(comboBoxS);

		comboBoxA = new JComboBox(getanArray(15));
		comboBoxA.setEnabled(false);
		comboBoxA.setSelectedIndex(-1);
		comboBoxA.setBounds(194, 123, 61, 20);
		panel.add(comboBoxA);

		comboBoxB = new JComboBox(getanArray(15));
		comboBoxB.setEnabled(false);
		comboBoxB.setSelectedIndex(-1);
		comboBoxB.setBounds(194, 164, 61, 20);
		panel.add(comboBoxB);

		comboBoxC = new JComboBox(getanArray(15));
		comboBoxC.setEnabled(false);
		comboBoxC.setSelectedIndex(-1);
		comboBoxC.setBounds(194, 204, 61, 20);
		panel.add(comboBoxC);

		comboBoxD = new JComboBox(getanArray(15));
		comboBoxD.setEnabled(false);
		comboBoxD.setSelectedIndex(-1);
		comboBoxD.setBounds(194, 247, 61, 20);
		panel.add(comboBoxD);

		comboBoxE = new JComboBox(getanArray(15));
		comboBoxE.setEnabled(false);
		comboBoxE.setSelectedIndex(-1);
		comboBoxE.setBounds(194, 290, 61, 20);
		panel.add(comboBoxE);

		comboBoxS.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxS.getSelectedIndex() + 1;
				changePokemonAnzahl(0, auswahl);
			}
		});
		comboBoxA.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxA.getSelectedIndex() + 1;
				changePokemonAnzahl(1, auswahl);
			}
		});
		comboBoxB.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxB.getSelectedIndex() + 1;
				changePokemonAnzahl(2, auswahl);
			}
		});
		comboBoxC.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxC.getSelectedIndex() + 1;
				changePokemonAnzahl(3, auswahl);
			}
		});
		comboBoxD.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxD.getSelectedIndex() + 1;
				changePokemonAnzahl(4, auswahl);
			}
		});
		comboBoxE.addPopupMenuListener(new PopupListener() {
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxE.getSelectedIndex() + 1;
				changePokemonAnzahl(5, auswahl);
			}
		});

		checkBoxS = new JCheckBox("");
		checkBoxS.addActionListener(e -> {
			if (checkBoxS.isSelected()) {
				tFS.setEditable(true);
				tFS.setText("S");
				comboBoxS.setEnabled(true);
			} else {
				tFS.setEditable(false);
				tFS.setText("");
				comboBoxS.setEnabled(false);
				comboBoxS.setSelectedIndex(-1);
				changePokemonAnzahl(0, 0);
			}
			changeSettings();
		});
		checkBoxS.setBounds(67, 83, 21, 23);
		panel.add(checkBoxS);

		checkBoxA = new JCheckBox("");
		checkBoxA.addActionListener(e -> {
			if (checkBoxA.isSelected()) {
				tFA.setEditable(true);
				tFA.setText("A");
				comboBoxA.setEnabled(true);
			} else {
				tFA.setEditable(false);
				tFA.setText("");
				comboBoxA.setEnabled(false);
				comboBoxA.setSelectedIndex(-1);
				changePokemonAnzahl(1, 0);
			}
			changeSettings();
		});
		checkBoxA.setBounds(67, 122, 21, 23);
		panel.add(checkBoxA);

		checkBoxB = new JCheckBox("");
		checkBoxB.addActionListener(e -> {
			if (checkBoxB.isSelected()) {
				tFB.setEditable(true);
				tFB.setText("B");
				comboBoxB.setEnabled(true);
			} else {
				tFB.setEditable(false);
				tFB.setText("");
				comboBoxB.setEnabled(false);
				comboBoxB.setSelectedIndex(-1);
				changePokemonAnzahl(2, 0);
			}
			changeSettings();
		});
		checkBoxB.setBounds(67, 163, 21, 23);
		panel.add(checkBoxB);

		checkBoxC = new JCheckBox("");
		checkBoxC.addActionListener(e -> {
			if (checkBoxC.isSelected()) {
				tFC.setEditable(true);
				tFC.setText("C");
				comboBoxC.setEnabled(true);
			} else {
				tFC.setEditable(false);
				tFC.setText("");
				comboBoxC.setEnabled(false);
				comboBoxC.setSelectedIndex(-1);
				changePokemonAnzahl(3, 0);
			}
			changeSettings();
		});
		checkBoxC.setBounds(67, 203, 21, 23);
		panel.add(checkBoxC);

		checkBoxD = new JCheckBox("");
		checkBoxD.addActionListener(e -> {
			if (checkBoxD.isSelected()) {
				tFD.setEditable(true);
				tFD.setText("D");
				comboBoxD.setEnabled(true);
			} else {
				tFD.setEditable(false);
				tFD.setText("");
				comboBoxD.setEnabled(false);
				comboBoxD.setSelectedIndex(-1);
				changePokemonAnzahl(4, 0);
			}
			changeSettings();
		});
		checkBoxD.setBounds(67, 246, 21, 23);
		panel.add(checkBoxD);

		checkBoxE = new JCheckBox("");
		checkBoxE.addActionListener(e -> {
			if (checkBoxE.isSelected()) {
				tFE.setEditable(true);
				tFE.setText("E");
				comboBoxE.setEnabled(true);
			} else {
				tFE.setEditable(false);
				tFE.setText("");
				comboBoxE.setEnabled(false);
				comboBoxE.setSelectedIndex(-1);
				changePokemonAnzahl(5, 0);
			}
			changeSettings();
		});
		checkBoxE.setBounds(67, 289, 21, 23);
		panel.add(checkBoxE);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 383, 393, 2);
		panel.add(separator);

		JTextPane txtpnResetetDieOben = new JTextPane();
		txtpnResetetDieOben.setFont(new Font(Manage.FONT, Font.PLAIN, 14));
		txtpnResetetDieOben.setText(
				"Resetet die oben gemachten Einstellungen auf den Urspungswert.\r\nKann gemachte Änderungen an der Tierlist nicht rückgängig machen!");
		txtpnResetetDieOben.setEditable(false);
		txtpnResetetDieOben.setBounds(35, 396, 148, 144);
		panel.add(txtpnResetetDieOben);

		JTextPane txtpnStelltDieEinstellungen = new JTextPane();
		txtpnStelltDieEinstellungen.setText(
				"Stellt die Einstellungen oben auf Standart zurück und setzt die Tierlist auf dem Wert, bevor du das erste mal hier was geändert hast!");
		txtpnStelltDieEinstellungen.setFont(new Font(Manage.FONT, Font.PLAIN, 14));
		txtpnStelltDieEinstellungen.setEditable(false);
		txtpnStelltDieEinstellungen.setBounds(218, 396, 148, 144);
		panel.add(txtpnStelltDieEinstellungen);

		JButton btnresettier = new JButton("RESET");
		btnresettier.addActionListener(e -> {
			resetSettings();
			Manage.msgboxErfolg("Erfolgreich Resetet!", Gui.getwindow().getFrmPokemonDraft());
		});
		btnresettier.setFont(new Font(Manage.FONT, Font.BOLD, 11));
		btnresettier.setBounds(65, 542, 89, 23);
		panel.add(btnresettier);

		JButton btnrestoretier = new JButton("RESTORE");
		btnrestoretier.addActionListener(e -> {
			try {
				Data.restoreTierlist();
				resetSettings();
				Manage.msgboxErfolg("Erfolgreich wiederhergestellt!", Gui.getwindow().getFrmPokemonDraft());
			} catch (Exception f) {
				Manage.msgboxError("Nichts zum wiederherstellen gefunden!", Gui.getwindow().getFrmPokemonDraft());
			}
		});
		btnrestoretier.setFont(new Font(Manage.FONT, Font.BOLD, 11));
		btnrestoretier.setBounds(248, 542, 89, 23);
		panel.add(btnrestoretier);

		add(panel);
	}

	private void changeSettings() {
		lblissettingsbestaetigung.setText("");
		if (!checkBoxS.isSelected() && !checkBoxA.isSelected() && !checkBoxB.isSelected() && !checkBoxC.isSelected()
				&& !checkBoxD.isSelected() && !checkBoxE.isSelected()) {
			lblTierStatusS.setText(" ");
			lblTierStatusA.setText(" ");
			lblTierStatusB.setText(" ");
			lblTierStatusC.setText(" ");
			lblTierStatusD.setText(" ");
			lblTierStatusE.setText(" ");
			return;
		}
		if (checkBoxS.isSelected()) {
			change[0] = true;
		}
		if (checkBoxA.isSelected()) {
			change[1] = true;
		}
		if (checkBoxB.isSelected()) {
			change[2] = true;
		}
		if (checkBoxC.isSelected()) {
			change[3] = true;
		}
		if (checkBoxD.isSelected()) {
			change[4] = true;
		}
		if (checkBoxE.isSelected()) {
			change[5] = true;
		}
		if (checkBoxS.isSelected()) {
			lblTierStatusS.setText("");
		} else {
			change[0] = false;
			if (!up(0)) {
				lblTierStatusS.setText(GEBANNT);
			}
		}
		if (checkBoxA.isSelected()) {
			lblTierStatusA.setText("");
		} else {
			change[1] = false;
			if (!up(1)) {
				lblTierStatusA.setText(GEBANNT);
			}
			if (!down(1) || (up(1) && down(1))) {
				lblTierStatusA.setText(HOCHGESTUFT);
			}
		}
		if (checkBoxB.isSelected()) {
			lblTierStatusB.setText("");
		} else {
			change[2] = false;
			if (!up(2)) {
				lblTierStatusB.setText(GEBANNT);
			}
			if (!down(2) || (up(2) && down(2))) {
				lblTierStatusB.setText(HOCHGESTUFT);
			}
		}
		if (checkBoxC.isSelected()) {
			lblTierStatusC.setText("");
		} else {
			change[3] = false;
			if (!up(3)) {
				lblTierStatusC.setText(GEBANNT);
			}
			if (!down(3) || (up(3) && down(3))) {
				lblTierStatusC.setText(HOCHGESTUFT);
			}

		}
		if (checkBoxD.isSelected()) {
			lblTierStatusD.setText("");
		} else {
			change[4] = false;
			if (!up(4)) {
				lblTierStatusD.setText(GEBANNT);
			}
			if (!down(4) || (up(4) && down(4))) {
				lblTierStatusD.setText(HOCHGESTUFT);
			}
		}
		if (checkBoxE.isSelected()) {
			lblTierStatusE.setText("");
		} else {
			change[5] = false;
			if (!down(5)) {
				lblTierStatusE.setText(HOCHGESTUFT);
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

	private void resetSettings() {
		checkBoxS.setSelected(false);
		checkBoxA.setSelected(false);
		checkBoxB.setSelected(false);
		checkBoxC.setSelected(false);
		checkBoxD.setSelected(false);
		checkBoxE.setSelected(false);
		changeSettings();
		Gui.getwindow().getPanelTierlist().resetRadioButtons();
		tFS.setText("");
		tFA.setText("");
		tFB.setText("");
		tFC.setText("");
		tFD.setText("");
		tFE.setText("");
		tFS.setEditable(false);
		tFA.setEditable(false);
		tFB.setEditable(false);
		tFC.setEditable(false);
		tFD.setEditable(false);
		tFE.setEditable(false);
		comboBoxS.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
		comboBoxA.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
		comboBoxB.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
		comboBoxC.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
		comboBoxD.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
		comboBoxE.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
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

	private void changePokemonAnzahl(int a, int auswahl) {
		lblissettingsbestaetigung.setText("");
		if (auswahl == -1) {
			auswahl = 0;
		}
		int count = 15 - auswahl + countauswahl[a];
		for (int k : countauswahl) {
			count -= k;
		}
		countauswahl[a] = auswahl;
		try {
			comboBoxS.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[0] + count)));
			comboBoxA.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[1] + count)));
			comboBoxB.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[2] + count)));
			comboBoxC.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[3] + count)));
			comboBoxD.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[4] + count)));
			comboBoxE.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[5] + count)));

			comboBoxS.setSelectedIndex(countauswahl[0] - 1);
			comboBoxA.setSelectedIndex(countauswahl[1] - 1);
			comboBoxB.setSelectedIndex(countauswahl[2] - 1);
			comboBoxC.setSelectedIndex(countauswahl[3] - 1);
			comboBoxD.setSelectedIndex(countauswahl[4] - 1);
			comboBoxE.setSelectedIndex(countauswahl[5] - 1);
		} catch (Exception g) {
			Manage.msgboxError("Huch da ist was bei der Anzahl der Pokemon schief gelaufen" + "\n"
					+ "Keine Sorge das war nicht deine Schuld aber leider können deine Einstellungen nicht übernommen werden",
					Gui.getwindow().getFrmPokemonDraft());
			try {
				comboBoxS.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
				comboBoxA.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
				comboBoxB.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
				comboBoxC.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
				comboBoxD.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
				comboBoxE.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Gibt ein StringArray mit den Zahlen 1 bis der eingegebenen length
	 * 
	 * @param length die Länge des zurückgegebenes Arrays
	 * @return StringArray der Länge length mit den Zahlen 1 bis length
	 */
	private static String[] getanArray(int length) {
		if (length <= 0) {
			return new String[] {};
		}
		if (length > 15) {
			length = 15;
		}
		String[] a = new String[length];
		for (int k = 0; k < a.length; k++) {
			a[k] = String.valueOf(k + 1);
		}
		return a;
	}

	protected void setSettings(Object[] list) {
		resetSettings();
		try {
			for (int k = 0; k < list.length; k++) {
				switch (k) {
				case 1:
					checkBoxS.setSelected(list[k].equals(true));
					break;
				case 2:
					checkBoxS.setSelected(list[k].equals(true));
					break;
				case 3:
					checkBoxS.setSelected(list[k].equals(true));
					break;
				case 4:
					checkBoxS.setSelected(list[k].equals(true));
					break;
				case 5:
					checkBoxS.setSelected(list[k].equals(true));
					break;
				case 6:
					checkBoxS.setSelected(list[k].equals(true));
					break;
				case 7:
					tFS.setText(list[k].toString());
					break;
				case 8:
					tFA.setText(list[k].toString());
					break;
				case 9:
					tFB.setText(list[k].toString());
					break;
				case 10:
					tFC.setText(list[k].toString());
					break;
				case 11:
					tFD.setText(list[k].toString());
					break;
				case 12:
					tFE.setText(list[k].toString());
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			Manage.msgboxError("Settings konnten nicht geladen werden", Gui.getwindow().getFrmPokemonDraft());
			resetSettings();
		}
		changeSettings();
	}

	protected void toTheLastTier() {
		for (int k = 0; k < Data.getTierlist().length; k++) {
			if (Data.getTierlist(k) == '0') {
				if (checkBoxE.isSelected()) {
					Data.editTierlist(k, 'E');
				} else {
					if (checkBoxD.isSelected()) {
						Data.editTierlist(k, 'D');
					} else {
						if (checkBoxC.isSelected()) {
							Data.editTierlist(k, 'C');
						} else {
							if (checkBoxB.isSelected()) {
								Data.editTierlist(k, 'B');
							} else {
								if (checkBoxA.isSelected()) {
									Data.editTierlist(k, 'A');
								} else {
									if (checkBoxS.isSelected()) {
										Data.editTierlist(k, 'S');
									} else {
										Manage.msgboxError("Es wurde noch keine Tier Einstellung getroffen",
												Gui.getwindow().getFrmPokemonDraft());
									}
								}
							}
						}
					}
				}
			}
		}
	}

	protected boolean areSettingsChanges() {
		return !lblissettingsbestaetigung.getText().equals("Änderungen wurden übernommen!");
	}

	protected int[] getCountauswahl() {
		return countauswahl;
	}

	public Object[] getSettings() {
		return new Object[] { checkBoxS.isSelected(), checkBoxA.isSelected(), checkBoxB.isSelected(),
				checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(), tFS.getText(), tFA.getText(),
				tFB.getText(), tFC.getText(), tFD.getText(), tFE.getText() };
	}
}