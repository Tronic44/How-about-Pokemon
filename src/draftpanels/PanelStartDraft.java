package draftpanels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
		btnTierlist.addActionListener(e -> DraftGui.getwindow().visTierlist());
		panel.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		btnSpielerTeams.addActionListener(e -> {
			DraftGui.getwindow().getPanelPlayer().reloadTeamlist();
			DraftGui.getwindow().visPlayer();
		});
		btnSpielerTeams.setBounds(74, 147, 255, 71);
		panel.add(btnSpielerTeams);

		JButton btnAnzahlDerPokemon = new JButton("Anzahl der Pokemon");
		btnAnzahlDerPokemon.addActionListener(e -> {
			DraftGui.getwindow().visSettings();
		});
		btnAnzahlDerPokemon.setBounds(74, 256, 255, 71);
		panel.add(btnAnzahlDerPokemon);

		btnReihenfolge = new JButton("Reihenfolge");
		btnReihenfolge.addActionListener(e -> {
			if (DraftGui.getwindow().getPanelPlayer().isSafed()
					&& DraftGui.getwindow().getPanelPlayer().player.size() > 1) {
				DraftGui.getwindow().visOrder();
			} else {
				Manage.msgboxError("Du kannst nicht ohne Teams keine Reihenfolge bilden",
						DraftGui.getwindow().getFrmPokemonDraft());
				DraftGui.getwindow().visPlayer();
			}
		});
		btnReihenfolge.setBounds(74, 365, 255, 71);
		panel.add(btnReihenfolge);
		btnReihenfolge.setEnabled(true);

		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(e -> {
			if (DraftGui.getwindow().getPanelDraft().finishdrafting) {
				DraftGui.getwindow().visAfterDraft();
				return;
			}
			if (!DraftGui.getwindow().isFinishlayout()) {
				int count = 0;
				for (char k : data.PokemonDraft.getTierlist()) {
					if (k == '0' || k == 'X') {
						count++;
					}
				}
				if (count > 880) {
					Manage.msgboxError("Du hast zu wenige Pokemon ein Tier zugewiesen, um einen Draft zu starten",
							DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().visTierlist();
					DraftGui.getwindow().visTierlist();
					return;
				}
				if (!DraftGui.getwindow().getPanelPlayer().isSafed()) {
					Manage.msgboxError("Du hast keine Teams eingetragen", DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().getPanelPlayer().remove(DraftGui.getwindow().getPanelPlayer().cBTeams);
					DraftGui.getwindow().getPanelPlayer().reloadTeamlist();
					DraftGui.getwindow().visPlayer();
					return;
				}
				int pokeanzahl = 0;
				for (int k : DraftGui.getwindow().getPanelSettings().getCountauswahl()) {
					pokeanzahl += k;
				}
				if (pokeanzahl == 0) {
					Manage.msgboxError("Du hast noch nicht die Anzhal der Pokemon ausgewählt",
							DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().visSettings();
					return;
				}
				if (DraftGui.getwindow().getPanelOrder().getOrder() == 0) {
					Manage.msgboxError("Du hast noch keine Reihenfolge ausgewählt",
							DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().visOrder();
					return;
				}
				if (count < data.PokemonDraft.getPokedex().length - 15) {
					Object[] options = { "BANNEN", "In das untersete Tier einfügen", "Selbst einordnen" };
					switch (JOptionPane.showOptionDialog(DraftGui.getwindow().getFrmPokemonDraft(),
							"Du hast noch nicht allen Pokenmon einen Tier zugewiesen, was möchtest du tun? " + "\n"
									+ "Alle nicht zugewisenen:",
							"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[2])) {
					case 0:
						for (int k = 0; k < data.PokemonDraft.getPokedex().length; k++) {

							if (data.PokemonDraft.getTierlist(k) == '0') {
								data.PokemonDraft.editTierlist(k, 'X');
							}
						}
						DraftGui.getwindow().getPanelDraft().opendraft();
						break;
					case 1:
						if (DraftGui.getwindow().getPanelSettings().areSettingsChanges()) {
							Manage.msgboxError("Du hast ungespeicherte Änderungen in deiner Tiereinstellungen",
									DraftGui.getwindow().getFrmPokemonDraft());
							DraftGui.getwindow().visSettings();
						} else {
							DraftGui.getwindow().getPanelSettings().toTheLastTier();
							DraftGui.getwindow().getPanelDraft().opendraft();
						}

						break;
					case 2:
						DraftGui.getwindow().visTierlist();
						break;
					default:
						break;
					}
				} else {
					DraftGui.getwindow().getPanelDraft().opendraft();
				}
			} else {
//				if (DraftGui.getwindow().getPanelPlayer().spieler.length != DraftGui.getwindow()
//						.getPanelDraft().getDraftauswahllength()) {
//					DraftGui.getwindow().getPanelDraft().resetDraft();
//					DraftGui.getwindow().getPanelDraft().remove(DraftGui.getwindow().getPanelDraft().cBchangeteam);
//					DraftGui.getwindow().getPanelDraft().opendraft();
//					return;
//				}
				DraftGui.getwindow().getFrmPokemonDraft().setBounds(DraftGui.getwindow().getFrmPokemonDraft().getX(),
						DraftGui.getwindow().getFrmPokemonDraft().getY(), 900,
						DraftGui.getwindow().getPanelDraft().getDraftHight());
				DraftGui.getwindow().visDraft();
			}
		});
		btnFertig.setBounds(74, 474, 255, 71);
		panel.add(btnFertig);
		btnFertig.setEnabled(true);

		add(panel);
	}

	protected void deaktivatebtnReihenfolge() {
		btnReihenfolge.setEnabled(false);
	}

	protected void aktivatebtnReihenfolge() {
		btnReihenfolge.setEnabled(true);
	}
}