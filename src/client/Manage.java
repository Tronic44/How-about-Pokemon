package client;

import java.awt.Frame;
import javax.swing.JOptionPane;

public class Manage {
	public static JOptionPane alert;

	public static void main(String[] args) {

		MainMenu.startMainMenu();

	}

	public static void initPoketier() {
		for (int i = 0; i < Data.tierlist.length; i++) {
			Data.tierlist[i] = '0';
		}
	}

	public static void msgbox(String s, Frame f) {
		JOptionPane.showMessageDialog(f, s, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}
	public static void msgboxerr(String s) {
		JOptionPane.showMessageDialog(null, s, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}


	public static void msgboxerf(String s, Frame f) {
		JOptionPane.showMessageDialog(f, s, "Aktion erfolgreich", JOptionPane.INFORMATION_MESSAGE);
	}

	public static int msgfertig(Frame f) {
		Object[] options = {  "BANNEN", "In das untersete Tier einfügen", "Selbst einordnen" };
		return JOptionPane.showOptionDialog(f, "Du hast noch nicht allen Pokenmon einen Tier zugewiesen, was möchtest du tun? "+"\n"+"Alle nicht zugewisenen:",
				"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[2]);
	}
}
