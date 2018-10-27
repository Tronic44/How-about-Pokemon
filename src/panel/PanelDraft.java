package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	boolean finishdraft = false;
	private int changeteam = 0;

	public PanelDraft() {

		panel.setLayout(null);

		JLabel lbteams = new JLabel("Teams:");
		lbteams.setBounds(123, 14, 45, 14);
		panel.add(lbteams);

		add(panel);
	}

	protected void opendraft() {
		String[] Spieler = Gui.getwindow().getPanel_player().Spieler;
		Gui.getwindow().visLoading();
		try {
			if (draftauswahl == null) {
				draftauswahl = new int[Spieler.length][15];
				for (int k = 0; k < draftauswahl.length; k++) {
					for (int j = 0; j < 15; j++) {
						draftauswahl[k][j] = -1;
					}
				}
			}
			if (Spieler.length != draftauswahl.length) {
				int[][] clonedraftauswahl = draftauswahl.clone();
				draftauswahl = new int[Spieler.length][15];
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
			draftauswahl = new int[Spieler.length][15];
			for (int k = 0; k < draftauswahl.length; k++) {
				for (int j = 0; j < 15; j++) {
					draftauswahl[k][j] = -1;
				}
			}
		}
		cBchangeteam = new JComboBox<String>();
		cBchangeteam.addItemListener(new ItemListener() {
			@SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.DESELECTED) {
					for (int k = 0; k < 15; k++) {
						try {
							draftauswahl[changeteam][k] = cbDraft[k].getSelectedIndex();
						} catch (Exception e) {

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
			}
		});
		cBchangeteam.setModel(new DefaultComboBoxModel<String>(Spieler));
		cBchangeteam.setBounds(178, 11, 114, 20);
		if (Gui.getwindow().getPanel_order().getOrder()!=1) {
			cBchangeteam.setEnabled(false);
		}
		Gui.getwindow().getPanel_draft().add(cBchangeteam);

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
		Gui.getwindow().getFrmPokemonDraft().setBounds(Gui.getwindow().getFrmPokemonDraft().getX(),
				Gui.getwindow().getFrmPokemonDraft().getY(), 1100, hight);
		if (!finishdraft) {
			Data.inittierpokemon();
			draftlayout();
		}
		Gui.getwindow().visDraft();
	}

	@SuppressWarnings("unchecked")
	private void draftlayout() {
		Gui.getwindow().visLoading();
		for (FilterComboBox k : cbDraft) {
			try {
				Gui.getwindow().getPanel_draft().remove(k);
			} catch (Exception e) {

			}
		}
		int[] pokeanzahl = Gui.getwindow().getPanel_settings().countauswahl.clone();
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
				Gui.getwindow().getPanel_draft().add(separator);
				JLabel lblNewLabel_2 = new JLabel(Gui.getwindow().getPanel_tierlist().gettiernamen(i));
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_2.setBounds(54, line - 40, 550, 14);
				Gui.getwindow().getPanel_draft().add(lblNewLabel_2);
			}
			try {
				int[] nxco = nextcolumn(pkan);
				for (int co = 0; co < pkan; co++) {
					cbDraft[count] = new FilterComboBox(Arrays.asList(Data.gettierpokemon(i)));
					cbDraft[count].addPopupMenuListener(new PopupListener(cbDraft[count]) {
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
					cbDraft[count].setEnabled(false);
					cbDraft[count].setBounds(nxco[co], line, 169, 20);
					if (Gui.getwindow().getPanel_order().getOrder()!=1) {
						cbDraft[count].setEnabled(false);
					}
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
					}
					Gui.getwindow().getPanel_draft().add(cbDraft[count]);
					count++;
				}
				sep = false;
			} catch (Exception e) {
				e.getStackTrace();
				pokeanzahl[i] -= 3;
				i--;
				sep = true;
			}
			line += 130;
		}
		finishdraft = true;
//		cbDraft[0].setEnabled(true);
	}

	private int[] nextcolumn(int k) {
		if (k > 3) {
			k = 3;
		}
		int[] spalte;
		switch (k) {
		case 1:
			spalte = new int[] { 464 };
			break;
		case 2:
			spalte = new int[] { 260, 675 };
			break;
		case 3:
			spalte = new int[] { 54, 464, 860 };
			break;
		default:
			return null;
		}
		return spalte;
	}

	@SuppressWarnings("unchecked")
	protected void resetdraft() {
		draftauswahl = null;
		for (JComboBox<String> k : cbDraft) {
			try {
				k.setSelectedIndex(-1);
			} catch (Exception f) {
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
							box.setEditable(true);
							box.setSelectedItem("keine Doppelten");
							box.setEditable(false);
							return;
						}

					} catch (Exception e) {

					}
				}

			}
		}

	}

	private void selectnext(int teamindex, String name) {
		System.out.print(name);
	}

}
