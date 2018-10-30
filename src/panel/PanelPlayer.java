package panel;

import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import client.Manage;
import client.Writer;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.util.ArrayList;
import javax.swing.event.CaretListener;
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
	private int hight = 39;
	private CaretListener clisten = new CaretListener() {
		public void caretUpdate(CaretEvent e) {
			if (((JTextField) e.getSource()).getText().trim().length() > 0
					&& teams.indexOf(((JTextField) e.getSource())) < 15) {
				try {

					teams.get(teams.indexOf(((JTextField) e.getSource())) + 1);
				} catch (Exception f) {
					teams.add(new JTextField());
					System.out.print(teams.size() + " ");
					hight += 26;
					teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).setBounds(129, hight, 151, 20);
					teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).addCaretListener(clisten);
					panel.add(teams.get(teams.indexOf(((JTextField) e.getSource())) + 1));
					panel.revalidate();
					panel.updateUI();
				}
			}
			Gui.getwindow().getPanelPlayer().revalidate();
		}
	};
	private JTextField nextTextField;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelPlayer() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);
//		btn();

//		JCheckBox checkbox1 = new JCheckBox("");
//		checkbox1.setEnabled(false);
//		checkbox1.setBounds(88, 38, 21, 23);
//		panel.add(checkbox1);

		teams.add(new JTextField());
		teams.get(0).addCaretListener(clisten);
		teams.get(0).setBounds(129, hight, 151, 20);
		panel.add(teams.get(0));

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 560, 409, 3);
		panel.add(separator);

//		JCheckBox checkBox = new JCheckBox("");
//		checkBox.setEnabled(false);
//		checkBox.setBounds(88, 64, 21, 23);
//		panel.add(checkBox);

//		nextTextField = new JTextField();
//		nextTextField.setEnabled(false);
//		nextTextField.setColumns(10);
//		nextTextField.setBounds(129, 65, 151, 20);
//		panel.add(nextTextField);

		add(panel);

	}

//	private void btn() {
//		JButton btnPlayer = new JButton("Bestätige");
//		btnPlayer.addActionListener(e -> {
//			String[] eingabe = ePTeam.getText().split("\n");
//			int count = 0;
//			for (int i = 0; i < eingabe.length; i++) {
//				if (eingabe[i].trim().length() > 1) {
//					count++;
//				}
//			}
//			if ((int) spinnerteam.getValue() == 0) {
//				Manage.msgboxError("Du willst doch nicht ohne auch nur ein Team spielen, oder?",
//						Gui.getwindow().getFrmPokemonDraft());
//			} else {
//				if (count != (int) spinnerteam.getValue()) {
//					Manage.msgboxError(
//							"Deine Liste Stimmt nicht mit der Team Anzahl überein" + "\n"
//									+ "Denk dran: Teams haben niemals nur einen Buchstaben!",
//							Gui.getwindow().getFrmPokemonDraft());
//				} else {
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
//				}
//			}
//		});
//		btnPlayer.setBounds(160, 526, 89, 23);
//		panel.add(btnPlayer);
//
//		JLabel lblTeamNamen = new JLabel("Team-Namen");
//		lblTeamNamen.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblTeamNamen.setBounds(151, 11, 106, 20);
//		panel.add(lblTeamNamen);
//
//		tFSafeTeams = new JTextField();
//		tFSafeTeams.setColumns(10);
//		tFSafeTeams.setBounds(59, 571, 106, 20);
//		panel.add(tFSafeTeams);
//
//		JButton btnsafeteams = new JButton("Speichern");
//		btnsafeteams.addActionListener(e -> {
//
//			if (ePfinalteam.getText().length() < 1) {
//				Manage.msgboxError("Du hast keine Teams bestätigt", Gui.getwindow().getFrmPokemonDraft());
//				return;
//			}
//			if (tFTeams.getText().equals("") || tFTeams.getText().equals("Gespeichert")) {
//				Manage.msgboxError("Du hast keinen Namen eingegeben", Gui.getwindow().getFrmPokemonDraft());
//				tFTeams.setText("");
//			} else {
//				if (tFTeams.getText().contains(":")) {
//					Manage.msgboxError("Der Name das keinen Doppelpunkt enthalten",
//							Gui.getwindow().getFrmPokemonDraft());
//				} else {
//					if (ePfinalteam.getText().length() > 2) {
//						Boolean b = true;
//						for (String k : teamname) {
//							if (tFTeams.getText().equals(k)) {
//								Manage.msgboxError("Der Name existiert schon", Gui.getwindow().getFrmPokemonDraft());
//								b = false;
//								break;
//							}
//							if (k.equals("Lese Error")) {
//								return;
//							}
//						}
//						if (b) {
//							Writer.print("teamlist", tFTeams.getText(), teamname);
//							tFTeams.setText("Gespeichert");
//							panel.remove(cBTeams);
//							teamlist();
//						}
//					}
//
//				}
//			}
//		});
//		btnsafeteams.setBounds(59, 606, 109, 23);
//		panel.add(btnsafeteams);
//
//		cBTeams = new JComboBox(new Object[] {});
//		cBTeams.setBounds(224, 567, 124, 28);
//		panel.add(cBTeams);
//
//		JButton btnloadteams = new JButton("Laden");
//		btnloadteams.addActionListener(e -> {
//			StringBuilder read = new StringBuilder("");
//			for (int k = 1; k < teamlist[cBTeams.getSelectedIndex()].length; k++) {
//				read.append(teamlist[cBTeams.getSelectedIndex()][k] + "\n");
//			}
//			ePTeam.setText(read.toString());
//			ePfinalteam.setText("");
//			spinnerteam.setValue(teamlist[cBTeams.getSelectedIndex()].length - 1);
//		});
//		btnloadteams.setBounds(242, 606, 89, 23);
//		panel.add(btnloadteams);
//	}

	protected void teamlist() {
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