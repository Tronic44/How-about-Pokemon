package panel;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import client.Data;
import client.Manage;

@SuppressWarnings("serial")
public class PanelStartDraft extends JPanel {

	private JPanel panel = new JPanel();
	private JButton btnReihenfolge;

	public PanelStartDraft() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
		panel.setVisible(true);

		JButton btnTierlist = new JButton("Tierlist");
		btnTierlist.setBounds(74, 38, 255, 71);
		btnTierlist.addActionListener(e -> {
			if (Gui.getwindow().finishdraft) {
				Manage.msgbox(
						"ACHTUNG: Änderungen hier, führen zum ... sollten funktionieren, tut es nur noch nicht^^ ",
						Gui.getwindow().getFrmPokemonDraft());
			}
			Gui.getwindow().getPanelTierlist().opentierlist();
		});
		panel.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		btnSpielerTeams.addActionListener(e -> {
			if (Gui.getwindow().finishdraft) {
				Manage.msgbox(
						"ACHTUNG: Teams können vertauscht und der Name verändert werden " + "\n"
								+ " Aber das hinzufügen und entfernen von Teams restart den Draft",
						Gui.getwindow().getFrmPokemonDraft());
			}
			Gui.getwindow().getPanelPlayer().remove(Gui.getwindow().getPanelPlayer().cBTeams);
			Gui.getwindow().getPanelPlayer().teamlist();
			Gui.getwindow().visPlayer();
		});
		btnSpielerTeams.setBounds(74, 147, 255, 71);
		panel.add(btnSpielerTeams);

		JButton btnAnzahlDerPokemon = new JButton("Anzahl der Pokemon");
		btnAnzahlDerPokemon.addActionListener(e -> {
			if (Gui.getwindow().finishdraft) {
				Manage.msgbox("ACHTUNG: Änderungen hier, restarten den Draft!", Gui.getwindow().getFrmPokemonDraft());
			}
			Gui.getwindow().visSettings();
		});
		btnAnzahlDerPokemon.setBounds(74, 256, 255, 71);
		panel.add(btnAnzahlDerPokemon);

		btnReihenfolge = new JButton("Reihenfolge");
		btnReihenfolge.addActionListener(e -> {
			if (Gui.getwindow().getPanelPlayer().teamname == null
					|| Gui.getwindow().getPanelPlayer().ePfinalteam.getText().length() < 1) {
				Manage.msgbox("Du kannst nicht ohne Teams keine Reihenfolge bilden",
						Gui.getwindow().getFrmPokemonDraft());
			} else {
				Gui.getwindow().visOrder();
			}
		});
		btnReihenfolge.setBounds(74, 365, 255, 71);
		panel.add(btnReihenfolge);
		btnReihenfolge.setEnabled(true);

		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(e -> {
			if (!Gui.getwindow().getPanelDraft().finishdraft) {
				int count = 0;
				for (char k : Data.getTierlist().toCharArray()) {
					if (k == '0') {
						count++;
					}
				}
				if (count > 880) {
					Manage.msgbox("Du hast zu wenige Pokemon ein Tier zugewiesen, um einen Draft zu starten",
							Gui.getwindow().getFrmPokemonDraft());
					Gui.getwindow().getPanelTierlist().opentierlist();
					Gui.getwindow().visTierlist();
					return;
				}
				if (Gui.getwindow().getPanelPlayer().teamname == null
						|| Gui.getwindow().getPanelPlayer().ePfinalteam.getText().length() < 1) {
					Manage.msgbox("Du hast keine Teams eingetragen", Gui.getwindow().getFrmPokemonDraft());
					Gui.getwindow().getPanelPlayer().remove(Gui.getwindow().getPanelPlayer().cBTeams);
					Gui.getwindow().getPanelPlayer().teamlist();
					Gui.getwindow().visPlayer();
					return;
				}
				int pokeanzahl = 0;
				for (int k : Gui.getwindow().getPanelSettings().countauswahl) {
					pokeanzahl += k;
				}
				if (pokeanzahl == 0) {
					Manage.msgbox("Du hast noch nicht die Anzhal der Pokemon ausgewählt",
							Gui.getwindow().getFrmPokemonDraft());
					Gui.getwindow().visSettings();
					return;
				}
				if (Gui.getwindow().getPanelOrder().getOrder() == 0) {
					Manage.msgbox("Du hast noch keine Reihenfolge ausgewählt", Gui.getwindow().getFrmPokemonDraft());
					Gui.getwindow().visOrder();
					return;
				}
				if (count > 0) {
					Object[] options = { "BANNEN", "In das untersete Tier einfügen", "Selbst einordnen" };
					switch (JOptionPane.showOptionDialog(Gui.getwindow().getFrmPokemonDraft(),
							"Du hast noch nicht allen Pokenmon einen Tier zugewiesen, was möchtest du tun? " + "\n"
									+ "Alle nicht zugewisenen:",
							"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[2])) {
					case 0:
						for (int k = 0; k < Data.getPokedex().length; k++) {

							if (Data.getTierlist(k) == '0') {
								Data.editTierlist(k, 'X');
							}
						}
						Gui.getwindow().getPanelDraft().opendraft();
						break;
					case 1:
						if (Gui.getwindow().getPanelSettings().lblbest.getText()
								.equals("Änderungen wurden übernommen!")) {
							for (int k = 0; k < Data.getTierlist().length(); k++) {
								if (Data.getTierlist(k) == '0') {
									if (Gui.getwindow().getPanelSettings().cBE.isSelected()) {
										Data.editTierlist(k, 'E');
									} else {
										if (Gui.getwindow().getPanelSettings().cBD.isSelected()) {
											Data.editTierlist(k, 'D');
										} else {
											if (Gui.getwindow().getPanelSettings().cBC.isSelected()) {
												Data.editTierlist(k, 'C');
											} else {
												if (Gui.getwindow().getPanelSettings().cBB.isSelected()) {
													Data.editTierlist(k, 'B');
												} else {
													if (Gui.getwindow().getPanelSettings().cBA.isSelected()) {
														Data.editTierlist(k, 'A');
													} else {
														if (Gui.getwindow().getPanelSettings().cBS.isSelected()) {
															Data.editTierlist(k, 'S');
														} else {
															Manage.msgbox(
																	"Es wurde noch keine Tier Einstellung getroffen",
																	Gui.getwindow().getFrmPokemonDraft());
															return;
														}
													}
												}
											}
										}
									}
								}
							}
							Gui.getwindow().getPanelDraft().opendraft();
						} else {
							Manage.msgbox("Du hast ungespeicherte Änderungen in deiner Tiereinstellungen",
									Gui.getwindow().getFrmPokemonDraft());
							Gui.getwindow().visSettings();
						}
						break;
					case 2:
						Gui.getwindow().getPanelTierlist().opentierlist();
						break;
					default:
						break;
					}
				} else {
					Gui.getwindow().getPanelDraft().opendraft();
				}
			} else {
				if (Gui.getwindow().getPanelPlayer().spieler.length != Gui.getwindow()
						.getPanelDraft().draftauswahl.length) {
					Gui.getwindow().getPanelDraft().resetdraft();
					Gui.getwindow().getPanelDraft().remove(Gui.getwindow().getPanelDraft().cBchangeteam);
					Gui.getwindow().getPanelDraft().opendraft();
					return;
				}
				Gui.getwindow().getFrmPokemonDraft().setBounds(100, 100, 1100,
						Gui.getwindow().getPanelDraft().getDrafthigth());
				Gui.getwindow().visDraft();
			}
		});
		btnFertig.setBounds(74, 474, 255, 71);
		panel.add(btnFertig);
		btnFertig.setEnabled(true);

		add(panel);
	}

	protected void enablebtnReihenfolge() {
		btnReihenfolge.setEnabled(true);
	}
}