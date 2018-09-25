package client;

import java.awt.Frame;
import javax.swing.JOptionPane;

public class Manage {
	protected static JOptionPane alert;

	protected static void main(String[] args) {
		MainMenu.startMainMenu();

	}

	protected static void initPoketier() {
		for (int i = 0; i < Data.tierlist.length; i++) {
			Data.tierlist[i] = '0';
		}
	}

	protected static void msgbox(String nachricht, Frame frame) {
		JOptionPane.showMessageDialog(frame, nachricht, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}

	protected static void msgboxerr(String nachricht) {
		JOptionPane.showMessageDialog(null, nachricht, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}

	protected static void msgboxerf(String nachricht, Frame frame) {
		JOptionPane.showMessageDialog(frame, nachricht, "Aktion erfolgreich", JOptionPane.INFORMATION_MESSAGE);
	}

}
