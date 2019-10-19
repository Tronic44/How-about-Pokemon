package draftpanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import client.Manage;
import data.PokemonDraft;

@SuppressWarnings("serial")
public class PanelStartDraft extends JPanel {

	private JLayeredPane panel = new JLayeredPane();
	private JButton btnReihenfolge;

	
	/**
	 * called {@link PanelDraft#getDraftHight()}
	 */
	public PanelStartDraft() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
		panel.setVisible(true);

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(409, 640, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 409, 640);
		panel.add(back);

		JButton btnTierlist = new JButton("Tierlist");
		panel.setLayer(btnTierlist, 1);
		btnTierlist.setBounds(74, 38, 255, 71);
		btnTierlist.addActionListener(e -> DraftGui.getwindow().visTierlist());
		panel.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		panel.setLayer(btnSpielerTeams, 1);
		btnSpielerTeams.addActionListener(e -> {
			DraftGui.getwindow().getPanelPlayer().reloadTeamlist();
			DraftGui.getwindow().visPlayer();
		});
		btnSpielerTeams.setBounds(74, 147, 255, 71);
		panel.add(btnSpielerTeams);

		JButton btnAnzahlDerPokemon = new JButton("Anzahl der Pokemon");
		panel.setLayer(btnAnzahlDerPokemon, 1);
		btnAnzahlDerPokemon.addActionListener(e -> DraftGui.getwindow().visSettings());
		btnAnzahlDerPokemon.setBounds(74, 256, 255, 71);
		panel.add(btnAnzahlDerPokemon);

		btnReihenfolge = new JButton("Reihenfolge");
		panel.setLayer(btnReihenfolge, 1);
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
		panel.setLayer(btnFertig, 1);
		btnFertig.addActionListener(e -> {
			if (DraftGui.getwindow().getPanelDraft().finishdrafting) {
				DraftGui.getwindow().visAfterDraft();
				return;
			}
			if (!DraftGui.getwindow().isFinishlayout()) {
				if (!DraftGui.getwindow().getPanelPlayer().isSafed()) {
					Manage.msgboxError("Du hast keine Teams eingetragen", DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().getPanelPlayer().remove(DraftGui.getwindow().getPanelPlayer().cBTeams);
					DraftGui.getwindow().visPlayer();
					return;
				}
				int pokeanzahl = 0;
				for (int k : DraftGui.getwindow().getPanelSettings().getCountauswahl()) {
					pokeanzahl += k;
				}
				if (pokeanzahl == 0) {
					Manage.msgboxError("Du hast noch nicht die Anzahl der Pokemon ausgewählt",
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
				if (DraftGui.getwindow().getPanelSettings().areSettingsChanges()) {
					Manage.msgboxError("Du hast ungespeicherte Einstellungen",
							DraftGui.getwindow().getFrmPokemonDraft());
					DraftGui.getwindow().visSettings();
					return;
				}
				int count = 0;
				for (char k : data.PokemonDraft.getTierlist()) {
					if (k == '0' || k == 'X') {
						count++;
					}
				}
				if (count > 0) {
					Object[] options = { "BANNEN", "In das unterste Tier einfügen", "Selbst einordnen" };
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
						if (checkforenoughPokemon()) {
							DraftGui.getwindow().visDraft();

						} else {
							Manage.msgboxError("Du hast zu wenige Pokemon ausgewählt, damit alle Draften können",
									DraftGui.getwindow().getFrmPokemonDraft());
							DraftGui.getwindow().visTierlist();
						}
						break;
					case 1:
						if (DraftGui.getwindow().getPanelSettings().areSettingsChanges()) {
							Manage.msgboxError("Du hast ungespeicherte Änderungen in deiner Tiereinstellungen",
									DraftGui.getwindow().getFrmPokemonDraft());
							DraftGui.getwindow().visSettings();
						} else {
							DraftGui.getwindow().getPanelSettings().toTheLastTier();
							if (checkforenoughPokemon()) {
								DraftGui.getwindow().visDraft();
							} else {
								Manage.msgboxError("Du hast zu wenige Pokemon ausgewählt, damit alle draften können",
										DraftGui.getwindow().getFrmPokemonDraft());
								DraftGui.getwindow().visTierlist();
							}
						}
						break;
					case 2:
						DraftGui.getwindow().visTierlist();
						break;
					default:
						break;
					}

				} else {
					DraftGui.getwindow().visDraft();
				}
			} else {
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

	private boolean checkforenoughPokemon() {
		PokemonDraft.initTierPokemon();
		int[] auswahl = DraftGui.getwindow().getPanelSettings().getCountauswahl();
		for (int k = 0; k < auswahl.length; k++) {
			if (DraftGui.getwindow().getPanelPlayer().player.size() < 15) {
				if (PokemonDraft.getTierPokemon(k).length < auswahl[k]
						* DraftGui.getwindow().getPanelPlayer().player.size() - 1) {
					return false;
				}
			} else {
				if (PokemonDraft.getTierPokemon(k).length < auswahl[k]
						* DraftGui.getwindow().getPanelPlayer().player.size()) {
					return false;
				}
			}
		}
		return true;
	}

	protected void deaktivatebtnReihenfolge() {
		btnReihenfolge.setEnabled(false);
	}

	protected void aktivatebtnReihenfolge() {
		btnReihenfolge.setEnabled(true);
	}
}