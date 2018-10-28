package client;

import java.awt.Frame;

import javax.swing.JOptionPane;

/**
 * Die Klasse Manage stellt die Main-Methode und soll bzw wird als Schnittstelle
 * zu der Gui dienen. Stellt alle AlertPanels.
 * 
 * @author Yannick Dreher
 *
 */
public class Manage {
	/**
	 * Stellt die Schrfitart einheitlich zur Verfügung
	 */
	public static final String FONT = "Tahoma";

	/**
	 * Main Methode des PokemonDrafts. Startet das Programm bzw die GUI.
	 * 
	 * @param args - keine Verwendung bisher
	 */
	public static void main(String[] args) {
		panel.Gui.startMainMenu();
	}

	/**
	 * Eine Error AlertBox mit dem Titel: "Da ist was schief gelaufen"
	 * 
	 * @param nachricht String - Die anzuzeigende Nachricht
	 * @param frame     - den Frame, von dem aus der Aufruf kommt, wird dazu genutzt
	 *                  den Alert zentriert anzuzeigen
	 */
	public static void msgbox(String nachricht, Frame frame) {
		JOptionPane.showMessageDialog(frame, nachricht, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Eine Error AlertBox mit dem Titel: "Da ist was schief gelaufen". Diesmal ohne
	 * Frame.
	 * 
	 * @param nachricht String - Die anzuzeigende Nachricht
	 */
	protected static void msgboxerr(String nachricht) {
		JOptionPane.showMessageDialog(null, nachricht, "Da ist was schief gelaufen", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Eine Info AlertBox mit dem Titel: "Aktion erfolgreich"
	 * 
	 * @param nachricht String - Die anzuzeigende Nachricht
	 * @param frame     - den Frame, von dem aus der Aufruf kommt, wird dazu genutzt
	 *                  den Alert zentriert anzuzeigen
	 */
	public static void msgboxerf(String nachricht, Frame frame) {
		JOptionPane.showMessageDialog(frame, nachricht, "Aktion erfolgreich", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Gibt ein StringArray mit den Zahlen 1 bis der eingegebenen length
	 * 
	 * @param length die Länge des zurückgegebenes Arrays
	 * @return StringArray der Länge length mit den Zahlen 1 bis length
	 */
	public static String[] getaarray(int length) {
		if (length <= 0) {
			return new String[] {};
		}
		if (length > 15) {
			length = 15;
		}
		String[] a = new String[length];
		for (int k = 0; k < a.length; k++) {
			a[k] = String.valueOf(k + 1);
		}
		return a;
	}
}