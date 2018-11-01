package draftpanels;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Manage;

@SuppressWarnings("serial")
public class PanelMainMenu extends JPanel {

	private JPanel panel;
	private static final Font FONT = new Font(Manage.FONT, Font.BOLD, 23);

	public PanelMainMenu() {

		panel = new JPanel();
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblPokemonRandomDraft = new JLabel("Pokemon Random Draft");
		lblPokemonRandomDraft.setBounds(17, 11, 374, 38);
		panel.add(lblPokemonRandomDraft);
		lblPokemonRandomDraft.setFont(new Font(Manage.FONT, Font.BOLD, 31));

		JButton btnNewButton = new JButton("Start Draft");
		btnNewButton.addActionListener(e -> Gui.getwindow().visStartDraft());
		btnNewButton.setFont(FONT);
		btnNewButton.setBounds(86, 109, 236, 68);
		panel.add(btnNewButton);

		JButton btnLoadDraft = new JButton("Load Draft");
		btnLoadDraft.addActionListener(e -> Gui.getwindow().visLoadDraft());
		btnLoadDraft.setFont(FONT);
		btnLoadDraft.setBounds(86, 286, 236, 68);
		panel.add(btnLoadDraft);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> System.exit(0));

		btnExit.setFont(FONT);
		btnExit.setBounds(85, 463, 239, 68);
		panel.add(btnExit);

		setLayout(null);
		add(panel);
	}
}