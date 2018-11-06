package draftpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuEvent;
import client.FilterComboBox;
import client.PopupListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelDraft extends JPanel {

	private JPanel panel = new JPanel();
	private String[][] draftauswahl;
	private JComboBox<String> cBchangeteam = new JComboBox<>();
	private FilterComboBox[] cbDraft = new FilterComboBox[15];
	private int changeteam = 0;
	private JLabel[] labellist = new JLabel[6];
	private int[] tierlistcB = new int[cbDraft.length];
	private static Thread one = new Thread();
	private int[] teamfolge;
	private boolean waitforstatusupdate = false;	//DO NOT TOUCH
	protected boolean finishdrafting = false;
	private boolean selectnewPokemon = false;	//DO NOT TOUCH
	private int order;	//DO NOT TOUCH
	private int oldorder;	//DO NOT TOUCH
	private int threadpoint; //DO NOT TOUCH

	@SuppressWarnings("unchecked")
	public PanelDraft() {

		panel.setBounds(0, 0, 600, 50);
		panel.setLayout(null);

		Arrays.fill(cbDraft, 0, cbDraft.length - 1, new FilterComboBox());

		JLabel lbteams = new JLabel("Teams:");
		lbteams.setBounds(123, 14, 45, 14);
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
		panel.add(cBchangeteam);

		add(panel);

		one = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (one) {
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
									Thread.currentThread().interrupt();
									endDrafting();
								}
								nextteam(k);
								if(threadpoint<k) {
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

	private synchronized void nextteam(int k) {
		threadpoint = k;
		try {
			synchronized (one) {

				while (!waitforstatusupdate) {
					wait(100);
				}
			}
		} catch (InterruptedException e1) {
			one.interrupt();
		}
		try {
			changeteam = teamfolge[threadpoint + 1];
			cBchangeteam.setSelectedIndex(changeteam);
		} catch (Exception e) {
			cBchangeteam.setSelectedIndex(0);
		}
		waitforstatusupdate = false;
	}

	@SuppressWarnings("unchecked")
	protected void opendraft() {
		order = DraftGui.getwindow().getPanelOrder().getOrder();
		String[] spieler = DraftGui.getwindow().getPanelPlayer().player.toArray(new String[0]);
		DraftGui.getwindow().visLoading();
		try {
			if (draftauswahl == null) {
				draftauswahl = new String[spieler.length][15];
				for (int k = 0; k < draftauswahl.length; k++) {
					Arrays.fill(draftauswahl[k], null);
				}
			}
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
		if (order != 1 && !one.isAlive()) {
			cBchangeteam.setEnabled(false);

			JButton btnPause = new JButton("Pause");
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
			});
			btnPause.setBounds(470, 10, 110, 23);
			panel.add(btnPause);

			one.start();

		}
		DraftGui.getwindow().getFrmPokemonDraft().setBounds(DraftGui.getwindow().getFrmPokemonDraft().getX(),
				DraftGui.getwindow().getFrmPokemonDraft().getY(), 900, getDraftHight());
		if (!DraftGui.getwindow().isFinishlayout()) {
			data.PokemonDraft.initTierPokemon();
			draftLayout();
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
	}

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

	@SuppressWarnings("unchecked")
	private void draftLayout() {
		DraftGui.getwindow().visLoading();
		for (FilterComboBox k : cbDraft) {
			try {
				DraftGui.getwindow().getPanelDraft().remove(k);
			} catch (Exception e) {
				break;
			}
		}
		int[] pokeanzahl = DraftGui.getwindow().getPanelSettings().getCountauswahl().clone();
		int count = 0;
		int line = 120;
		boolean sep = false;
		// die jeweils seleceteten Pokemon (Anzahl)
		for (int i = 0; i < pokeanzahl.length; i++) {
			int pkan = pokeanzahl[i];
			if (pkan == 0) {
				sep = false;
				continue;
			}
			if (!sep) {
				JSeparator separator = new JSeparator();
				separator.setBounds(0, line - 60, 900, 2);
				DraftGui.getwindow().getPanelDraft().add(separator);
				labellist[i] = new JLabel(DraftGui.getwindow().getPanelTierlist().getTiernamen(i));
				labellist[i].setFont(new Font("Tahoma", Font.BOLD, 11));
				labellist[i].setBounds(54, line - 40, 550, 14);
				DraftGui.getwindow().getPanelDraft().add(labellist[i]);
			}
			try {
				int[] nxco = nextDraftColumn(pkan);
				for (int co = 0; co < pkan; co++) {
					cbDraft[count] = new FilterComboBox(Arrays.asList(data.PokemonDraft.getTierPokemon(i)));
					tierlistcB[count] = i;
					cbDraft[count].addPopupMenuListener(new PopupListener(cbDraft[count]) {
						@Override
						public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
							changeDraftPokemon(this.box);
							if (box.getSelectedItem() != null && (box.getSelectedIndex() >= 0
									|| !box.getSelectedItem().equals("keine Doppelten"))) {
								box.updateUI();
								safeDraftAuswahl();
								selectnext(cBchangeteam.getSelectedIndex(), box.getSelectedItem().toString());
							}
						}
					});
					cbDraft[count].setSelectedIndex(-1);
					cbDraft[count].setBounds(nxco[co], line - 30, 170, 20);
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
					DraftGui.getwindow().getPanelDraft().add(cbDraft[count]);
					count++;
				}
				sep = false;
			} catch (Exception e) {
				pokeanzahl[i] -= 3;
				i -= 1;
				sep = true;
			}
			line += 80;
		}
		DraftGui.getwindow().setFinishlayout(true);
	}

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

	private void safeDraftAuswahl() {
		for (int k = 0; k < 15; k++) {
			try {
				if (draftauswahl[cBchangeteam.getSelectedIndex()][k] == null && cbDraft[k].getSelectedIndex() != -1) {
					selectnewPokemon = true;
				}
				if (cbDraft[k].getSelectedIndex() != -1) {
					draftauswahl[cBchangeteam.getSelectedIndex()][k] = cbDraft[k].getSelectedItem().toString();
				}
			} catch (Exception e) {
				break;
			}
		}
	}

	private void changeDraftPokemon(JComboBox<String> box) {
		for (int k = 0; k < cbDraft.length; k++) {
			for (int j = 0; j < cbDraft.length; j++) {
				if (k != j) {
					try {
						if (box.getSelectedItem() == "" || box.getSelectedItem().equals("keine Doppelten")) {
							box.setSelectedIndex(-1);
							return;
						}
						if (cbDraft[k].getSelectedItem().equals(cbDraft[j].getSelectedItem())) {
							box.setSelectedItem("keine Doppelten");
							return;
						}
					} catch (Exception e) {
						break;
					}
				}
			}
		}
	}

	protected void updateTiernamen(int k, String text) throws NullPointerException {
		DraftGui.getwindow().getPanelTierlist().setTiernamen(k, text);
		labellist[k].setText(text.trim());
	}

	@SuppressWarnings("unchecked")
	protected void updateTierPokemon() {
		if (DraftGui.getwindow().isFinishlayout()) {
			data.PokemonDraft.initTierPokemon();
			for (int count = 0; count < tierlistcB.length; count++) {
				try {
					cbDraft[count].setModel(
							new DefaultComboBoxModel<String>(data.PokemonDraft.getTierPokemon(tierlistcB[count])));
					cbDraft[count].setSelectedItem(draftauswahl[changeteam][count]);
				} catch (Exception e) {
					break;
				}
			}
		}
	}

	public void renewDraftauswahl(String ort) {
		String[][] safedauswahl = draftauswahl.clone();
		for (int f = 0; f < safedauswahl.length; f++) {
			for (int i = 0; i < safedauswahl[0].length; i++) {
				if (safedauswahl[f][i].equals(ort)) {
					safedauswahl[f][i] = null;
				}
			}
		}
		draftauswahl = safedauswahl;
	}

	public int getDraftauswahllength() {
		return draftauswahl.length;
	}

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

	private void selectnext(int teamindex, String name) {
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
				Object[] options = { "Ja", "Nein" };
				if (JOptionPane.showOptionDialog(DraftGui.getwindow().getFrmPokemonDraft(),
						"Alle Pokemon sind gedraftet, möchtest du den Draft verlassen?", "Es sind noch Dinge ungeklärt",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[1]) == 1) {
					return;
				}
				endDrafting();
			}
		} else {
			safeDraftAuswahl();
		}
	}

	private void endDrafting() {
		finishdrafting = true;
		DraftGui.getwindow().visAfterDraft(draftauswahl);
	}
}