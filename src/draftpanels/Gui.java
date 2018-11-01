package draftpanels;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import client.Manage;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Gui {

	private static Gui window;
	private JFrame frmPokemonDraft;
	private PanelMainMenu panelMainMenu;
	private PanelStartDraft panelStartDraft;
	private PanelLoadDraft panelLoadDraft;
	private PanelTierlist panelTierlist;
	private PanelDraft panelDraft;
	private PanelPlayer panelPlayer;
	private PanelSettings panelSettings;
	private PanelOrder panelOrder;
	private JPanel panelloading;
	private boolean finishdraft = false;

	public static void startMainMenu() {
		EventQueue.invokeLater(() -> {
			try {
				Manage.msgboxErfolg("Pre-release" + "\n" + "You can test all valid functions in the program" + "\n"
						+ "Please contact me and send me all files of the program if an error occurs.", null);

				window = new Gui();
				window.frmPokemonDraft.setVisible(true);
			} catch (Exception e) {
				try {
					e.printStackTrace(
							new PrintWriter(new BufferedWriter(new FileWriter("Error" + ".txt", true)), true));
					Manage.msgboxError("Ein Error ist aufgetreten und gespeichert", null);
				} catch (Exception e1) {
				}
			}
		});
	}

	public static Gui getwindow() {
		return window;
	}

	private Gui() {
		frmPokemonDraft = new JFrame();
		frmPokemonDraft.setTitle("Pokemon Draft        Alpha by Tronic44");
		frmPokemonDraft.setResizable(false);
		frmPokemonDraft.setBounds(100, 100, 409, 640);
//		frmPokemonDraft.setBounds(100, 100, 1100, 800);
		frmPokemonDraft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPokemonDraft.getContentPane().setLayout(new CardLayout(0, 0));

		initmenubar();
		initpanel();
	}

	private void initpanel() {
		panelMainMenu = new PanelMainMenu();
		frmPokemonDraft.getContentPane().add(panelMainMenu, "name_526992955635103");
		panelMainMenu.setLayout(null);
		panelMainMenu.setVisible(true);

		panelStartDraft = new PanelStartDraft();
		frmPokemonDraft.getContentPane().add(panelStartDraft, "name_527021172921335");
		panelStartDraft.setVisible(false);
		panelStartDraft.setLayout(null);

		panelLoadDraft = new PanelLoadDraft();
		frmPokemonDraft.getContentPane().add(panelLoadDraft, "name_527666975961040");
		panelLoadDraft.setLayout(null);
		panelLoadDraft.setVisible(false);

		panelTierlist = new PanelTierlist();
		frmPokemonDraft.getContentPane().add(panelTierlist, "name_2032838076395");
		panelTierlist.setLayout(null);
		panelTierlist.setVisible(false);

		panelPlayer = new PanelPlayer();
		frmPokemonDraft.getContentPane().add(panelPlayer, "name_601620298180688");
		panelPlayer.setVisible(false);
		panelPlayer.setLayout(null);

		panelSettings = new PanelSettings();
		frmPokemonDraft.getContentPane().add(panelSettings, "name_601636768594680");
		panelSettings.setVisible(false);
		panelSettings.setLayout(null);

		panelOrder = new PanelOrder();
		frmPokemonDraft.getContentPane().add(panelOrder, "name_601652242106220");
		panelOrder.setVisible(false);
		panelOrder.setLayout(null);

		panelDraft = new PanelDraft();
		frmPokemonDraft.getContentPane().add(panelDraft, "name_2679324427935");
		panelDraft.setLayout(null);
		panelDraft.setVisible(false);

		panelloading = new JPanel();
		frmPokemonDraft.getContentPane().add(panelloading, "name_910613970759788");
		initloading();
	}

	private void initloading() {
		JLabel lblLabel = new JLabel("Loading");
		lblLabel.setBounds(148, 40, 107, 33);
		lblLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		JLabel lblNewLabel = new JLabel("Wenn du das lesen kannst, hast du ein Problem. Versusche es bitte erneut");
		lblNewLabel.setBounds(28, 79, 500, 14);
		panelloading.setLayout(null);
		panelloading.add(lblLabel);
		panelloading.add(lblNewLabel);
		panelDraft.setVisible(false);
	}

	private void initmenubar() {
		JMenuBar menuBar = new JMenuBar();
		frmPokemonDraft.setJMenuBar(menuBar);

		JButton btnMainmenu = new JButton("MainMen\u00FC");
		btnMainmenu.addActionListener(e -> {
			if (panelDraft.isVisible()) {
				Manage.msgboxError("ACHTUNG: Änderungen nach dem Draft beginn, kann zum neustart des Draftens führen!",
						frmPokemonDraft);
			}
			frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
			visMainMenu();
		});
		menuBar.add(btnMainmenu);

		JButton btnmenuback = new JButton("zur\u00FCck");
		btnmenuback.addActionListener(e -> {

			if (panelStartDraft.isVisible()) {
				visMainMenu();
			}
			if (panelDraft.isVisible()) {
				visLoading();
				client.Manage.msgboxError(
						"ACHTUNG: Änderungen nach dem Draft beginn, kann zum neustart des Draftens führen!",
						frmPokemonDraft);
				visStartDraft();
			}
			if (panelTierlist.isVisible() || panelSettings.isVisible() || panelOrder.isVisible()) {
				visStartDraft();
			}
			if (panelPlayer.isVisible()) {
				if (!getPanelPlayer().isTeams())
					Manage.msgboxErfolg("Beachte: Du hast ungespeicherte Änderung", Gui.getwindow().frmPokemonDraft);
				visStartDraft();
			}
			if (panelLoadDraft.isVisible()) {
				visMainMenu();
			}
			frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
		});
		menuBar.add(btnmenuback);

		JButton btnDebug = new JButton("Debug");
		btnDebug.addActionListener(e -> {
			for (Object k : Gui.getwindow().getFrmPokemonDraft().getContentPane().getComponents()) {
				System.out.println(k);
			}
			System.out.println();
			frmPokemonDraft.list();
			System.out.println("\n");
		});
		menuBar.add(btnDebug);
	}

	public JFrame getFrmPokemonDraft() {
		return frmPokemonDraft;
	}

	public PanelMainMenu getPanelMainMenu() {
		return panelMainMenu;
	}

	public PanelStartDraft getPanelStartDraft() {
		return panelStartDraft;
	}

	public PanelLoadDraft getPanelLoadDraft() {
		return panelLoadDraft;
	}

	public PanelTierlist getPanelTierlist() {
		return panelTierlist;
	}

	public PanelDraft getPanelDraft() {
		return panelDraft;
	}

	public PanelPlayer getPanelPlayer() {
		return panelPlayer;
	}

	public PanelSettings getPanelSettings() {
		return panelSettings;
	}

	public PanelOrder getPanelOrder() {
		return panelOrder;
	}

	public boolean isFinishdraft() {
		return finishdraft;
	}

	public void setFinishdraft(boolean b) {
		finishdraft = b;
	}

	protected void visStartDraft() {
		visLoading();
		panelStartDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visLoadDraft() {
		visLoading();
		panelLoadDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visMainMenu() {
		visLoading();
		panelMainMenu.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visPlayer() {
		visLoading();
		panelPlayer.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visTierlist() {
		visLoading();
		getPanelTierlist().tierlist();
		panelTierlist.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visSettings() {
		visLoading();
		panelSettings.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visOrder() {
		visLoading();
		panelOrder.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visDraft() {
		visLoading();
		panelDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visLoading() {
		panelTierlist.setVisible(false);
		panelLoadDraft.setVisible(false);
		panelStartDraft.setVisible(false);
		panelPlayer.setVisible(false);
		panelSettings.setVisible(false);
		panelOrder.setVisible(false);
		panelDraft.setVisible(false);
		panelMainMenu.setVisible(false);
		panelloading.setVisible(true);
	}
}