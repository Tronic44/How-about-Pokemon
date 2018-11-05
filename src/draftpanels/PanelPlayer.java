package draftpanels;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.util.ArrayList;
import javax.swing.event.CaretListener;
import client.Manage;
import client.Writer;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class PanelPlayer extends JPanel {
	private JPanel panel = new JPanel();
	protected ArrayList<String> player = new ArrayList<>();
	protected String[] loadedteamnames;
	private String[][] loadedteamlist;
	protected JComboBox<String> cBTeams;
	private ArrayList<JTextField> teams = new ArrayList<>();
	private int hight = 39;
	private CaretListener clisten;
	private KeyAdapter enterpressed;
	private boolean safed = false;
	JCheckBox checkBoxSafe;

	public PanelPlayer() {

		panel.setBounds(0, 0, 409, 600);
		panel.setLayout(null);

		btn();

		enterpressed = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() == '\n') {
					try {
						teams.get(teams.indexOf(evt.getSource()) + 1).requestFocusInWindow();
					} catch (Exception e) {
					}
				}
				setSafe(false);
			}
		};

		clisten = new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (((JTextField) e.getSource()).getText().trim().length() > 0
						&& teams.indexOf(((JTextField) e.getSource())) < 15) {
					try {
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1);
					} catch (Exception f) {
						teams.add(new JTextField());
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).setBounds(129, hight, 151, 20);
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).addCaretListener(clisten);
						teams.get(teams.indexOf(((JTextField) e.getSource())) + 1).addKeyListener(enterpressed);
						panel.add(teams.get(teams.indexOf(((JTextField) e.getSource())) + 1));
						hight += 26;
						panel.revalidate();
						panel.updateUI();
						setSafe(false);
					}
				}
				DraftGui.getwindow().getPanelPlayer().revalidate();
			}
		};

		teams.add(new JTextField());
		teams.get(0).addCaretListener(clisten);
		teams.get(0).addKeyListener(enterpressed);
		teams.get(0).setBounds(129, hight, 151, 20);
		hight += 26;
		panel.add(teams.get(0));

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 495, 409, 3);
		panel.add(separator);

		checkBoxSafe = new JCheckBox("");
		checkBoxSafe.setEnabled(false);
		checkBoxSafe.setBounds(251, 461, 97, 23);
		panel.add(checkBoxSafe);

		add(panel);
	}

	private void removeEmptyFields() {
		for (int k = 0; k < teams.size() - 1; k++) {
			if (teams.get(k).getText().trim().length() < 1) {
				if (DraftGui.getwindow().isFinishdraft()) {
					Object[] options = { "Ja", "Nein" };
					if (JOptionPane.showOptionDialog(DraftGui.getwindow().getFrmPokemonDraft(),
							"Du bist im Begriff das Team:  " + player.get(k) + "  zu löschen" + "\n"
									+ "Möchtest du Fortfahren?",
							"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[1]) == 1) {
						return;
					}
					DraftGui.getwindow().getPanelDraft().removeFromDraft(k);
				}
				DraftGui.getwindow().getPanelPlayer().teams.get(k).setEnabled(false);
				DraftGui.getwindow().getPanelPlayer().teams.get(k).setBounds(0, 0, 0, 0);
				DraftGui.getwindow().getPanelPlayer().remove(teams.get(k));
				teams.remove(k);
				redrawteams();
				removeEmptyFields();

				return;
			}
		}
	}

	private void redrawteams() {
		hight = 39;
		for (int k = 0; k < teams.size(); k++) {
			teams.get(k).setBounds(129, hight, 151, 20);
			hight += 26;
		}
		DraftGui.getwindow().getPanelPlayer().updateUI();
		DraftGui.getwindow().getPanelPlayer().revalidate();
		DraftGui.getwindow().getPanelPlayer().repaint();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void btn() {
		JButton btnPlayer = new JButton("Bestätige");
		btnPlayer.addActionListener(e -> {
			removeEmptyFields();
			if (teams.size() < 2) {
				Manage.msgboxError("Du möchtest ohen Teams spielen?", DraftGui.getwindow().getFrmPokemonDraft());
				return;
			}
			player = new ArrayList<>();
			for (int k = 0; k < teams.size(); k++) {
				for (int i = 0; i < teams.size(); i++) {
					if (i != k && teams.get(k).getText().trim().equals(teams.get(i).getText().trim())) {
						Manage.msgboxError("Zwei Teams können nicht den Selben Namen haben!",
								DraftGui.getwindow().getFrmPokemonDraft());
						return;
					}
				}
				if (teams.get(k).getText().trim().length() > 0) {
					player.add(teams.get(k).getText().trim());
				}
			}
			DraftGui.getwindow().getPanelDraft().cBchangeteam
					.setModel(new DefaultComboBoxModel<String>(player.toArray(new String[0])));
			setSafe(true);
		});
		btnPlayer.setBounds(160, 461, 89, 23);
		panel.add(btnPlayer);

		JLabel lblTeamNamen = new JLabel("Team-Namen");
		lblTeamNamen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeamNamen.setBounds(151, 11, 106, 20);
		panel.add(lblTeamNamen);

		JTextField tFSafeTeams = new JTextField();
		tFSafeTeams.setColumns(10);
		tFSafeTeams.setBounds(59, 506, 106, 20);
		panel.add(tFSafeTeams);

		JButton btnsafeteams = new JButton("Speichern");
		btnsafeteams.addActionListener(e -> {
			if (!safed) {
				Manage.msgboxError("Du hast keine Teams bestätigt", DraftGui.getwindow().getFrmPokemonDraft());
				return;
			}
			if (tFSafeTeams.getText().equals("") || tFSafeTeams.getText().equals("Gespeichert")) {
				Manage.msgboxError("Du hast keinen Namen eingegeben", DraftGui.getwindow().getFrmPokemonDraft());
				tFSafeTeams.setText("");
				return;
			}
			if (tFSafeTeams.getText().contains(":")) {
				Manage.msgboxError("Der Name das keinen Doppelpunkt enthalten",
						DraftGui.getwindow().getFrmPokemonDraft());
				return;
			}
			for (String k : loadedteamnames) {
				if (tFSafeTeams.getText().equals(k)) {
					Manage.msgboxError("Der Name existiert schon", DraftGui.getwindow().getFrmPokemonDraft());
					return;
				}
				if (k.equals("Lese Error")) {
					return;
				}
			}
			String[] safenames = new String[teams.size()];
			for (int k = 0; k < teams.size(); k++) {
				safenames[k] = teams.get(k).getText().trim();
			}
			Writer.print("teamlist", tFSafeTeams.getText(), safenames);
			tFSafeTeams.setText("Gespeichert");
			reloadTeamlist();
		});
		btnsafeteams.setBounds(59, 541, 109, 23);
		panel.add(btnsafeteams);

		cBTeams = new JComboBox(new String[] {});
		cBTeams.setBounds(224, 502, 124, 28);
		panel.add(cBTeams);

		JButton btnloadteams = new JButton("Laden");
		btnloadteams.addActionListener(e -> {
			for (JTextField k : teams) {
				k.setText("");
			}
			removeEmptyFields();
			for (int k = 1; k < loadedteamlist[cBTeams.getSelectedIndex()].length; k++) {
				teams.get(k - 1).setText(loadedteamlist[cBTeams.getSelectedIndex()][k]);
				teams.get(k - 1).setCaretPosition(1);
			}
			btnPlayer.doClick();
		});
		btnloadteams.setBounds(242, 541, 89, 23);
		panel.add(btnloadteams);
	}

	protected void reloadTeamlist() {
		try {
			loadedteamlist = client.Writer.read("teamlist");
			loadedteamnames = new String[loadedteamlist.length];
			for (int i = 0; i < loadedteamnames.length; i++) {
				loadedteamnames[i] = loadedteamlist[i][0];
			}
		} catch (Exception e) {
			loadedteamnames = new String[] { "Lese Error" };
		}
		cBTeams.setModel(new DefaultComboBoxModel<String>(loadedteamnames));
	}

	public boolean isSafed() {
		return safed;
	}

	private void setSafe(boolean b) {
		safed = b;
		checkBoxSafe.setSelected(b);
	}
}