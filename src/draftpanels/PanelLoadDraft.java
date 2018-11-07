package draftpanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Manage;
import client.Writer;

@SuppressWarnings("serial")
public class PanelLoadDraft extends JPanel {

	private JLayeredPane panel = new JLayeredPane();
	private JTextField tFsafename;
	private JComboBox<String> cBloaddraft;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PanelLoadDraft() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));
		Image img = background.getImage();
		Image temp = img.getScaledInstance(409, 640, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 409, 640);
		panel.add(new JLabel(background));

		JLabel lblstatus = new JLabel("");
		lblstatus.setBounds(154, 158, 152, 14);
		panel.add(lblstatus);
		panel.setVisible(false);

		JButton btnloaddraft = new JButton("Laden");
		btnloaddraft.addActionListener(e -> {

		});
		btnloaddraft.setBounds(217, 437, 89, 23);
		panel.add(btnloaddraft);

		tFsafename = new JTextField();
		tFsafename.setBounds(158, 93, 86, 20);
		panel.add(tFsafename);
		tFsafename.setColumns(10);

		JButton btnsafedraft = new JButton("Speichern");
		btnsafedraft.addActionListener(e -> {
			if (tFsafename.getText().trim().length() < 1) {
				Manage.msgboxError("Du solltest schon ein Namen zum Speichern eingeben",
						DraftGui.getwindow().getFrmPokemonDraft());
			} else {
				int status = Writer.safeasjson(tFsafename.getText(), DraftGui.getwindow().getFrmPokemonDraft());
				if (status == 0) {
					lblstatus.setText("Error");
				} else {
					lblstatus.setText("Erfolgreich gespeichert");
				}
			}
		});
		btnsafedraft.setBounds(157, 124, 89, 23);
		panel.add(btnsafedraft);

		cBloaddraft = new JComboBox();
		cBloaddraft.setBounds(72, 440, 125, 20);
		panel.add(cBloaddraft);

		add(panel);
	}
}