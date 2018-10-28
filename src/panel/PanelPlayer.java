package panel;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import client.Manage;
import client.Writer;

@SuppressWarnings("serial")
public class PanelPlayer extends JPanel {
	private JPanel panel = new JPanel();
	protected JSpinner spinnerteam;
	protected String[] player;
	protected JEditorPane ePTeam;
	protected String[] spieler = new String[0];
	protected String[] teamname;
	protected JEditorPane ePfinalteam;
	private JTextField tFTeams;
	protected JComboBox<String> cBTeams;
	private String[][] teamlist;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelPlayer() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		spinnerteam = new JSpinner();
		spinnerteam.addChangeListener(e -> {
			int count = (int) spinnerteam.getValue();
			if (count < 0) {
				spinnerteam.setValue(0);
			} else {
				player = new String[count];
			}

		});
		spinnerteam.setBounds(22, 11, 50, 20);
		panel.add(spinnerteam);

		JLabel lblSpieleranzahl = new JLabel("Team-Anzahl");
		lblSpieleranzahl.setFont(new Font(Manage.FONT, Font.BOLD, 14));
		lblSpieleranzahl.setBounds(78, 13, 106, 17);
		panel.add(lblSpieleranzahl);

		ePTeam = new JEditorPane();
		ePTeam.setBounds(22, 52, 176, 479);
		panel.add(ePTeam);

		JButton btnPlayer = new JButton("Bestätige");
		btnPlayer.addActionListener(e -> {
			String[] eingabe = ePTeam.getText().split("\n");
			int count = 0;
			for (int i = 0; i < eingabe.length; i++) {
				if (eingabe[i].trim().length() > 1) {
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
					spieler = new String[count];
					for (int k = 0; k < spieler.length; k++) {
						for (int i = k; i < eingabe.length; i++) {
							if (eingabe[i].trim().length() > 1) {
								if (k > 0 && eingabe[i].trim().equals(spieler[k - 1])) {
									continue;
								}
								spieler[k] = eingabe[i].trim();
								break;
							}
						}
					}
					teamname = new String[count];
					StringBuilder list = new StringBuilder("");
					for (int k = 0; k < spieler.length; k++) {
						player = new String[spieler.length];
						player[k] = spieler[k];
						teamname[k] = player[k] + ":";
						list.append((k + 1) + "   " + player[k] + "\n");
					}
					teamname[teamname.length - 1] = teamname[teamname.length - 1].substring(0,
							teamname[teamname.length - 1].length() - 1);
					ePfinalteam.setText(list.toString());
					Gui.getwindow().getPanelStartDraft().enablebtnReihenfolge();
				}
			}
		});
		btnPlayer.setBounds(66, 542, 89, 23);
		panel.add(btnPlayer);

		ePfinalteam = new JEditorPane();
		ePfinalteam.setEditable(false);
		ePfinalteam.setBounds(217, 52, 176, 293);
		panel.add(ePfinalteam);

		JLabel lblgespeicherteTeams = new JLabel("Gespeicherte Teams");
		lblgespeicherteTeams.setBounds(239, 37, 133, 14);
		panel.add(lblgespeicherteTeams);

		JLabel lblTeamNamen = new JLabel("Team-Namen");
		lblTeamNamen.setBounds(57, 37, 106, 14);
		panel.add(lblTeamNamen);

		tFTeams = new JTextField();
		tFTeams.setColumns(10);
		tFTeams.setBounds(262, 367, 86, 20);
		panel.add(tFTeams);

		JButton btnsafeteams = new JButton("Speichern");
		btnsafeteams.addActionListener(e -> {
			if (ePfinalteam.getText().length() < 1) {
				Manage.msgbox("Du hast keine Teams bestätigt", Gui.getwindow().getFrmPokemonDraft());
				return;
			}
			if (tFTeams.getText().equals("") || tFTeams.getText().equals("Gespeichert")) {
				Manage.msgbox("Du hast keinen Namen eingegeben", Gui.getwindow().getFrmPokemonDraft());
				tFTeams.setText("");
			} else {
				if (tFTeams.getText().contains(":")) {
					Manage.msgbox("Der Name das keinen Doppelpunkt enthalten", Gui.getwindow().getFrmPokemonDraft());
				} else {
					if (ePfinalteam.getText().length() > 2) {
						Boolean b = true;
						for (String k : teamname) {
							if (tFTeams.getText().equals(k)) {
								Manage.msgbox("Der Name existiert schon", Gui.getwindow().getFrmPokemonDraft());
								b = false;
								break;
							}
							if (k.equals("Lese Error")) {
								return;
							}
						}
						if (b) {
							Writer.print("teamlist", tFTeams.getText(), teamname);
							tFTeams.setText("Gespeichert");
							panel.remove(cBTeams);
							teamlist();
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
		btnloadteams.addActionListener(e -> {
			StringBuilder read = new StringBuilder("");
			for (int k = 1; k < teamlist[cBTeams.getSelectedIndex()].length; k++) {
				read.append(teamlist[cBTeams.getSelectedIndex()][k] + "\n");
			}
			ePTeam.setText(read.toString());
			ePfinalteam.setText("");
			spinnerteam.setValue(teamlist[cBTeams.getSelectedIndex()].length - 1);
		});
		btnloadteams.setBounds(261, 508, 89, 23);
		panel.add(btnloadteams);

		add(panel);
	}

	void teamlist() {
		try {
			teamlist = client.Writer.read("teamlist");
			teamname = new String[teamlist.length];
			for (int i = 0; i < teamname.length; i++) {
				try {
					teamname[i] = teamlist[i][0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			teamname = new String[] { "Lese Error" };
		}
		cBTeams.setModel(new DefaultComboBoxModel<String>(teamname));
	}

	public Object[] getteam() {
		StringBuilder finalteam = new StringBuilder("");
		try {
			for (String k : spieler) {
				finalteam.append(k + ":");
			}
			finalteam.substring(0, finalteam.length() - 1);
		} catch (Exception e) {
			return new Object[] { Gui.getwindow().getPanelPlayer().spinnerteam.getValue().toString(),
					Gui.getwindow().getPanelPlayer().ePTeam.getText(), org.json.JSONObject.NULL };

		}
		return new String[] { Gui.getwindow().getPanelPlayer().spinnerteam.getValue().toString(),
				Gui.getwindow().getPanelPlayer().ePTeam.getText(), finalteam.toString() };
	}
}