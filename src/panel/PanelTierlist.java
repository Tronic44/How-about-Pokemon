package panel;

import java.awt.Font;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import client.Data;
import client.Manage;
import client.Writer;

@SuppressWarnings("serial")
public class PanelTierlist extends JPanel {

	private JPanel panel = new JPanel();
	protected JRadioButton radioButtonS;
	protected JRadioButton radioButtonA;
	protected JRadioButton radioButtonB;
	protected JRadioButton radioButtonC;
	protected JRadioButton radioButtonD;
	protected JRadioButton radioButtonE;
	protected JRadioButton radioButtonX;
	protected JRadioButton radioButtonnull;
	private ButtonGroup tierlistbuttongruppe;
	private String[][] tierlist;
	private String[] tiernamen;
	private JTextField tFsearch;
	private List list;
	private JTextField tFPoke;
	private JTextField tFTiername;
	private JComboBox<String> cBTierlist;

	public PanelTierlist() {

		panel.setBounds(0, 0, 409, 640);
		panel.setLayout(null);

		JLabel lblPokemon = new JLabel("Pokemon");
		lblPokemon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPokemon.setBounds(20, 0, 73, 28);
		panel.add(lblPokemon);

		list = new List();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				changetier();
			}
		});
		list.setBounds(20, 33, 197, 516);
		panel.add(list);

		tFPoke = new JTextField();
		tFPoke.setEditable(false);
		tFPoke.setBounds(248, 56, 124, 20);
		panel.add(tFPoke);

		tierlistbuttongruppe = new ButtonGroup();

		radioButtonS = new JRadioButton("S");
		radioButtonS.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'S');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonS.setBounds(256, 101, 109, 23);
		panel.add(radioButtonS);
		tierlistbuttongruppe.add(radioButtonS);

		radioButtonA = new JRadioButton("A");
		radioButtonA.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'A');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonA.setBounds(256, 127, 109, 23);
		panel.add(radioButtonA);
		tierlistbuttongruppe.add(radioButtonA);

		radioButtonB = new JRadioButton("B");
		radioButtonB.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'B');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonB.setBounds(256, 151, 109, 23);
		panel.add(radioButtonB);
		tierlistbuttongruppe.add(radioButtonB);

		radioButtonC = new JRadioButton("C");
		radioButtonC.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'C');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonC.setBounds(256, 177, 109, 23);
		panel.add(radioButtonC);
		tierlistbuttongruppe.add(radioButtonC);

		radioButtonD = new JRadioButton("D");
		radioButtonD.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'D');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonD.setBounds(256, 203, 109, 23);
		panel.add(radioButtonD);
		tierlistbuttongruppe.add(radioButtonD);

		radioButtonX = new JRadioButton("Banned");
		radioButtonX.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'X');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonX.setBounds(256, 255, 109, 23);
		panel.add(radioButtonX);
		tierlistbuttongruppe.add(radioButtonX);

		radioButtonnull = new JRadioButton("");
		radioButtonnull.setEnabled(false);
		radioButtonnull.setBounds(256, 286, 109, 23);
		panel.add(radioButtonnull);
		tierlistbuttongruppe.add(radioButtonnull);

		JButton btnsafetierlist = new JButton("Speichern");
		btnsafetierlist.addActionListener(e -> {
			if (tFTiername.getText().equals("") || tFTiername.getText().equals("Gespeichert")) {
				Manage.msgbox("Du hast keinen Namen eingegeben", Gui.getwindow().getFrmPokemonDraft());
				tFTiername.setText("");
			} else {
				if (tFTiername.getText().contains(":")) {
					Manage.msgbox("Der Name das keinen Doppelpunkt enthalten", Gui.getwindow().getFrmPokemonDraft());

				} else {
					Boolean b = true;
					for (String k : tiernamen) {
						if (tFTiername.getText().equals(k)) {
							Manage.msgbox("Der Name existiert schon", Gui.getwindow().getFrmPokemonDraft());
							b = false;
							break;
						}
					}
					if (b) {
						Writer.print("tierlist", tFTiername.getText(), Data.getTierlist());
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
			Data.setTierlist(tierlist[cBTierlist.getSelectedIndex()][1].toCharArray());
			list.select(0);
			changetier();
		});
		btnloadtier.setBounds(266, 486, 89, 23);
		panel.add(btnloadtier);

		tFTiername = new JTextField();
		tFTiername.setBounds(267, 345, 86, 20);
		panel.add(tFTiername);

		radioButtonE = new JRadioButton("E");
		radioButtonE.addActionListener(e -> {
			Data.editTierlist(list.getSelectedIndex(), 'E');
			if (list.getSelectedIndex() < Data.getPokedex().length)
				list.select(list.getSelectedIndex() + 1);
			changetier();
		});
		radioButtonE.setBounds(256, 229, 109, 23);
		panel.add(radioButtonE);
		radioButtonnull.setVisible(false);
		tierlistbuttongruppe.add(radioButtonE);

		tFsearch = new JTextField();
		tFsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String search = null;
				try {
					search = Data.getPokedexlist().stream()
							.filter(element -> element.toLowerCase().contains(tFsearch.getText().toLowerCase()))
							.findFirst().get().toString();
				} catch (Exception e) {
				}
				if (search != null) {
					list.select(Data.getPokedex(search));
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void tierlist() {
		try {
			tierlist = client.Writer.read("tierlist");
		} catch (Exception e) {
		}
		try {
			tiernamen = new String[tierlist.length];
			for (int i = 0; i < tiernamen.length; i++) {
				try {
					tiernamen[i] = tierlist[i][0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			tiernamen = new String[] { "Lese Error" };
		}
		try {
			Gui.getwindow().getPanelTierlist().remove(cBTierlist);
		} catch (Exception e) {

		}
		cBTierlist = new JComboBox(tiernamen);
		cBTierlist.setBounds(248, 447, 124, 28);
		try {
			Gui.getwindow().getPanelTierlist().add(cBTierlist);
		} catch (Exception e) {
			panel.add(cBTierlist);
		}
	}

	protected void changetier() {
		tFPoke.setText(list.getSelectedItem());
		switch (Data.getTierlist(list.getSelectedIndex())) {
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

	protected void opentierlist() {
		list.removeAll();
		for (int i = 0; i < Data.getPokedex().length; i++) {
			list.add(Data.getPokedex(i));
		}
		list.select(0);
		changetier();
		tierlist();
		Gui.getwindow().visTierlist();
	}

	protected void settiernamen(int k, String eintrag) {
		if (k < tiernamen.length) {
			tiernamen[k] = eintrag;
		}
	}

	protected String gettiernamen(int k) {
		if (k < tiernamen.length) {
			return tiernamen[k];
		}
		return null;
	}
}