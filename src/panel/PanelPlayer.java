package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import client.Manage;
import client.Writer;

@SuppressWarnings("serial")
public class PanelPlayer extends JPanel {
	private JPanel panel = new JPanel();
	protected JSpinner spinnerteam;
	protected String[] Player;
	protected JEditorPane ePTeam;
	protected String[] Spieler = new String[0];
	protected String[] teamname;
	protected JEditorPane ePfinalteam;
	private JTextField tF_Teams;
	protected JComboBox<String> cBTeams;
	private String[][] teamlist;

	public PanelPlayer() {
		
		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
		
		spinnerteam = new JSpinner();
		spinnerteam.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int count = (int) spinnerteam.getValue();
				if (count < 0) {
					spinnerteam.setValue(0);
				} else {
					Player = new String[count];
				}

			}
		});
		spinnerteam.setBounds(22, 11, 50, 20);
		panel.add(spinnerteam);

		JLabel lblSpieleranzahl = new JLabel("Team-Anzahl");
		lblSpieleranzahl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpieleranzahl.setBounds(78, 13, 106, 17);
		panel.add(lblSpieleranzahl);

		ePTeam = new JEditorPane();
		ePTeam.setBounds(22, 52, 176, 479);
		panel.add(ePTeam);

		JButton btnPlayer = new JButton("Bestätige");
		btnPlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String[] Eingabe = ePTeam.getText().split("\n");
				int count = 0;
				for (int i = 0; i < Eingabe.length; i++) {
					if (Eingabe[i].trim().length() > 1) {
						count++;
					}
				}
				if ((int) spinnerteam.getValue() == 0) {
					Manage.msgbox("Du willst doch nicht ohne auch nur ein Team spielen, oder?",
							Gui.getwindow().getFrmPokemonDraft());
				} else {
					if (count != (int) spinnerteam.getValue()) {
						Manage.msgbox(
								"Deine Liste Stimmt nicht mit der Team Anzahl überein" + "\n"
										+ "Denk dran: Teams haben niemals nur einen Buchstaben!",
								Gui.getwindow().getFrmPokemonDraft());
					} else {
						Spieler = new String[count];
						for (int k = 0; k < Spieler.length; k++) {
							for (int i = k; i < Eingabe.length; i++) {
								if (Eingabe[i].trim().length() > 1) {
									if (k > 0) {
										if (Eingabe[i].trim().equals(Spieler[k - 1])) {
											continue;
										}
									}
									Spieler[k] = Eingabe[i].trim();
									break;
								}
							}
						}
						teamname = new String[count];
						String list = "";
						for (int k = 0; k < Spieler.length; k++) {
							Player = new String[Spieler.length];
							Player[k] = Spieler[k];
							teamname[k] = Player[k] + ":";
							list = list + (k + 1) + "   " + Player[k] + "\n";
						}
						teamname[teamname.length - 1] = teamname[teamname.length - 1].substring(0,
								teamname[teamname.length - 1].length() - 1);
						ePfinalteam.setText(list);
						Gui.getwindow().getPanelStartDraft().enablebtnReihenfolge();
					}
				}
			}
		});
		btnPlayer.setBounds(66, 542, 89, 23);
		panel.add(btnPlayer);

		ePfinalteam = new JEditorPane();
		ePfinalteam.setEditable(false);
		ePfinalteam.setBounds(217, 52, 176, 293);
		panel.add(ePfinalteam);

		JLabel lblNewLabel = new JLabel("Gespeicherte Teams");
		lblNewLabel.setBounds(239, 37, 133, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Team-Namen");
		lblNewLabel_1.setBounds(57, 37, 106, 14);
		panel.add(lblNewLabel_1);

		tF_Teams = new JTextField();
		tF_Teams.setColumns(10);
		tF_Teams.setBounds(262, 367, 86, 20);
		panel.add(tF_Teams);

		JButton btnsafeteams = new JButton("Speichern");
		btnsafeteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ePfinalteam.getText().toString().length() < 1) {
					Manage.msgbox("Du hast keine Teams bestätigt", Gui.getwindow().getFrmPokemonDraft());
					return;
				}
				if (tF_Teams.getText().equals("") || tF_Teams.getText().equals("Gespeichert")) {
					Manage.msgbox("Du hast keinen Namen eingegeben", Gui.getwindow().getFrmPokemonDraft());
					tF_Teams.setText("");
				} else {
					if (tF_Teams.getText().contains(":")) {
						Manage.msgbox("Der Name das keinen Doppelpunkt enthalten",
								Gui.getwindow().getFrmPokemonDraft());
					} else {
						if (ePfinalteam.getText().length() > 2) {
							Boolean b = true;
							for (String k : teamname) {
								if (tF_Teams.getText().equals(k)) {
									Manage.msgbox("Der Name existiert schon", Gui.getwindow().getFrmPokemonDraft());
									b = false;
									break;
								}
							}
							if (b) {
								Writer.print("teamlist", tF_Teams.getText(), teamname);
								tF_Teams.setText("Gespeichert");
								panel.remove(cBTeams);
								teamlist();
							}
						}

					}
				}
			}

		});
		btnsafeteams.setBounds(251, 398, 109, 23);
		panel.add(btnsafeteams);

		cBTeams = new JComboBox(new Object[] {});
		cBTeams.setBounds(243, 469, 124, 28);
		panel.add(cBTeams);

		JButton btnloadteams = new JButton("Laden");
		btnloadteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String read = "";
				for (int k = 1; k < teamlist[cBTeams.getSelectedIndex()].length; k++) {
					read = read + teamlist[cBTeams.getSelectedIndex()][k] + "\n";
				}
				ePTeam.setText(read);
				ePfinalteam.setText("");
				spinnerteam.setValue(teamlist[cBTeams.getSelectedIndex()].length - 1);
			}
		});
		btnloadteams.setBounds(261, 508, 89, 23);
		panel.add(btnloadteams);

		add(panel);

	}

	void teamlist() {
		try {
			teamlist = client.Writer.read("teamlist");
		} catch (Exception e) {

		}
		try {
			teamname = new String[teamlist.length];
			for (int i = 0; i < teamname.length; i++) {
				try {
					teamname[i] = teamlist[i][0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			teamname = new String[0];
		}

		cBTeams = new JComboBox(teamname);
		cBTeams.setBounds(234, 469, 124, 28);
		panel.add(cBTeams);
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
}
