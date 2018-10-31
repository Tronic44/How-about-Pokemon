package panel;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.util.ArrayList;
import javax.swing.event.CaretListener;

import client.Manage;
import client.Writer;

import javax.swing.event.CaretEvent;

@SuppressWarnings("serial")
public class PanelPlayer extends JPanel {
	private JPanel panel = new JPanel();
	protected String[] player;
	protected String[] teamname;
	private JTextField tFSafeTeams;
	protected JComboBox<String> cBTeams;
	private String[][] teamlist;
	private ArrayList<JTextField> teams = new ArrayList<>();
	private ArrayList<JCheckBox> checkteams = new ArrayList<>();
	private int hight = 39;
	private CaretListener clisten;

	public PanelPlayer() {

		panel.setBounds(0, 0, 409, 600);
		panel.setLayout(null);

		btn();

		clisten = new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (((JTextField) e.getSource()).getText().trim().length() > 0
						&& teams.indexOf(((JTextField) e.getSource())) < 15) {
					try {
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1);
					} catch (Exception f) {
						teams.add(new JTextField());
						hight += 26;
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).setBounds(129, hight, 151, 20);
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).addCaretListener(clisten);
						panel.add(teams.get(teams.indexOf(((JTextField) e.getSource())) + 1));
						checkteams.add(new JCheckBox());
						checkteams.get(teams.indexOf(((JTextField) e.getSource())) + 1).setBounds(88, hight, 21, 23);
						checkteams.get(teams.indexOf(((JTextField) e.getSource())) + 1).setEnabled(false);
						panel.add(checkteams.get(teams.indexOf(((JTextField) e.getSource())) + 1));

						panel.revalidate();
						panel.updateUI();
					}
				}
				Gui.getwindow().getPanelPlayer().revalidate();
			}
		};

		teams.add(new JTextField());
		teams.get(0).addCaretListener(clisten);
		teams.get(0).setBounds(129, hight, 151, 20);
		panel.add(teams.get(0));

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 495, 409, 3);
		panel.add(separator);

		checkteams.add(new JCheckBox(""));
		checkteams.get(0).setEnabled(false);
		checkteams.get(0).setBounds(88, 38, 21, 23);
		panel.add(checkteams.get(0));

		add(panel);
	}

	private void btn() {
		JButton btnPlayer = new JButton("Bestätige");
		btnPlayer.addActionListener(e -> {
//			String[] eingabe = ePTeam.getText().split("\n");
//			int count = 0;
//			for (int i = 0; i < eingabe.length; i++) {
//				if (eingabe[i].trim().length() > 1) {
//					count++;
//				}
//			}
//					spieler = new String[count];
//					for (int k = 0; k < spieler.length; k++) {
//						for (int i = k; i < eingabe.length; i++) {
//							if (eingabe[i].trim().length() > 1) {
//								if (k > 0 && eingabe[i].trim().equals(spieler[k - 1])) {
//									continue;
//								}
//								spieler[k] = eingabe[i].trim();
//								break;
//							}
//						}
//					}
//					teamname = new String[count];
//					StringBuilder list = new StringBuilder("");
//					for (int k = 0; k < spieler.length; k++) {
//						player = new String[spieler.length];
//						player[k] = spieler[k];
//						teamname[k] = player[k] + ":";
//						list.append((k + 1) + "   " + player[k] + "\n");
//					}
//					teamname[teamname.length - 1] = teamname[teamname.length - 1].substring(0,
//							teamname[teamname.length - 1].length() - 1);
//					ePfinalteam.setText(list.toString());
//					Gui.getwindow().getPanelStartDraft().enablebtnReihenfolge();
		});
		btnPlayer.setBounds(160, 461, 89, 23);
		panel.add(btnPlayer);

		JLabel lblTeamNamen = new JLabel("Team-Namen");
		lblTeamNamen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeamNamen.setBounds(151, 11, 106, 20);
		panel.add(lblTeamNamen);

		tFSafeTeams = new JTextField();
		tFSafeTeams.setColumns(10);
		tFSafeTeams.setBounds(59, 506, 106, 20);
		panel.add(tFSafeTeams);

		JButton btnsafeteams = new JButton("Speichern");
		btnsafeteams.addActionListener(e -> {

			Manage.msgboxError("Du hast keine Teams bestätigt", Gui.getwindow().getFrmPokemonDraft());
			if (tFSafeTeams.getText().equals("") || tFSafeTeams.getText().equals("Gespeichert")) {
				Manage.msgboxError("Du hast keinen Namen eingegeben", Gui.getwindow().getFrmPokemonDraft());
				tFSafeTeams.setText("");
			} else {
				if (tFSafeTeams.getText().contains(":")) {
					Manage.msgboxError("Der Name das keinen Doppelpunkt enthalten",
							Gui.getwindow().getFrmPokemonDraft());
				} else {
					Boolean b = true;
					for (String k : teamname) {
						if (tFSafeTeams.getText().equals(k)) {
							Manage.msgboxError("Der Name existiert schon", Gui.getwindow().getFrmPokemonDraft());
							b = false;
							break;
						}
						if (k.equals("Lese Error")) {
							return;
						}
					}
					if (b) {
						Writer.print("teamlist", tFSafeTeams.getText(), teamname);
						tFSafeTeams.setText("Gespeichert");
						panel.remove(cBTeams);
						teamlist();
					}

				}
			}
		});
		btnsafeteams.setBounds(59, 541, 109, 23);
		panel.add(btnsafeteams);

		cBTeams = new JComboBox(new Object[] {});
		cBTeams.setBounds(224, 502, 124, 28);
		panel.add(cBTeams);

		JButton btnloadteams = new JButton("Laden");
		btnloadteams.addActionListener(e -> {
//			StringBuilder read = new StringBuilder("");
//			for (int k = 1; k < teamlist[cBTeams.getSelectedIndex()].length; k++) {
//				read.append(teamlist[cBTeams.getSelectedIndex()][k] + "\n");
//			}
//			ePTeam.setText(read.toString());
//			spinnerteam.setValue(teamlist[cBTeams.getSelectedIndex()].length - 1);
		});
		btnloadteams.setBounds(242, 541, 89, 23);
		panel.add(btnloadteams);
	}

	protected void teamlist() {
		try {
			teamlist = client.Writer.read("teamlist");
			teamname = new String[teamlist.length];
			for (int i = 0; i < teamname.length; i++) {
				try {
					teamname[i] = teamlist[i][0];
				} catch (Exception e) {
//					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			teamname = new String[] { "Lese Error" };
		}
		cBTeams.setModel(new DefaultComboBoxModel<String>(teamname));
	}

//	public Object[] getTeam() {
//		StringBuilder finalteam = new StringBuilder("");
//		try {
//			for (String k : spieler) {
//				finalteam.append(k + ":");
//			}
//			finalteam.substring(0, finalteam.length() - 1);
//		} catch (Exception e) {
//			return new Object[] { Gui.getwindow().getPanelPlayer().spinnerteam.getValue().toString(),
//					Gui.getwindow().getPanelPlayer().ePTeam.getText(), org.json.JSONObject.NULL };
//
//		}
//		return new String[] { Gui.getwindow().getPanelPlayer().spinnerteam.getValue().toString(),
//				Gui.getwindow().getPanelPlayer().ePTeam.getText(), finalteam.toString() };
//	}
}