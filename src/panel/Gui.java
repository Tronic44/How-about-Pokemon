package panel;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import client.Manage;
import panel.PanelMainMenu;
import javax.swing.JLabel;
import java.awt.Font;

public class Gui {

	private static Gui window;
	private JFrame frmPokemonDraft;
	private PanelMainMenu panelMainMenu;
	private PanelStartDraft panelStartDraft;
	private PanelLoadDraft panelLoadDraft;
	private PanelTierlist panel_tierlist;
	private PanelDraft panel_draft;
	private PanelPlayer panel_player;
	private PanelSettings panel_settings;
	private PanelOrder panel_order;
	private JPanel panelloading;
	boolean finishdraft;
	private JLabel lblLabel;

	public static void startMainMenu() {
		client.Manage.initPoketier();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Gui();
					window.frmPokemonDraft.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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

		panel_tierlist = new PanelTierlist();
		frmPokemonDraft.getContentPane().add(panel_tierlist, "name_2032838076395");
		panel_tierlist.setLayout(null);
		panel_tierlist.setVisible(false);

		panel_player = new PanelPlayer();
		frmPokemonDraft.getContentPane().add(panel_player, "name_601620298180688");
		panel_player.setVisible(false);
		panel_player.setLayout(null);

		panel_settings = new PanelSettings();
		frmPokemonDraft.getContentPane().add(panel_settings, "name_601636768594680");
		panel_settings.setVisible(false);
		panel_settings.setLayout(null);

		panel_order = new PanelOrder();
		frmPokemonDraft.getContentPane().add(panel_order, "name_601652242106220");
		panel_order.setVisible(false);
		panel_order.setLayout(null);

		panel_draft = new PanelDraft();
		frmPokemonDraft.getContentPane().add(panel_draft, "name_2679324427935");
		panel_draft.setLayout(null);
		panel_draft.setVisible(false);

		panelloading = new JPanel();
		frmPokemonDraft.getContentPane().add(panelloading, "name_910613970759788");
		initloading();

	}

	private void initloading() {
		lblLabel = new JLabel("Loading");
		lblLabel.setBounds(148, 40, 107, 33);
		lblLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		JLabel lblNewLabel = new JLabel("Wenn du das lesen kannst, hast du ein Problem. Verusch es bitte erneut");
		lblNewLabel.setBounds(28, 79, 500, 14);
		panelloading.setLayout(null);
		panelloading.add(lblLabel);
		panelloading.add(lblNewLabel);
		panel_draft.setVisible(false);
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
				frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
				visMainMenu();
			}
		});
		menuBar.add(btnMainmenu);

		JButton btnmenuback = new JButton("zur\u00FCck");
		btnmenuback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelStartDraft.isVisible()) {
					visMainMenu();
				}
				if (panel_draft.isVisible()) {
					visLoading();
					client.Manage.msgbox(
							"ACHTUNG: Änderungen nach dem Draft beginn, kann zum neustart des Draftens führen!",
							frmPokemonDraft);
					visStartDraft();
				}
				if (panel_tierlist.isVisible() || panel_player.isVisible() || panel_settings.isVisible()
						|| panel_order.isVisible()) {
					visStartDraft();
				}
				if (panelLoadDraft.isVisible()) {
					visMainMenu();
				}
				frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
			}
		});
		menuBar.add(btnmenuback);

		JButton btnDebug = new JButton("Debug");
		btnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Object k : Gui.getwindow().getFrmPokemonDraft().getContentPane().getComponents()) {
					System.out.println(k);
				}
				System.out.println();
				frmPokemonDraft.list();
				System.out.println("\n");
			}
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

	public PanelTierlist getPanel_tierlist() {
		return panel_tierlist;
	}

	public PanelDraft getPanel_draft() {
		return panel_draft;
	}

	public PanelPlayer getPanel_player() {
		return panel_player;
	}

	public PanelSettings getPanel_settings() {
		return panel_settings;
	}

	public PanelOrder getPanel_order() {
		return panel_order;
	}

	public boolean isFinishdraft() {
		return finishdraft;
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
		panel_player.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visTierlist() {
		visLoading();
		panel_tierlist.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visSettings() {
		visLoading();
		panel_settings.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visOrder() {
		visLoading();
		panel_order.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visDraft() {
		visLoading();
		panel_draft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visLoading() {
		panel_tierlist.setVisible(false);
		panelLoadDraft.setVisible(false);
		panelStartDraft.setVisible(false);
		panel_player.setVisible(false);
		panel_settings.setVisible(false);
		panel_order.setVisible(false);
		panel_draft.setVisible(false);
		panelMainMenu.setVisible(false);
		panelloading.setVisible(true);
	}
}
