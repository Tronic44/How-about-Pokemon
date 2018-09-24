package client;

import javax.swing.JOptionPane;

public class Manage {
	public static JOptionPane alert;
	
	public static void initPoketier() {
		for (int i = 0; i < Data.tierlist.length; i++) {
			Data.tierlist[i] = '0';
		}
	}

	public static void msgbox(String s) {
		JOptionPane.showMessageDialog(null, s, "Da ist was schief gelaufen", JOptionPane.PLAIN_MESSAGE);
	}

	public static void msgboxerf(String s) {
		JOptionPane.showMessageDialog(null, s, "Aktion erfolgreich", JOptionPane.PLAIN_MESSAGE);
	}

}
