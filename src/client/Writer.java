package client;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.json.*;
import panel.Gui;

/**
 * Die Klasse Writer ist die Schnittstelle zwischen Programm und Datein. Sie
 * dient dem Speichnern von Programminhalten entweder als .txt Datei oder .json.
 * Desweiternen liest sie diese Dateien ein überprüft sie auf Fehler und gibt
 * sie an das Programm weiter, um das wiederherstellen von vorherigen
 * einstellungen zu ermöglichen. Es wird generell nicht empfohlen Dateien
 * manuell zu verändern um Fehler oder gar Programm abstürze zu vermeiden.
 * 
 * @author yannick Dreher
 *
 */
public class Writer {
	/**
	 * Die Methode print ist dazu ausgelegt ein Array in eine .txt zu schreiben.
	 * Kann die Datei neu anlegen.
	 * 
	 * @param datei - Der Dateiname
	 * @param name  - Der Zuordnungsname, dieser wird verknüpft mit dem text in die
	 *              Datei geschrieben um später ein gezieltes lesen und verarbeiten
	 *              zu ermöglichen
	 * @param text  - Ein CharArray, dass ohne Zeichentrennung in die Datei
	 *              geschreiben wird
	 */
	public static void print(String datei, String name, char[] text) {
		try {
			PrintWriter	pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
			pWriter.print(name + ":");
			for (int i = 0; i < text.length; i++) {
				pWriter.print(text[i]);
			}
			pWriter.println();
			pWriter.close();
		} catch (IOException ioe) {
			Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWp-1" + "\n" + ioe.toString());
		}
	}

	/**
	 * Ist dazu ausgelegt ein Array in eine .txt zu schreiben. Kann die Datei neu
	 * anlegen.
	 * 
	 * @param datei - Der Dateiname
	 * @param name  - Der Zuordnungsname, dieser wird verknüpft mit dem text in die
	 *              Datei geschrieben um später ein gezieltes lesen und verarbeiten
	 *              zu ermöglichen
	 * @param text  - Ein StringArray, dass ohne Zeichentrennung in die Datei
	 *              geschreiben wird. Es wird Empfohlen, dass das hinter jedem
	 *              Eintrag des Array schon ein Zeichentrenner eingefügt wurde
	 */
	public static void print(String datei, String name, String[] text) {
		try {
			PrintWriter pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
			pWriter.print(name + ":");
			for (int i = 0; i < text.length; i++) {
				pWriter.print(text[i]);
			}
			pWriter.println();
			pWriter.close();
		} catch (IOException ioe) {
			Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWp-2" + "\n" + ioe.toString());
		}
	}

	/**
	 * Liest eine .txt Datei Zeile für Zeile ein, und schreibt diese in ein 2
	 * Dimensionales Array, wobei die erste Dimension die einzelnen Zeilen sind, und
	 * die zweite der Zeileninhalt gesplittet bei jedem ':'. Kann die Datei neu
	 * anlegen.
	 * 
	 * @param datei - Der Dateiname, der einzulesen ist
	 * @return String[][]
	 */
	public static String[][] read(String datei) {
		FileReader fr;
		String line;
		String[] zeile;
		String[][] list;
		String[][] buffer = new String[50][];

		for (int rewrite = 0; rewrite <= 1; rewrite++) {
			try {
				fr = new FileReader(datei + ".txt");
				try {
					// List Zeile für Zeile die Datei und buffert diese
					BufferedReader br = new BufferedReader(fr);
					int i = 0;
					do {
						line = br.readLine();
						if (line != null) {
							zeile = line.split(":");
							if (zeile.length > 0 && zeile[0].toString().trim().equals("")) {
								zeile = (String[]) ArrayUtils.remove(zeile, 0);
							}
							if (zeile.length > 2 && zeile[zeile.length - 1].toString().trim().equals("")) {
								zeile = (String[]) ArrayUtils.remove(zeile, zeile.length - 1);
							}
							buffer[i] = zeile;
							i++;
						}
					} while (line != null && i < 50);
					list = new String[i][];
					for (int k = 0; k < i; k++) {
						list[k] = buffer[k];

					}
					br.close();
					return list;
				} catch (Exception e) {
					Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWr-1" + "\n" + e.toString());
					e.printStackTrace();
				}
				rewrite++;
			} catch (FileNotFoundException e) {
				PrintWriter pWriter = null;
				try {
					pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
					pWriter.close();
				} catch (IOException ioe) {
					if (rewrite != 0) {
						Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWr-3" + "\n" + ioe.toString());
						ioe.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Schreibt alle den Benutzer veränderbaren Variablen in die Draft.json Datei.
	 * Überprüft auf doppelte Namensnennung. Kann die Datei neu Anlegen.
	 * 
	 * @param name  - Der Zuordungsname, unter dem das gepeichtere wieder einzulesen
	 *              ist
	 * @param frame - den Frame, von dem aus der Aufruf kommt, wird dazu genutzt
	 *              Allertboxes zentriert anzuzeigen
	 * @return int - 0 bei einem Fehler, 1 bei Erfolg, weites in Planung
	 */
	public static int safeasjson(String name, JFrame frame) {
		try {
			JSONArray listname;
			JSONObject drafts = readjson(frame);
			listname = drafts.getJSONArray("name");
			drafts.remove("name");
			if (listname.toList().contains(name)) {
				Manage.msgbox("Der Name existiert schon", frame);
				return 0;
			}
			listname.put(name);
			drafts.put("name", listname);

			JSONObject aktuell = new JSONObject();
			aktuell.put("poketier", Data.getTierlist());
			aktuell.put("poketierclone", Data.getTierlistclone());
			aktuell.put("team", Gui.getwindow().getPanelPlayer().getteam());
			aktuell.put("settings", Gui.getwindow().getPanelSettings().getsettings());

			drafts.put(name, aktuell);

			try (FileWriter file = new FileWriter("Draft.json")) {
				System.out.println("Datei:" + drafts.toString(4));
				file.write(drafts.toString(4));
				return 1;
			} catch (IOException e) {
				Manage.msgbox("Beim schreiben der json-Datei ist ein Fehler aufgetreten    Code:PKD-safeasjson-1" + "\n"
						+ e.toString(), frame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Object[] options = { "Datei Überschreiben, Daten gehen verloren!",
					"Nichts tun, die Daten werden nicht verändert" };
			switch (JOptionPane.showOptionDialog(null, "Es wurden Fehler in der 'Draft.json' Datei enteckt",
					"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[1])) {
			case 0:
				PrintWriter pWriter = null;
				try {
					pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Draft.json")), true);
					pWriter.print("{" + "\n" + "\"" + "name" + "\"" + ": [" + "\n" + "]" + "}");
					safeasjson(name, frame);
				} catch (IOException g) {
					Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-safeasjson-2" + "\n"
							+ g.toString(), frame);
					return 0;
				}
				pWriter.close();
				break;
			case 1:
				return 0;
			default:
				break;
			}
		}
		return 0;

	}

	/**
	 * Soll eine neu eingelesende .json Datei auf Syntax und Eingabe Fehler prüfen
	 * 
	 * @param frame - den Frame, von dem aus der Aufruf kommt, wird dazu genutzt
	 *              Allertboxes zentriert anzuzeigen
	 */
	protected static void loadjson(JFrame frame) {
//		JSONObject file = readjson(frame);
	}

	/**
	 * list die Datei Draft.json im Ausführungsverzeichniss und prüft auf einfache
	 * Syntaxfehler. Kann die Datei neu anlegen.
	 * 
	 * @param frame - den Frame, von dem aus der Aufruf kommt, wird dazu genutzt
	 *              Allertboxes zentriert anzuzeigen
	 * @return JSONObject
	 */
	protected static JSONObject readjson(JFrame frame) {
		try {
			File file = new File("./Draft.json");
			return new JSONObject(FileUtils.readFileToString(file, "utf-8"));
		} catch (FileNotFoundException e) {
			PrintWriter pWriter = null;
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Draft.json")), true);
				pWriter.print("{" + "\n" + "\"" + "name" + "\"" + ": [" + "\n" + "]" + "}");
			} catch (IOException g) {
				Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-1" + "\n"
						+ g.toString(), frame);
				return null;
			}
			pWriter.close();
			try {
				return readjson(frame);
			} catch (Exception f) {
				Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-2" + "\n"
						+ f.toString(), frame);
				return null;
			}
		} catch (JSONException e) {
			try {
				Object[] options = { "Datei Überschreiben, Daten gehen verloren!",
						"Nichts tun, die Daten werden nicht verändert" };
				switch (JOptionPane.showOptionDialog(frame, "Es wurden Fehler in der 'Draft.json' Datei enteckt",
						"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1])) {
				case 0:
					PrintWriter pWriter = null;
					try {
						pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Draft.json")), true);
						pWriter.print("{" + "\n" + "\"" + "name" + "\"" + ": [" + "\n" + "]" + "}");
					} catch (IOException g) {
						Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-3"
								+ "\n" + g.toString(), frame);
						return null;
					}
					pWriter.close();
					try {
						return readjson(frame);
					} catch (Exception f) {
						Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-4"
								+ "\n" + f.toString(), frame);
						return null;
					}
				case 1:
					return null;
				default:
					return null;
				}
			} catch (Exception f) {
				Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-5" + "\n"
						+ f.toString(), frame);
				return null;
			}
		} catch (IOException e) {
			Manage.msgbox(
					"Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-6" + "\n" + e.toString(),
					frame);
			return null;
		}
	}
}