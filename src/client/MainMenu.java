package client;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import draftpanels.DraftGui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainMenu {

	private static MainMenu window;
	private JFrame frame;
	private static DraftGui windowPokemonDraft;
	private static JFrame frmPokeDraft;
	private JLayeredPane panel;

	public static void startHowAboutPokemon() {
		EventQueue.invokeLater(() -> {
			try {
				Manage.msgboxErfolg("Pre-release" + "\n" + "You can test all valid functions in the program" + "\n"
						+ "Please contact me and send me all files of the program if an error occurs.", null);

				window = new MainMenu();
				window.frame.setVisible(true);
			} catch (Exception e) {
				try {
					e.printStackTrace(
							new PrintWriter(new BufferedWriter(new FileWriter("Error" + ".txt", true)), true));
					Manage.msgboxError("Ein Error ist aufgetreten und gespeichert", null);
				} catch (IOException e1) {

				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("How about Pokemon       Alpha by Tronic44");
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JLayeredPane();
		panel.setBounds(0, 0, 500, 500);
		panel.setLayout(null);

		ImageIcon logoicon = new ImageIcon(getClass().getResource("Pokemon_logo.png"));
		Image img = logoicon.getImage();
		Image temp = img.getScaledInstance(490, 184, Image.SCALE_SMOOTH);
		logoicon = new ImageIcon(temp);
		JLabel logo = new JLabel(logoicon);
		panel.setLayer(logo, 1);
		logo.setLayout(null);
		logo.setBounds(0, 0, 490, 200);
		panel.add(logo);

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img2 = background.getImage();
		Image temp2 = img2.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp2);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		panel.setLayer(back, 0);
		back.setBounds(0, 0, 500, 500);
		panel.add(back);

		JButton btnNewButton = new JButton("Start PokemonDraft");
		panel.setLayer(btnNewButton, 0);
		btnNewButton.addActionListener(e -> {
			if (windowPokemonDraft != null) {
				Object[] options = { "NEUSTART", "WEITER" };
				if (JOptionPane.showOptionDialog(DraftGui.getwindow().getFrmPokemonDraft(),
						"Möchtest du das Programm neustarten, oder den letzen Draft weitermachen?",
						"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1]) == 0) {

					Object[] options2 = { "JA, ich möchte alles löschen", "NEIN, lass mich am alten weiter arbeiten" };
					if (JOptionPane.showOptionDialog(DraftGui.getwindow().getFrmPokemonDraft(),
							"Du bist dabei dein bisherigen Fortschritt zu löchen", "Es sind noch Dinge ungeklärt",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2,
							options2[1]) == 0) {

						restartDraftGui();
						frame.setVisible(false);
						frmPokeDraft.setVisible(true);
						frmPokeDraft.setBounds(frame.getX(), frame.getY(), frmPokeDraft.getWidth(),
								frmPokeDraft.getHeight());
					} else {
						frame.setVisible(false);
						frmPokeDraft.setVisible(true);
						frmPokeDraft.setBounds(frame.getX(), frame.getY(), frmPokeDraft.getWidth(),
								frmPokeDraft.getHeight());
					}
				} else {
					frame.setVisible(false);
					frmPokeDraft.setVisible(true);
					frmPokeDraft.setBounds(frame.getX(), frame.getY(), frmPokeDraft.getWidth(),
							frmPokeDraft.getHeight());
				}
				btnNewButton.setBounds(158, 216, 178, 38);
				frame.getContentPane().add(btnNewButton);
			} else {
				restartDraftGui();
				frame.setVisible(false);
				frmPokeDraft.setVisible(true);
				frmPokeDraft.setBounds(frame.getX(), frame.getY(), frmPokeDraft.getWidth(), frmPokeDraft.getHeight());
			}
		});

		btnNewButton.setBounds(158, 216, 178, 38);
		panel.setLayer(btnNewButton, 1);
		panel.add(btnNewButton);
		frame.getContentPane().add(panel);
	}

	public void visMainMenu() {
		frame.setVisible(true);
		frmPokeDraft.setVisible(false);
		frame.setBounds(frmPokeDraft.getX(), frmPokeDraft.getY(), frame.getWidth(), frame.getHeight());
	}

	private static void restartDraftGui() {
		windowPokemonDraft = new DraftGui();
		frmPokeDraft = windowPokemonDraft.getFrmPokemonDraft();
	}

	public static MainMenu getwindow() {
		return window;
	}

	public static DraftGui getPokemonDraft() {
		return windowPokemonDraft;
	}
}
