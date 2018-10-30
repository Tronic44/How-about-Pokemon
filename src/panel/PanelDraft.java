package panel;

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
import client.Data;
import client.FilterComboBox;
import client.PopupListener;

@SuppressWarnings("serial")
public class PanelDraft extends JPanel {

	private JPanel panel = new JPanel();
	protected int[][] draftauswahl;
	protected JComboBox<String> cBchangeteam;
	private FilterComboBox cBD01;
	private FilterComboBox cBD02;
	private FilterComboBox cBD03;
	private FilterComboBox cBD04;
	private FilterComboBox cBD05;
	private FilterComboBox cBD06;
	private FilterComboBox cBD07;
	private FilterComboBox cBD08;
	private FilterComboBox cBD09;
	private FilterComboBox cBD10;
	private FilterComboBox cBD11;
	private FilterComboBox cBD12;
	private FilterComboBox cBD13;
	private FilterComboBox cBD14;
	private FilterComboBox cBD15;
	private FilterComboBox[] cbDraft = new FilterComboBox[] { cBD01, cBD02, cBD03, cBD04, cBD05, cBD06, cBD07, cBD08,
			cBD09, cBD10, cBD11, cBD12, cBD13, cBD14, cBD15 };
	private int changeteam = 0;
	private JLabel[] labellist = new JLabel[6];

	public PanelDraft() {

		panel.setLayout(null);

		JLabel lbteams = new JLabel("Teams:");
		lbteams.setBounds(123, 14, 45, 14);
		panel.add(lbteams);

		add(panel);
	}

	@SuppressWarnings("unchecked")
	protected void opendraft() {
		String[] spieler = Gui.getwindow().getPanelPlayer().spieler;
		Gui.getwindow().visLoading();
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
						} catch (Exception e) {
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
		cBchangeteam = new JComboBox<>();
		cBchangeteam.addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.DESELECTED) {
				for (int k = 0; k < 15; k++) {
					try {
						draftauswahl[changeteam][k] = cbDraft[k].getSelectedIndex();
					} catch (Exception e) {
						break;
					}
				}
			}
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
		cBchangeteam.setModel(new DefaultComboBoxModel<String>(spieler));
		cBchangeteam.setBounds(178, 11, 114, 20);
		if (Gui.getwindow().getPanelOrder().getOrder() != 1) {
			cBchangeteam.setEnabled(false);
		}
		Gui.getwindow().getPanelDraft().add(cBchangeteam);
		Gui.getwindow().getFrmPokemonDraft().setBounds(Gui.getwindow().getFrmPokemonDraft().getX(),
				Gui.getwindow().getFrmPokemonDraft().getY(), 1100, getDrafthigth());
		if (!Gui.getwindow().isFinishdraft()) {
			Data.inittierpokemon();
			draftlayout();
		}
		Gui.getwindow().visDraft();
	}

	protected int getDrafthigth() {
		int hight = 100;
		for (int k : Gui.getwindow().getPanelSettings().getCountauswahl()) {
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
	private void draftlayout() {
		Gui.getwindow().visLoading();
		for (FilterComboBox k : cbDraft) {
			try {
				Gui.getwindow().getPanelDraft().remove(k);
			} catch (Exception e) {
				break;
			}
		}
		int[] pokeanzahl = Gui.getwindow().getPanelSettings().getCountauswahl().clone();
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
				Gui.getwindow().getPanelDraft().add(separator);
				labellist[i] = new JLabel(Gui.getwindow().getPanelTierlist().gettiernamen(i));
				labellist[i].setFont(new Font("Tahoma", Font.BOLD, 11));
				labellist[i].setBounds(54, line - 40, 550, 14);
				Gui.getwindow().getPanelDraft().add(labellist[i]);
			}
			try {
				int[] nxco = nextcolumn(pkan);
				for (int co = 0; co < pkan; co++) {
					cbDraft[count] = new FilterComboBox(Arrays.asList(Data.gettierpokemon(i)));
					cbDraft[count].addPopupMenuListener(new PopupListener(cbDraft[count]) {
						@Override
						public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
							changedraftpokemon(this.box);
							if (box.getSelectedItem() != null && (box.getSelectedIndex() >= 0
									|| !box.getSelectedItem().equals("keine Doppelten"))) {
								box.updateUI();
								selectnext(cBchangeteam.getSelectedIndex(), box.getSelectedItem().toString());
							}
						}
					});
					cbDraft[count].setSelectedIndex(-1);
					cbDraft[count].setBounds(nxco[co], line, 169, 20);
					cbDraft[count].setEnabled(Gui.getwindow().getPanelOrder().getOrder() <= 2);
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
					Gui.getwindow().getPanelDraft().add(cbDraft[count]);
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
		Gui.getwindow().setFinishdraft(true);
	}

	private int[] nextcolumn(int k) {
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

	@SuppressWarnings("unchecked")
	protected void resetdraft() {
		draftauswahl = null;
		for (JComboBox<String> k : cbDraft) {
			try {
				k.setSelectedIndex(-1);
			} catch (Exception f) {
				break;
			}
		}
	}

	private void changedraftpokemon(JComboBox<String> box) {
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

	protected void updatetiernamen(int k, String text) throws NullPointerException {
		Gui.getwindow().getPanelTierlist().setTiernamen(k, text);
		labellist[k].setText(text.trim());
	}

	private void selectnext(int teamindex, String name) {
		System.out.println(teamindex + " " + name);
	}
}