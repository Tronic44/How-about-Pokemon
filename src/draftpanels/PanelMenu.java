package draftpanels;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.MainMenu;
import client.Manage;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class PanelMenu extends JPanel {

	private JLayeredPane panel;
	private static final Font FONT = new Font(Manage.FONT, Font.BOLD, 23);
	private JButton btnStartButton;

	public PanelMenu() {
		
		panel = new JLayeredPane();
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
		
		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(409, 640, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 409, 640);
		panel.add(back);

		JLabel lblPokemonRandomDraft = new JLabel("Pokemon Random Draft");
		panel.setLayer(lblPokemonRandomDraft, 1);
		lblPokemonRandomDraft.setBounds(17, 11, 374, 38);
		panel.add(lblPokemonRandomDraft);
		lblPokemonRandomDraft.setFont(new Font(Manage.FONT, Font.BOLD, 31));

		btnStartButton = new JButton("Start Draft");
		panel.setLayer(btnStartButton, 1);
		btnStartButton.addActionListener(e -> DraftGui.getwindow().visStartDraft());
		btnStartButton.setFont(FONT);
		btnStartButton.setBounds(86, 109, 236, 68);
		panel.add(btnStartButton);

		JButton btnLoadDraft = new JButton("Load Draft");
		panel.setLayer(btnLoadDraft, 1);
		btnLoadDraft.addActionListener(e -> DraftGui.getwindow().visLoadDraft());
		btnLoadDraft.setFont(FONT);
		btnLoadDraft.setEnabled(false);
		btnLoadDraft.setBounds(86, 286, 236, 68);
		panel.add(btnLoadDraft);

		JButton btnExit = new JButton("Exit");
		panel.setLayer(btnExit, 1);
		btnExit.addActionListener(e -> MainMenu.getwindow().visMainMenu());
		btnExit.setFont(FONT);
		btnExit.setBounds(85, 463, 239, 68);
		panel.add(btnExit);

		add(panel);
	}
	
	public void renamebtn(String s) {
		btnStartButton.setText(s);
	}
}