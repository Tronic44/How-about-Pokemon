package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.List;

public class MainMenu {

	private JFrame frame;
	private JPanel panelMainMenu;
	private JPanel panelStartDraft;
	private JPanel panelLoadDraft;
	private JPanel panel_tierlist;
	private List list;

	public static void startMainMenu() {
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
		
		panelMainMenu = new JPanel();
		frame.getContentPane().add(panelMainMenu, "name_526992955635103");
		panelMainMenu.setLayout(null);
		panelMainMenu.setVisible(true);
		
		panelStartDraft = new JPanel();
		frame.getContentPane().add(panelStartDraft, "name_527021172921335");
		panelStartDraft.setLayout(null);
		
		panelLoadDraft = new JPanel();
		frame.getContentPane().add(panelLoadDraft, "name_527666975961040");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JLabel lblNewLabel = new JLabel("\"Draft\"");
		lblNewLabel.setBounds(169, 212, 46, 14);
		panelStartDraft.add(lblNewLabel);
		
		JButton btnTierlist = new JButton("Tierlist");
		btnTierlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelStartDraft.setVisible(false);
				for(int i= 0 ; i<data.Data.getPokedex().length;i++) {
					list.add(data.Data.Pokedex[i]);
				}
				panel_tierlist.setVisible(true);
			}
		});
		btnTierlist.setBounds(63, 11, 255, 71);
		panelStartDraft.add(btnTierlist);
		
		JButton btnFertig = new JButton("Fertig");
		btnFertig.setBounds(63, 408, 255, 76);
		panelStartDraft.add(btnFertig);
		panelStartDraft.setVisible(false);
		
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
		
		panel_tierlist = new JPanel();
		frame.getContentPane().add(panel_tierlist, "name_2032838076395");
		panel_tierlist.setLayout(null);
		panel_tierlist.setVisible(false);
		
		JLabel lblPokemon = new JLabel("Pokemon");
		lblPokemon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPokemon.setBounds(10, 0, 73, 28);
		panel_tierlist.add(lblPokemon);
		
		list = new List();
		list.setBounds(20, 33, 197, 511);
		panel_tierlist.add(list);
		
		JPanel panel_draft = new JPanel();
		frame.getContentPane().add(panel_draft, "name_2679324427935");
		panel_draft.setLayout(null);
		
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
		
		
		JButton btnMainmenu = new JButton("MainMenu");
		btnMainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_tierlist.setVisible(false);
				panelLoadDraft.setVisible(false);
				panelStartDraft.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		menuBar.add(btnMainmenu);
		

	}
}
