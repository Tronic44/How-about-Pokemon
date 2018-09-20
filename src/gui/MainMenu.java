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

public class MainMenu {

	private JFrame frame;
	private JPanel panelMainMenu;
	private JPanel panelStartDraft;
	private JPanel panelLoadDraft;

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
		frame.setBounds(100, 100, 420, 581);
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
		
		
		JButton btnMainmenu = new JButton("MainMenu");
		btnMainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLoadDraft.setVisible(false);
				panelStartDraft.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		menuBar.add(btnMainmenu);
		

	}
}
