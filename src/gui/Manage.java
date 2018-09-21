package gui;

import javax.swing.JOptionPane;

public class Manage {

	public static void initPoketier() {
		for (int i = 0; i < data.Data.tierlist.length; i++) {
			data.Data.tierlist[i] = '0';
		}
	}

	public static void msgbox(String s) {
		gui.MainMenu.alert.showMessageDialog(null, s, "Da ist was schief gelaufen", JOptionPane.PLAIN_MESSAGE);
	}

}
