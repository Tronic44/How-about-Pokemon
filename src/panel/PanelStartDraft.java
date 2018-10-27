package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		btnTierlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Gui.getwindow().finishdraft) {
					Manage.msgbox(
							"ACHTUNG: Änderungen hier, führen zum ... sollten funktionieren, tut es nur noch nicht^^ ",
							Gui.getwindow().getFrmPokemonDraft());
				}
				Gui.getwindow().getPanel_tierlist().opentierlist();

			}

		});
		panel.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		btnSpielerTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent as) {
				if (Gui.getwindow().finishdraft) {
					Manage.msgbox(
							"ACHTUNG: Teams können vertauscht und der Name verändert werden " + "\n"
									+ " Aber das hinzufügen und entfernen von Teams restart den Draft",
							Gui.getwindow().getFrmPokemonDraft());
				}
				Gui.getwindow().getPanel_player().remove(Gui.getwindow().getPanel_player().cBTeams);
				Gui.getwindow().getPanel_player().teamlist();
				Gui.getwindow().visPlayer();

			}
		});
		btnSpielerTeams.setBounds(74, 147, 255, 71);
		panel.add(btnSpielerTeams);

		JButton btnAnzahlDerPokemon = new JButton("Anzahl der Pokemon");
		btnAnzahlDerPokemon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Gui.getwindow().finishdraft) {
					Manage.msgbox("ACHTUNG: Änderungen hier, restarten den Draft!",
							Gui.getwindow().getFrmPokemonDraft());
				}
				Gui.getwindow().visSettings();
			}
		});
		btnAnzahlDerPokemon.setBounds(74, 256, 255, 71);
		panel.add(btnAnzahlDerPokemon);

		btnReihenfolge = new JButton("Reihenfolge");
		btnReihenfolge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Gui.getwindow().getPanel_player().teamname == null
						|| Gui.getwindow().getPanel_player().ePfinalteam.getText().toString().length() < 1) {
					Manage.msgbox("Du kannst nicht ohne Teams keine Reihenfolge bilden",
							Gui.getwindow().getFrmPokemonDraft());
				} else {
					Gui.getwindow().visOrder();
				}

			}
		});
		btnReihenfolge.setBounds(74, 365, 255, 71);
		panel.add(btnReihenfolge);
		btnReihenfolge.setEnabled(true);

		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Gui.getwindow().getPanel_draft().finishdraft) {
					int count = 0;
					for (char k : Data.tierlist) {
						if (k == '0') {
							count++;
						}
					}
					if (count > 880) {
						Manage.msgbox("Du hast zu wenige Pokemon ein Tier zugewiesen, um einen Draft zu starten",
								Gui.getwindow().getFrmPokemonDraft());
						Gui.getwindow().getPanel_tierlist().opentierlist();
						Gui.getwindow().visTierlist();
						return;
					}
					if (Gui.getwindow().getPanel_player().teamname == null
							|| Gui.getwindow().getPanel_player().ePfinalteam.getText().toString().length() < 1) {
						Manage.msgbox("Du hast keine Teams eingetragen", Gui.getwindow().getFrmPokemonDraft());
						Gui.getwindow().getPanel_player().remove(Gui.getwindow().getPanel_player().cBTeams);
						Gui.getwindow().getPanel_player().teamlist();
						Gui.getwindow().visPlayer();
						return;
					}
					int pokeanzahl = 0;
					for (int k : Gui.getwindow().getPanel_settings().countauswahl) {
						pokeanzahl += k;
					}
					if (pokeanzahl == 0) {
						Manage.msgbox("Du hast noch nicht die Anzhal der Pokemon ausgewählt",
								Gui.getwindow().getFrmPokemonDraft());
						Gui.getwindow().visSettings();
						return;
					}
					if (Gui.getwindow().getPanel_order().getOrder() == 0) {
						Manage.msgbox("Du hast noch keine Reihenfolge ausgewählt",
								Gui.getwindow().getFrmPokemonDraft());
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
							for (int k = 0; k < Data.tierlist.length; k++) {
								if (Data.tierlist[k] == '0') {
									Data.tierlist[k] = 'X';
								}
							}
							Gui.getwindow().getPanel_draft().opendraft();
							break;
						case 1:
							if (Gui.getwindow().getPanel_settings().lblbest.getText()
									.equals("Änderungen wurden übernommen!")) {
								for (int k = 0; k < Data.tierlist.length; k++) {
									if (Data.tierlist[k] == '0') {
										if (Gui.getwindow().getPanel_settings().cBE.isSelected()) {
											Data.tierlist[k] = 'E';
										} else {
											if (Gui.getwindow().getPanel_settings().cBD.isSelected()) {
												Data.tierlist[k] = 'D';
											} else {
												if (Gui.getwindow().getPanel_settings().cBC.isSelected()) {
													Data.tierlist[k] = 'C';
												} else {
													if (Gui.getwindow().getPanel_settings().cBB.isSelected()) {
														Data.tierlist[k] = 'B';
													} else {
														if (Gui.getwindow().getPanel_settings().cBA.isSelected()) {
															Data.tierlist[k] = 'A';
														} else {
															if (Gui.getwindow().getPanel_settings().cBS.isSelected()) {
																Data.tierlist[k] = 'S';
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
								Gui.getwindow().getPanel_draft().opendraft();
							} else {
								Manage.msgbox("Du hast ungespeicherte Änderungen in deiner Tiereinstellungen",
										Gui.getwindow().getFrmPokemonDraft());
								Gui.getwindow().visSettings();
							}
							break;
						case 2:
							Gui.getwindow().getPanel_tierlist().opentierlist();
							break;
						}
					} else {
						Gui.getwindow().getPanel_draft().opendraft();
					}
				} else {
					if (Gui.getwindow().getPanel_player().Spieler.length != Gui.getwindow()
							.getPanel_draft().draftauswahl.length) {
						Gui.getwindow().getPanel_draft().resetdraft();
						Gui.getwindow().getPanel_draft().remove(Gui.getwindow().getPanel_draft().cBchangeteam);
						Gui.getwindow().getPanel_draft().opendraft();
						return;
					}
					int hight = 100;
					for (int k : Gui.getwindow().getPanel_settings().countauswahl) {
						if (k == 0) {
							continue;
						}
						if (k <= 3) {
							hight += 130;
							continue;
						}
						if (k <= 6) {
							hight += 260;
							continue;
						}
						if (k <= 9) {
							hight += 390;
							continue;
						}
						if (k <= 12) {
							hight += 520;
							continue;
						}
						if (k <= 15) {
							hight += 650;
							continue;
						}

					}
					Gui.getwindow().getFrmPokemonDraft().setBounds(100, 100, 1100, hight);
					Gui.getwindow().visDraft();
				}
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
