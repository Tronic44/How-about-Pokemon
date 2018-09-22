package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.List;
import javax.swing.JTextField;
import client.Writer;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JList;

public class MainMenu {

	public static JOptionPane alert;
	private JFrame frame;
	private JPanel panelMainMenu;
	private JPanel panelStartDraft;
	private JPanel panelLoadDraft;
	private JPanel panel_tierlist;
	private List list;
	private JTextField tFPoke;
	private JRadioButton radioButtonS;
	private JRadioButton radioButtonA;
	private JRadioButton radioButtonB;
	private JRadioButton radioButtonC;
	private JRadioButton radioButtonD;
	private JRadioButton radioButtonX;
	private JRadioButton radioButtonnull;
	private ButtonGroup tierlistbuttongruppe;
	private JPanel panel_draft;
	private JPanel panel_player;
	private JPanel panel_settings;
	private JPanel panel_order;
	private String[][] tierlist;
	private String[] tiername;
	private JTextField tF_tiername;
	private JComboBox<String> cBTierlist;
	private String[][] teamlist;
	private String[] teamname;
	private JEditorPane ePTeam;
	public String[] Player;
	private JEditorPane ePfinalteam;
	private JTextField tF_Teams;
	private JComboBox cBTeams;

	public static void startMainMenu() {
		gui.Manage.initPoketier();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu() {
		openMainMenu();
	}

	private void openMainMenu() {
		frame = new JFrame();
		frame.setBounds(100, 100, 409, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		initpanel();
		initmenu();
		initstartdraft();
		initmenubar();
		inittierlist();
		initdraft();
		initplayer();

	}

	private void initpanel() {
		panelMainMenu = new JPanel();
		frame.getContentPane().add(panelMainMenu, "name_526992955635103");
		panelMainMenu.setLayout(null);
		panelMainMenu.setVisible(true);

		panelStartDraft = new JPanel();
		frame.getContentPane().add(panelStartDraft, "name_527021172921335");
		panelStartDraft.setLayout(null);
		panelStartDraft.setVisible(false);

		panelLoadDraft = new JPanel();
		frame.getContentPane().add(panelLoadDraft, "name_527666975961040");
		panelLoadDraft.setLayout(null);
		panelLoadDraft.setVisible(false);

		panel_tierlist = new JPanel();
		frame.getContentPane().add(panel_tierlist, "name_2032838076395");
		panel_tierlist.setLayout(null);
		panel_tierlist.setVisible(false);

		panel_player = new JPanel();
		frame.getContentPane().add(panel_player, "name_601620298180688");
		panel_player.setVisible(false);
		panel_player.setLayout(null);

		panel_settings = new JPanel();
		frame.getContentPane().add(panel_settings, "name_601636768594680");
		panel_settings.setVisible(false);
		panel_settings.setLayout(null);

		panel_order = new JPanel();
		frame.getContentPane().add(panel_order, "name_601652242106220");
		panel_order.setVisible(false);
		panel_order.setLayout(null);

		panel_draft = new JPanel();
		frame.getContentPane().add(panel_draft, "name_2679324427935");
		panel_draft.setLayout(null);
		panel_draft.setLayout(null);

	}

	private void initplayer() {
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int count = (int) spinner.getValue();
				if (count < 0) {
					spinner.setValue(0);
				} else {
					Player = new String[count];
				}

			}
		});
		spinner.setBounds(22, 11, 50, 20);
		panel_player.add(spinner);

		JLabel lblSpieleranzahl = new JLabel("Team-Anzahl");
		lblSpieleranzahl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpieleranzahl.setBounds(78, 13, 106, 17);
		panel_player.add(lblSpieleranzahl);

		ePTeam = new JEditorPane();
		ePTeam.setBounds(22, 52, 176, 479);
		panel_player.add(ePTeam);

		JButton btnPlayer = new JButton("Bestätige");
		btnPlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String[] Eingabe = ePTeam.getText().split("\n");
				int count = 0;
				for (int i = 0; i < Eingabe.length; i++) {
					if (Eingabe[i].trim().length() > 1) {
						count++;
					}
				}
				if ((int) spinner.getValue() == 0) {
					Manage.msgbox("Du willst doch nicht ohne auch nur ein Team spielen, oder?");
				} else {
					if (count != (int) spinner.getValue()) {
						Manage.msgbox("Deine Liste Stimmt nicht mit der Team Anzahl überein" + "\n"
								+ "Denk dran: Teams haben niemals nur einen Buchstaben!");
					} else {
						String[] Spieler = new String[count];
						for (int k = 0; k < Spieler.length; k++) {
							for (int i = k; i < Eingabe.length; i++) {
								if (Eingabe[i].trim().length() > 1) {
									if (k > 0) {
										if (Eingabe[i].trim().equals(Spieler[k - 1])) {
											continue;
										}
									}
									Spieler[k] = Eingabe[i].trim();
									break;
								}
							}
						}
						teamname = new String[count];
						String list = "";
						for (int k = 0; k < Spieler.length; k++) {
							Player = new String[Spieler.length];
							Player[k] = Spieler[k];
							teamname[k] = Player[k] + ":";
							list = list + (k + 1) + "   " + Player[k] + "\n";
						}
						teamname[teamname.length - 1] = teamname[teamname.length - 1].substring(0,
								teamname[teamname.length - 1].length() - 1);
						ePfinalteam.setText(list);
					}
				}
			}
		});
		btnPlayer.setBounds(65, 542, 89, 23);
		panel_player.add(btnPlayer);

		ePfinalteam = new JEditorPane();
		ePfinalteam.setEditable(false);
		ePfinalteam.setBounds(207, 52, 176, 293);
		panel_player.add(ePfinalteam);

		JLabel lblNewLabel = new JLabel("Gespeicherte Teams");
		lblNewLabel.setBounds(239, 37, 106, 14);
		panel_player.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Team-Namen");
		lblNewLabel_1.setBounds(65, 37, 69, 14);
		panel_player.add(lblNewLabel_1);

		tF_Teams = new JTextField();
		tF_Teams.setColumns(10);
		tF_Teams.setBounds(249, 367, 86, 20);
		panel_player.add(tF_Teams);

		JButton btnsafeteams = new JButton("Speichern");
		btnsafeteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tF_Teams.getText().equals("") || tF_Teams.getText().equals("Gespeichert")) {
					gui.Manage.msgbox("Du hast keinen Namen eingegeben");
					tF_Teams.setText("");
				} else {
					if (tF_Teams.getText().contains(":")) {
						gui.Manage.msgbox("Der Name das keinen Doppelpunkt enthalten");
					} else {
						if (ePfinalteam.getText().length() > 2) {
							Boolean b = true;
							for (String k : teamname) {
								if (tF_Teams.getText().equals(k)) {
									gui.Manage.msgbox("Der Name existiert schon");
									b = false;
									break;
								}
							}
							if (b) {
								Writer.print("teamlist", tF_Teams.getText(), teamname);
								tF_Teams.setText("Gespeichert");
								panel_player.remove(cBTeams);
								teamlist();
							}
						}

					}
				}
			}

		});
		btnsafeteams.setBounds(249, 398, 89, 23);
		panel_player.add(btnsafeteams);

		cBTeams = new JComboBox(new Object[] {});
		cBTeams.setBounds(234, 469, 124, 28);
		panel_player.add(cBTeams);

		JButton btnloadteams = new JButton("Laden");
		btnloadteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String read="";
				for(int k=1;k<teamlist.length;k++) {
					read= read + teamlist[cBTeams.getSelectedIndex()][k]+"\n";
				}
				ePTeam.setText(read);
				ePfinalteam.setText(read);
			}
		});
		btnloadteams.setBounds(249, 508, 89, 23);
		panel_player.add(btnloadteams);

	}

	private void initmenu() {
		JLabel lblPokemonRandomDraft = new JLabel("Pokemon Random Draft");
		lblPokemonRandomDraft.setBounds(15, 5, 374, 38);
		panelMainMenu.add(lblPokemonRandomDraft);
		lblPokemonRandomDraft.setFont(new Font("Tahoma", Font.BOLD, 31));

		JButton btnNewButton = new JButton("Start Draft");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMainMenu.setVisible(false);
				panelStartDraft.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(132, 97, 126, 66);
		panelMainMenu.add(btnNewButton);

		JButton btnLoadDraft = new JButton("Load Draft");
		btnLoadDraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMainMenu.setVisible(false);
				panelLoadDraft.setVisible(true);
			}
		});
		btnLoadDraft.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadDraft.setBounds(132, 234, 126, 66);
		panelMainMenu.add(btnLoadDraft);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setBounds(132, 373, 126, 66);
		panelMainMenu.add(btnExit);
	}

	private void initstartdraft() {
		JButton btnTierlist = new JButton("Tierlist");
		btnTierlist.setBounds(63, 23, 255, 71);
		btnTierlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelStartDraft.setVisible(false);
				for (int i = 0; i < data.Data.getPokedex().length; i++) {
					list.add(data.Data.getPokedex(i));
				}
				list.select(0);
				changetier();

				tierlist = client.Writer.read("tierlist");
				try {
					for (int i = 0; i < tierlist.length; i++) {
						tiername[i] = tierlist[i][0];
					}
				} catch (Exception e) {

				}
				panel_tierlist.setVisible(true);

			}
		});
		panelStartDraft.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		btnSpielerTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartDraft.setVisible(false);

				teamlist = client.Writer.read("teamlist");
				try {
					for (int i = 0; i < teamlist.length; i++) {
						teamname[i] = teamlist[i][0];
					}
				} catch (Exception f) {

				}
				panel_player.setVisible(true);

			}
		});
		btnSpielerTeams.setBounds(63, 133, 255, 71);
		panelStartDraft.add(btnSpielerTeams);

		JButton btnAnzahlDerPokemon = new JButton("Anzahl der Pokemon");
		btnAnzahlDerPokemon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartDraft.setVisible(false);
				panel_settings.setVisible(true);
			}
		});
		btnAnzahlDerPokemon.setBounds(63, 249, 255, 71);
		panelStartDraft.add(btnAnzahlDerPokemon);

		JButton btnReihenfolge = new JButton("Reihenfolge");
		btnReihenfolge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartDraft.setVisible(false);
				panel_order.setVisible(true);
			}
		});
		btnReihenfolge.setBounds(63, 362, 255, 71);
		panelStartDraft.add(btnReihenfolge);

		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartDraft.setVisible(false);
				panel_draft.setVisible(true);
			}
		});
		btnFertig.setBounds(63, 476, 255, 71);
		panelStartDraft.add(btnFertig);

	}

	private void initmenubar() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JButton btnMainmenu = new JButton("MainMen\u00FC");
		btnMainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_tierlist.setVisible(false);
				panelLoadDraft.setVisible(false);
				panelStartDraft.setVisible(false);
				panel_player.setVisible(false);
				panel_settings.setVisible(false);
				panel_order.setVisible(false);
				panel_draft.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		menuBar.add(btnMainmenu);

		JButton btnmenuback = new JButton("zur\u00FCck");
		btnmenuback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelStartDraft.isVisible()) {
					panelStartDraft.setVisible(false);
					panelMainMenu.setVisible(true);
				}
				if (panel_tierlist.isVisible() || panel_player.isVisible() || panel_settings.isVisible()
						|| panel_order.isVisible() || panel_draft.isVisible()) {
					panelStartDraft.setVisible(true);
					panel_tierlist.setVisible(false);
					panel_player.setVisible(false);
					panel_settings.setVisible(false);
					panel_order.setVisible(false);
					panel_draft.setVisible(false);
				}
			}
		});
		menuBar.add(btnmenuback);
	}

	private void inittierlist() {
		JLabel lblPokemon = new JLabel("Pokemon");
		lblPokemon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPokemon.setBounds(10, 0, 73, 28);
		panel_tierlist.add(lblPokemon);

		list = new List();
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changetier();
			}
		});
		list.setBounds(20, 33, 197, 511);
		panel_tierlist.add(list);

		tFPoke = new JTextField();
		tFPoke.setEditable(false);
		tFPoke.setBounds(256, 56, 86, 20);
		panel_tierlist.add(tFPoke);
		tFPoke.setColumns(10);

		tierlistbuttongruppe = new ButtonGroup();

		radioButtonS = new JRadioButton("S");
		radioButtonS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.Data.tierlist[list.getSelectedIndex()] = 'S';
				if (list.getSelectedIndex() < data.Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonS.setBounds(256, 101, 109, 23);
		panel_tierlist.add(radioButtonS);
		tierlistbuttongruppe.add(radioButtonS);

		radioButtonA = new JRadioButton("A");
		radioButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.Data.tierlist[list.getSelectedIndex()] = 'A';
				if (list.getSelectedIndex() < data.Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonA.setBounds(256, 127, 109, 23);
		panel_tierlist.add(radioButtonA);
		tierlistbuttongruppe.add(radioButtonA);

		radioButtonB = new JRadioButton("B");
		radioButtonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.Data.tierlist[list.getSelectedIndex()] = 'B';
				if (list.getSelectedIndex() < data.Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonB.setBounds(256, 151, 109, 23);
		panel_tierlist.add(radioButtonB);
		tierlistbuttongruppe.add(radioButtonB);

		radioButtonC = new JRadioButton("C");
		radioButtonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.Data.tierlist[list.getSelectedIndex()] = 'C';
				if (list.getSelectedIndex() < data.Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonC.setBounds(256, 177, 109, 23);
		panel_tierlist.add(radioButtonC);
		tierlistbuttongruppe.add(radioButtonC);

		radioButtonD = new JRadioButton("D");
		radioButtonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.Data.tierlist[list.getSelectedIndex()] = 'D';
				if (list.getSelectedIndex() < data.Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonD.setBounds(256, 203, 109, 23);
		panel_tierlist.add(radioButtonD);
		tierlistbuttongruppe.add(radioButtonD);

		radioButtonX = new JRadioButton("Banned");
		radioButtonX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.Data.tierlist[list.getSelectedIndex()] = 'X';
				if (list.getSelectedIndex() < data.Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonX.setBounds(256, 229, 109, 23);
		panel_tierlist.add(radioButtonX);
		tierlistbuttongruppe.add(radioButtonX);

		radioButtonnull = new JRadioButton("");
		radioButtonnull.setEnabled(false);
		radioButtonnull.setBounds(256, 259, 109, 23);
		panel_tierlist.add(radioButtonnull);
		tierlistbuttongruppe.add(radioButtonnull);

		JButton btnsafetierlist = new JButton("Speichern");
		btnsafetierlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tF_tiername.getText().equals("") || tF_tiername.getText().equals("Gespeichert")) {
					gui.Manage.msgbox("Du hast keinen Namen eingegeben");
					tF_tiername.setText("");
				} else {
					if (tF_tiername.getText().contains(":")) {
						gui.Manage.msgbox("Der Name das keinen Doppelpunkt enthalten");

					} else {
						Boolean b = true;
						for (String k : tiername) {
							if (tF_tiername.getText().equals(k)) {
								gui.Manage.msgbox("Der Name existiert schon");
								b = false;
								break;
							}
						}
						if (b) {
							Writer.print("tierlist", tF_tiername.getText(), data.Data.tierlist);
							tF_tiername.setText("Gespeichert");
							panel_tierlist.remove(cBTierlist);
							tierlist();
						}

					}
				}
			}
		});
		btnsafetierlist.setBounds(256, 376, 89, 23);
		panel_tierlist.add(btnsafetierlist);

		tierlist();

		JButton btnloadtier = new JButton("Laden");
		btnloadtier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.Data.settierlist(tierlist[cBTierlist.getSelectedIndex()][1].toCharArray());
				list.select(0);
				changetier();
				;
			}
		});
		btnloadtier.setBounds(256, 486, 89, 23);
		panel_tierlist.add(btnloadtier);

		tF_tiername = new JTextField();
		tF_tiername.setBounds(256, 345, 86, 20);
		panel_tierlist.add(tF_tiername);
		tF_tiername.setColumns(10);
		radioButtonnull.setVisible(false);
	}

	private void initdraft() {
		JLabel lblTeam = new JLabel("Team 1");
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTeam.setBounds(519, 11, 80, 31);
		panel_draft.add(lblTeam);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(54, 110, 169, 98);
		panel_draft.add(btnNewButton_1);

		JButton button = new JButton("New button");
		button.setBounds(464, 110, 169, 98);
		panel_draft.add(button);

		JButton button_1 = new JButton("New button");
		button_1.setBounds(860, 110, 169, 98);
		panel_draft.add(button_1);

		JButton button_2 = new JButton("New button");
		button_2.setBounds(260, 280, 169, 98);
		panel_draft.add(button_2);

		JButton button_3 = new JButton("New button");
		button_3.setBounds(675, 280, 169, 98);
		panel_draft.add(button_3);

		JButton button_4 = new JButton("New button");
		button_4.setBounds(54, 441, 169, 98);
		panel_draft.add(button_4);

		JButton button_5 = new JButton("New button");
		button_5.setBounds(464, 441, 169, 98);
		panel_draft.add(button_5);

		JButton button_6 = new JButton("New button");
		button_6.setBounds(860, 441, 169, 98);
		panel_draft.add(button_6);

	}

	private void tierlist() {
		try {
			tierlist = client.Writer.read("tierlist");
		} catch (Exception e) {
			tierlist = client.Writer.read("tierlist");
		}
		try {
			tiername = new String[tierlist.length];
			for (int i = 0; i < tiername.length; i++) {
				try {
					tiername[i] = tierlist[i][0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			tiername = new String[0];
		}

		cBTierlist = new JComboBox(tiername);
		cBTierlist.setBounds(241, 447, 124, 28);
		panel_tierlist.add(cBTierlist);

	}

	private void teamlist() {

		try {
			teamlist = client.Writer.read("teamlist");
		} catch (Exception e) {
			teamlist = client.Writer.read("teamlist");
		}
		try {
			teamname = new String[teamlist.length];
			for (int i = 0; i < teamname.length; i++) {
				try {
					teamname[i] = teamlist[i][0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			teamname = new String[0];
		}

		cBTeams = new JComboBox(teamname);
		cBTeams.setBounds(234, 469, 124, 28);
		panel_player.add(cBTeams);
	}

	private void changetier() {
		tFPoke.setText(list.getSelectedItem());
		switch (data.Data.tierlist[list.getSelectedIndex()]) {
		case 'S':
			radioButtonS.setSelected(true);
			break;
		case 'A':
			radioButtonA.setSelected(true);
			break;
		case 'B':
			radioButtonB.setSelected(true);
			break;
		case 'C':
			radioButtonC.setSelected(true);
			break;
		case 'D':
			radioButtonD.setSelected(true);
			break;
		case 'X':
			radioButtonX.setSelected(true);
			break;
		case '0':
			radioButtonnull.setSelected(true);
			break;
		default:
			radioButtonnull.setSelected(true);
			break;
		}
	}
}
