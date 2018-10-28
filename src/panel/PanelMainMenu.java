package panel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelMainMenu extends JPanel {

	private JPanel panel;

	public PanelMainMenu() {

		panel = new JPanel();
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblPokemonRandomDraft = new JLabel("Pokemon Random Draft");
		lblPokemonRandomDraft.setBounds(17, 11, 374, 38);
		panel.add(lblPokemonRandomDraft);
		lblPokemonRandomDraft.setFont(new Font("Tahoma", Font.BOLD, 31));

		JButton btnNewButton = new JButton("Start Draft");
		btnNewButton.addActionListener(e -> Gui.getwindow().visStartDraft());
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(117, 109, 175, 68);
		panel.add(btnNewButton);

		JButton btnLoadDraft = new JButton("Load Draft");
		btnLoadDraft.addActionListener(e -> Gui.getwindow().visLoadDraft());
		btnLoadDraft.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadDraft.setBounds(117, 286, 174, 68);
		panel.add(btnLoadDraft);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> System.exit(0));

		setLayout(null);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setBounds(114, 463, 181, 68);
		panel.add(btnExit);

		add(panel);
	}
}