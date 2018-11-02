package draftpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuEvent;
import client.FilterComboBox;
import client.PopupListener;

@SuppressWarnings("serial")
public class PanelDraft extends JPanel {

	private JPanel panel = new JPanel();
	private int[][] draftauswahl;
	protected JComboBox<String> cBchangeteam = new JComboBox<>();
	private FilterComboBox[] cbDraft = new FilterComboBox[15];
	private int changeteam = 0;
	private JLabel[] labellist = new JLabel[6];
	private int[] tierlistcB = new int[cbDraft.length];

	@SuppressWarnings("unchecked")
	public PanelDraft() {

		panel.setBounds(0, 0, 400, 50);
		panel.setLayout(null);
		
		Arrays.fill(cbDraft, 0, cbDraft.length-1, new FilterComboBox());

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
						k.setSelectedIndex(draftauswahl[changeteam][i]);
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
	}

	protected void opendraft() {
		String[] spieler = DraftGui.getwindow().getPanelPlayer().player.toArray(new String[0]);
		DraftGui.getwindow().visLoading();
		try {
			if (draftauswahl == null) {
				draftauswahl = new int[spieler.length][15];
				for (int k = 0; k < draftauswahl.length; k++) {
					for (int j = 0; j < 15; j++) {
						draftauswahl[k][j] = -1;
					}
				}
			}
			if (spieler.length != draftauswahl.length) {
				int[][] clonedraftauswahl = draftauswahl.clone();
				draftauswahl = new int[spieler.length][15];
				for (int k = 0; k < draftauswahl.length; k++) {
					for (int j = 0; j < 15; j++) {
						try {
							draftauswahl[k][j] = clonedraftauswahl[k][j];
						} catch (Exception f) {
							draftauswahl[k][j] = -1;
						}
					}
				}
			}
		} catch (Exception e) {
			draftauswahl = new int[spieler.length][15];
			for (int k = 0; k < draftauswahl.length; k++) {
				for (int j = 0; j < 15; j++) {
					draftauswahl[k][j] = -1;
				}
			}
		}
		cBchangeteam.setModel(new DefaultComboBoxModel<String>(spieler));
		if (DraftGui.getwindow().getPanelOrder().getOrder() != 1) {
			cBchangeteam.setEnabled(false);
		}
		DraftGui.getwindow().getFrmPokemonDraft().setBounds(DraftGui.getwindow().getFrmPokemonDraft().getX(),
				DraftGui.getwindow().getFrmPokemonDraft().getY(), 1100, getDraftHight());
		if (!DraftGui.getwindow().isFinishdraft()) {
			data.PokemonDraft.initTierPokemon();
			draftLayout();
		}
		DraftGui.getwindow().visDraft();
	}

	protected int getDraftHight() {
		int hight = 100;
		for (int k : DraftGui.getwindow().getPanelSettings().getCountauswahl()) {
			if (k == 0) {
				hight += 0;
			} else {
				if (k <= 3) {
					hight += 130;
				} else {
					if (k <= 6) {
						hight += 260;
					} else {
						if (k <= 9) {
							hight += 390;
						} else {
							if (k <= 12) {
								hight += 520;
							} else {
								if (k <= 15) {
									hight += 650;
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
				separator.setBounds(0, line - 60, 1100, 2);
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
					cbDraft[count].setBounds(nxco[co], line, 169, 20);
					cbDraft[count].setEnabled(DraftGui.getwindow().getPanelOrder().getOrder() <= 2);
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
			line += 130;
		}
		DraftGui.getwindow().setFinishdraft(true);
	}

	private int[] nextDraftColumn(int k) {
		if (k > 3) {
			k = 3;
		}
		switch (k) {
		case 1:
			return new int[] { 464 };
		case 2:
			return new int[] { 260, 675 };
		case 3:
			return new int[] { 54, 464, 860 };
		default:
			return new int[] {};
		}
	}

	private void safeDraftAuswahl() {
		for (int k = 0; k < 15; k++) {
			try {
				draftauswahl[changeteam][k] = cbDraft[k].getSelectedIndex();
			} catch (Exception e) {
				break;
			}
		}
	}

//	protected void resetDraft() {
//		draftauswahl = null;
//		for (JComboBox<String> k : cbDraft) {
//			try {
//				k.setSelectedIndex(-1);
//			} catch (Exception f) {
//				break;
//			}
//		}
//	}

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
		if (DraftGui.getwindow().isFinishdraft()) {
			data.PokemonDraft.initTierPokemon();
			for (int count = 0; count < tierlistcB.length; count++) {
				try {
					cbDraft[count].setModel(
							new DefaultComboBoxModel<String>(data.PokemonDraft.getTierPokemon(tierlistcB[count])));
					cbDraft[count].setSelectedIndex(draftauswahl[changeteam][count]);
				} catch (Exception e) {
					break;
				}
			}
		}
	}

	public void renewDraftauswahl(int ort) {
		int[][] safedauswahl = draftauswahl.clone();
		for (int f = 0; f < safedauswahl.length; f++) {
			for (int i = 0; i < safedauswahl[0].length; i++) {
				if (safedauswahl[f][i] == ort) {
					safedauswahl[f][i] = -1;
				}
				if (safedauswahl[f][i] > ort) {
					safedauswahl[f][i] -= 1;
				}
			}
		}
		draftauswahl = safedauswahl;
	}

	public int getDraftauswahllength() {
		return draftauswahl.length;
	}

	protected void removeFromDraft(int toremove) {
		int[][] temp = new int[draftauswahl.length - 1][15];
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
//		System.out.println(teamindex + " " + name);
	}
}