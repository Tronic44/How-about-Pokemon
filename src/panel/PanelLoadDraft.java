package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Manage;
import client.Writer;

@SuppressWarnings("serial")
public class PanelLoadDraft extends JPanel {

	private JPanel panel = new JPanel();
	private JTextField tFsafename;
	private JComboBox<String> cBloaddraft;
	private String[] tiernamen = new String[6];

	public PanelLoadDraft() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblstatus = new JLabel("");
		lblstatus.setBounds(154, 158, 152, 14);
		panel.add(lblstatus);
		panel.setVisible(false);

		JButton btnloaddraft = new JButton("Laden");
		btnloaddraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnloaddraft.setBounds(217, 437, 89, 23);
		panel.add(btnloaddraft);

		tFsafename = new JTextField();
		tFsafename.setBounds(158, 93, 86, 20);
		panel.add(tFsafename);
		tFsafename.setColumns(10);

		JButton btnsafedraft = new JButton("Speichern");
		btnsafedraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tFsafename.getText().trim().length() < 1) {
					Manage.msgbox("Du solltest schon ein Namen zum Speichern eingeben",
							Gui.getwindow().getFrmPokemonDraft());
				} else {
					int status = Writer.safeasjson(tFsafename.getText(), Gui.getwindow().getFrmPokemonDraft());
					if (status == 0) {
						lblstatus.setText("Error");
					} else {
						lblstatus.setText("Erfolgreich gespeichert");
					}

				}
			}
		});
		btnsafedraft.setBounds(157, 124, 89, 23);
		panel.add(btnsafedraft);

		cBloaddraft = new JComboBox();
		cBloaddraft.setBounds(72, 440, 125, 20);
		panel.add(cBloaddraft);

	}

	public Object[] getteam() {
		String finalteam = "";
		try {
			for (String k : Gui.getwindow().getPanel_player().Spieler) {
				finalteam = finalteam + k + ":";
			}
			finalteam.substring(0, finalteam.length() - 1);
		} catch (Exception e) {
			return new Object[] { Gui.getwindow().getPanel_player().spinnerteam.getValue().toString(),
					Gui.getwindow().getPanel_player().ePTeam.getText(), org.json.JSONObject.NULL };

		}
		return new String[] { Gui.getwindow().getPanel_player().spinnerteam.getValue().toString(),
				Gui.getwindow().getPanel_player().ePTeam.getText(), finalteam };
	}

	public Object[] getsettings() {
		return new Object[] { Gui.getwindow().getPanel_settings().cBS.isSelected(),
				Gui.getwindow().getPanel_settings().cBA.isSelected(),
				Gui.getwindow().getPanel_settings().cBB.isSelected(),
				Gui.getwindow().getPanel_settings().cBC.isSelected(),
				Gui.getwindow().getPanel_settings().cBD.isSelected(),
				Gui.getwindow().getPanel_settings().cBE.isSelected(), Gui.getwindow().getPanel_settings().tF1.getText(),
				Gui.getwindow().getPanel_settings().tF2.getText(), Gui.getwindow().getPanel_settings().tF3.getText(),
				Gui.getwindow().getPanel_settings().tF4.getText(), Gui.getwindow().getPanel_settings().tF5.getText(),
				Gui.getwindow().getPanel_settings().tF6.getText() };
	}

	
}