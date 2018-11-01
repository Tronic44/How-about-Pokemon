package draftpanels;

import java.awt.Font;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import client.Manage;
import client.Writer;

@SuppressWarnings("serial")
public class PanelTierlist extends JPanel {

	private JPanel panel = new JPanel();
	private JRadioButton radioButtonS;
	private JRadioButton radioButtonA;
	private JRadioButton radioButtonB;
	private JRadioButton radioButtonC;
	private JRadioButton radioButtonD;
	private JRadioButton radioButtonE;
	private JRadioButton radioButtonX;
	private JRadioButton radioButtonnull;
	private ButtonGroup tierlistbuttongruppe;
	private String[][] tierlist;
	private String[] safedNamen;
	private String[] tiernamen = new String[] { "S", "A", "B", "C", "D", "E" };
	private JTextField tFsearch;
	private List pokemonListe;
	private JTextField tFPoke;
	private JTextField tFTiername;
	private JComboBox<String> cBTierlist = new JComboBox<>();

	public PanelTierlist() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblPokemon = new JLabel("Pokemon");
		lblPokemon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPokemon.setBounds(20, 0, 73, 28);
		panel.add(lblPokemon);

		pokemonListe = new List();
		pokemonListe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				changeTier();
			}
		});
		pokemonListe.setBounds(20, 33, 197, 516);
		panel.add(pokemonListe);

		tFPoke = new JTextField();
		tFPoke.setEditable(false);
		tFPoke.setBounds(248, 56, 124, 20);
		panel.add(tFPoke);

		tierlistbuttongruppe = new ButtonGroup();

		radioButtonS = new JRadioButton(tiernamen[0]);
		radioButtonS.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'S');
			if (!Gui.getwindow().isFinishdraft() && pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonS.setBounds(256, 101, 109, 23);
		panel.add(radioButtonS);
		tierlistbuttongruppe.add(radioButtonS);

		radioButtonA = new JRadioButton(tiernamen[1]);
		radioButtonA.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'A');
			if (!Gui.getwindow().isFinishdraft() &&pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonA.setBounds(256, 127, 109, 23);
		panel.add(radioButtonA);
		tierlistbuttongruppe.add(radioButtonA);

		radioButtonB = new JRadioButton(tiernamen[2]);
		radioButtonB.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'B');
			if (!Gui.getwindow().isFinishdraft() &&pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonB.setBounds(256, 151, 109, 23);
		panel.add(radioButtonB);
		tierlistbuttongruppe.add(radioButtonB);

		radioButtonC = new JRadioButton(tiernamen[3]);
		radioButtonC.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'C');
			if (!Gui.getwindow().isFinishdraft() &&pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonC.setBounds(256, 177, 109, 23);
		panel.add(radioButtonC);
		tierlistbuttongruppe.add(radioButtonC);

		radioButtonD = new JRadioButton(tiernamen[4]);
		radioButtonD.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'D');
			if (!Gui.getwindow().isFinishdraft() &&pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonD.setBounds(256, 203, 109, 23);
		panel.add(radioButtonD);
		tierlistbuttongruppe.add(radioButtonD);

		radioButtonE = new JRadioButton(tiernamen[5]);
		radioButtonE.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'E');
			if (!Gui.getwindow().isFinishdraft() &&pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonE.setBounds(256, 229, 109, 23);
		panel.add(radioButtonE);
		tierlistbuttongruppe.add(radioButtonE);

		radioButtonX = new JRadioButton("Banned");
		radioButtonX.addActionListener(e -> {
			data.PokemonDraft.editTierlist(pokemonListe.getSelectedIndex(), 'X');
			if (!Gui.getwindow().isFinishdraft() &&pokemonListe.getSelectedIndex() < data.PokemonDraft.getPokedex().length)
				pokemonListe.select(pokemonListe.getSelectedIndex() + 1);
			changeTier();
			Gui.getwindow().getPanelDraft().updateTierPokemon();
		});
		radioButtonX.setBounds(256, 255, 109, 23);
		panel.add(radioButtonX);
		tierlistbuttongruppe.add(radioButtonX);

		radioButtonnull = new JRadioButton("");
		radioButtonnull.setEnabled(false);
		radioButtonnull.setBounds(256, 286, 109, 23);
		radioButtonnull.setVisible(false);
		panel.add(radioButtonnull);
		tierlistbuttongruppe.add(radioButtonnull);

		JButton btnsafetierlist = new JButton("Speichern");
		btnsafetierlist.addActionListener(e -> {
			if (tFTiername.getText().equals("") || tFTiername.getText().equals("Gespeichert")) {
				Manage.msgboxError("Du hast keinen Namen eingegeben", Gui.getwindow().getFrmPokemonDraft());
				tFTiername.setText("");
			} else {
				if (tFTiername.getText().contains(":")) {
					Manage.msgboxError("Der Name das keinen Doppelpunkt enthalten",
							Gui.getwindow().getFrmPokemonDraft());

				} else {
					Boolean b = true;
					for (String k : safedNamen) {
						if (tFTiername.getText().equals(k)) {
							Manage.msgboxError("Der Name existiert schon", Gui.getwindow().getFrmPokemonDraft());
							b = false;
							break;
						}
					}
					if (b) {
						Writer.print("tierlist", tFTiername.getText(), data.PokemonDraft.getTierlist());
						tFTiername.setText("Gespeichert");
						panel.remove(cBTierlist);
						tierlist();
					}
				}
			}
		});
		btnsafetierlist.setBounds(266, 376, 89, 23);
		panel.add(btnsafetierlist);

		tierlist();

		JButton btnloadtier = new JButton("Laden");
		btnloadtier.addActionListener(e -> {
			data.PokemonDraft.setTierlist(tierlist[cBTierlist.getSelectedIndex()][1].toCharArray());
			pokemonListe.select(0);
			changeTier();
		});
		btnloadtier.setBounds(266, 486, 89, 23);
		panel.add(btnloadtier);

		tFTiername = new JTextField();
		tFTiername.setBounds(267, 345, 86, 20);
		panel.add(tFTiername);

		tFsearch = new JTextField();
		tFsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String search = null;
				try {
					search = data.PokemonDraft.getPokedexlist().stream()
							.filter(element -> element.toLowerCase().contains(tFsearch.getText().toLowerCase()))
							.findFirst().get();
				} catch (Exception e) {
					search = null;
				}
				if (search != null) {
					pokemonListe.select(data.PokemonDraft.getPokedex(search));
				}
			}
		});
		tFsearch.setBounds(86, 555, 109, 20);
		panel.add(tFsearch);
		tFsearch.setColumns(10);

		JLabel lblSuche = new JLabel("Suche:");
		lblSuche.setBounds(30, 558, 46, 14);
		panel.add(lblSuche);

		add(panel);
	}

	protected void tierlist() {
		try {
			tierlist = client.Writer.read("tierlist");
			safedNamen = new String[tierlist.length];
			for (int i = 0; i < safedNamen.length; i++) {
				safedNamen[i] = tierlist[i][0];
			}
		} catch (Exception e) {
			safedNamen = new String[] { "Lese Error" };
		}
		cBTierlist = new JComboBox<>();
		cBTierlist.setModel(new DefaultComboBoxModel<String>(safedNamen));
		cBTierlist.setBounds(248, 447, 124, 28);
		try {
			Gui.getwindow().getPanelTierlist().add(cBTierlist);
		} catch (Exception e) {
			panel.add(cBTierlist);
		}
	}

	protected void resetRadioButtons() {
		radioButtonS.setEnabled(true);
		radioButtonA.setEnabled(true);
		radioButtonB.setEnabled(true);
		radioButtonC.setEnabled(true);
		radioButtonD.setEnabled(true);
		radioButtonE.setEnabled(true);
		tiernamen = new String[] { "S", "A", "B", "C", "D", "E" };
		radioButtonS.setText(tiernamen[0]);
		radioButtonA.setText(tiernamen[1]);
		radioButtonB.setText(tiernamen[2]);
		radioButtonC.setText(tiernamen[3]);
		radioButtonD.setText(tiernamen[4]);
		radioButtonE.setText(tiernamen[5]);
	}

	protected void changeTier() {
		tFPoke.setText(pokemonListe.getSelectedItem());
		switch (data.PokemonDraft.getTierlist(pokemonListe.getSelectedIndex())) {
		case 'S':
			radioButtonS.setSelected(true);
			break;
		case 'A':
			radioButtonA.setSelected(true);
			break;
		case 'B':
			radioButtonB.setSelected(true);
			break;
		case 'C':
			radioButtonC.setSelected(true);
			break;
		case 'D':
			radioButtonD.setSelected(true);
			break;
		case 'E':
			radioButtonE.setSelected(true);
			break;
		case 'X':
			radioButtonX.setSelected(true);
			break;
		case '0':
			radioButtonnull.setSelected(true);
			break;
		default:
			radioButtonnull.setSelected(true);
			break;
		}
	}

	protected void openTierlist() {
		pokemonListe.removeAll();
		for (int i = 0; i < data.PokemonDraft.getPokedex().length; i++) {
			pokemonListe.add(data.PokemonDraft.getPokedex(i));
		}
		pokemonListe.select(0);
		changeTier();
		tierlist();
		Gui.getwindow().visTierlist();
	}

	protected int setRadioButton(boolean[] b, String[] st) {
		JRadioButton[] jRB = new JRadioButton[] { radioButtonS, radioButtonA, radioButtonB, radioButtonC, radioButtonD,
				radioButtonE };
		int count = 0;
		for (int k = 0; k < b.length; k++) {
			jRB[k].setEnabled(b[k]);
			if (b[k]) {
				setTiernamen(k, st[k]);
			} else {
				setTiernamen(k, "");
				count++;
			}
		}
		return count;
	}

	protected void setTiernamen(int k, String text) {
		JRadioButton[] jRB = new JRadioButton[] { radioButtonS, radioButtonA, radioButtonB, radioButtonC, radioButtonD,
				radioButtonE };
		jRB[k].setText(text);
		tiernamen[k] = text;
	}

	protected String getTiernamen(int k) {
		if (k < tiernamen.length) {
			return tiernamen[k];
		}
		return null;
	}
}