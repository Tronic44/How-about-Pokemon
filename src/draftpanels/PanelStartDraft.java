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
		btnTierlist.addActionListener(e -> DraftGui.getwindow().getPanelTierlist().openTierlist());
		panel.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		btnSpielerTeams.addActionListener(e -> {
//			if (DraftGui.getwindow().isFinishdraft()) {
//				Manage.msgboxError(
//						"ACHTUNG: Teams können vertauscht und der Name verändert werden " + "\n"
//								+ " Aber das hinzufügen und entfernen von Teams restart den Draft",
//						DraftGui.getwindow().getFrmPokemonDraft());
//			}
//			DraftGui.getwindow().getPanelPlayer().remove(DraftGui.getwindow().getPanelPlayer().cBTeams);
//			DraftGui.getwindow().getPanelPlayer().teamlist();
			DraftGui.getwindow().visPlayer();
		});
		btnSpielerTeams.setBounds(74, 147, 255, 71);
		panel.add(btnSpielerTeams);

		JButton btnAnzahlDerPokemon = new JButton("Anzahl der Pokemon");
		btnAnzahlDerPokemon.addActionListener(e -> {
			if (DraftGui.getwindow().isFinishdraft()) {
				Manage.msgboxError("ACHTUNG: Änderungen hier, restarten den Draft!",
						DraftGui.getwindow().getFrmPokemonDraft());
			}
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
			}
		});
		btnReihenfolge.setBounds(74, 365, 255, 71);
		panel.add(btnReihenfolge);
		btnReihenfolge.setEnabled(true);

		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(e -> {
			if (!DraftGui.getwindow().isFinishdraft()) {
				int count = 0;
				for (char k : data.PokemonDraft.getTierlist()) {
					if (k == '0' || k == 'X') {
						count++;
					}
				}
				if (count > 880) {
					Manage.msgboxError("Du hast zu wenige Pokemon ein Tier zugewiesen, um einen Draft zu starten",
							DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().getPanelTierlist().openTierlist();
					DraftGui.getwindow().visTierlist();
					return;
				}
				if (!DraftGui.getwindow().getPanelPlayer().isSafed()) {
					Manage.msgboxError("Du hast keine Teams eingetragen", DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().getPanelPlayer().remove(DraftGui.getwindow().getPanelPlayer().cBTeams);
					DraftGui.getwindow().getPanelPlayer().teamlist();
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
						DraftGui.getwindow().getPanelTierlist().openTierlist();
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
				DraftGui.getwindow().getFrmPokemonDraft().setBounds(100, 100, 1100,
						DraftGui.getwindow().getPanelDraft().getDraftHight());
				DraftGui.getwindow().visDraft();
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