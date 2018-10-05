package client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainMenu {

	private static MainMenu window;
	private JFrame frmPokemonDraft;
	private JPanel panelMainMenu;
	private JPanel panelStartDraft;
	private JPanel panelLoadDraft;
	private JPanel panel_tierlist;
	private JPanel panel_draft;
	private JPanel panel_player;
	private JPanel panel_settings;
	private JPanel panel_order;
	private List list;
	private JTextField tFPoke;
	private JRadioButton radioButtonS;
	private JRadioButton radioButtonA;
	private JRadioButton radioButtonB;
	private JRadioButton radioButtonC;
	private JRadioButton radioButtonD;
	private JRadioButton radioButtonE;
	private JRadioButton radioButtonX;
	private JRadioButton radioButtonnull;
	private ButtonGroup tierlistbuttongruppe;
	private String[][] tierlist;
	private String[] tiername;
	private JTextField tF_tiername;
	private JComboBox<String> cBTierlist;
	private String[][] teamlist;
	private String[] teamname;
	private JEditorPane ePTeam;
	protected String[] Player;
	private JEditorPane ePfinalteam;
	private JTextField tF_Teams;
	private JComboBox<String> cBTeams;
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
	private JLabel lblTier6;
	private JTextField tF6;
	private JCheckBox cBS;
	private JCheckBox cBA;
	private JCheckBox cBB;
	private JCheckBox cBC;
	private JCheckBox cBD;
	private JCheckBox cBE;
	private boolean[] change = new boolean[6];
	private JLabel lblTierS;
	private JLabel lblTierA;
	private JLabel lblTierB;
	private JLabel lblTierC;
	private JLabel lblTierD;
	private JLabel lblTierE;
	private JLabel lblbest;
	private JButton btnReihenfolge;
	private JTextField tFsafename;
	private JComboBox<String> cBloaddraft;
	private JSpinner spinnerteam;
	private String[] Spieler = new String[0];
	private JComboBox<String> comboBoxS;
	private JComboBox<String> comboBoxA;
	private JComboBox<String> comboBoxB;
	private JComboBox<String> comboBoxC;
	private JComboBox<String> comboBoxD;
	private JComboBox<String> comboBoxE;
	int[] countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };
	String[] tiernamen = new String[6];
	private JComboBox<String> cBD01;
	private JComboBox<String> cBD02;
	private JComboBox<String> cBD03;
	private JComboBox<String> cBD04;
	private JComboBox<String> cBD05;
	private JComboBox<String> cBD06;
	private JComboBox<String> cBD07;
	private JComboBox<String> cBD08;
	private JComboBox<String> cBD09;
	private JComboBox<String> cBD10;
	private JComboBox<String> cBD11;
	private JComboBox<String> cBD12;
	private JComboBox<String> cBD13;
	private JComboBox<String> cBD14;
	private JComboBox<String> cBD15;
	private JComboBox<String>[] cbDraft = new JComboBox[] { cBD01, cBD02, cBD03, cBD04, cBD05, cBD06, cBD07, cBD08,
			cBD09, cBD10, cBD11, cBD12, cBD13, cBD14, cBD15 };
	private JTextField tFsearch;
	private JComboBox<String> cBchangeteam;
	private int changeteam = 0;
	private int[][] draftauswahl;
	private boolean finishdraft = false;
	private Boolean order;

	protected static void startMainMenu() {
		Manage.initPoketier();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainMenu();
					window.frmPokemonDraft.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected MainMenu() {
		openMainMenu();
	}

	private void openMainMenu() {
		frmPokemonDraft = new JFrame();
		frmPokemonDraft.setTitle("Pokemon Draft        Alpha by Tronic44");
		frmPokemonDraft.setResizable(false);
		frmPokemonDraft.setBounds(100, 100, 409, 640);
//		frmPokemonDraft.setBounds(100, 100, 1100, 800);
		frmPokemonDraft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPokemonDraft.getContentPane().setLayout(new CardLayout(0, 0));

		initpanel();
		initmenu();
		initstartdraft();
		initmenubar();
		inittierlist();
		initdraft();
		initplayer();
		initsettings();
		initload();
		initorder();

	}

	private void initpanel() {
		panelMainMenu = new JPanel();
		frmPokemonDraft.getContentPane().add(panelMainMenu, "name_526992955635103");
		panelMainMenu.setLayout(null);
		panelMainMenu.setVisible(true);

		panelStartDraft = new JPanel();
		frmPokemonDraft.getContentPane().add(panelStartDraft, "name_527021172921335");
		panelStartDraft.setLayout(null);
		panelStartDraft.setVisible(false);

		panelLoadDraft = new JPanel();
		frmPokemonDraft.getContentPane().add(panelLoadDraft, "name_527666975961040");
		panelLoadDraft.setLayout(null);

		panel_tierlist = new JPanel();
		frmPokemonDraft.getContentPane().add(panel_tierlist, "name_2032838076395");
		panel_tierlist.setLayout(null);
		panel_tierlist.setVisible(false);

		panel_player = new JPanel();
		frmPokemonDraft.getContentPane().add(panel_player, "name_601620298180688");
		panel_player.setVisible(false);
		panel_player.setLayout(null);

		panel_settings = new JPanel();
		frmPokemonDraft.getContentPane().add(panel_settings, "name_601636768594680");
		panel_settings.setVisible(false);
		panel_settings.setLayout(null);

		panel_order = new JPanel();
		frmPokemonDraft.getContentPane().add(panel_order, "name_601652242106220");
		panel_order.setVisible(false);
		panel_order.setLayout(null);

		panel_draft = new JPanel();
		frmPokemonDraft.getContentPane().add(panel_draft, "name_2679324427935");
		panel_draft.setVisible(false);
		panel_draft.setLayout(null);

	}

	private void initorder() {
		JLabel lblorder = new JLabel("Wie soll gedraftet werden?");
		lblorder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblorder.setBounds(10, 26, 227, 27);
		panel_order.add(lblorder);

		JCheckBox chckbxRandom = new JCheckBox("Random");
		JCheckBox chckbxManuell = new JCheckBox("Manuell");
		chckbxManuell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxManuell.isSelected()) {
					order = true;
				} else {
					order = null;
				}
				chckbxRandom.setSelected(false);
			}
		});
		chckbxManuell.setBounds(73, 92, 97, 23);
		panel_order.add(chckbxManuell);

		JTextPane txtpnJederzeitZwischenTeanms = new JTextPane();
		txtpnJederzeitZwischenTeanms.setText("Jederzeit zwischen Teanms wechseln, beliebig viele Pokemon auswählen");
		txtpnJederzeitZwischenTeanms.setBounds(73, 122, 254, 39);
		panel_order.add(txtpnJederzeitZwischenTeanms);

		chckbxRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxRandom.isSelected()) {
					order = false;
				} else {
					order = null;
				}
				chckbxManuell.setSelected(false);
			}
		});
		chckbxRandom.setBounds(73, 174, 97, 23);
		panel_order.add(chckbxRandom);

		JTextPane txtpnZuflligeReihenfolgeImmer = new JTextPane();
		txtpnZuflligeReihenfolgeImmer.setText("Zufällige Reihenfolge, immer ein Pokemon\r\n");
		txtpnZuflligeReihenfolgeImmer.setBounds(73, 204, 254, 39);
		panel_order.add(txtpnZuflligeReihenfolgeImmer);

	}

	private void initload() {
		JLabel lblstatus = new JLabel("");
		lblstatus.setBounds(154, 158, 152, 14);
		panelLoadDraft.add(lblstatus);
		panelLoadDraft.setVisible(false);

		JButton btnloaddraft = new JButton("Laden");
		btnloaddraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnloaddraft.setBounds(217, 439, 89, 23);
		panelLoadDraft.add(btnloaddraft);

		tFsafename = new JTextField();
		tFsafename.setBounds(144, 93, 86, 20);
		panelLoadDraft.add(tFsafename);
		tFsafename.setColumns(10);

		JButton btnsafedraft = new JButton("Speichern");
		btnsafedraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tFsafename.getText().trim().length() < 1) {
					Manage.msgbox("Du solltest schon ein Namen zum Speichern eingeben", frmPokemonDraft);
				} else {
					int status = Writer.safeasjson(tFsafename.getText(), frmPokemonDraft);
					if (status == 0) {
						lblstatus.setText("Error");
					} else {
						lblstatus.setText("Erfolgreich gespeichert");
					}

				}
			}
		});
		btnsafedraft.setBounds(144, 124, 89, 23);
		panelLoadDraft.add(btnsafedraft);

		cBloaddraft = new JComboBox();
		cBloaddraft.setBounds(72, 440, 125, 20);
		panelLoadDraft.add(cBloaddraft);

	}

	private void initsettings() {
		lblTier1 = new JLabel("S:");
		lblTier1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier1.setBounds(32, 83, 56, 17);
		panel_settings.add(lblTier1);

		lblTier2 = new JLabel("A:");
		lblTier2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier2.setBounds(32, 123, 56, 17);
		panel_settings.add(lblTier2);

		lblTier3 = new JLabel("B:");
		lblTier3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier3.setBounds(32, 164, 56, 17);
		panel_settings.add(lblTier3);

		lblTier4 = new JLabel("C:");
		lblTier4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier4.setBounds(32, 204, 56, 17);
		panel_settings.add(lblTier4);

		lblTier5 = new JLabel("D:");
		lblTier5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier5.setBounds(32, 247, 56, 17);
		panel_settings.add(lblTier5);

		lblbest = new JLabel("");
		lblbest.setBounds(168, 346, 206, 14);
		panel_settings.add(lblbest);

		lblTier6 = new JLabel("E:");
		lblTier6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTier6.setBounds(32, 290, 56, 17);
		panel_settings.add(lblTier6);

		JTextPane txtpnAchtungnderungenHier = new JTextPane();
		txtpnAchtungnderungenHier.setEditable(false);
		txtpnAchtungnderungenHier.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnAchtungnderungenHier.setText(
				"Achtung: Änderungen hier führen zu einer\r\nAnpassung der aktuell eingegenbenen Tierlist. \r\nWenn du das nicht möchtest, speichere sie vorher!");
		txtpnAchtungnderungenHier.setBounds(32, 11, 323, 51);
		panel_settings.add(txtpnAchtungnderungenHier);

		JButton btnsafetier = new JButton("Bestätige");
		btnsafetier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!cBS.isSelected() && !cBA.isSelected() && !cBB.isSelected() && !cBC.isSelected()
						&& !cBD.isSelected() && !cBE.isSelected()) {
					Manage.msgbox("Kein Tier? So geht das aber nicht!", frmPokemonDraft);
					return;
				}
				if (cBS.isSelected() && tF1.getText().trim().length() < 1) {
					Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?", frmPokemonDraft);
					return;
				}
				if (cBA.isSelected() && tF2.getText().trim().length() < 1) {
					Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?", frmPokemonDraft);
					return;
				}
				if (cBB.isSelected() && tF3.getText().trim().length() < 1) {
					Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?", frmPokemonDraft);
					return;
				}
				if (cBC.isSelected() && tF4.getText().trim().length() < 1) {
					Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?", frmPokemonDraft);
					return;
				}
				if (cBD.isSelected() && tF5.getText().trim().length() < 1) {
					Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?", frmPokemonDraft);
					return;
				}
				if (cBE.isSelected() && tF6.getText().trim().length() < 1) {
					Manage.msgbox("Der Tiername ist etwas zu kruz, findest du nicht?", frmPokemonDraft);
					return;
				}
				if (cBS.isSelected() && comboBoxS.getSelectedIndex() < 0) {
					Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
							+ "Wo gibt es denn sowas?", frmPokemonDraft);
					return;
				}
				if (cBA.isSelected() && comboBoxA.getSelectedIndex() < 0) {
					Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
							+ "Wo gibt es denn sowas?", frmPokemonDraft);
					return;
				}
				if (cBB.isSelected() && comboBoxB.getSelectedIndex() < 0) {
					Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
							+ "Wo gibt es denn sowas?", frmPokemonDraft);
					return;
				}
				if (cBC.isSelected() && comboBoxC.getSelectedIndex() < 0) {
					Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
							+ "Wo gibt es denn sowas?", frmPokemonDraft);
					return;
				}
				if (cBD.isSelected() && comboBoxD.getSelectedIndex() < 0) {
					Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
							+ "Wo gibt es denn sowas?", frmPokemonDraft);
					return;
				}
				if (cBE.isSelected() && comboBoxE.getSelectedIndex() < 0) {
					Manage.msgbox("Du Möchtest mit einem Tier spielen, wovon du keine Pokemon draften darfst?" + "\n"
							+ "Wo gibt es denn sowas?", frmPokemonDraft);
					return;
				}
				String[] tiernamenlist = new String[] { tF1.getText().trim(), tF2.getText().trim(),
						tF3.getText().trim(), tF4.getText().trim(), tF5.getText().trim(), tF6.getText().trim() };

				for (int k = 0; k < tiernamenlist.length; k++) {
					for (int j = 0; j < tiernamenlist.length; j++) {
						if (tiernamenlist[k].equals("") || tiernamenlist[j].equals("") || j == k) {
							continue;
						}
						if (tiernamenlist[k].equals(tiernamenlist[j])) {
							Manage.msgbox("Den selben Namen für zwei Tier's? Das ist doch nur verwirrend!",
									frmPokemonDraft);
							return;
						}
					}
				}

				if (Data.tierlistclone == null) {
					Data.tierlistclone = Data.tierlist.clone();
				}
				int count = 0;
				if (cBS.isSelected()) {
					radioButtonS.setEnabled(true);
					radioButtonS.setText(tF1.getText());
					tiernamen[0] = tF1.getText();
				} else {
					radioButtonS.setEnabled(false);
					radioButtonS.setText("");
					tiernamen[0] = null;
					count++;
				}
				if (cBA.isSelected()) {
					radioButtonA.setEnabled(true);
					radioButtonA.setText(tF2.getText());
					tiernamen[1] = tF2.getText();
				} else {
					radioButtonA.setEnabled(false);
					radioButtonA.setText("");
					tiernamen[1] = null;
					count++;
				}
				if (cBB.isSelected()) {
					radioButtonB.setEnabled(true);
					radioButtonB.setText(tF3.getText());
					tiernamen[2] = tF3.getText();
				} else {
					radioButtonB.setEnabled(false);
					radioButtonB.setText("");
					tiernamen[2] = null;
					count++;
				}
				if (cBC.isSelected()) {
					radioButtonC.setEnabled(true);
					radioButtonC.setText(tF4.getText());
					tiernamen[3] = tF4.getText();
				} else {
					radioButtonC.setEnabled(false);
					radioButtonC.setText("");
					tiernamen[3] = null;
					count++;
				}
				if (cBD.isSelected()) {
					radioButtonD.setEnabled(true);
					radioButtonD.setText(tF5.getText());
					tiernamen[4] = tF5.getText();
				} else {
					radioButtonD.setEnabled(false);
					radioButtonD.setText("");
					tiernamen[4] = null;
					count++;
				}
				if (cBE.isSelected()) {
					radioButtonE.setEnabled(true);
					radioButtonE.setText(tF6.getText());
					tiernamen[5] = tF6.getText();
				} else {
					radioButtonE.setEnabled(false);
					radioButtonE.setText("");
					tiernamen[5] = null;
					count++;
				}
				for (int i = 0; i < count; i++) {
					for (int k = 0; k < Data.tierlist.length; k++) {
						switch (Data.tierlist[k]) {
						case 'S':
							if (lblTierS.getText().equals("wird gebannt")) {
								Data.tierlist[k] = 'X';
							}
							break;
						case 'A':
							if (lblTierA.getText().equals("wird gebannt")) {
								Data.tierlist[k] = 'X';
							}
							if (lblTierA.getText().equals("wird hochgestuft")) {
								Data.tierlist[k] = 'S';
							}
							break;
						case 'B':
							if (lblTierB.getText().equals("wird gebannt")) {
								Data.tierlist[k] = 'X';
							}
							if (lblTierB.getText().equals("wird hochgestuft")) {
								Data.tierlist[k] = 'A';
							}
							break;
						case 'C':
							if (lblTierC.getText().equals("wird gebannt")) {
								Data.tierlist[k] = 'X';
							}
							if (lblTierC.getText().equals("wird hochgestuft")) {
								Data.tierlist[k] = 'B';
							}
							break;
						case 'D':
							if (lblTierD.getText().equals("wird gebannt")) {
								Data.tierlist[k] = 'X';
							}
							if (lblTierD.getText().equals("wird hochgestuft")) {
								Data.tierlist[k] = 'C';
							}
							break;
						case 'E':
							if (lblTierE.getText().equals("wird hochgestuft")) {
								Data.tierlist[k] = 'D';
							}
							break;
						default:
							break;

						}
					}
				}
				lblbest.setText("Änderungen wurden übernommen!");
			}
		});
		btnsafetier.setBounds(69, 337, 89, 23);
		panel_settings.add(btnsafetier);

		tF2 = new JTextField();
		tF2.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblbest.setText("");
			}
		});
		tF2.setColumns(10);
		tF2.setBounds(98, 123, 86, 20);
		panel_settings.add(tF2);

		tF3 = new JTextField();
		tF3.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblbest.setText("");
			}
		});
		tF3.setColumns(10);
		tF3.setBounds(98, 164, 86, 20);
		panel_settings.add(tF3);

		tF4 = new JTextField();
		tF4.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblbest.setText("");
			}
		});
		tF4.setColumns(10);
		tF4.setBounds(98, 204, 86, 20);
		panel_settings.add(tF4);

		tF5 = new JTextField();
		tF5.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblbest.setText("");
			}
		});
		tF5.setColumns(10);
		tF5.setBounds(98, 247, 86, 20);
		panel_settings.add(tF5);

		tF1 = new JTextField();
		tF1.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblbest.setText("");
			}
		});
		tF1.setColumns(10);
		tF1.setBounds(98, 83, 86, 20);
		panel_settings.add(tF1);

		tF6 = new JTextField();
		tF6.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblbest.setText("");
			}
		});
		tF6.setColumns(10);
		tF6.setBounds(98, 290, 86, 20);
		panel_settings.add(tF6);

		tF1.setEditable(false);
		tF2.setEditable(false);
		tF3.setEditable(false);
		tF4.setEditable(false);
		tF5.setEditable(false);
		tF6.setEditable(false);

		lblTierS = new JLabel("");
		lblTierS.setBounds(276, 86, 117, 14);
		panel_settings.add(lblTierS);

		lblTierA = new JLabel("");
		lblTierA.setBounds(276, 126, 117, 14);
		panel_settings.add(lblTierA);

		lblTierB = new JLabel("");
		lblTierB.setBounds(276, 167, 117, 14);
		panel_settings.add(lblTierB);

		lblTierC = new JLabel("");
		lblTierC.setBounds(276, 207, 117, 14);
		panel_settings.add(lblTierC);

		lblTierD = new JLabel("");
		lblTierD.setBounds(276, 250, 117, 14);
		panel_settings.add(lblTierD);

		lblTierE = new JLabel("");
		lblTierE.setBounds(276, 293, 117, 14);
		panel_settings.add(lblTierE);

		String[] countPoke = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15" };

		comboBoxS = new JComboBox(countPoke);
		comboBoxS.setEnabled(false);
		comboBoxS.setSelectedIndex(-1);
		comboBoxS.setBounds(194, 83, 61, 20);
		panel_settings.add(comboBoxS);

		comboBoxA = new JComboBox(countPoke);
		comboBoxA.setEnabled(false);
		comboBoxA.setSelectedIndex(-1);
		comboBoxA.setBounds(194, 123, 61, 20);
		panel_settings.add(comboBoxA);

		comboBoxB = new JComboBox(countPoke);
		comboBoxB.setEnabled(false);
		comboBoxB.setSelectedIndex(-1);
		comboBoxB.setBounds(194, 164, 61, 20);
		panel_settings.add(comboBoxB);

		comboBoxC = new JComboBox(countPoke);
		comboBoxC.setEnabled(false);
		comboBoxC.setSelectedIndex(-1);
		comboBoxC.setBounds(194, 204, 61, 20);
		panel_settings.add(comboBoxC);

		comboBoxD = new JComboBox(countPoke);
		comboBoxD.setEnabled(false);
		comboBoxD.setSelectedIndex(-1);
		comboBoxD.setBounds(194, 247, 61, 20);
		panel_settings.add(comboBoxD);

		comboBoxE = new JComboBox(countPoke);
		comboBoxE.setEnabled(false);
		comboBoxE.setSelectedIndex(-1);
		comboBoxE.setBounds(194, 290, 61, 20);
		panel_settings.add(comboBoxE);

		comboBoxS.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxS.getSelectedIndex() + 1;
				changepokeanzahl(0, auswahl);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});

		comboBoxA.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxA.getSelectedIndex() + 1;
				changepokeanzahl(1, auswahl);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});

		comboBoxB.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxB.getSelectedIndex() + 1;
				changepokeanzahl(2, auswahl);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboBoxC.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxC.getSelectedIndex() + 1;
				changepokeanzahl(3, auswahl);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboBoxD.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxD.getSelectedIndex() + 1;
				changepokeanzahl(4, auswahl);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboBoxE.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				int auswahl;
				auswahl = comboBoxE.getSelectedIndex() + 1;
				changepokeanzahl(5, auswahl);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});

		cBS = new JCheckBox("");
		cBS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBS.isSelected()) {
					tF1.setEditable(true);
					tF1.setText("S");
					comboBoxS.setEnabled(true);
				} else {
					tF1.setEditable(false);
					tF1.setText("");
					comboBoxS.setEnabled(false);
					comboBoxS.setSelectedIndex(-1);
					changepokeanzahl(0, 0);
				}
				changesetting();
			}
		});
		cBS.setBounds(67, 83, 21, 23);
		panel_settings.add(cBS);

		cBA = new JCheckBox("");
		cBA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBA.isSelected()) {
					tF2.setEditable(true);
					tF2.setText("A");
					comboBoxA.setEnabled(true);
				} else {
					tF2.setEditable(false);
					tF2.setText("");
					comboBoxA.setEnabled(false);
					comboBoxA.setSelectedIndex(-1);
					changepokeanzahl(1, 0);
				}
				changesetting();
			}
		});
		cBA.setBounds(67, 122, 21, 23);
		panel_settings.add(cBA);

		cBB = new JCheckBox("");
		cBB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBB.isSelected()) {
					tF3.setEditable(true);
					tF3.setText("B");
					comboBoxB.setEnabled(true);
				} else {
					tF3.setEditable(false);
					tF3.setText("");
					comboBoxB.setEnabled(false);
					comboBoxB.setSelectedIndex(-1);
					changepokeanzahl(2, 0);
				}
				changesetting();
			}
		});
		cBB.setBounds(67, 163, 21, 23);
		panel_settings.add(cBB);

		cBC = new JCheckBox("");
		cBC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBC.isSelected()) {
					tF4.setEditable(true);
					tF4.setText("C");
					comboBoxC.setEnabled(true);
				} else {
					tF4.setEditable(false);
					tF4.setText("");
					comboBoxC.setEnabled(false);
					comboBoxC.setSelectedIndex(-1);
					changepokeanzahl(3, 0);
				}
				changesetting();
			}
		});
		cBC.setBounds(67, 203, 21, 23);
		panel_settings.add(cBC);

		cBD = new JCheckBox("");
		cBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBD.isSelected()) {
					tF5.setEditable(true);
					tF5.setText("D");
					comboBoxD.setEnabled(true);
				} else {
					tF5.setEditable(false);
					tF5.setText("");
					comboBoxD.setEnabled(false);
					comboBoxD.setSelectedIndex(-1);
					changepokeanzahl(4, 0);
				}
				changesetting();
			}
		});
		cBD.setBounds(67, 246, 21, 23);
		panel_settings.add(cBD);

		cBE = new JCheckBox("");
		cBE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBE.isSelected()) {
					tF6.setEditable(true);
					tF6.setText("E");
					comboBoxE.setEnabled(true);
				} else {
					tF6.setEditable(false);
					tF6.setText("");
					comboBoxE.setEnabled(false);
					comboBoxE.setSelectedIndex(-1);
					changepokeanzahl(5, 0);
				}
				changesetting();
			}
		});
		cBE.setBounds(67, 289, 21, 23);
		panel_settings.add(cBE);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 383, 393, 2);
		panel_settings.add(separator);

		JTextPane txtpnResetetDieOben = new JTextPane();
		txtpnResetetDieOben.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnResetetDieOben.setText(
				"Resetet die oben gemachten Einstellungen auf den Urspungswert.\r\nKann gemachte Änderungen an der Tierlist nicht rückgängig machen!");
		txtpnResetetDieOben.setEditable(false);
		txtpnResetetDieOben.setBounds(24, 396, 148, 144);
		panel_settings.add(txtpnResetetDieOben);

		JTextPane txtpnStelltDieEinstellungen = new JTextPane();
		txtpnStelltDieEinstellungen.setText(
				"Stellt die Einstellungen oben auf Standart zurück und setzt die Tierlist auf dem Wert, bevor du das erste mal hier was geändert hast!");
		txtpnStelltDieEinstellungen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnStelltDieEinstellungen.setEditable(false);
		txtpnStelltDieEinstellungen.setBounds(207, 396, 148, 144);
		panel_settings.add(txtpnStelltDieEinstellungen);

		JButton btnresettier = new JButton("RESET");
		btnresettier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetsettings();
				Manage.msgboxerf("Erfolgreich Resetet!", frmPokemonDraft);
			}
		});
		btnresettier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnresettier.setBounds(50, 542, 89, 23);
		panel_settings.add(btnresettier);

		JButton btnrestoretier = new JButton("RESTORE");
		btnrestoretier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Data.tierlist = Data.tierlistclone.clone();
					Data.tierlistclone = null;
					resetsettings();
					Manage.msgboxerf("Erfolgreich wiederhergestellt!", frmPokemonDraft);
				} catch (Exception f) {
					Manage.msgbox("Nichts zum wiederherstellen gefunden!", frmPokemonDraft);
				}
			}
		});
		btnrestoretier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnrestoretier.setBounds(236, 542, 89, 23);
		panel_settings.add(btnrestoretier);

	}

	private void initplayer() {
		spinnerteam = new JSpinner();
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
					Manage.msgbox("Du willst doch nicht ohne auch nur ein Team spielen, oder?", frmPokemonDraft);
				} else {
					if (count != (int) spinnerteam.getValue()) {
						Manage.msgbox("Deine Liste Stimmt nicht mit der Team Anzahl überein" + "\n"
								+ "Denk dran: Teams haben niemals nur einen Buchstaben!", frmPokemonDraft);
					} else {
						Spieler = new String[count];
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
						btnReihenfolge.setEnabled(true);
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
		lblNewLabel.setBounds(239, 37, 133, 14);
		panel_player.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Team-Namen");
		lblNewLabel_1.setBounds(53, 37, 106, 14);
		panel_player.add(lblNewLabel_1);

		tF_Teams = new JTextField();
		tF_Teams.setColumns(10);
		tF_Teams.setBounds(249, 367, 86, 20);
		panel_player.add(tF_Teams);

		JButton btnsafeteams = new JButton("Speichern");
		btnsafeteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ePfinalteam.getText().toString().length() < 1) {
					Manage.msgbox("Du hast keine Teams bestätigt", frmPokemonDraft);
					return;
				}
				if (tF_Teams.getText().equals("") || tF_Teams.getText().equals("Gespeichert")) {
					Manage.msgbox("Du hast keinen Namen eingegeben", frmPokemonDraft);
					tF_Teams.setText("");
				} else {
					if (tF_Teams.getText().contains(":")) {
						Manage.msgbox("Der Name das keinen Doppelpunkt enthalten", frmPokemonDraft);
					} else {
						if (ePfinalteam.getText().length() > 2) {
							Boolean b = true;
							for (String k : teamname) {
								if (tF_Teams.getText().equals(k)) {
									Manage.msgbox("Der Name existiert schon", frmPokemonDraft);
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
		btnsafeteams.setBounds(239, 398, 109, 23);
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
				spinnerteam.setValue(teamlist[cBTeams.getSelectedIndex()].length - 1);
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
				opentierlist();

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

		btnReihenfolge = new JButton("Reihenfolge");
		btnReihenfolge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (teamname == null || ePfinalteam.getText().toString().length() < 1) {
					Manage.msgbox("Du kannst nicht ohne Teams keine Reihenfolge bilden", frmPokemonDraft);
				} else {
					panelStartDraft.setVisible(false);
					panel_order.setVisible(true);
				}

			}
		});
		btnReihenfolge.setBounds(63, 362, 255, 71);
		panelStartDraft.add(btnReihenfolge);
		btnReihenfolge.setEnabled(true);

		JButton btnFertig = new JButton("Fertig");
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!finishdraft) {
					int count = 0;
					for (char k : Data.tierlist) {
						if (k == '0') {
							count++;
						}
					}
					if (count > 880) {
						Manage.msgbox("Du hast zu wenige Pokemon ein Tier zugewiesen, um einen Draft zu starten",
								frmPokemonDraft);
						opentierlist();
						panelStartDraft.setVisible(false);
						return;
					}
					if (teamname == null || ePfinalteam.getText().toString().length() < 1) {
						Manage.msgbox("Du hast keine Teams eingetragen", frmPokemonDraft);
						panelStartDraft.setVisible(false);
						panel_player.remove(cBTeams);
						teamlist();
						panel_player.setVisible(true);
						return;
					}
					int pokeanzahl = 0;
					for (int k : countauswahl) {
						pokeanzahl += k;
					}
					if (pokeanzahl == 0) {
						Manage.msgbox("Du hast noch nicht die Anzhal der Pokemon ausgewählt", frmPokemonDraft);
						panelStartDraft.setVisible(false);
						panel_settings.setVisible(true);
						return;
					}
					if (order == null) {
						Manage.msgbox("Du hast noch keine Reihenfolge ausgewählt", frmPokemonDraft);
						panelStartDraft.setVisible(false);
						panel_order.setVisible(true);
						return;
					}
					if (count > 0) {
						Object[] options = { "BANNEN", "In das untersete Tier einfügen", "Selbst einordnen" };
						switch (JOptionPane.showOptionDialog(frmPokemonDraft,
								"Du hast noch nicht allen Pokenmon einen Tier zugewiesen, was möchtest du tun? " + "\n"
										+ "Alle nicht zugewisenen:",
								"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, options[2])) {
						case 0:
							panelStartDraft.setVisible(false);
							for (int k = 0; k < Data.tierlist.length; k++) {
								if (Data.tierlist[k] == '0') {
									Data.tierlist[k] = 'X';
								}
							}
							opendraft();
							break;
						case 1:
							if (lblbest.getText().equals("Änderungen wurden übernommen!")) {
								for (int k = 0; k < Data.tierlist.length; k++) {
									if (Data.tierlist[k] == '0') {
										if (cBE.isSelected()) {
											Data.tierlist[k] = 'E';
										} else {
											if (cBD.isSelected()) {
												Data.tierlist[k] = 'D';
											} else {
												if (cBC.isSelected()) {
													Data.tierlist[k] = 'C';
												} else {
													if (cBB.isSelected()) {
														Data.tierlist[k] = 'B';
													} else {
														if (cBA.isSelected()) {
															Data.tierlist[k] = 'A';
														} else {
															if (cBS.isSelected()) {
																Data.tierlist[k] = 'S';
															} else {
																Manage.msgbox(
																		"Es wurde noch keine Tier Einstellung getroffen",
																		frmPokemonDraft);
																return;
															}
														}
													}
												}
											}
										}
									}
								}
								opendraft();
							} else {
								Manage.msgbox("Du hast ungespeicherte Änderungen in deiner Tiereinstellungen",
										frmPokemonDraft);
								panelStartDraft.setVisible(false);
								panel_settings.setVisible(true);
							}
							break;
						case 2:
							panelStartDraft.setVisible(false);
							opentierlist();
							break;
						}
					} else {
						opendraft();
					}
				} else {
					if (Spieler.length != draftauswahl.length) {
						resetdraft();
						panel_draft.remove(cBchangeteam);
						opendraft();
						return;
					}
					panelStartDraft.setVisible(false);
					int hight = 100;
					for (int k : countauswahl) {
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
					frmPokemonDraft.setBounds(100, 100, 1100, hight);
					panel_draft.setVisible(true);
				}
			}
		});
		btnFertig.setBounds(63, 476, 255, 71);
		panelStartDraft.add(btnFertig);
		btnFertig.setEnabled(true);
	}

	private void initmenubar() {
		JMenuBar menuBar = new JMenuBar();
		frmPokemonDraft.setJMenuBar(menuBar);

		JButton btnMainmenu = new JButton("MainMen\u00FC");
		btnMainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_draft.isVisible()) {
					Manage.msgbox("ACHTUNG: Änderungen nach dem Draft beginn, kann zum neustart des Draftens führen!",
							frmPokemonDraft);
				}
				frmPokemonDraft.setBounds(100, 100, 409, 640);
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
				if (panel_draft.isVisible()) {
					Manage.msgbox("ACHTUNG: Änderungen nach dem Draft beginn, kann zum neustart des Draftens führen!",
							frmPokemonDraft);
					panelStartDraft.setVisible(true);
					panel_draft.setVisible(false);
				}
				if (panel_tierlist.isVisible() || panel_player.isVisible() || panel_settings.isVisible()
						|| panel_order.isVisible()) {
					panelStartDraft.setVisible(true);
					panel_tierlist.setVisible(false);
					panel_player.setVisible(false);
					panel_settings.setVisible(false);
					panel_order.setVisible(false);
				}
				if (panelLoadDraft.isVisible()) {
					panelLoadDraft.setVisible(false);
					panelMainMenu.setVisible(true);
				}
				frmPokemonDraft.setBounds(100, 100, 409, 640);
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
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				changetier();
			}
		});
		list.setBounds(20, 33, 197, 516);
		panel_tierlist.add(list);

		tFPoke = new JTextField();
		tFPoke.setEditable(false);
		tFPoke.setBounds(241, 56, 124, 20);
		panel_tierlist.add(tFPoke);
		tFPoke.setColumns(10);

		tierlistbuttongruppe = new ButtonGroup();

		radioButtonS = new JRadioButton("S");
		radioButtonS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.tierlist[list.getSelectedIndex()] = 'S';
				if (list.getSelectedIndex() < Data.getPokedex().length)
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
				Data.tierlist[list.getSelectedIndex()] = 'A';
				if (list.getSelectedIndex() < Data.getPokedex().length)
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
				Data.tierlist[list.getSelectedIndex()] = 'B';
				if (list.getSelectedIndex() < Data.getPokedex().length)
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
				Data.tierlist[list.getSelectedIndex()] = 'C';
				if (list.getSelectedIndex() < Data.getPokedex().length)
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
				Data.tierlist[list.getSelectedIndex()] = 'D';
				if (list.getSelectedIndex() < Data.getPokedex().length)
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
				Data.tierlist[list.getSelectedIndex()] = 'X';
				if (list.getSelectedIndex() < Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});

		radioButtonX.setBounds(256, 255, 109, 23);
		panel_tierlist.add(radioButtonX);
		tierlistbuttongruppe.add(radioButtonX);

		radioButtonnull = new JRadioButton("");
		radioButtonnull.setEnabled(false);
		radioButtonnull.setBounds(256, 286, 109, 23);
		panel_tierlist.add(radioButtonnull);
		tierlistbuttongruppe.add(radioButtonnull);

		JButton btnsafetierlist = new JButton("Speichern");
		btnsafetierlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tF_tiername.getText().equals("") || tF_tiername.getText().equals("Gespeichert")) {
					Manage.msgbox("Du hast keinen Namen eingegeben", frmPokemonDraft);
					tF_tiername.setText("");
				} else {
					if (tF_tiername.getText().contains(":")) {
						Manage.msgbox("Der Name das keinen Doppelpunkt enthalten", frmPokemonDraft);

					} else {
						Boolean b = true;
						for (String k : tiername) {
							if (tF_tiername.getText().equals(k)) {
								Manage.msgbox("Der Name existiert schon", frmPokemonDraft);
								b = false;
								break;
							}
						}
						if (b) {
							Writer.print("tierlist", tF_tiername.getText(), Data.tierlist);
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
				Data.settierlist(tierlist[cBTierlist.getSelectedIndex()][1].toCharArray());
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
//		tF_tiername.setColumns(10);

		radioButtonE = new JRadioButton("E");
		radioButtonE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.tierlist[list.getSelectedIndex()] = 'E';
				if (list.getSelectedIndex() < Data.getPokedex().length)
					list.select(list.getSelectedIndex() + 1);
				changetier();
			}
		});
		radioButtonE.setBounds(256, 229, 109, 23);
		panel_tierlist.add(radioButtonE);
		radioButtonnull.setVisible(false);
		tierlistbuttongruppe.add(radioButtonE);

		tFsearch = new JTextField();
		tFsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (tFsearch.getText() != null || !tFsearch.getText().trim().equals("")) {
					int a = Data.searchPokedex(tFsearch.getText().toLowerCase().trim());
					if (a >= 0) {
						list.select(a);
						changetier();
					}
				}
			}
		});
		tFsearch.setBounds(86, 555, 109, 20);
		panel_tierlist.add(tFsearch);
		tFsearch.setColumns(10);

		JLabel lblSuche = new JLabel("Suche:");
		lblSuche.setBounds(30, 558, 46, 14);
		panel_tierlist.add(lblSuche);
	}

	private void initdraft() {

		JLabel lbteams = new JLabel("Teams:");
		lbteams.setBounds(123, 14, 45, 14);
		panel_draft.add(lbteams);

//		comboBox = new JComboBox();
//		comboBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent arg0) {
//			}
//		});
//		comboBox.setBounds(195, 11, 28, 20);
//		panel_draft.add(comboBox);

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
		switch (Data.tierlist[list.getSelectedIndex()]) {
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
		case 'E':
			radioButtonE.setSelected(true);
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

	private void changesetting() {
		lblbest.setText("");
		if (!cBS.isSelected() && !cBA.isSelected() && !cBB.isSelected() && !cBC.isSelected() && !cBD.isSelected()
				&& !cBE.isSelected()) {
			lblTierS.setText(" ");
			lblTierA.setText(" ");
			lblTierB.setText(" ");
			lblTierC.setText(" ");
			lblTierD.setText(" ");
			lblTierE.setText(" ");
			return;
		}

		if (cBS.isSelected()) {
			change[0] = true;
		}
		if (cBA.isSelected()) {
			change[1] = true;
		}
		if (cBB.isSelected()) {
			change[2] = true;
		}
		if (cBC.isSelected()) {
			change[3] = true;
		}
		if (cBD.isSelected()) {
			change[4] = true;
		}
		if (cBE.isSelected()) {
			change[5] = true;
		}

		if (cBS.isSelected()) {
			lblTierS.setText("");
		} else {
			change[0] = false;
			if (up(0) == false) {
				lblTierS.setText("wird gebannt");
			}
		}
		if (cBA.isSelected()) {
			lblTierA.setText("");
		} else {
			change[1] = false;
			if (up(1) == false) {
				lblTierA.setText("wird gebannt");
			}
			if (down(1) == false || (up(1) && down(1))) {
				lblTierA.setText("wird hochgestuft");
			}
		}
		if (cBB.isSelected()) {
			lblTierB.setText("");
		} else {
			change[2] = false;
			if (up(2) == false) {
				lblTierB.setText("wird gebannt");
			}
			if (down(2) == false || (up(2) && down(2))) {
				lblTierB.setText("wird hochgestuft");
			}
		}
		if (cBC.isSelected()) {
			lblTierC.setText("");
		} else {
			change[3] = false;
			if (up(3) == false) {
				lblTierC.setText("wird gebannt");
			}
			if (down(3) == false || (up(3) && down(3))) {
				lblTierC.setText("wird hochgestuft");
			}

		}
		if (cBD.isSelected()) {
			lblTierD.setText("");
		} else {
			change[4] = false;
			if (up(4) == false) {
				lblTierD.setText("wird gebannt");
			}
			if (down(4) == false || (up(4) && down(4))) {
				lblTierD.setText("wird hochgestuft");
			}
		}
		if (cBE.isSelected()) {
			lblTierE.setText("");
		} else {
			change[5] = false;
			if (down(5) == false) {
				lblTierE.setText("wird hochgestuft");
			}
		}

	}

	private boolean up(int a) {
		try {
			if (change[a - 1]) {
				return true;
			} else {
				return up(a - 1);
			}
		} catch (Exception e) {
			return false;
		}
	}

	private boolean down(int a) {
		try {
			if (change[a + 1]) {
				return true;
			} else {
				return down(a + 1);
			}
		} catch (Exception e) {
			return false;
		}
	}

	private void resetsettings() {
		cBS.setSelected(false);
		cBA.setSelected(false);
		cBB.setSelected(false);
		cBC.setSelected(false);
		cBD.setSelected(false);
		cBE.setSelected(false);
		changesetting();
		radioButtonS.setEnabled(true);
		radioButtonA.setEnabled(true);
		radioButtonB.setEnabled(true);
		radioButtonC.setEnabled(true);
		radioButtonD.setEnabled(true);
		radioButtonE.setEnabled(true);
		radioButtonS.setText("S");
		radioButtonA.setText("A");
		radioButtonB.setText("B");
		radioButtonC.setText("C");
		radioButtonD.setText("D");
		radioButtonE.setText("E");
		tF1.setText("");
		tF2.setText("");
		tF3.setText("");
		tF4.setText("");
		tF5.setText("");
		tF6.setText("");
		tF1.setEditable(false);
		tF2.setEditable(false);
		tF3.setEditable(false);
		tF4.setEditable(false);
		tF5.setEditable(false);
		tF6.setEditable(false);
		comboBoxS.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxA.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxB.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxC.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxD.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxE.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		comboBoxS.setSelectedIndex(-1);
		comboBoxA.setSelectedIndex(-1);
		comboBoxB.setSelectedIndex(-1);
		comboBoxC.setSelectedIndex(-1);
		comboBoxD.setSelectedIndex(-1);
		comboBoxE.setSelectedIndex(-1);
		comboBoxS.setEnabled(false);
		comboBoxA.setEnabled(false);
		comboBoxB.setEnabled(false);
		comboBoxC.setEnabled(false);
		comboBoxD.setEnabled(false);
		comboBoxE.setEnabled(false);
		countauswahl = new int[] { 0, 0, 0, 0, 0, 0 };

	}

	private void opentierlist() {
		panelMainMenu.setVisible(false);
		list.removeAll();
		for (int i = 0; i < Data.getPokedex().length; i++) {
			list.add(Data.getPokedex(i));
		}
		list.select(0);
		changetier();
		tierlist();
		panel_tierlist.setVisible(true);
	}

	protected static MainMenu getwindow() {
		return window;
	}

	protected Object[] getteam() {
		String finalteam = "";
		try {
			for (String k : Spieler) {
				finalteam = finalteam + k + ":";
			}
			finalteam.substring(0, finalteam.length() - 1);
		} catch (Exception e) {
			return new Object[] { window.spinnerteam.getValue().toString(), window.ePTeam.getText(),
					org.json.JSONObject.NULL };

		}
		return new String[] { window.spinnerteam.getValue().toString(), window.ePTeam.getText(), finalteam };
	}

	protected Object[] getsettings() {
		return new Object[] { window.cBS.isSelected(), window.cBA.isSelected(), window.cBB.isSelected(),
				window.cBC.isSelected(), window.cBD.isSelected(), window.cBE.isSelected(), window.tF1.getText(),
				window.tF2.getText(), window.tF3.getText(), window.tF4.getText(), window.tF5.getText(),
				window.tF6.getText() };
	}

	protected void setteam(String[] list) throws Exception {
		int count = Integer.parseInt(list[0]);
		spinnerteam.setValue(count);
		ePTeam.setText(list[1]);
		String[] Spieler = list[2].split(":");
		teamname = new String[count];
		String print = "";
		for (int k = 0; k < Spieler.length; k++) {
			Player = new String[Spieler.length];
			Player[k] = Spieler[k];
			teamname[k] = Player[k] + ":";
			print = print + (k + 1) + "   " + Player[k] + "\n";
		}
		teamname[teamname.length - 1] = teamname[teamname.length - 1].substring(0,
				teamname[teamname.length - 1].length() - 1);
		ePfinalteam.setText(print);
	}

	protected void setsettings(Object[] list) throws Exception {
		resetsettings();
		for (int k = 0; k < list.length; k++) {
			switch (k) {
			case 1:
				if (list[k].equals(true)) {
					cBS.setSelected(true);
				} else {
					cBS.setSelected(false);
				}
				break;
			case 2:
				if (list[k].equals(true)) {
					cBS.setSelected(true);
				} else {
					cBS.setSelected(false);
				}
				break;
			case 3:
				if (list[k].equals(true)) {
					cBS.setSelected(true);
				} else {
					cBS.setSelected(false);
				}
				break;
			case 4:
				if (list[k].equals(true)) {
					cBS.setSelected(true);
				} else {
					cBS.setSelected(false);
				}
				break;
			case 5:
				if (list[k].equals(true)) {
					cBS.setSelected(true);
				} else {
					cBS.setSelected(false);
				}
				break;
			case 6:
				if (list[k].equals(true)) {
					cBS.setSelected(true);
				} else {
					cBS.setSelected(false);
				}
				break;
			case 7:
				tF1.setText(list[k].toString());
				break;
			case 8:
				tF2.setText(list[k].toString());
				break;
			case 9:
				tF3.setText(list[k].toString());
				break;
			case 10:
				tF4.setText(list[k].toString());
				break;
			case 11:
				tF5.setText(list[k].toString());
				break;
			case 12:
				tF6.setText(list[k].toString());
				break;
			}

		}
		changesetting();
	}

	protected void opendraft() {
		panelStartDraft.setVisible(false);
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
		panel_draft.add(cBchangeteam);

		int hight = 100;
		for (int k : countauswahl) {
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
		frmPokemonDraft.setBounds(100, 100, 1100, hight);
		if (!finishdraft) {
			Data.inittierpokemon();
			draftlayout();
		}
		panel_draft.setVisible(true);
	}

	protected void changepokeanzahl(int a, int auswahl) {
		lblbest.setText("");
		if (auswahl == -1) {
			auswahl = 0;
		}
		int count = 15 - auswahl + countauswahl[a];
		for (int k : countauswahl) {
			count -= k;
		}
		countauswahl[a] = auswahl;
//		System.out.println("- " + a + " " + count + " " + auswahl);
		try {
			comboBoxS.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[0] + count)));
			comboBoxA.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[1] + count)));
			comboBoxB.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[2] + count)));
			comboBoxC.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[3] + count)));
			comboBoxD.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[4] + count)));
			comboBoxE.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(countauswahl[5] + count)));

			comboBoxS.setSelectedIndex(countauswahl[0] - 1);
			comboBoxA.setSelectedIndex(countauswahl[1] - 1);
			comboBoxB.setSelectedIndex(countauswahl[2] - 1);
			comboBoxC.setSelectedIndex(countauswahl[3] - 1);
			comboBoxD.setSelectedIndex(countauswahl[4] - 1);
			comboBoxE.setSelectedIndex(countauswahl[5] - 1);
		} catch (Exception g) {
			Manage.msgbox("Huch da ist was bei der Anzahl der Pokemon schief gelaufen" + "\n"
					+ "Keine Sorge das war nicht deine Schuld aber leider können deine Einstellungen nicht übernommen werden",
					frmPokemonDraft);
			comboBoxS.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
			comboBoxA.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
			comboBoxB.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
			comboBoxC.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
			comboBoxD.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
			comboBoxE.setModel(new DefaultComboBoxModel<String>(Manage.getaarray(15)));
		}
	}

	protected void draftlayout() {
		panelStartDraft.setVisible(false);
		for (JComboBox<String> k : cbDraft) {
			try {
				panel_draft.remove(k);
			} catch (Exception e) {

			}
		}
		int[] pokeanzahl = countauswahl.clone();
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
				panel_draft.add(separator);
				JLabel lblNewLabel_2 = new JLabel(tiernamen[i]);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_2.setBounds(54, line - 40, 550, 14);
				panel_draft.add(lblNewLabel_2);
			}
			try {
				int[] nxco = nextcolumn(pkan);
				for (int co = 0; co < pkan; co++) {
					cbDraft[count] = new JComboBox<String>(Data.gettierpokemon(i));
					cbDraft[count].addPopupMenuListener(new PopupListener(cbDraft[count]) {
						public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
							changedraftpokemon(this.box);
						}
					});
					cbDraft[count].setSelectedIndex(-1);
					cbDraft[count].setBounds(nxco[co], line, 169, 20);
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
					panel_draft.add(cbDraft[count]);
					count++;
				}
				sep = false;
			} catch (Exception e) {
				pokeanzahl[i] -= 3;
				i--;
				sep = true;
			}
			line += 130;
		}
		finishdraft = true;
	}

	protected int[] nextcolumn(int k) {
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
}
