package draftpanels;

import java.awt.Font;
import java.util.ArrayList;
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
import client.Manage;
import client.PopupListener;

@SuppressWarnings("serial")
public class PanelSettings extends JPanel {

	private JPanel panel = new JPanel();
	private ArrayList<JLabel> lblTier = new ArrayList<>();
	private JLabel lblissettingsbestaetigung;
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
	private ArrayList<JTextField> tfTiername = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	private ArrayList<JComboBox> comboBoxes = new ArrayList<>();
	private ArrayList<JLabel> lblStatus = new ArrayList<>();
	private boolean[] change = new boolean[6];
	private int[] countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };
	private static final String GEBANNT = "wird GEBANNT";
	private static final String HOCHGESTUFT = "wird HOCHGESTUFT";
	private static final Font TEXTFONT = new Font(Manage.FONT, Font.PLAIN, 15);

	public PanelSettings() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		int[] arrangey = new int[] { 84, 124, 164, 204, 244, 284 };
		char[] standartTier = new char[] { 'S', 'A', 'B', 'C', 'D', 'E' };

		for (int k = 0; k < arrangey.length; k++) {
			lblTier.add(new JLabel(standartTier[k] + ":"));
			lblTier.get(k).setFont(TEXTFONT);
			lblTier.get(k).setBounds(32, arrangey[k], 35, 17);
			panel.add(lblTier.get(k));
		}

		lblissettingsbestaetigung = new JLabel("");
		lblissettingsbestaetigung.setBounds(168, 346, 206, 14);
		panel.add(lblissettingsbestaetigung);

		JTextPane txtpnAchtungnderungenHier = new JTextPane();
		txtpnAchtungnderungenHier.setEditable(false);
		txtpnAchtungnderungenHier.setFont(new Font(Manage.FONT, Font.BOLD, 12));
		txtpnAchtungnderungenHier.setText(
				"Achtung: Änderungen hier führen zu einer\r\nAnpassung der aktuell eingegenbenen Tierlist. \r\nWenn du das nicht möchtest, speichere sie vorher!");
		txtpnAchtungnderungenHier.setBounds(40, 11, 323, 51);
		panel.add(txtpnAchtungnderungenHier);

		JButton btnsafetier = new JButton("Bestätige");
		btnsafetier.addActionListener(e -> {
			if (!checkBoxes.get(0).isSelected() && !checkBoxes.get(1).isSelected() && !checkBoxes.get(2).isSelected()
					&& !checkBoxes.get(3).isSelected() && !checkBoxes.get(4).isSelected()
					&& !checkBoxes.get(5).isSelected()) {
				Manage.msgboxError("Kein Tier? So geht das aber nicht!", DraftGui.getwindow().getFrmPokemonDraft());
				return;
			}
			for (JCheckBox k : checkBoxes) {
				if (k.isSelected() && tfTiername.get(checkBoxes.indexOf(k)).getText().trim().length() < 1) {
					Manage.msgboxError("Der Tiername ist etwas zu kruz, findest du nicht?",
							DraftGui.getwindow().getFrmPokemonDraft());
					return;
				}
			}
			String[] tiernamenlist = new String[] { tfTiername.get(0).getText().trim(),
					tfTiername.get(1).getText().trim(), tfTiername.get(2).getText().trim(),
					tfTiername.get(3).getText().trim(), tfTiername.get(4).getText().trim(),
					tfTiername.get(5).getText().trim() };
			for (int k = 0; k < tiernamenlist.length; k++) {
				for (int j = 0; j < tiernamenlist.length; j++) {
					if (tiernamenlist[k].equals("") || tiernamenlist[j].equals("") || j == k) {
						continue;
					}
					if (tiernamenlist[k].equals(tiernamenlist[j])) {
						Manage.msgboxError("Den selben Namen für zwei Tier's? Das ist doch nur verwirrend!",
								DraftGui.getwindow().getFrmPokemonDraft());
						return;
					}
				}
			}
			if (lblissettingsbestaetigung.getText().equals("Änderungen wurden übernommen!")) {
				return;
			} else if (DraftGui.getwindow().isFinishlayout()) {
				Object[] options = { "Fortfahren", "Abbrechen" };
				if (JOptionPane.showOptionDialog(DraftGui.getwindow().getFrmPokemonDraft(),
						"Achtung: Die Änderungen die Du gemacht hast restarten den Draft",
						"Willst du wiklich Fortfahren", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1]) == 1) {
					return;
				} else {
					DraftGui.getwindow().setFinishlayout(false);
				}
			}
			data.PokemonDraft.cloneTierlist();
			stufetierlist(DraftGui.getwindow().getPanelTierlist()
					.setRadioButton(new boolean[] { checkBoxes.get(0).isSelected(), checkBoxes.get(1).isSelected(),
							checkBoxes.get(2).isSelected(), checkBoxes.get(3).isSelected(),
							checkBoxes.get(4).isSelected(), checkBoxes.get(5).isSelected() }, tiernamenlist));			
			lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
		});
		btnsafetier.setBounds(42, 342, 116, 23);
		panel.add(btnsafetier);

		for (int k = 0; k < lblTier.size(); k++) {
			tfTiername.add(new JTextField());
			tfTiername.get(k).setBounds(98, arrangey[k], 86, 20);
			tfTiername.get(k).addCaretListener(e -> {
				String text = tfTiername.get(tfTiername.indexOf(e.getSource())).getText().trim();
				try {
					if (text.equals("")) {
						lblissettingsbestaetigung.setText("");
					} else {
						DraftGui.getwindow().getPanelDraft().updateTiernamen(tfTiername.indexOf(e.getSource()), text);
						if (DraftGui.getwindow().isFinishlayout()) {
							lblissettingsbestaetigung.setText("Änderungen wurden übernommen!");
						}
					}
				} catch (Exception f) {
					lblissettingsbestaetigung.setText("");
				}
			});
			tfTiername.get(k).setEditable(false);
			panel.add(tfTiername.get(k));
		}
		for (int k = 0; k < lblTier.size(); k++) {
			lblStatus.add(new JLabel(""));
			lblStatus.get(k).setBounds(276, arrangey[k], 117, 14);
			panel.add(lblStatus.get(k));
		}
		for (int k = 0; k < lblTier.size(); k++) {
			comboBoxes.add(new JComboBox<>(getanArray(15)));
			comboBoxes.get(k).setEnabled(false);
			comboBoxes.get(k).setSelectedIndex(-1);
			comboBoxes.get(k).setBounds(194, arrangey[k], 61, 20);
			panel.add(comboBoxes.get(k));
			comboBoxes.get(k).addPopupMenuListener(new PopupListener() {
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					changePokemonAnzahl(comboBoxes.indexOf(e.getSource()),
							comboBoxes.get(comboBoxes.indexOf(e.getSource())).getSelectedIndex() + 1);
				}
			});
		}
		for (int k = 0; k < lblTier.size(); k++) {
			checkBoxes.add(new JCheckBox(""));
			checkBoxes.get(k).setBounds(67, arrangey[k], 21, 23);
			panel.add(checkBoxes.get(k));
			checkBoxes.get(k).addActionListener(e -> {
				int ort = checkBoxes.indexOf(e.getSource());
				if (checkBoxes.get(ort).isSelected()) {
					try {
						comboBoxes.get(ort).setSelectedIndex(0);
						changePokemonAnzahl(ort, comboBoxes.get(ort).getSelectedIndex() + 1);
						tfTiername.get(ort).setEditable(true);
						tfTiername.get(ort).setText(standartTier[ort] + "");
						comboBoxes.get(ort).setEnabled(true);
					} catch (Exception f) {
						checkBoxes.get(ort).setSelected(false);
						Manage.msgboxError("Du hast keine Pokemon-Slots mehr übrig, erlaubt sind maximal 15",
								DraftGui.getwindow().getFrmPokemonDraft());
					}
				} else {
					tfTiername.get(ort).setEditable(false);
					tfTiername.get(ort).setText("");
					comboBoxes.get(ort).setEnabled(false);
					comboBoxes.get(ort).setSelectedIndex(-1);
					changePokemonAnzahl(ort, 0);
				}
				changeSettings();
			});
		}

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
			if (!DraftGui.getwindow().isFinishlayout()) {
				resetSettings();
				Manage.msgboxErfolg("Erfolgreich Resetet!", DraftGui.getwindow().getFrmPokemonDraft());
			}
		});
		btnresettier.setFont(new Font(Manage.FONT, Font.BOLD, 11));
		btnresettier.setBounds(65, 542, 89, 23);
		panel.add(btnresettier);

		JButton btnrestoretier = new JButton("RESTORE");
		btnrestoretier.addActionListener(e -> {
			if (!DraftGui.getwindow().isFinishlayout()) {
				try {
					data.PokemonDraft.restoreTierlist();
					resetSettings();
					Manage.msgboxErfolg("Erfolgreich wiederhergestellt!", DraftGui.getwindow().getFrmPokemonDraft());
				} catch (Exception f) {
					Manage.msgboxError("Nichts zum wiederherstellen gefunden!",
							DraftGui.getwindow().getFrmPokemonDraft());
				}
			}
		});
		btnrestoretier.setFont(new Font(Manage.FONT, Font.BOLD, 11));
		btnrestoretier.setBounds(248, 542, 89, 23);
		panel.add(btnrestoretier);

		add(panel);
	}

	protected void stufetierlist(int count) {
		for (int i = 0; i < count; i++) {
			for (int k = 0; k < data.PokemonDraft.getTierlist().length; k++) {
				switch (data.PokemonDraft.getTierlist(k)) {
				case 'S':
					if (lblStatus.get(0).getText().equals(GEBANNT)) {
						data.PokemonDraft.editTierlist(k, 'X');
					}
					break;
				case 'A':
					if (lblStatus.get(1).getText().equals(GEBANNT)) {
						data.PokemonDraft.editTierlist(k, 'X');
					}
					if (lblStatus.get(1).getText().equals(HOCHGESTUFT)) {
						data.PokemonDraft.editTierlist(k, 'S');
					}
					break;
				case 'B':
					if (lblStatus.get(2).getText().equals(GEBANNT)) {
						data.PokemonDraft.editTierlist(k, 'X');
					}
					if (lblStatus.get(2).getText().equals(HOCHGESTUFT)) {
						data.PokemonDraft.editTierlist(k, 'A');
					}
					break;
				case 'C':
					if (lblStatus.get(3).getText().equals(GEBANNT)) {
						data.PokemonDraft.editTierlist(k, 'X');
					}
					if (lblStatus.get(3).getText().equals(HOCHGESTUFT)) {
						data.PokemonDraft.editTierlist(k, 'B');
					}
					break;
				case 'D':
					if (lblStatus.get(4).getText().equals(GEBANNT)) {
						data.PokemonDraft.editTierlist(k, 'X');
					}
					if (lblStatus.get(4).getText().equals(HOCHGESTUFT)) {
						data.PokemonDraft.editTierlist(k, 'C');
					}
					break;
				case 'E':
					if (lblStatus.get(5).getText().equals(HOCHGESTUFT)) {
						data.PokemonDraft.editTierlist(k, 'D');
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private void changeSettings() {
		lblissettingsbestaetigung.setText("");
		if (!checkBoxes.get(0).isSelected() && !checkBoxes.get(1).isSelected() && !checkBoxes.get(2).isSelected()
				&& !checkBoxes.get(3).isSelected() && !checkBoxes.get(4).isSelected()
				&& !checkBoxes.get(5).isSelected()) {
			for (JLabel k : lblStatus) {
				k.setText("");
			}
			return;
		}
		for (JCheckBox k : checkBoxes) {
			change[checkBoxes.indexOf(k)] = k.isSelected();
		}
		for (JCheckBox k : checkBoxes) {
			int ort = checkBoxes.indexOf(k);
			if (k.isSelected()) {
				lblStatus.get(ort).setText("");
			} else {
				change[ort] = k.isSelected();
				if (ort != 5 && !up(ort)) {
					lblStatus.get(ort).setText(GEBANNT);
				}
				if (ort != 0 && !down(ort) || (up(ort) && down(ort))) {
					lblStatus.get(ort).setText(HOCHGESTUFT);
				}
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

	@SuppressWarnings("unchecked")
	private void resetSettings() {
		for (JCheckBox k : checkBoxes) {
			k.setSelected(false);
		}
		for (JTextField k : tfTiername) {
			k.setText("");
			k.setEditable(false);
		}
		for (JComboBox<String> k : comboBoxes) {
			k.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
			k.setSelectedIndex(-1);
			k.setEnabled(false);
		}
		changeSettings();
		DraftGui.getwindow().getPanelTierlist().resetRadioButtons();
		countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };
	}

	@SuppressWarnings("unchecked")
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
			for (JComboBox<String> k : comboBoxes) {
				k.setModel(new DefaultComboBoxModel<String>(getanArray(countauswahl[comboBoxes.indexOf(k)] + count)));
				k.setSelectedIndex(countauswahl[comboBoxes.indexOf(k)] - 1);
			}
		} catch (Exception g) {
			Manage.msgboxError("Huch da ist was bei der Anzahl der Pokemon schief gelaufen" + "\n"
					+ "Keine Sorge das war nicht deine Schuld aber leider können deine Einstellungen nicht übernommen werden",
					DraftGui.getwindow().getFrmPokemonDraft());
			for (JComboBox<String> k : comboBoxes) {
				k.setModel(new DefaultComboBoxModel<String>(getanArray(15)));
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
//		try {
//			for (int k = 0; k < list.length; k++) {
//				switch (k) {
//				case 1:
//					checkBoxS.setSelected(list[k].equals(true));
//					break;
//				case 2:
//					checkBoxS.setSelected(list[k].equals(true));
//					break;
//				case 3:
//					checkBoxS.setSelected(list[k].equals(true));
//					break;
//				case 4:
//					checkBoxS.setSelected(list[k].equals(true));
//					break;
//				case 5:
//					checkBoxS.setSelected(list[k].equals(true));
//					break;
//				case 6:
//					checkBoxS.setSelected(list[k].equals(true));
//					break;
//				case 7:
//					tFS.setText(list[k].toString());
//					break;
//				case 8:
//					tFA.setText(list[k].toString());
//					break;
//				case 9:
//					tFB.setText(list[k].toString());
//					break;
//				case 10:
//					tFC.setText(list[k].toString());
//					break;
//				case 11:
//					tFD.setText(list[k].toString());
//					break;
//				case 12:
//					tFE.setText(list[k].toString());
//					break;
//				default:
//					break;
//				}
//			}
//		} catch (Exception e) {
//			Manage.msgboxError("Settings konnten nicht geladen werden", DraftGui.getwindow().getFrmPokemonDraft());
//			resetSettings();
//		}
		changeSettings();
	}

	protected void toTheLastTier() {
		for (int k = 0; k < data.PokemonDraft.getTierlist().length; k++) {
			if (data.PokemonDraft.getTierlist(k) == '0') {
				if (checkBoxes.get(5).isSelected()) {
					data.PokemonDraft.editTierlist(k, 'E');
				} else {
					if (checkBoxes.get(4).isSelected()) {
						data.PokemonDraft.editTierlist(k, 'D');
					} else {
						if (checkBoxes.get(3).isSelected()) {
							data.PokemonDraft.editTierlist(k, 'C');
						} else {
							if (checkBoxes.get(2).isSelected()) {
								data.PokemonDraft.editTierlist(k, 'B');
							} else {
								if (checkBoxes.get(1).isSelected()) {
									data.PokemonDraft.editTierlist(k, 'A');
								} else {
									if (checkBoxes.get(0).isSelected()) {
										data.PokemonDraft.editTierlist(k, 'S');
									} else {
										Manage.msgboxError("Es wurde noch keine Tier Einstellung getroffen",
												DraftGui.getwindow().getFrmPokemonDraft());
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

	@SuppressWarnings("unchecked")
	protected void disablecrtiticalchanges() {
		for (JCheckBox k : checkBoxes) {
			k.setEnabled(false);
		}
		for (JComboBox<String> k : comboBoxes) {
			k.setEnabled(false);
		}
		for (JLabel k : lblStatus) {
			k.setText("");
		}

	}

	public Object[] getSettings() {
//		Object[] k = new Object[] { checkBoxS.isSelected(), checkBoxA.isSelected(), checkBoxB.isSelected(),
//				checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(), tFS.getText(), tFA.getText(),
//				tFB.getText(), tFC.getText(), tFD.getText(), tFE.getText() };
		return null;
	}
}