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

	public static void msgbox(String nachricht, Frame frame) {
		JOptionPane.showMessageDialog(frame, nachricht, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}

	public static void msgboxerr(String nachricht) {
		JOptionPane.showMessageDialog(null, nachricht, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}

	public static void msgboxerf(String nachricht, Frame frame) {
		JOptionPane.showMessageDialog(frame, nachricht, "Aktion erfolgreich", JOptionPane.INFORMATION_MESSAGE);
	}

}
