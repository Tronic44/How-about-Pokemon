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
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

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
	private boolean teamload = false;
	private boolean tierload = false;
	private JComboBox cBsettings;
	private JTextField tF2;
	private JTextField tF3;
	private JTextField tF4;
	private JTextField tF5;
	private JTextField tF1;
	private JLabel lblTier1;
	private JLabel lblTier2;
	private JLabel lblTier3;
	private JLabel lblTier4;
	private JLabel lblTier5;

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
		initsettings();

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

	private void initsettings() {

		JTextPane txtpnAchtungnderungenHier = new JTextPane();
		txtpnAchtungnderungenHier.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnAchtungnderungenHier.setText(
				"Achtung: Änderungen hier führen zu einer\r\nAnpassung der aktuell eingegenbenen Tierlist. \r\nWenn du das nicht möchtest, speichere sie vorher!");
		txtpnAchtungnderungenHier.setBounds(32, 11, 323, 51);
		panel_settings.add(txtpnAchtungnderungenHier);

		JLabel lblWieVieleTiers = new JLabel("Wie viele Tiers soll es geben?");
		lblWieVieleTiers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWieVieleTiers.setBounds(156, 104, 180, 17);
		panel_settings.add(lblWieVieleTiers);
		
		
		cBsettings = new JComboBox(new String[] { "1", "2", "3", "4", "5" });
		cBsettings.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				tF1.setEditable(false);
				tF2.setEditable(false);
				tF3.setEditable(false);
				tF4.setEditable(false);
				tF5.setEditable(false);
				switch(cBsettings.getSelectedIndex()) {
				case 0:
					tF1.setEditable(true);
					tF2.setText("");
					tF3.setText("");
					tF4.setText("");
					tF5.setText("");
					break;
				case 1:
					tF1.setEditable(true);
					tF2.setEditable(true);
					tF3.setText("");
					tF4.setText("");
					tF5.setText("");
					break;
				case 2:
					tF1.setEditable(true);
					tF2.setEditable(true);
					tF3.setEditable(true);
					tF4.setText("");
					tF5.setText("");
					break;
				case 3: 
					tF1.setEditable(true);
					tF2.setEditable(true);
					tF3.setEditable(true);
					tF4.setEditable(true);
					tF5.setText("");
					break;
				case 4: 
					tF1.setEditable(true);
					tF2.setEditable(true);
					tF3.setEditable(true);
					tF4.setEditable(true);
					tF5.setEditable(true);
					break;
				default:
					break;
				}
			}

		});
		cBsettings.setBounds(32, 104, 114, 20);
		panel_settings.add(cBsettings);

		JButton btnsafetier = new JButton("Bestätige");
		btnsafetier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch(cBsettings.getSelectedIndex()) {
				case 0:
					radioButtonS.setText(tF1.getText());
					break;
				case 1:
					radioButtonS.setText(tF1.getText());
					radioButtonA.setText(tF2.getText());
					break;
				case 2:
					radioButtonS.setText(tF1.getText());
					radioButtonA.setText(tF2.getText());
					radioButtonB.setText(tF3.getText());
					break;
				case 3: 
					radioButtonS.setText(tF1.getText());
					radioButtonA.setText(tF2.getText());
					radioButtonB.setText(tF3.getText());
					radioButtonC.setText(tF4.getText());
					break;
				case 4: 
					radioButtonS.setText(tF1.getText());
					radioButtonA.setText(tF2.getText());
					radioButtonB.setText(tF3.getText());
					radioButtonC.setText(tF4.getText());
					radioButtonD.setText(tF5.getText());
					break;
				default:
					break;
				}
				Manage.initPoketier();
				

			}
		});
		btnsafetier.setBounds(67, 394, 89, 23);
		panel_settings.add(btnsafetier);
		
		lblTier1 = new JLabel("Tier 1:");
		lblTier1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier1.setBounds(32, 181, 56, 17);
		panel_settings.add(lblTier1);
		
		lblTier2 = new JLabel("Tier 2:");
		lblTier2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier2.setBounds(32, 221, 56, 17);
		panel_settings.add(lblTier2);
		
		lblTier3 = new JLabel("Tier 3:");
		lblTier3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier3.setBounds(32, 262, 56, 17);
		panel_settings.add(lblTier3);
		
		lblTier4 = new JLabel("Tier 4:");
		lblTier4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier4.setBounds(32, 302, 56, 17);
		panel_settings.add(lblTier4);
		
		lblTier5 = new JLabel("Tier 5:");
		lblTier5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier5.setBounds(32, 345, 56, 17);
		panel_settings.add(lblTier5);
		
		tF2 = new JTextField();
		tF2.setColumns(10);
		tF2.setBounds(98, 221, 86, 20);
		panel_settings.add(tF2);
		
		tF3 = new JTextField();
		tF3.setColumns(10);
		tF3.setBounds(98, 262, 86, 20);
		panel_settings.add(tF3);
		
		tF4 = new JTextField();
		tF4.setColumns(10);
		tF4.setBounds(98, 302, 86, 20);
		panel_settings.add(tF4);
		
		tF5 = new JTextField();
		tF5.setColumns(10);
		tF5.setBounds(98, 345, 86, 20);
		panel_settings.add(tF5);
		
		tF1 = new JTextField();
		tF1.setColumns(10);
		tF1.setBounds(98, 181, 86, 20);
		panel_settings.add(tF1);

		tF2.setEditable(false);
		tF3.setEditable(false);
		tF4.setEditable(false);
		tF5.setEditable(false);
		
	}

	private void initplayer() {
		JSpinner spinnerteam = new JSpinner();
		spinnerteam.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int count = (int) spinnerteam.getValue();
				if (count < 0) {
					spinnerteam.setValue(0);
				} else {
					Player = new String[count];
				}

			}
		});
		spinnerteam.setBounds(22, 11, 50, 20);
		panel_player.add(spinnerteam);

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
				if ((int) spinnerteam.getValue() == 0) {
					Manage.msgbox("Du willst doch nicht ohne auch nur ein Team spielen, oder?");
				} else {
					if (count != (int) spinnerteam.getValue()) {
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
//							if (!teamload) {  das wäre für überschreiben
							for (String k : teamname) {
								if (tF_Teams.getText().equals(k)) {
									gui.Manage.msgbox("Der Name existiert schon");
									b = false;
									break;
								}
							}
//							}
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
				String read = "";
				for (int k = 1; k < teamlist[cBTeams.getSelectedIndex()].length; k++) {
					read = read + teamlist[cBTeams.getSelectedIndex()][k] + "\n";
				}
				ePTeam.setText(read);
				ePfinalteam.setText("");
				spinnerteam.setValue(teamlist[cBTeams.getSelectedIndex()].length-1);
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
				tierlist();
				panel_tierlist.setVisible(true);

			}
		});
		panelStartDraft.add(btnTierlist);

		JButton btnSpielerTeams = new JButton("Spieler / Teams");
		btnSpielerTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent as) {
				panelStartDraft.setVisible(false);
				panel_player.remove(cBTeams);
				teamlist();
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
		try {
			panel_tierlist.remove(cBTierlist);
		} catch (Exception e) {

		}
		cBTierlist = new JComboBox(tiername);
		cBTierlist.setBounds(241, 447, 124, 28);
		panel_tierlist.add(cBTierlist);

	}

	private void teamlist() {
		try {
			teamlist = client.Writer.read("teamlist");
		} catch (Exception e) {

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
