package draftpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuEvent;
import client.FilterComboBox;
import client.PopupListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelDraft extends JPanel {

	/**
	 * Stellt das Panel
	 */
	private JLayeredPane panel = new JLayeredPane();
	/**
	 * Ein Array der Größe 15x15 für 15 spieler a 15 Pokemon. In ihm werden die
	 * Pokemonnamen gespeichert
	 */
	private String[][] draftauswahl = new String[15][15];
	/**
	 * Die ComboBox mit dem das aktuelle Team ausgewählt werden kann
	 */
	private JComboBox<String> cBchangeteam = new JComboBox<>();
	/**
	 * Ein FilterComboBox Array der Größe 15, für jedes zu draftene Pokemon eins
	 */
	private FilterComboBox[] cbDraft = new FilterComboBox[15];
	/**
	 * Ist der Index von {@link #cBchangeteam}
	 */
	private int changeteam = 0;
	/**
	 * Ein JLabel Array der Länge 6, wlche die Tiernamen anzeigen
	 */
	private JLabel[] labellist = new JLabel[6];
	/**
	 * Ein int Array der Länge 15, der zu jedem Pokemon das Tier speichert
	 */
	private int[] tierlistcB = new int[15];
	/**
	 * Der Thread, der für den Draft mit zufälliger Reihenfolge zuständig ist
	 */
	private static Thread randonDraft = new Thread();
	/**
	 * Die Reihenfolged der Teams, bei zufällliger Reihenfolge
	 */
	private int[] teamfolge;
	/**
	 * Der Knopf "Pause", der beim Draften in zufälliger Reihenfolge angezeigt wird
	 */
	private JButton btnPause;
	/**
	 * Der knopf "Ende", der beim manuellen Draften angezeigt wird, und den Draft
	 * beendet, insofern alles gedraftet wurde
	 */
	private JButton btnEnd;
	/**
	 * Boolean der den Status des Thread {@link #randonDraft} wiedergibt.<br> DO NOT
	 * TOUCH!
	 */
	private boolean waitforstatusupdate = false; // DO NOT TOUCH
	/**
	 * Ist true, sobald der Draft abgeschlossen ist
	 */
	protected boolean finishdrafting = false;
	/**
	 * Ist true, sobald erstmalig ein Pokemon gesetzt wurde.<br> DO NOT TOUCH!
	 */
	private boolean selectnewPokemon = false; // DO NOT TOUCH
	/**
	 * Speichert die aktuelle Draft Reihenfolge.<br> DO NOT TOUCH!
	 */
	private int order; // DO NOT TOUCH
	/**
	 * Zwischenspeichert die aktuelle Draft Reihenfolge, falls man den Draft
	 * Pausiert. <br>DO NOT TOUCH!
	 */
	private int oldorder; // DO NOT TOUCH
	/**
	 * DO NOT TOUCH!<br> Also Wirklich nicht!
	 */
	private int threadpoint; // DO NOT TOUCH

	/**
	 * Konstruktor, initialisiert das Panel, alle nicht generischen Elemente, und
	 * den Draft-Thread {@link #randonDraft} <br> called {@link PanelDraft#endDrafting()}
	 */
	@SuppressWarnings("unchecked")
	public PanelDraft() {

		panel.setBounds(0, 0, 900, 50);
		panel.setLayout(null);

		Arrays.fill(cbDraft, 0, cbDraft.length - 1, new FilterComboBox(new ArrayList<>()));

		JLabel lbteams = new JLabel("Teams:");
		lbteams.setBounds(123, 14, 45, 14);
		panel.setLayer(lbteams, 1);
		panel.add(lbteams);

		cBchangeteam = new JComboBox<>();
		cBchangeteam.addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				changeteam = cBchangeteam.getSelectedIndex();
				int i = 0;
				for (JComboBox<String> k : cbDraft) {
					try {
						k.setSelectedItem(draftauswahl[changeteam][i]);
						i++;
					} catch (Exception e) {
						try {
							k.setSelectedIndex(-1);
							i++;
						} catch (Exception f) {
							i++;
						}
					}
				}
			}
		});
		cBchangeteam.setBounds(178, 11, 114, 20);
		panel.setLayer(cBchangeteam, 1);
		panel.add(cBchangeteam);

		add(panel);

		randonDraft = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (randonDraft) {
						sleep(1000);
						while (!finishdrafting) {
							int anyteamleft = DraftGui.getwindow().getPanelPlayer().player.size();
							teamfolge = DraftGui.getwindow().getPanelOrder().getTeamfolge();
							for (int k = 0; k < teamfolge.length; k++) {
								cBchangeteam.setSelectedIndex(teamfolge[k]);
								Thread.sleep(100);
								int count = 0;
								for (int m = 0; m < cbDraft.length; m++) {
									try {
										cbDraft[m].setEnabled(true);
										if (cbDraft[m].getX() > 5 && draftauswahl[teamfolge[k]][m] == null) {
											count++;
										}
									} catch (Exception e) {
										break;
									}
								}
								if (count == 0) {
									anyteamleft--;
									waitforstatusupdate = true;
								}
								if (anyteamleft == 0) {
									finishdrafting = false;
									waitforstatusupdate = false;
									endDrafting();
									Thread.currentThread().interrupt();
								}
								threadpoint = k;
								try {
									synchronized (randonDraft) {
										while (!waitforstatusupdate) {
											wait(100);
										}
									}
								} catch (InterruptedException e1) {
									Thread.currentThread().interrupt();
								}
								try {
									changeteam = teamfolge[threadpoint + 1];
									cBchangeteam.setSelectedIndex(changeteam);
								} catch (Exception e) {
									cBchangeteam.setSelectedIndex(0);
								}
								waitforstatusupdate = false;
								if (threadpoint < k) {
									k--;
								}
							}
						}
					}
				} catch (InterruptedException v) {
					Thread.currentThread().interrupt();
				}
			}
		};
	}

	/**
	 * Sollte bei jedem öffnen des Draftfenster aufgerufen werden.
	 * 
	 * Passt den Draft auf Änderungen der Teams an, dabei wird keine Zugehörigkeit
	 * sichergestellt. Initialisiert beim erstmaligen Aufruf, den Hintergrund und
	 * ggf einen Pause Knopf. Startet ggf. den Draft-Thread.<br> called
	 * {@link PanelDraft#endDrafting()} <br> called {@link PanelDraft#getDraftHight()} <br>
	 * called {@link PanelDraft#buildDraftLayout()}
	 */
	@SuppressWarnings("unchecked")
	protected void opendraft() {
		order = DraftGui.getwindow().getPanelOrder().getOrder();
		String[] spieler = DraftGui.getwindow().getPanelPlayer().player.toArray(new String[0]);
		DraftGui.getwindow().visLoading();
		try {
			if (spieler.length != draftauswahl.length) {
				String[][] clonedraftauswahl = draftauswahl.clone();
				draftauswahl = new String[spieler.length][15];
				for (int k = 0; k < draftauswahl.length; k++) {
					for (int j = 0; j < 15; j++) {
						try {
							draftauswahl[k][j] = clonedraftauswahl[k][j];
						} catch (Exception f) {
							draftauswahl[k][j] = null;
						}
					}
				}
			}
		} catch (Exception e) {
			draftauswahl = new String[spieler.length][15];
			for (int k = 0; k < draftauswahl.length; k++) {
				for (int j = 0; j < 15; j++) {
					draftauswahl[k][j] = null;
				}
			}
		}

		cBchangeteam.setModel(new DefaultComboBoxModel<String>(spieler));

		if (order == 2) {
			cBchangeteam.setEnabled(false);

			btnPause = new JButton("Pause");
			btnPause.addActionListener(e -> {
				if (btnPause.getText().equals("Pause")) {
					btnPause.setText("Fortsetzen");
					oldorder = order;
					order = 0;
					cBchangeteam.setEnabled(true);
				} else {
					btnPause.setText("Pause");
					order = oldorder;
					cBchangeteam.setEnabled(false);
					threadpoint -= 1;
					waitforstatusupdate = true;
				}
				panel.revalidate();
				panel.updateUI();
			});
			btnPause.setBounds(470, 10, 110, 23);
			panel.setLayer(btnPause, 2);
			panel.add(btnPause);
		}
		if (finishdrafting) {
			try {
				panel.remove(btnPause);
				panel.revalidate();
				panel.updateUI();
			} catch (Exception e) {
			}
			btnEnd = new JButton("End Draft");
			btnEnd.addActionListener(e -> endDrafting());
			btnEnd.setBounds(470, 10, 110, 23);
			panel.setLayer(btnEnd, 2);
			panel.add(btnEnd);

			cBchangeteam.setEnabled(true);
		}
		DraftGui.getwindow().getFrmPokemonDraft().setBounds(DraftGui.getwindow().getFrmPokemonDraft().getX(),
				DraftGui.getwindow().getFrmPokemonDraft().getY(), 900, getDraftHight());
		panel.setBounds(0, 0, 900, getDraftHight());

		if (!DraftGui.getwindow().isFinishlayout()) {
			ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
			Image img = background.getImage();
			Image temp = img.getScaledInstance(900, getDraftHight(), Image.SCALE_SMOOTH);
			background = new ImageIcon(temp);
			JLabel back = new JLabel(background);
			back.setLayout(null);
			back.setBounds(0, 0, 900, getDraftHight());
			panel.setLayer(back, 0);
			panel.add(back);

			data.PokemonDraft.initTierPokemon();
			buildDraftLayout();
		}
		int i = 0;
		for (JComboBox<String> k : cbDraft) {
			try {
				k.setSelectedItem(draftauswahl[cBchangeteam.getSelectedIndex()][i]);
				i++;
			} catch (Exception e) {
				try {
					k.setSelectedIndex(-1);
					i++;
				} catch (Exception f) {
					i++;
				}
			}
		}
		if (order != 1 && !randonDraft.isAlive()) {
			randonDraft.start();
		}
		panel.revalidate();
		panel.updateUI();
	}

	/**
	 * Gibt auf basis der Anzahl der Ausgewählten Pokemon die Höhe zurück, die das
	 * Fenster haben soll
	 * 
	 * @return int - 110 < X < 510
	 */
	protected int getDraftHight() {
		int hight = 110;
		for (int k : DraftGui.getwindow().getPanelSettings().getCountauswahl()) {
			if (k == 0) {
				hight += 0;
			} else {
				if (k <= 3) {
					hight += 80;
				} else {
					if (k <= 6) {
						hight += 160;
					} else {
						if (k <= 9) {
							hight += 240;
						} else {
							if (k <= 12) {
								hight += 320;
							} else {
								if (k <= 15) {
									hight += 400;
								}
							}
						}
					}
				}
			}
		}
		return hight;
	}

	/**
	 * Generiert das Layout und alle Elemente des Draft-Bildschrims <br> 
	 * called {@link PanelDraft#nextDraftColumn(int)} <br> 
	 * called {@link PanelDraft#safeDraftAuswahl()} <br>
	 * called {@link PanelDraft#endDrafting()} <br>
	 * called [@link PanelDraft#updateTierPokemon()}
	 */
	@SuppressWarnings("unchecked")
	private void buildDraftLayout() {
		DraftGui.getwindow().visLoading();
		data.PokemonDraft.initTierPokemon();
		int[] pokeanzahl = DraftGui.getwindow().getPanelSettings().getCountauswahl().clone();
		int count = 0;
		int line = 120;
		boolean sep = false;
		for (int i = 0; i < pokeanzahl.length; i++) {
			int pkan = pokeanzahl[i];
			if (pkan == 0) {
				sep = false;
				continue;
			}
			if (!sep) {
				JSeparator separator = new JSeparator();
				separator.setBounds(0, line - 60, 900, 2);
				panel.setLayer(separator, 2);
				panel.add(separator);
				labellist[i] = new JLabel(DraftGui.getwindow().getPanelTierlist().getTiernamen(i));
				labellist[i].setFont(new Font("Tahoma", Font.BOLD, 11));
				panel.setLayer(labellist[i], 2);
				labellist[i].setBounds(54, line - 40, 550, 14);
				panel.add(labellist[i]);
			}
			try {
				int[] nxco = nextDraftColumn(pkan);
				for (int co = 0; co < pkan; co++) {
					cbDraft[count] = new FilterComboBox(Arrays.asList(data.PokemonDraft.getTierPokemon(i)));
					tierlistcB[count] = i;
					cbDraft[count].addPopupMenuListener(new PopupListener(cbDraft[count]) {
						@Override
						public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
							if (box.getSelectedItem() != null && box.getSelectedIndex() >= 0) {
								box.updateUI();
								safeDraftAuswahl();
								String name = box.getSelectedItem().toString();
								data.PokemonDraft.removePokemonfromTier(name);
								updateTierPokemon();
								if (order == 2) {
									if (!selectnewPokemon) {
										return;
									}
									for (FilterComboBox cb : cbDraft) {
										try {
											cb.setEnabled(false);
										} catch (Exception e) {
											break;
										}
									}
									selectnewPokemon = false;
									waitforstatusupdate = true;
								} else if (order == 1) {
									int pokecount = 0;
									int auswahlcount = 0;
									for (int k : DraftGui.getwindow().getPanelSettings().getCountauswahl()) {
										pokecount += k;
									}
									for (int k = 0; k < DraftGui.getwindow().getPanelPlayer().player.size(); k++) {
										for (int m = 0; m < pokecount; m++) {
											if (draftauswahl[k][m] == null) {
												auswahlcount++;
											}
										}
									}
									if (auswahlcount == 0) {
										try {
											btnEnd.setEnabled(true);
										} catch (Exception e) {
											btnEnd = new JButton("End Draft");
											btnEnd.addActionListener(f -> endDrafting());
											btnEnd.setBounds(470, 10, 110, 23);
											btnEnd.setEnabled(true);
											panel.setLayer(btnEnd, 2);
											panel.add(btnEnd);
										}
										DraftGui.getwindow().getPanelOrder().setOrder();
										DraftGui.getwindow().visDraft();
									}
								}
							}
						}
					});
					cbDraft[count].setSelectedIndex(-1);
					cbDraft[count].setBounds(nxco[co], line - 30, 170, 20);
					panel.setLayer(cbDraft[count], 2);
					cbDraft[count].setEnabled(order <= 2);
					switch (i) {
					case 0:
						cbDraft[count].setBackground(new Color(232, 198, 236));
						break;
					case 1:
						cbDraft[count].setBackground(new Color(82, 192, 65));
						break;
					case 2:
						cbDraft[count].setBackground(new Color(130, 213, 197));
						break;
					case 3:
						cbDraft[count].setBackground(new Color(219, 214, 147));
						break;
					case 4:
						cbDraft[count].setBackground(new Color(195, 75, 96));
						break;
					case 5:
						cbDraft[count].setBackground(new Color(102, 103, 204));
						break;
					default:
						break;
					}
					panel.add(cbDraft[count]);
					count++;
				}
				sep = false;
			} catch (Exception e) {
				pokeanzahl[i] -= 3;
				i -= 1;
				sep = true;
			}
			line += 80;
			panel.revalidate();
			panel.updateUI();
		}
		panel.revalidate();
		panel.updateUI();
		DraftGui.getwindow().setFinishlayout(true);
	}

	/**
	 * @param k int Die Anzahl der Pokemon von einem Tier
	 * @return int[] - Der Länge 1 - 3 mit den jeweiligen x-Positionsangaben für die
	 *         Auswahl Boxen
	 */
	private int[] nextDraftColumn(int k) {
		if (k > 3) {
			k = 3;
		}
		switch (k) {
		case 1:
			return new int[] { 365 };
		case 2:
			return new int[] { 187, 543 };
		case 3:
			return new int[] { 97, 365, 632 };
		default:
			return new int[] {};
		}
	}

	/**
	 * Speichert alle aktuell ausgewählten ComboBoxen {@link draftauswahl} , setzt
	 * {@link selectnewPokemon} auf true, falls ein neuer Wert gesetzt wurde
	 */
	private void safeDraftAuswahl() {
		for (int k = 0; k < 15; k++) {
			try {
				if (draftauswahl[cBchangeteam.getSelectedIndex()][k] == null && cbDraft[k].getSelectedIndex() != -1) {
					selectnewPokemon = true;
				}
				if (draftauswahl[cBchangeteam.getSelectedIndex()][k] != null && cbDraft[k].getSelectedIndex() != -1
						&& draftauswahl[cBchangeteam.getSelectedIndex()][k] != cbDraft[k].getSelectedItem()
								.toString()) {
					data.PokemonDraft.addPokemontoTier(tierlistcB[k], draftauswahl[cBchangeteam.getSelectedIndex()][k]);
				}
				if (cbDraft[k].getSelectedIndex() != -1) {
					draftauswahl[cBchangeteam.getSelectedIndex()][k] = cbDraft[k].getSelectedItem().toString();
				}
			} catch (Exception e) {
				break;
			}
		}
	}

	/**
	 * updatet die Tiernamen im Drafting-Fenster
	 * 
	 * @param k    int Tierindex
	 * @param text String, der neue Name
	 * @throws NullPointerException for k
	 */
	protected void updateTiernamen(int k, String text) throws NullPointerException {
		DraftGui.getwindow().getPanelTierlist().setTiernamen(k, text);
		labellist[k].setText(text.trim());
		panel.revalidate();
		panel.updateUI();
	}

	/**
	 * Updatet die ComboBoxen auf die Aktuelle PokemonTierListe
	 */
	@SuppressWarnings("unchecked")
	protected synchronized void updateTierPokemon() {
		if (DraftGui.getwindow().isFinishlayout()) {
			for (int count = 0; count < tierlistcB.length; count++) {
				try {
					cbDraft[count].setModel(
							new DefaultComboBoxModel<String>(data.PokemonDraft.getTierPokemon(tierlistcB[count])));
					cbDraft[count].setList(data.PokemonDraft.getTierPokemon(tierlistcB[count]));
					cbDraft[count].setSelectedItem(draftauswahl[changeteam][count]);
				} catch (Exception e) {
					break;
				}
			}
		}
	}

	/**
	 * Entfernt ein Pokemon aus {@link draftauswahl}
	 * 
	 * @param pokemon - String , das zu entfernende Pokemon
	 */
	public void removeEntetyFromDraftAuswahl(String pokemon) {
		String[][] safedauswahl = draftauswahl.clone();
		for (int f = 0; f < safedauswahl.length; f++) {
			for (int i = 0; i < safedauswahl[0].length; i++) {
				try {
					if (safedauswahl[f][i].equals(pokemon)) {
						safedauswahl[f][i] = null;
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		draftauswahl = safedauswahl;
	}

	/**
	 * Entfernt ein Team aus {@link draftauswahl}
	 * 
	 * @param toremove int , der Index des Teams
	 */
	protected void removeTeamFromDraft(int toremove) {
		String[][] temp = new String[draftauswahl.length - 1][15];
		for (int k = 0; k < draftauswahl.length - 1; k++) {
			if (k < toremove) {
				temp[k] = draftauswahl[k];
			} else {
				temp[k] = draftauswahl[k + 1];
			}
		}
		draftauswahl = temp;
	}

	/**
	 * Beendet den Draftvorgang und wechselt auf die Übersicht
	 */
	private void endDrafting() {
		finishdrafting = true;
		DraftGui.getwindow().visAfterDraft(draftauswahl);
	}
}